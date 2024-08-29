package com.terminal.api.batch;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class MyItemWriter implements ItemWriter<MyEntity> {
    
	
	public void write(List<? extends MyEntity> items) throws Exception {
        // Implementar lógica de escritura aquí
		System.out.println("write list");
    }

	@Override
	public void write(Chunk<? extends MyEntity> chunk) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("write Chunk");
		System.out.println("Procesando:::"+chunk);
		
	}
}