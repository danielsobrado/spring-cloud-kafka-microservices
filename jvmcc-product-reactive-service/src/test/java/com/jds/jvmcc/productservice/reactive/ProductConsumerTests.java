package com.jds.jvmcc.productservice.reactive;

// import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
// import static com.github.tomakehurst.wiremock.client.WireMock.post;
// import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
// import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

// import org.junit.Before;
// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.web.reactive.function.client.WebClient;

// import com.jds.jvmcc.productservice.reactive.service.ProductConsumerService;
// import com.jds.jvmcc.productservice.reactive.util.JacksonUtils;

// import reactor.core.publisher.Mono;
// import reactor.test.StepVerifier;

// @RunWith(SpringRunner.class)
// @ActiveProfiles("test")
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureWireMock(port = 9999)

// /**
//  * @author J. Daniel Sobrado
//  * @version 1.1
//  * @since 2022-08-13
//  */
// public class ProductConsumerTests {
//     @Autowired
// 	private WebClient webClient;

// 	@Autowired
// 	private ProductConsumerService productConsumerService;

// 	@Before
// 	public void setup(){

// 		String customerJson = productJson();
		
// 		stubFor(post(urlEqualTo(RestEndpoints.Product_ENDPOINT))
// 				.willReturn(aResponse()
// 					.withBody(customerJson)
// 					.withHeader("Content-Type", "application/json")));
// 	}

// 	@Test
// 	public void testSaveProduct() {

// 		String customerJson = customerJson();
// 		Customer customer = (Customer)JacksonUtils.fromJson(customerJson,Customer.class);
// 		Mono<Customer> customerMono = this.customerConsumerService.saveCustomer(customer);

// 		StepVerifier.create(customerMono)
// 				 .expectNextCount(1)
// 				 .verifyComplete();
// 	}

// 	public String productJson(){
// 		return  "{\n" +
// 				"    \"customerId\": 1,\n" +
// 				"    \"customerName\": \"BARATH\",\n" +
// 				"    \"customerGender\": \"MALE\"\n" +
// 				"  }";
// 	}
// }
