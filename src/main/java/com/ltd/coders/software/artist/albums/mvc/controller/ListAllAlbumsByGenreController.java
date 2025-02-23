package com.ltd.coders.software.artist.albums.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import com.ltd.coders.software.artist.albums.mvc.service.AlbumService;
import com.ltd.coders.software.artist.albums.mvc.validator.AlbumFormValidator;

@Controller
public class ListAllAlbumsByGenreController {

	private final Logger logger = LoggerFactory.getLogger(ListAllAlbumsByGenreController.class);

	private AlbumService albumService;

	@Autowired
	private AlbumFormValidator albumFormValidator;

	public ListAllAlbumsByGenreController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(albumFormValidator);
	}

	@GetMapping(value = { "/albums/genre/{genre}" })
	public String listAllAlbumsByGenre(@PathVariable String genre, Model model) {
		logger.debug("listAlbums()...");

		if (genre != null) {
			model.addAttribute("albums", albumService.findAllByGenre(genre));
		}
		return "/allalbums";
	}
}
