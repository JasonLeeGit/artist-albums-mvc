package com.ltd.coders.software.artist.albums.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import com.ltd.coders.software.artist.albums.mvc.service.AlbumService;
import com.ltd.coders.software.artist.albums.mvc.validator.AlbumFormValidator;

@Controller
public class ListAllAlbumsController {

	private final Logger logger = LoggerFactory.getLogger(ListAllAlbumsController.class);

	private AlbumService albumService;

	@Autowired
	private AlbumFormValidator albumFormValidator;

	public ListAllAlbumsController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(albumFormValidator);
	}

	@GetMapping(value = { "/", "/albums" })
	public String listAllAlbums(Model model) {

		logger.debug("listAlbums()...");

		model.addAttribute("albums", albumService.findAll());

		return "/allalbums";
	}
}
