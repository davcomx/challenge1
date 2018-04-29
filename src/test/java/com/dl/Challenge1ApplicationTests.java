package com.dl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Challenge1ApplicationTests {

	private static final String RESPONSE_YES = "yes";
	private static final String RESPONSE_NO  = "no";
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void connected() throws Exception {
		String host = "http://localhost:%d/connected?origin=%s&destination=%s";

		String url = String.format(host, port, "Boston", "Newark");
		assertThat(getResponse(url)).contains(RESPONSE_YES);
	
		url = String.format(host, port, "Boston", "Philadelphia");
		assertThat(getResponse(url)).contains(RESPONSE_YES);
	
		url = String.format(host, port, "Philadelphia", "Albany");
		assertThat(getResponse(url)).contains(RESPONSE_NO);

		url = String.format(host, port, "Texas", "Washington");
		assertThat(getResponse(url)).contains(RESPONSE_NO);

		url = String.format(host, port, null, "Washington");
		assertThat(getResponse(url)).contains(RESPONSE_NO);
		
		url = String.format(host, port, "Texas", null);
		assertThat(getResponse(url)).contains(RESPONSE_NO);

		url = String.format(host, port, null, null);
		assertThat(getResponse(url)).contains(RESPONSE_NO);
		
		url = String.format(host, port, "", "Washington");
		assertThat(getResponse(url)).contains(RESPONSE_NO);

		url = String.format(host, port, "Texas", "");
		assertThat(getResponse(url)).contains(RESPONSE_NO);

		url = String.format(host, port, "", "");
		assertThat(getResponse(url)).contains(RESPONSE_NO);

		url = String.format(host, port, "123", "345");
		assertThat(getResponse(url)).contains(RESPONSE_NO);

		url = String.format(host, port, "$!@#", "^&*()");
		assertThat(getResponse(url)).contains(RESPONSE_NO);
	}
	
	private String getResponse(final String url) {
		return restTemplate.getForObject(url, String.class);
	}
	
}
