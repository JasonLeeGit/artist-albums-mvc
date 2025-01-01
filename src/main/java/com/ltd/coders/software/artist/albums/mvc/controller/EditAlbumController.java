package com.ltd.coders.software.artist.albums.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltd.coders.software.artist.albums.mvc.model.Album;
import com.ltd.coders.software.artist.albums.mvc.service.AlbumService;
import com.ltd.coders.software.artist.albums.mvc.util.DataUtils;
import com.ltd.coders.software.artist.albums.mvc.validator.AlbumFormValidator;

@Controller
public class EditAlbumController {

	private final Logger logger = LoggerFactory.getLogger(EditAlbumController.class);

	private AlbumService albumService;

	@Autowired
	private AlbumFormValidator albumFormValidator;

	public EditAlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(albumFormValidator);
	}

	@PostMapping("/albums")
	public String saveOrUpdateAlbum(@ModelAttribute("albumForm") @Validated Album album, BindingResult bindingResult,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("saveOrUpdateAlbum() : {}", album);

		if (bindingResult.hasErrors()) {
			popuateLists(model);
			return "albumForm";
		} else {
			redirectAttributes.addFlashAttribute("alert", "success");
			if (album.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "Album added successfully!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Album updated successfully!");
			}
			if (album != null) {
				albumService.saveOrUpdate(album);
			}
			return "redirect:/albums/" + album.getId();
		}
	}

	private void popuateLists(Model model) {
		model.addAttribute("formatList", DataUtils.FORMAT_LIST);
		model.addAttribute("genreList", DataUtils.GENRE);
	}
}
