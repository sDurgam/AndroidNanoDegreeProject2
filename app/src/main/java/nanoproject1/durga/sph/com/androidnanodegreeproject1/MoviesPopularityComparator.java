package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import java.util.Comparator;

import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.Movies;

/**
 * Created by durga on 2/26/16.
 */
public class MoviesPopularityComparator implements Comparator<Movies>
{
    @Override
    public int compare(Movies movie1, Movies movie2)
    {
        return Double.compare(movie2.getPopularity(), movie1.getPopularity());
    }

}
