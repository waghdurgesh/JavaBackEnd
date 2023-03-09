package com.app.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.transaction.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.entity.SendCode;

@Service
@Transactional
public class ConsoleService implements IConsoleService {
	@Override
	public void getAlllanguages() {
		String url = "http://localhost:3000/list";
		HttpRequest build = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		// HttpRequest.newBuilder().POST
		HttpClient build2 = HttpClient.newBuilder().build();

		try {
			@SuppressWarnings("unused")
			HttpResponse<String> send = build2.send(build, HttpResponse.BodyHandlers.ofString());
//		System.out.println("the response code = "+send.statusCode());
//			System.out.println("the data = " + send.body());
		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
		}

	}

	@Override
	public String sendCompileCode(String language, String input, String code) {
		SendCode newCode = new SendCode();
		newCode.setCode(code);
		newCode.setLanguage(language);
		newCode.setInput(input);

		RestTemplate restTemplate = new RestTemplate();
		// create an HTTP headers object with content type set to JSON
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// create an HTTP entity with the SendCode object and headers
		HttpEntity<SendCode> request = new HttpEntity<>(newCode, headers);

		// make the HTTP POST request to the API
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:3000/", request, String.class);
//		System.out.println("the response code = " + response.getStatusCodeValue());
//		System.out.println("the data = " + response.getBody());

		// check the HTTP response status code and return the response body
		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			throw new RuntimeException("Error executing request: " + response.getStatusCode());
		}
	}
}
