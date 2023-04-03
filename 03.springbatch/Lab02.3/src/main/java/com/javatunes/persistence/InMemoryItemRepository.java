/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.javatunes.domain.MusicItem;

public class InMemoryItemRepository implements ItemRepository {

	private List<MusicItem> catalogData = new CatalogData();

	private Integer maxSearchResults = 50;

	// Accessors
	public Integer getMaxSearchResults() {
		return maxSearchResults;
	}
	public void setMaxSearchResults(Integer maxSearchResults) {
		this.maxSearchResults = maxSearchResults;
	}
	

	public MusicItem get(Long id) {
		return catalogData.get(catalogData.indexOf(new MusicItem(id)));
	}
	
	public MusicItem getByIndex(int index) {
		return catalogData.get(index);
	}

	public Collection<MusicItem> searchByArtistTitle(String keyword) {

		Collection<MusicItem> result = new ArrayList<MusicItem>();

		for (MusicItem item : catalogData) {
			if (matches(keyword.toLowerCase(), item)
					&& result.size() < maxSearchResults) {
				result.add(item);
			}
		}
		return result;
	}

	private boolean matches(String keyword, MusicItem item) {
		return item.getTitle().toLowerCase().indexOf(keyword) != -1
				|| item.getArtist().toLowerCase().indexOf(keyword) != -1;
	}

	public Collection<MusicItem> getAll() {
		return Collections.unmodifiableCollection(catalogData);
	}

	@Override
	public int size() {
		return catalogData.size();
	}

}
