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

import com.ltd.coders.software.artist.albums.mvc.model.Album;
import com.ltd.coders.software.artist.albums.mvc.service.AlbumService;
import com.ltd.coders.software.artist.albums.mvc.util.DataUtils;
import com.ltd.coders.software.artist.albums.mvc.validator.AlbumFormValidator;

@Controller
public class ShowAlbumController {
	
	private final Logger logger = LoggerFactory.getLogger(ShowAlbumController.class);

	private AlbumService albumService;

	@Autowired
	private AlbumFormValidator albumFormValidator;

	public ShowAlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(albumFormValidator);
	}
	
	@GetMapping("/albums/{id}")
	public String showInfo(@PathVariable int id, Model model) {

		logger.debug("show() Id: {}", id);

		Album album = albumService.findById(id);
		if (album == null) {
			model.addAttribute("alert", "danger");
			model.addAttribute("msg", " not found!");
		}
		model.addAttribute("album", album);

		return "show";
	}

	@GetMapping("/albums/add")
	public String showAddForm(Model model, Album album) {

		logger.debug("showAddForm()");

		album.setArtistName("");
		album.setAlbumName("");
		album.setGenre("");
		popuateLists(model);
		model.addAttribute("albumForm", album);

		return "albumform";
	}
	
	@GetMapping("/albums/{id}/update")
	public String showUpdateForm(@PathVariable int id, Model model) {

		logger.debug("showUpdateForm() : {}", id);

		popuateLists(model);
		model.addAttribute("albumForm", albumService.findById(id));

		return "albumform";
	}
	
	private void popuateLists(Model model) {
		model.addAttribute("formatList", DataUtils.FORMAT_LIST);
		model.addAttribute("genreList", DataUtils.GENRE);
	}
}
