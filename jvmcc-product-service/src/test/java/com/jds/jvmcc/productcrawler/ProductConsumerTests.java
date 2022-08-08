import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import com.barath.app.configuration.RestEndpoints;
import com.barath.app.model.Customer;
import com.jds.jvmcc.productservice.service.ProductConsumerService;
import com.jds.jvmcc.productservice.util.JacksonUtils;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9999)

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-08
 */
public class ProductConsumerTests {
    @Autowired
	private WebClient webClient;

	@Autowired
	private ProductConsumerService productConsumerService;


}
