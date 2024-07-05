package com.ltd.coders.software.artist.albums.mvc.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.ltd.coders.software.artist.albums.mvc.model.Album;
import com.ltd.coders.software.artist.albums.mvc.model.AlbumMapper;

public class AlbumDaoImplTest {
	
	@Mock
	private NamedParameterJdbcTemplate mockNamedParameterJdbcTemplate;
	@Mock
	private SqlParameterSource mockSqlParameterSource;
	private AlbumDaoImpl classUnderTest;
	private Album result;
	private List<Album> allAlbumsListToReturn;
	private List<Album> resultsList;
	private AlbumMapper albumMapper;
	private Map<String, Object> params = new HashMap<>();
	private List<String> formatList = Arrays.asList("MP3", "CD");
	private Album albumToReturn = new Album(1, "artistName1", "albumName1", "Indie", formatList);

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		classUnderTest = new AlbumDaoImpl(mockNamedParameterJdbcTemplate, albumMapper);
		params.put("id", 1);
		allAlbumsListToReturn = Arrays.asList(new Album(1, "artistName1", "albumName1", "Indie", formatList),
				new Album(2, "artistName2", "albumName2", "Disco", formatList));
	}

	@Test
	public void findByValidId() {
		when(mockNamedParameterJdbcTemplate.queryForObject("SELECT * FROM ALBUMS WHERE id=:id", params, albumMapper))
				.thenReturn(albumToReturn);

		result = classUnderTest.findById(1);

		verify(mockNamedParameterJdbcTemplate, times(1)).queryForObject("SELECT * FROM ALBUMS WHERE id=:id", params,
				albumMapper);
		assertEquals("artistName1", result.getArtistName());
	}
	
	@Test
	public void findByInValidId() {
		when(mockNamedParameterJdbcTemplate.queryForObject("SELECT * FROM ALBUMS WHERE id=:id", params, albumMapper))
				.thenReturn(albumToReturn);

		result = classUnderTest.findById(0);
		params.put("id", 0);
		verify(mockNamedParameterJdbcTemplate, times(1)).queryForObject("SELECT * FROM ALBUMS WHERE id=:id", params,
				albumMapper);
		assertEquals(null, result);
	}

	@Test
	public void findAll() {
		when(mockNamedParameterJdbcTemplate.query("SELECT * FROM ALBUMS", albumMapper))
				.thenReturn(allAlbumsListToReturn);

		resultsList = classUnderTest.findAll();

		assertEquals("artistName1", resultsList.get(0).getArtistName());
	}

	@Test
	public void findByValidGenre() {
		when(mockNamedParameterJdbcTemplate.query("SELECT * FROM ALBUMS WHERE genre = " + "'" + "Indie" + "'",
				albumMapper)).thenReturn(allAlbumsListToReturn);

		resultsList = classUnderTest.findAllByGenre("Indie");

		assertEquals("Indie", resultsList.get(0).getGenre());
	}
	
	@Test
	public void findByInValidGenre() {
		when(mockNamedParameterJdbcTemplate.query("SELECT * FROM ALBUMS WHERE genre = " + "'" + "Indie" + "'",
				albumMapper)).thenReturn(allAlbumsListToReturn);

		resultsList = classUnderTest.findAllByGenre("Jazz");

		assertEquals(0, resultsList.size());
	}
}
