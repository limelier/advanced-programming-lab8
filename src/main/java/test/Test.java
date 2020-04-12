package test;

import com.github.javafaker.Faker;
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
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws Exception {
        createChartTest();
        rankArtistsTest();
    }

    public static void createChartTest() throws NotSavedException, NonExistentArtistException, NonExistentAlbumException {
        Faker faker = new Faker();
        Random random = new Random();
        List<ObjectId> albumList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Artist artist = new Artist(faker.name().fullName(), faker.country().name());
            ArtistController.getInstance().save(artist);
            Album album = new Album(artist.getId(), faker.funnyName().name(), 1980 + random.nextInt(9));
            AlbumController.getInstance().save(album);
            albumList.add(album.getId());
        }
        Chart chart = new Chart("Top 100 funniest songs of the 80's", albumList);
        ChartController.getInstance().save(chart);
    }

    public static void rankArtistsTest() {
        Chart chart = ChartController.getInstance().getByName("Top 100 funniest songs of the 80's");
        List<Artist> topArtists = ChartUtil.getTop(chart);
        System.out.println("Top three funny 80's artists: ");
        for (int i = 0; i < 3; i++) {
            System.out.println(topArtists.get(i).getName());
        }
    }
}
