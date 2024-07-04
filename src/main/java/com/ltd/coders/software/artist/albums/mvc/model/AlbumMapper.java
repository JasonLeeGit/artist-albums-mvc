package com.ltd.coders.software.artist.albums.mvc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AlbumMapper implements RowMapper<Album> {
	public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
		Album album = new Album();
		album.setId(rs.getInt("id"));
		album.setArtistName(rs.getString("artistName"));
		album.setAlbumName(rs.getString("albumName"));
		album.setGenre(rs.getString("genre"));
		album.setFormat(convertDelimitedStringToList(rs.getString("format")));
		return album;
	}

	private List<String> convertDelimitedStringToList(String delimitedString) {
		List<String> result = new ArrayList<>();
		if (!StringUtils.isEmpty(delimitedString)) {
			result = Arrays.asList(StringUtils.delimitedListToStringArray(delimitedString, ","));
		}
		return result;
	}
}