package com.ltd.coders.software.artist.albums.mvc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltd.coders.software.artist.albums.mvc.service.AlbumService;

public class DeleteAlbumControllerTest {
	
	@Mock
	private AlbumService mockAlbumService;
	@Mock
	private Model mockModel;
	@Mock
	BindingResult mockBindingResult;
	@Mock
	RedirectAttributes mockRedirectAttributes;
	
	private DeleteAlbumController classUnderTest;
	private String response;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		classUnderTest = new DeleteAlbumController(mockAlbumService);
	}

	@Test
	public void testDelete() {
		when(mockAlbumService.delete(1)).thenReturn(1);
		
		response = classUnderTest.delete(1, mockRedirectAttributes);
		
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("alert", "success");
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("msg", "Album is deleted!");
		verify(mockAlbumService, times(1)).delete(1);
		assertEquals("redirect:/", response);
	}
	
	@Test
	public void testDeleteZeroId() {
		when(mockAlbumService.delete(0)).thenReturn(0);
		
		response = classUnderTest.delete(0, mockRedirectAttributes);
		
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("alert", "delete failed");
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("msg", "Album ID less thean zero");
		verify(mockAlbumService, times(0)).delete(0);
		assertEquals("redirect:/", response);
	}
}
