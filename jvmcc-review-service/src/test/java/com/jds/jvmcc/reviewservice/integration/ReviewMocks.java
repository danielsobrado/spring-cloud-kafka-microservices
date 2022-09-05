package com.jds.jvmcc.reviewservice.integration;

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
 * @since 2022-09-05
 */
public class ReviewMocks {

    public static void setupMockReviewsResponse(WireMockServer mockService) throws IOException {

        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/review/B41234"))
          .willReturn(
            WireMock.aResponse()
              .withStatus(HttpStatus.OK.value())
              .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
              .withBody(
                copyToString(
                  ReviewMocks.class.getClassLoader().getResourceAsStream("payload/get-reviews-response.json"),
                  defaultCharset()))));
    }
}
