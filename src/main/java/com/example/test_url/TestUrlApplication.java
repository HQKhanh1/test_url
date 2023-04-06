package com.example.test_url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class TestUrlApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestUrlApplication.class, args);
	}
	@GetMapping("/get-url/{id}")
	public String[] getUrl(@PathVariable("id") String id) {
		String cookie = "SID=VAi7lJNscP-vPiMmSHnr3GDy6gtmdQJsWHs4SzpH14NjLDEzBJBARSayzrE6rjGWCEeh2Q.; __Secure-1PSID=VAi7lJNscP-vPiMmSHnr3GDy6gtmdQJsWHs4SzpH14NjLDEzV2vUywTEvXmq3IOma8evzg.; __Secure-3PSID=VAi7lJNscP-vPiMmSHnr3GDy6gtmdQJsWHs4SzpH14NjLDEzQ4j4iMY0srhYpsCOTcDmyg.; HSID=AXulVHsenXvN8nuOf; SSID=AzNch2gGqazvzqfA4; APISID=0VIDP08JytF3hKX6/AzVxz5iCKY8Yydhnv; SAPISID=bTn2-Dx8RMj8ulNK/AFAIkzJKwxK-a5QJt; __Secure-1PAPISID=bTn2-Dx8RMj8ulNK/AFAIkzJKwxK-a5QJt; __Secure-3PAPISID=bTn2-Dx8RMj8ulNK/AFAIkzJKwxK-a5QJt; NID=511=V6zsmhGOqo0zzqmQ7xgymsypJfYUht1WEipxgWCQf9vkZ3Gxpk3z2YSLaUaeGaIluSCrU_Fo8gUYk-gXrv3jl2GHY_B2C7Im0dIgQ4cb-HpfZ1x1bB8sMXsh0V5nOG5w9wfRE6PW2Q6R-6ij6iEn-rMCre-PspeOr3Lg_W3iF1Prut3OnGh3oyDKeg03; OSID=VAi7lF9FI5Np4d1Rdyf2FGPOnT5Y54SXWR7GhmsQZHCwGad6-PAE8DsBl6JctZo-Gd02Kw.; __Secure-OSID=VAi7lF9FI5Np4d1Rdyf2FGPOnT5Y54SXWR7GhmsQZHCwGad66QZt_X7c4HI2BWhHDG804w.; DRIVE_STREAM=CErp_v43H2o; SIDCC=AFvIBn-KKLW-nbvGZbq8I9QWCUCHVA-7BZoMvKwOYyccqWLVEtiHQnMRe-QAg6LptqBbozh2; __Secure-1PSIDCC=AFvIBn8p0ryDmwWWz8B4IT8PeNsg_Zg3OwBPDTrIrS-7bqfBTnVJAabrZkCSTmCjPlyukY0Yzg; __Secure-3PSIDCC=AFvIBn-5a1mkE61Z6IHIlSCCp0FTfFS7phdV9aXT3m8ojytOo4pazGFBUb7It1tMfDRHVrh5Wg";
		String url = "https://drive.google.com/e/get_video_info?docid=" + id + "&drive_originator_app=303";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cookie", cookie);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				entity,
				String.class
		);
		System.out.println(response);
		String[] listStr = response.getBody().toString().split("&");
		String fmt_stream_map = null;
		for (int i = 0; i < listStr.length; i++) {
			String[] temp = listStr[i].split("=");
			if(temp[0].equals("fmt_stream_map")) {
				fmt_stream_map = temp[1];
			}
		}
		try {
			String[] decodingUrl = URLDecoder.decode(fmt_stream_map, "UTF-8").split(",");
			System.out.println("\n\n\n\n\n\n\n" + decodingUrl.toString() + "\n\n\n\n\n\n\n");
			return decodingUrl;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
