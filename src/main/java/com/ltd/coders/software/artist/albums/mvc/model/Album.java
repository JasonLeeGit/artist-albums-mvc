package com.ltd.coders.software.artist.albums.mvc.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Album {

	Integer id;
	@NotEmpty
	@Size(min = 1, max = 100, message = "Artist name must be between 1 and 100 characters")
	String artistName;	
	String albumName;	
	String genre;
	List<String> format;
	
	public Album() {};
	
	public Album(int id, String artistName, String albumName, String genre, List<String> format){
		this.id = id;
		this.artistName =artistName;
		this.albumName = albumName;
		this.genre = genre;
		this.format = format;
	}
	
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
       
    public List<String> getFormat() {
        return format;
    }

    public void setFormat(List<String> format) {
        this.format = format;
    }

	public boolean isNew() {
		return (this.id == null);
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", artistName=" + artistName + ", albumName=" + albumName + ", genre=" + genre + "]";
	}
}
