package test;

import controllers.AlbumController;
import controllers.ArtistController;
import controllers.ChartController;
import db.Database;
import objects.Album;
import objects.Artist;
import objects.Chart;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws Exception {
        ArtistController artists = ArtistController.getInstance();
        AlbumController albums = AlbumController.getInstance();
        ChartController charts = ChartController.getInstance();

        Artist artist1 = new Artist("Gorillaz", "England");
        artists.save(artist1);
        Artist artist2 = new Artist("Nothing but Thieves", "England");
        artists.save(artist2);
        Artist artist3 = new Artist("Remo Palmier", "USA");
        artists.save(artist3);

        Album album1 = new Album(artist1.getId(), "Demon Days", 2005);
        Album album2 = new Album(artist2.getId(), "Broken Machine", 2017);
        Album album3 = new Album(artist3.getId(), "Windflower", 1978);
        albums.save(album1);
        albums.save(album2);
        albums.save(album3);

        Chart chart = new Chart("Random top hits", Arrays.asList(album1.getId(), album2.getId(), album3.getId()));
        charts.save(chart);

        System.out.println(chart);

        Database.getInstance().close();
    }
}
