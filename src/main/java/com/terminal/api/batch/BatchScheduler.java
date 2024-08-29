package com.terminal.api.batch;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
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

    @Scheduled(cron = "0 */1 * * * ?")
    public void runJob() throws Exception {
    	System.out.println("comienza la ejecución del cron");
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("prueba", "valoruno", true)
                .toJobParameters();
        jobLauncher.run(job, params);
    }
}
