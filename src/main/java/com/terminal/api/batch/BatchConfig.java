package com.terminal.api.batch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {


    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public JdbcCursorItemReader<EntryDispatchEntity> itemReader(DataSource dataSource) {
    	System.out.println(" * itemReader:::");
        return new JdbcCursorItemReaderBuilder<EntryDispatchEntity>()
                .dataSource(dataSource)
                .name("jdbcCursorItemReader")
                .sql("  SELECT my.id, my.name, my.description FROM  my_table my"
                		+ "   WHERE NOT EXISTS ("
                		+ "   SELECT 1"
                		+ "   FROM batch_job_execution_params pa"
                		+ "   WHERE my.id = CAST(pa.parameter_value AS BIGINT) and pa.parameter_name ='id') ")
                .rowMapper(new BeanPropertyRowMapper<>(EntryDispatchEntity.class))
                .build();
    }

    @Bean
    public Step myStep() {
    	System.out.println(" * myStep:::");
        return new StepBuilder("myStep", jobRepository)
                .<EntryDispatchEntity, EntryDispatchEntity>chunk(10, transactionManager)
                .reader(itemReader(null)) // Asegúrate de que DataSource esté configurado correctamente
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job myJob(DispatchJobExecutionListener listener) {
    	System.out.println(" * myJob:::");
        return new JobBuilder("myJob", jobRepository)        		
                .start(myStep())
                .listener(listener)
                .build();
    }

    @Bean
    public ItemProcessor<EntryDispatchEntity, EntryDispatchEntity> itemProcessor() {
    	System.out.println("ItemProcessor:::");
        return new DispatchItemProcessor();
    }

    @Bean
    public ItemWriter<EntryDispatchEntity> itemWriter() {
    	System.out.println("ItemWriter:::");
        return new DispatchItemWriter();
    }
    
    
    
/*
	@Bean
    public Job job(JobRepository jobRepository, Step step1) {
		System.out.println("****funcion Job");
        return new JobBuilder("job", jobRepository)
                .start(step1)
                .incrementer(new RunIdIncrementer()) // Si necesitas un incrementador
                .build();
    }
	 
	@Bean
    public Step step1(JobRepository jobRepository, 
                      PlatformTransactionManager transactionManager,
                      ItemReader<String> reader, 
                      ItemProcessor<String, String> processor, 
                      ItemWriter<String> writer) {
		System.out.println("****funcion step1");

        return new StepBuilder("step1", jobRepository)
                .<String, String>chunk(2, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

	
	@Bean
    public JdbcCursorItemReader<MyEntity> reader(DataSource dataSource) {
		System.out.println("****funcion JdbcCursorItemReader");
        return new JdbcCursorItemReaderBuilder<MyEntity>()
                .dataSource(dataSource)
                .name("jdbcCursorItemReader")
                .sql("SELECT id, name, description FROM my_table") 
                .rowMapper(new BeanPropertyRowMapper<>(MyEntity.class)) // Mapea las filas a la clase MyEntity
                .build();
    }

	
	/*
	@Bean
	public ItemReader<String> reader() {
		System.out.println("****funcion reader");
		//List<String> data = Arrays.asList("data1"+cadenaAleatoria(5), "data2"+cadenaAleatoria(5), "data3"+cadenaAleatoria(5));
		List<String> data = new ArrayList<>();
		
		Random aleatorio = new Random(System.currentTimeMillis());
		
		int intAletorio = aleatorio.nextInt(10);
		System.out.println("** tamaño lista "+intAletorio);
		for (int i = 0; i < intAletorio; i++) {
			data.add("data-"+i+"-"+cadenaAleatoria(5));
			
		}
		
		return new ListItemReader<>(data);
	}
	*/
/*
	@Bean
	public ItemProcessor<String, String> processor() {
		System.out.println("****funcion processor");
		return item -> {
			System.out.println("procesando "+item + "*");
			return item.toUpperCase(); 
		};
	}

	@Bean
	public ItemWriter<String> writer(RestTemplate restTemplate) {
		System.out.println("****funcion writer");
		return items -> {
			for (String item : items) {
				String url = "http://example.com/api/endpoint"; // URL del servicio web
				restTemplate.postForObject(url, item, String.class);
				System.out.println("escribiendo "+item + "*");
			}
			
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static String cadenaAleatoria(int longitud) {
	    // El banco de caracteres
	    String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    // La cadena en donde iremos agregando un carácter aleatorio
	    String cadena = "";
	    for (int x = 0; x < longitud; x++) {
	        int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
	        char caracterAleatorio = banco.charAt(indiceAleatorio);
	        cadena += caracterAleatorio;
	    }
	    return cadena;
	}
	public static int numeroAleatorioEnRango(int minimo, int maximo) {
	    // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
	    return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
	}
*/
}
