package com.jds.jvmcc.productservice.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;
/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-14
 */
public class ProductMocks {
    public static void setupMockProductsResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/product"))
          .willReturn(WireMock.aResponse()
            .withStatus(HttpStatus.OK.value())
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody(
              copyToString(
                ProductMocks.class.getClassLoader().getResourceAsStream("payload/get-product-response.json"),
                defaultCharset()))));
    }
}
