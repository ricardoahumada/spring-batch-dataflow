/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.javatunes.domain.MusicItem;
import com.javatunes.service.Catalog;

// A simple reader for MusicItem items
// TODO: Implement the correct type to be an item reader for MusicItem instances
public class MusicItemReader implements ItemReader<MusicItem>,StepExecutionListener {
	
	private int index = 0;
	
	@Autowired
	Catalog cat;

	// TODO: Uncomment the read() method once you've implemented the correct interface
	
	public MusicItem read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		System.out.println("MusicItemReader.read()");
		// Go through the MusicItems
		if (index < cat.size()) {

			// TODO: Get the item from the catalog
			MusicItem cur = cat.get(index);

			System.out.printf("MusicItemReader.read() -  Title: %s, Price: %s\n", cur.getTitle(), cur.getPrice());

			// TODO: Increment the index
			index++;

			return cur;
		} else {
			index=0;
			return null;
		}
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("Item reader inicializado....");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("Item reader finalizado....");
		return ExitStatus.COMPLETED;
	}

}
