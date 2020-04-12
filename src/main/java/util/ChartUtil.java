package util;

import controllers.ArtistController;
import controllers.ChartController;
import objects.Album;
import objects.Artist;
import objects.Chart;

import java.util.List;
import java.util.stream.Collectors;

public class ChartUtil {
    public static List<Artist> getTop(Chart chart) {
        List<Album> albums = ChartController.getInstance().getAlbums(chart);
        return albums
                .stream()
                .map(album -> ArtistController.getInstance().findById(album.getArtistId()))
                .collect(Collectors.toList());
    }
}
