package com.terminal.api.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

@Component
public class DispatchJobExecutionListener implements JobExecutionListener {

	private static final Map<String, Object> parameterStore = new HashMap<>();

	
    @Override
    public void beforeJob(JobExecution jobExecution) {
        // Here you can access job parameters if needed
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
    	System.out.println("** MyJobExecutionListener-afterJob");
        for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
            String entityName = (String) stepExecution.getExecutionContext().get("entityName");
            String entityId = (String) stepExecution.getExecutionContext().get("entityId");

            parameterStore.put("entityName", entityName);
            parameterStore.put("entityId", entityId);
            
         
        }
    }
    
    // Method to fetch parameters later
    public Map<String, Object> getStoredParameters() {
        return new HashMap<>(parameterStore);
    }
}