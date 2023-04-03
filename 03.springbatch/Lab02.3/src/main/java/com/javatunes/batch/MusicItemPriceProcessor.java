/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.batch;

import java.math.BigDecimal;

import org.springframework.batch.item.ItemProcessor;

import com.javatunes.domain.MusicItem;

// A simple Person processor that returns a Person
// TODO: Implement the correct type to be an item processor for MusicItem instances
// TODO: The processor input and output types should both be MusicItem
public class MusicItemPriceProcessor implements ItemProcessor<MusicItem, MusicItem>{

	public final static BigDecimal INC= new BigDecimal(10);
	
	@Override
	public MusicItem process(MusicItem item) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MusicItemPriceProcessor...");
		item.setPrice(item.getPrice().add(INC));
		return item;
	}

	// TODO: Create a process method appropriate for this class

}
