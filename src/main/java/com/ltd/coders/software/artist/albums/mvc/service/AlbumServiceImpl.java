package com.ltd.coders.software.artist.albums.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltd.coders.software.artist.albums.mvc.dao.AlbumDao;
import com.ltd.coders.software.artist.albums.mvc.model.Album;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumDao albumDao;

	@Override
	public Album findById(Integer id) {
		return albumDao.findById(id);
	}

	@Override
	public List<Album> findAll() {
		return albumDao.findAll();
	}
	
	@Override
	public List<Album> findAllByGenre(String genre) {
		return albumDao.findAllByGenre(genre);
	}

	@Override
	@Transactional
	public int saveOrUpdate(Album album) {

		if (findById(album.getId()) == null) {
			albumDao.save(album);
		} else {
			albumDao.update(album);
		}
		return album.getId();
	}

	@Override
	@Transactional
	public int delete(int id) {
		return albumDao.delete(id);
	}

}
