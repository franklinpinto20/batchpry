package com.terminal.api.batch;



import org.springframework.batch.item.ItemProcessor;

public class MyItemProcessor implements ItemProcessor<MyEntity, MyEntity> {
    @Override
    public MyEntity process(MyEntity item) throws Exception {
        // Implementar lógica de procesamiento aquí
    	System.out.println("Procesando::: "+item.getName());
        return item;
    }
}