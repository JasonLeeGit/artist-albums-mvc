package com.ltd.coders.software.artist.albums.mvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ltd.coders.software.artist.albums.mvc.model.Album;

@Component
public class AlbumFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Album.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Album album = (Album) target;
		
		// artistName is validated in Album class);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "albumName", "NotEmpty.albumForm.albumName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "NotEmpty.albumForm.genre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "NotEmpty.albumForm.genredd");
        
        if (album.getGenre().equalsIgnoreCase("NONE")) {
            errors.rejectValue("genre", "NotEmpty.albumForm.genredd");
        }
        if (album.getFormat() == null || album.getFormat().size() < 1) {
            errors.rejectValue("format", "Valid.albumForm.format");
        }
	}
}
