package com.jds.jvmcc.productservice.exception;

import feign.Feign;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 */
@Component
@RequiredArgsConstructor
public class HandlingFeignErrorDecoder implements ErrorDecoder {

    private final ApplicationContext applicationContext;
    private final ErrorDecoder.Default defaultErrorDecoder = new Default();
    private final Map<String, FeignHttpExceptionHandler> exceptionHandlerMap = new HashMap<>();

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> feignClients = applicationContext.getBeansWithAnnotation(FeignClient.class);
        List<Method> clientMethods = feignClients.values().stream()
                .map(Object::getClass)
                .map(aClass -> aClass.getInterfaces()[0])
                .map(ReflectionUtils::getDeclaredMethods)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        for (Method m : clientMethods) {
            String configKey = Feign.configKey(m.getDeclaringClass(), m);
            HandleFeignException annotation = getHandleFeignExceptionAnnotation(m);
            if (annotation != null) {
                FeignHttpExceptionHandler handlerBean = applicationContext.getBean(annotation.value());
                exceptionHandlerMap.put(configKey, handlerBean);
            }
        }
    }

    private HandleFeignException getHandleFeignExceptionAnnotation(Method m) {
        HandleFeignException result = m.getAnnotation(HandleFeignException.class);
        if (result == null) {
            result = m.getDeclaringClass().getAnnotation(HandleFeignException.class);
        }
        return result;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignHttpExceptionHandler handler = exceptionHandlerMap.get(methodKey);
        if (handler != null) {
            Exception exception = handler.handle(response);
            if (exception == null) {
                exception = defaultErrorDecoder.decode(methodKey, response);
            }
            return exception;
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

}
