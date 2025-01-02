package com.ltd.coders.software.artist.albums.mvc.util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataUtils {
    public static final List<String> FORMAT_LIST = Arrays.asList("MP3", "CD", "Vinyl");
	public static final Map<String, String> GENRE = createListOfGenres();

    private static Map<String, String> createListOfGenres() {
        Map<String, String> genre = new LinkedHashMap<>();
        genre.put("Alternative Rock", "Alternative Rock");
        genre.put("Britpop", "Britpop");
        genre.put("Dance", "Dance");
        genre.put("Disco", "Disco");
        genre.put("Electronic", "Electronic");
        genre.put("Indie", "Indie");
        genre.put("Pop", "Pop");
        genre.put("Rock", "Rock");
        genre.put("Unknown", "Unknown");
        return genre;
    }
}
