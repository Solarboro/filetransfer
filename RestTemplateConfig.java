package org.solar.config;

import org.solar.util.SslClientHttpRequestFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}
 
	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SslClientHttpRequestFactory  factory = new SslClientHttpRequestFactory();
		factory.setReadTimeout(5000);
		factory.setConnectTimeout(15000);
		return factory;
	}
}
