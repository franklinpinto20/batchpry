package com.terminal.api.batch;



import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class BatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;
    
    @Autowired
    DispatchJobExecutionListener listener;

    @Scheduled(cron = "0 */1 * * * ?")
    public void runJob() throws Exception {
    	System.out.println("comienza la ejecución del cron");
    	
    	Map<String, Object> parameters=listener.getStoredParameters();
    	String id="0";
    	for (Map.Entry<String, Object> entry : parameters.entrySet()) {
    		String key = entry.getKey();
    		Object val = entry.getValue();
    		System.out.println("key: "+key);
    		System.out.println("val: "+val);
    		id=(String) val;
			
		}
          
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("prueba", "valoruno", true)
                .addString("id", id, true)
                .toJobParameters();
        jobLauncher.run(job, params);
    }
}
