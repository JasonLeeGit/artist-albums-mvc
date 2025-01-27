package com.ltd.coders.software.artist.albums.mvc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltd.coders.software.artist.albums.mvc.model.Album;
import com.ltd.coders.software.artist.albums.mvc.service.AlbumService;

public class EditAlbumControllerTest {

	@Mock
	private AlbumService mockAlbumService;
	@Mock
	private Model mockModel;
	@Mock
	BindingResult mockBindingResult;
	@Mock
	RedirectAttributes mockRedirectAttributes;

	private EditAlbumController classUnderTest;
	private List<Album> allAlbumsList;
	private Album album;
	private List<String> formatList;
	private String response;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		classUnderTest = new EditAlbumController(mockAlbumService);

		album = new Album();
		formatList = Arrays.asList("MP3", "CD");
		allAlbumsList = Arrays.asList(new Album(1, "artistName1", "albumName1", "Indie", formatList),
				new Album(2, "artistName2", "albumName2", "Disco", formatList));
	}

	@Test
	public void testSaveOrUpdateAlbumForNewAlbum() {
		when(mockAlbumService.saveOrUpdate(album)).thenReturn(3);

		response = classUnderTest.saveOrUpdateAlbum(album, mockBindingResult, mockModel, mockRedirectAttributes);

		verify(mockRedirectAttributes, times(1)).addFlashAttribute("alert", "success");
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("msg", "Album added successfully!");
		verify(mockAlbumService, times(1)).saveOrUpdate(album);
		assertEquals("redirect:/albums/" + album.getId(), response);
	}

	@Test
	public void testSaveOrUpdateAlbumForExistingAlbum() {
		when(mockBindingResult.hasErrors()).thenReturn(true);

		response = classUnderTest.saveOrUpdateAlbum(album, mockBindingResult, mockModel, mockRedirectAttributes);

		verify(mockAlbumService, times(0)).saveOrUpdate(album);
		assertEquals("albumForm", response);
	}

	@Test
	public void testSaveOrUpdateAlbumWithBindingError() {
		when(mockAlbumService.saveOrUpdate(allAlbumsList.get(0))).thenReturn(allAlbumsList.get(0).getId());

		response = classUnderTest.saveOrUpdateAlbum(allAlbumsList.get(0), mockBindingResult, mockModel,
				mockRedirectAttributes);

		verify(mockRedirectAttributes, times(1)).addFlashAttribute("alert", "success");
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("msg", "Album updated successfully!");
		verify(mockAlbumService, times(1)).saveOrUpdate(allAlbumsList.get(0));
		assertEquals("redirect:/albums/" + allAlbumsList.get(0).getId(), response);
	}
}
