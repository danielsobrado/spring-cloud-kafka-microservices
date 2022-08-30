// package com.jds.jvmcc.productservice.integration;

// import com.github.tomakehurst.wiremock.WireMockServer;
// import com.netflix.loadbalancer.Server;
// import com.netflix.loadbalancer.ServerList;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.TestConfiguration;
// import org.springframework.cloud.netflix.ribbon.StaticServerList;
// import org.springframework.context.annotation.Bean;
// import org.springframework.test.context.ActiveProfiles;

// import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

// /**
//  * @author J. Daniel Sobrado
//  * @version 1.0
//  * @since 2022-08-14
//  */
// @TestConfiguration
// @ActiveProfiles("ribbon-test")
// public class RibbonTestConfig {

//     @Autowired
//     private WireMockServer mockProductService;

//     @Autowired
//     private WireMockServer secondMockProductService;

//     @Bean(initMethod = "start", destroyMethod = "stop")
//     public WireMockServer mockProductService() {
//         return new WireMockServer(options().dynamicPort());
//     }

//     @Bean(name="secondMockProductService", initMethod = "start", destroyMethod = "stop")
//     public WireMockServer secondProductMockService() {
//         return new WireMockServer(options().dynamicPort());
//     }

//     @Bean
//     public ServerList ribbonServerList() {
//         return new StaticServerList<>(
//           new Server("localhost", mockProductService.port()),
//           new Server("localhost", secondMockProductService.port()));
//     }
    
// }
