package com.ltd.coders.software.artist.albums.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltd.coders.software.artist.albums.mvc.service.AlbumService;
import com.ltd.coders.software.artist.albums.mvc.validator.AlbumFormValidator;

@Controller
public class DeleteAlbumController {

	private final Logger logger = LoggerFactory.getLogger(DeleteAlbumController.class);

	private AlbumService albumService;

	@Autowired
	private AlbumFormValidator albumFormValidator;

	public DeleteAlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(albumFormValidator);
	}

	@PostMapping("/albums/{id}/delete")
	public String delete(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		logger.debug("delete() : {}", id);

		if (id > 0) {
			albumService.delete(id);
			redirectAttributes.addFlashAttribute("alert", "success");
			redirectAttributes.addFlashAttribute("msg", "Album is deleted!");
		} else {
			redirectAttributes.addFlashAttribute("alert", "delete failed");
			redirectAttributes.addFlashAttribute("msg", "Album ID less thean zero");
		}

		return "redirect:/";
	}
}
