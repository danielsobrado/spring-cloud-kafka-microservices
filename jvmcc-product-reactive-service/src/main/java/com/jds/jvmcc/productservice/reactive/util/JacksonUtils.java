package com.jds.jvmcc.productservice.reactive.util;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-13
 */
@Slf4j
public class JacksonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JacksonUtils() {
    }

    /**
     * @param inputObj
     * @return
     */
    public static String toJson(Object inputObj)  {

        try {
            return mapper.writeValueAsString(inputObj);
        } catch (JsonProcessingException e) {
            log.error("Error while converting from object to json: {}", e.getMessage());
            log.error("Stack trace: {}", e);
            log.error("Object: {}", inputObj.toString());
        }
        return Objects.toString(inputObj);
    }

    public static Object fromJson( String json, Class<?> clazz)  {

        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("Error while converting from json to object: {}", e.getMessage());
            log.error("Stack trace: {}", e);
            log.error("Json: {}", json);
        }
        return null;
    }

}