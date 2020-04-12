package test;

import db.Database;
import exceptions.NonExistentArtistException;
import exceptions.NotSavedException;
import model.AlbumController;
import model.ArtistController;
import objects.Album;
import objects.Artist;

public class Test {
    public static void main(String[] args) {
        ArtistController artists = ArtistController.getInstance();
        AlbumController albums = AlbumController.getInstance();

        Artist artist = new Artist("Nothing but Thieves", "England");
        Album album;

        try {
            album = new Album(artist.getId(), "Broken Machine", 2017);
        } catch (NotSavedException e) {
            System.out.println("Can't make an album for an artist that's not saved yet...");
        }

        artists.save(artist);
        try {
            album = new Album(artist.getId(), "Broken Machine", 2017);
            albums.save(album);
        } catch (NotSavedException e) {
            System.out.println("Somehow, you got here and the artist still wasn't saved?");
        } catch (NonExistentArtistException e) {
            System.out.println("Somehow, the artist was no longer in the database with the ID we had.");
        }


        System.out.println(artists.findByName("Nothing but Thieves"));
        try {
            System.out.println(albums.findByArtist(artist.getId()));
        } catch (NotSavedException e) {
            System.out.println("Artist we tried to search by isn't saved yet.");
        }

        Database.getInstance().close();
    }
}
