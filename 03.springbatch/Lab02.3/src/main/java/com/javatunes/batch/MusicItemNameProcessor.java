/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.batch;

import org.springframework.batch.item.ItemProcessor;

import com.javatunes.domain.MusicItem;

// A simple Person processor that returns a Person
// TODO: Implement the correct type to be an item processor for MusicItem instances
// TODO: The processor input and output types should both be MusicItem
public class MusicItemNameProcessor implements ItemProcessor<MusicItem, MusicItem>{

	@Override
	public MusicItem process(MusicItem item) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MusicItemNameProcessor...");
		item.setTitle(item.getTitle().toUpperCase());
		return item;
	}

	// TODO: Create a process method appropriate for this class

}
