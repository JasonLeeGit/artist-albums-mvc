package com.ltd.coders.software.artist.albums.mvc.service;

import java.util.List;

import com.ltd.coders.software.artist.albums.mvc.model.Album;

public interface AlbumService {
	
	Album findById(Integer id);

	List<Album> findAll();

	List<Album> findAllByGenre(String genre);
	
	int saveOrUpdate(Album album);

	int delete(int id);
}
