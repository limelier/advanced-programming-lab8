package test;

import controllers.AlbumController;
import controllers.ArtistController;
import controllers.ChartController;
import exceptions.NonExistentAlbumException;
import exceptions.NonExistentArtistException;
import exceptions.NotSavedException;
import objects.Album;
import objects.Artist;
import objects.Chart;
import org.bson.types.ObjectId;
import util.ChartUtil;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        createChartTest();
        rankArtistsTest();
    }

    public static void createChartTest() throws NotSavedException, NonExistentArtistException, NonExistentAlbumException {
        List<ObjectId> albumList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Artist artist = new Artist("Maroon " + i, "England");
            ArtistController.getInstance().save(artist);
            Album album = new Album(artist.getId(), "Mambo No. " + i, 2000 + i);
            AlbumController.getInstance().save(album);
            albumList.add(album.getId());
        }
        Chart chart = new Chart("Top 10 Mambos", albumList);
        ChartController.getInstance().save(chart);
    }

    public static void rankArtistsTest() {
        Chart chart = ChartController.getInstance().getByName("Top 10 Mambos");
        List<Artist> topArtists = ChartUtil.getTop(chart);
        System.out.println("Fifth place: " + topArtists.get(5));
    }
}
