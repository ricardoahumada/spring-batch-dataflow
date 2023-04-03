/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.batch;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.javatunes.domain.MusicItem;

// A simple writer for Person items
// TODO: Implement the correct type to be an item reader for MusicItem instances
public class MusicItemWriter implements ItemWriter<MusicItem> {

	@Override
	public void write(List<? extends MusicItem> items) throws Exception {
		// TODO Auto-generated method stub
		for (MusicItem musicItem : items) {
			//System.out.println("MusicItemWriter:"+musicItem);
			System.out.printf("MusicItemWriter.erite() -  Title: %s, Price: %s\n", musicItem.getTitle(), musicItem.getPrice());
		}
		
	}

	// TODO: Create a write method

}
