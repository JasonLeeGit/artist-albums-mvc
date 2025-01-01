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
import com.ltd.coders.software.artist.albums.mvc.util.DataUtils;

public class ShowAlbumControllerTest {

	@Mock
	private AlbumService mockAlbumService;
	@Mock
	private Model mockModel;
	@Mock
	BindingResult mockBindingResult;
	@Mock
	RedirectAttributes mockRedirectAttributes;

	private ShowAlbumController classUnderTest;
	private List<Album> allAlbumsList;
	private Album album;
	private List<String> formatList;
	private String response;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		classUnderTest = new ShowAlbumController(mockAlbumService);

		album = new Album();
		formatList = Arrays.asList("MP3", "CD");
		allAlbumsList = Arrays.asList(new Album(1, "artistName1", "albumName1", "Indie", formatList),
				new Album(2, "artistName2", "albumName2", "Disco", formatList));
	}

	@Test
	public void testShowAddForm() {
		response = classUnderTest.showAddForm(mockModel, album);

		verify(mockModel, times(1)).addAttribute("formatList", DataUtils.FORMAT_LIST);
		verify(mockModel, times(1)).addAttribute("genreList", DataUtils.GENRE);
		verify(mockModel, times(1)).addAttribute("albumForm", album);
		assertEquals("albumform", response);
	}

	@Test
	public void testShowUpdateForm() {
		when(mockAlbumService.findById(allAlbumsList.get(0).getId())).thenReturn(allAlbumsList.get(0));

		response = classUnderTest.showUpdateForm(1, mockModel);

		verify(mockModel, times(1)).addAttribute("formatList", DataUtils.FORMAT_LIST);
		verify(mockModel, times(1)).addAttribute("genreList", DataUtils.GENRE);
		verify(mockModel, times(1)).addAttribute("albumForm", allAlbumsList.get(0));
		assertEquals("albumform", response);
	}

	@Test
	public void testShow() {
		when(mockAlbumService.findById(1)).thenReturn(allAlbumsList.get(0));

		response = classUnderTest.showInfo(1, mockModel);

		verify(mockModel, times(1)).addAttribute("album", allAlbumsList.get(0));
		verify(mockAlbumService, times(1)).findById(1);
		assertEquals("show", response);
	}
}
