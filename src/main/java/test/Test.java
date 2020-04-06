package test;

import db.Database;
import model.AlbumController;
import model.ArtistController;

public class Test {
    public static void main(String[] args) {
        ArtistController artists = ArtistController.getInstance();
        AlbumController albums = AlbumController.getInstance();

        String artistId;
        String albumId;

        try {
            albumId = albums.create("Broken Machine", "fakeId", 2017);
        } catch (IllegalArgumentException e) {
            System.out.println("Whoops! Can't add the album first...");
        }

        artistId = artists.create("Nothing but Thieves", "England");
        albumId = albums.create("Broken Machine", artistId, 2017);

        System.out.println(artists.findByName("Nothing but Thieves"));
        System.out.println(albums.findByArtist(artistId));

        Database.getInstance().close();
    }
}
