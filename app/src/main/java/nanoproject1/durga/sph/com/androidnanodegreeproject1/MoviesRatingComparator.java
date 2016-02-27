package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import java.util.Comparator;

/**
 * Created by durga on 2/26/16.
 */
public class MoviesRatingComparator implements Comparator<Movies>
{
    @Override
    public int compare(Movies movie1, Movies movie2)
    {
        return Double.compare(movie2.getVote_average(), movie1.getVote_average());
    }

}
