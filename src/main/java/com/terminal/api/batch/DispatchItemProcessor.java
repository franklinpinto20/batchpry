package com.terminal.api.batch;



import org.springframework.batch.item.ItemProcessor;

public class DispatchItemProcessor implements ItemProcessor<EntryDispatchEntity, EntryDispatchEntity> {
    @Override
    public EntryDispatchEntity process(EntryDispatchEntity item) throws Exception {
        // Implementar lógica de procesamiento aquí
    	System.out.println("Procesando::: "+item.getName());
        return item;
    }
}