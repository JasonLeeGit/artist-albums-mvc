package com.ltd.coders.software.artist.albums.mvc.dao;

import java.util.List;

import com.ltd.coders.software.artist.albums.mvc.model.Album;

public interface AlbumDao {

	Album findById(Integer id);

	List<Album> findAll();

	List<Album> findAllByGenre(String genre);
	
	int save(Album album);

	int update(Album album);

	int delete(Integer id);
}
