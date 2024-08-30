package com.terminal.api.batch;

import org.springframework.batch.item.Chunk;

import java.util.ArrayList;
import java.util.List;


public class ChunkConverter {

	 public static <T> List<T> convertChunkToList(Chunk<? extends T> chunk) {
	        List<T> list = new ArrayList<>();
	        chunk.forEach(list::add); // Add each element in the chunk to the list
	        return list;
	    }
}