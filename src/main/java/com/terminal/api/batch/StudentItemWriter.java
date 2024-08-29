package com.terminal.api.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@Component
public class StudentItemWriter implements ItemWriter<Student> {

    private final RestTemplate restTemplate;
    private final String serviceUrl;

    public StudentItemWriter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.serviceUrl = "http://example.com/api/students"; // URL del servicio web
    }

    public void write(List<? extends Student> students) throws Exception {
        for (Student student : students) {
            // Invocación del servicio web para cada estudiante
            restTemplate.postForObject(serviceUrl, student, String.class);
        }
    }

	@Override
	public void write(Chunk<? extends Student> chunk) throws Exception {
		// TODO Auto-generated method stub
		
	}
}