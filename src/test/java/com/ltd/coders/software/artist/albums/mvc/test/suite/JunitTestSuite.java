package com.ltd.coders.software.artist.albums.mvc.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ltd.coders.software.artist.albums.mvc.controller.DeleteAlbumControllerTest;
import com.ltd.coders.software.artist.albums.mvc.controller.EditAlbumControllerTest;
import com.ltd.coders.software.artist.albums.mvc.controller.ListAllAlbumsByGenreControllerTest;
import com.ltd.coders.software.artist.albums.mvc.controller.ListAllAlbumsControllerTest;
import com.ltd.coders.software.artist.albums.mvc.controller.ShowAlbumControllerTest;

@RunWith(Suite.class)
@SuiteClasses({
	DeleteAlbumControllerTest.class,
	EditAlbumControllerTest.class,
	ListAllAlbumsByGenreControllerTest.class,
	ListAllAlbumsControllerTest.class,
	ShowAlbumControllerTest.class
})
public class JunitTestSuite {

}