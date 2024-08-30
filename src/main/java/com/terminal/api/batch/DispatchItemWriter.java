package com.terminal.api.batch;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.web.client.RestTemplate;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;


public class DispatchItemWriter implements ItemWriter<EntryDispatchEntity>, StepExecutionListener {
	 
	private StepExecution stepExecution;
	private RestTemplate restTemplate;
	private String serviceUrl="http://example.com/api/students";

	
    public DispatchItemWriter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.serviceUrl = "http://example.com/api/students"; // URL del servicio web
    }
	
	public DispatchItemWriter() {
		// TODO Auto-generated constructor stub
	}

	public void write(List<? extends EntryDispatchEntity> items) throws Exception {
        // Implementar lógica de escritura aquí
		System.out.println("write list");
    }

	@Override
	public void write(Chunk<? extends EntryDispatchEntity> chunk) throws Exception {
		// TODO Auto-generated method stub
		 System.out.println("write Chunk");
		 List<EntryDispatchEntity> entityList = ChunkConverter.convertChunkToList(chunk);
		 for (EntryDispatchEntity entryDispatchEntity : entityList) {
			 System.out.println("Procesando:: id: "+entryDispatchEntity.getId() + "name: "+entryDispatchEntity.getName());	
		 }
		 
		 System.out.println("write Chunk - subiendo parametros al contexto de ejecución");
		    for (EntryDispatchEntity entity : chunk) {
	            String entityId = entity.getId().toString();
	            String entityName = entity.getName();
	            System.out.println("entityId: "+entityId);
	            System.out.println("entityName: "+entityName);

	            // Store these values in the StepExecution context
	            stepExecution.getExecutionContext().put("entityId", entityId);
	            stepExecution.getExecutionContext().put("entityName", entityName);
	            //enviando al servicio web
	            restTemplate.postForObject(serviceUrl, entity, String.class);
	        }
	
	}
	
	@Override
    public void beforeStep(StepExecution stepExecution) {
		System.out.println("**beforeStep");
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
    	System.out.println("**afterStep");
        return ExitStatus.COMPLETED;
    }
}

