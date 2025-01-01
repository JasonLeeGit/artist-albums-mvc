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

public class ListAllAlbumsControllerTest {

	@Mock
	private AlbumService mockAlbumService;
	@Mock
	private Model mockModel;
	@Mock
	BindingResult mockBindingResult;
	@Mock
	RedirectAttributes mockRedirectAttributes;
	
	private ListAllAlbumsController classUnderTest;
	private List<Album> allAlbumsList;
	private List<String> formatList;
	private String response;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		classUnderTest = new ListAllAlbumsController(mockAlbumService);

		formatList = Arrays.asList("MP3", "CD");
		allAlbumsList = Arrays.asList(
				new Album(1, "artistName1", "albumName1", "Indie", formatList),
				new Album(2, "artistName2", "albumName2", "Disco", formatList));
	}

	@Test
	public void testListAllAlbums() {
		when(mockAlbumService.findAll()).thenReturn(allAlbumsList);
		
		response = classUnderTest.listAllAlbums(mockModel);
		
		verify(mockAlbumService, times(1)).findAll();
		verify(mockModel, times(1)).addAttribute("albums", allAlbumsList);	
		assertEquals("/allalbums", response);
	}

}
