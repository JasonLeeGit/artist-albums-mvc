package com.ltd.coders.software.artist.albums.web;

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

public class AlbumControllerTest {

	@Mock
	private AlbumService mockAlbumService;
	@Mock
	private Model mockModel;
	@Mock
	BindingResult mockBindingResult;
	@Mock
	RedirectAttributes mockRedirectAttributes;
	
	private AlbumController classUnderTest;
	private List<Album> allAlbumsList;
	private Album album;
	private List<String> formatList;
	private String response;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		classUnderTest = new AlbumController(mockAlbumService);
		
		album = new Album();
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
	
	@Test
	public void testFindAllByGenre() {
		when(mockAlbumService.findAllByGenre("Indie")).thenReturn(allAlbumsList);

		response = classUnderTest.listAllAlbumsByGenre("Indie", mockModel);
		
		verify(mockAlbumService, times(1)).findAllByGenre("Indie");
		verify(mockModel, times(1)).addAttribute("albums", allAlbumsList);			
		assertEquals("/allalbums", response);
	}
	
	@Test
	public void testSaveOrUpdateAlbumForNewAlbum() {	
		when(mockAlbumService.saveOrUpdate(album)).thenReturn(3);
		
		response = classUnderTest.saveOrUpdateAlbum(album, mockBindingResult, mockModel, mockRedirectAttributes);

		verify(mockRedirectAttributes, times(1)).addFlashAttribute("alert", "success");
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("msg", "Album added successfully!");
		verify(mockAlbumService, times(1)).saveOrUpdate(album);
		assertEquals("redirect:/albums/"+album.getId(), response);
	}
	
	@Test
	public void testSaveOrUpdateAlbumForExistingAlbum() {
		when(mockBindingResult.hasErrors()).thenReturn(true);
		
		response = classUnderTest.saveOrUpdateAlbum(album, mockBindingResult, mockModel, mockRedirectAttributes);

		verify(mockAlbumService, times(0)).saveOrUpdate(album);
		assertEquals("albumform",response);
	}
	
	@Test
	public void testSaveOrUpdateAlbumWithBindingError() {
		when(mockAlbumService.saveOrUpdate(allAlbumsList.get(0))).thenReturn(allAlbumsList.get(0).getId());
		
		response = classUnderTest.saveOrUpdateAlbum(allAlbumsList.get(0), mockBindingResult, mockModel, mockRedirectAttributes);
		
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("alert", "success");
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("msg", "Album updated successfully!");
		verify(mockAlbumService, times(1)).saveOrUpdate(allAlbumsList.get(0));
		assertEquals("redirect:/albums/"+allAlbumsList.get(0).getId(), response);
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
	public void testDelete() {
		when(mockAlbumService.delete(1)).thenReturn(1);
		
		response = classUnderTest.delete(1, mockRedirectAttributes);
		
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("alert", "success");
		verify(mockRedirectAttributes, times(1)).addFlashAttribute("msg", "Album is deleted!");
		verify(mockAlbumService, times(1)).delete(1);
		assertEquals("redirect:/", response);
	}
	
	
	@Test
	public void testShow() {	
		when(mockAlbumService.findById(1)).thenReturn(allAlbumsList.get(0));
		
		response = classUnderTest.show(1, mockModel);
		
		verify(mockModel, times(1)).addAttribute("album", allAlbumsList.get(0));
		verify(mockAlbumService, times(1)).findById(1);
		assertEquals("show", response);
	}
}
