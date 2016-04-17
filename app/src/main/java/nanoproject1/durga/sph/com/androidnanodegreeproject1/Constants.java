package nanoproject1.durga.sph.com.androidnanodegreeproject1;

/**
 * Created by durga on 2/26/16.
 */
public class Constants
{
    public static final String _ID = "id";
    public static final String COLUMN_THUMBNAIL = "poster_path";
    public static final String COLUMN_PLOT = "overview";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_TITLE = "original_title";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_RATING = "vote_average";
    public static final String COLUMN_FAVORITE = "favorite";
    public static final String isPreferenceChanged = "ispreferencechanged";
    public static enum sortOrder { popularity, vote_average};
    public static final String MOVIEARGS = "movies";

    public static final String MOVIEID = "id";
    public static final String MOVIETITLE = "title";

    public static final String PLOTSTR = "Plot: ";
    public static final String AVGSTR = "/10";
    public static final String RELEASESTR = "Release Date: ";
    public static final String DETAILFRAGMENT_TAG = "MFTAG";
    public static final String DATEPARSEERROR = "Unable to parse date string";
    public static final String FAVORITES = "favorites";
    //Region for trailers
    public static final String TRAILERID = "id";
    public static final String TRAILERKEY = "key";
    public static final String TRAILERNAME = "name";
    public static final String TRAILERSITE = "site";
    public static final String TRAILERSIZE = "size";
    public static final String TRAILERTYPE = "type";
    //End Region for trailers
    //Region for reviews
    public static final String REVIEWSID = "id";
    public static final String REVIEWSAUTHOR = "author";
    public static final String REVIEWSCONTENT = "content";
    public static final String REVIEWSURL = "url";
    //End Region for reviews
    public static final int MAX_COUNT = 3;
}
