package com.ltd.coders.software.artist.albums.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltd.coders.software.artist.albums.mvc.model.Album;
import com.ltd.coders.software.artist.albums.mvc.service.AlbumService;
import com.ltd.coders.software.artist.albums.mvc.util.DataUtils;
import com.ltd.coders.software.artist.albums.mvc.validator.AlbumFormValidator;

/**
 * cd c:/users/softw/eclipse-workspace/artist.albums.mvc 
 * mvn clean jetty:run
 * http://localhost:8080/
 */

@Controller
public class AlbumController {

	private final Logger logger = LoggerFactory.getLogger(AlbumController.class);

	private AlbumService albumService;
	
	@Autowired
	private AlbumFormValidator albumFormValidator;

	public AlbumController(AlbumService albumService) {
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

	@GetMapping(value = {"/albums/genre/{genre}" })
	public String listAllAlbumsByGenre(@PathVariable("genre") String genre, Model model) {
		logger.debug("listAlbums()...");
		
		model.addAttribute("albums", albumService.findAllByGenre(genre));

		return "/allalbums";
	}

	@PostMapping("/albums")
	public String saveOrUpdateAlbum(@ModelAttribute("albumForm") @Validated Album album, BindingResult bindingResult,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("saveOrUpdateAlbum() : {}", album);

		if (bindingResult.hasErrors()) {
			popuateLists(model);
			return "albumform";
		} else {
			redirectAttributes.addFlashAttribute("alert", "success");
			if (album.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "Album added successfully!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Album updated successfully!");
			}

			albumService.saveOrUpdate(album);
			
			return "redirect:/albums/" + album.getId();
		}
	}

	@GetMapping("/albums/{id}")
	public String show(@PathVariable("id") int Id, Model model) {

		logger.debug("show() Id: {}", Id);

		Album album = albumService.findById(Id);
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
	public String showUpdateForm(@PathVariable("id") int id, Model model) {

		logger.debug("showUpdateForm() : {}", id);

		popuateLists(model);
		model.addAttribute("albumForm", albumService.findById(id));

		return "albumform";
	}

	@PostMapping("/albums/{id}/delete")
	public String delete(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		logger.debug("delete() : {}", id);

		albumService.delete(id);

		redirectAttributes.addFlashAttribute("alert", "success");
		redirectAttributes.addFlashAttribute("msg", "Album is deleted!");

		return "redirect:/";

	}
	
	private void popuateLists(Model model) {
	    model.addAttribute("formatList", DataUtils.FORMAT_LIST);
		model.addAttribute("genreList", DataUtils.GENRE);
	}
}
