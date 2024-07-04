package com.ltd.coders.software.artist.albums.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ltd.coders.software.artist.albums.mvc.model.Album;
import com.ltd.coders.software.artist.albums.mvc.model.AlbumMapper;

@Repository
public class AlbumDaoImpl implements AlbumDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private AlbumMapper albumMapper;

	public AlbumDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, AlbumMapper albumMapper) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.albumMapper = albumMapper;
	}

	@Override
	public Album findById(Integer id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);

		String sql = "SELECT * FROM ALBUMS WHERE id=:id";
		Album result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, albumMapper);
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}
		return result;
	}

	@Override
	public List<Album> findAll() {
		String sql = "SELECT * FROM ALBUMS"; // ORDER BY artistname
		List<Album> result = namedParameterJdbcTemplate.query(sql, albumMapper);
		return result;
	}

	@Override
	public List<Album> findAllByGenre(String genre) {
		String sql = "SELECT * FROM ALBUMS WHERE genre = " + "'" + genre + "'";
		List<Album> result = namedParameterJdbcTemplate.query(sql, albumMapper);
		return result;
	}

	@Override
	public int save(Album album) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "INSERT INTO ALBUMS(artistname, albumname, genre, format) "
				+ "VALUES ( :artistName, :albumName, :genre, :format)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(album), keyHolder);
		album.setId(keyHolder.getKey().intValue());
		return album.getId();
	}

	@Override
	public int update(Album album) {
		
		String sql = "UPDATE ALBUMS SET "
				+ "artistname=:artistName, albumname=:albumName, genre=:genre, format=:format WHERE id=:id";
		return namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(album));
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM ALBUMS WHERE id= :id";
		return namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
	}

	private SqlParameterSource getSqlParameterByModel(Album album) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", album.getId());
		paramSource.addValue("artistName", album.getArtistName());
		paramSource.addValue("albumName", album.getAlbumName());
		paramSource.addValue("genre", album.getGenre());
		paramSource.addValue("format", convertListToDelimitedString(album.getFormat()));
		return paramSource;
	}

	private String convertListToDelimitedString(List<String> list) {
		String result = "";
		if (list != null) {
			result = StringUtils.arrayToCommaDelimitedString(list.toArray());
		}
		return result;
	}
}
