/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.persistence;

import java.util.Collection;

import com.javatunes.domain.MusicItem;


public interface ItemRepository {

  public MusicItem get(Long id);
  
  public MusicItem getByIndex(int index);

  public Collection<MusicItem> getAll();

  public Collection<MusicItem> searchByArtistTitle(String keyword);
  
  public int size();
}
