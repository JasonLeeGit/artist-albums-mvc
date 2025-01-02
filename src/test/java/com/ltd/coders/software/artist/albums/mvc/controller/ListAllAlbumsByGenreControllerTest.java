package com.ltd.coders.software.artist.albums.mvc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

public class ListAllAlbumsByGenreControllerTest {

	@Mock
	private AlbumService mockAlbumService;
	@Mock
	private Model mockModel;
	@Mock
	BindingResult mockBindingResult;
	@Mock
	RedirectAttributes mockRedirectAttributes;

	private ListAllAlbumsByGenreController classUnderTest;
	private List<Album> allAlbumsList;
	private String response;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		classUnderTest = new ListAllAlbumsByGenreController(mockAlbumService);
	}

	@Test
	public void testFindAllAlbumsByGenre() {
		when(mockAlbumService.findAllByGenre("Indie")).thenReturn(allAlbumsList);

		response = classUnderTest.listAllAlbumsByGenre("Indie", mockModel);

		verify(mockAlbumService, times(1)).findAllByGenre("Indie");
		verify(mockModel, times(1)).addAttribute("albums", allAlbumsList);
		assertEquals("/allalbums", response);
	}
	
	@Test
	public void testFindAllAlbumsByGenreWhenGenreNotFound() {
		when(mockAlbumService.findAllByGenre("GenreMissing")).thenReturn(allAlbumsList);

		response = classUnderTest.listAllAlbumsByGenre("GenreMissing", mockModel);

		verify(mockAlbumService, times(1)).findAllByGenre("GenreMissing");
		verify(mockModel, times(1)).addAttribute("albums", allAlbumsList);
		assertEquals("/allalbums", response);
	}
}
