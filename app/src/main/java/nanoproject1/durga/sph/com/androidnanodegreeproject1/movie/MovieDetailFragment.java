package nanoproject1.durga.sph.com.androidnanodegreeproject1.movie;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.BaseFragment;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.Constants;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.MoviesProvider;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.R;

/**
 * Created by durga on 2/27/16.
 */
public class MovieDetailFragment extends BaseFragment
{
    @BindString(R.string.movie_absolutepath)
    String url;
    @Bind(R.id.mplot)
    TextView mPlot;
    @Bind(R.id.mthumbnail)
    ImageView mThumbnail;
    @Bind(R.id.mrating)
    TextView mRating;
    @Bind(R.id.mdate)
    TextView mDate;
    @Bind(R.id.mfavorite)
    TextView mFavorite;

    private final static String TAG = "MovieDetailFragment";
    public MovieDetailFragment()
    {

    }

    public static MovieDetailFragment newInstance(Movies movie)
    {
        Bundle args = new Bundle();
        args.putParcelable(Constants.MOVIEARGS, movie);
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    Movies movie;

    @Override
    public void onResume()
    {
        super.onResume();
        //get arguments
        if(getArguments() != null)
        {
            movie = (Movies) this.getArguments().get(Constants.MOVIEARGS);
            PopulateMovieView(movie);
        }
    }

    public void UpdateContent(Movies movie)
    {
        PopulateMovieView(movie);
    }

    private void PopulateMovieView(Movies movie)
    {
        String title = movie.getTitle();
        String plot = Constants.PLOTSTR + movie.getOverview();
        String imageurl = url+movie.getPoster_path();
        String rating = String.valueOf(movie.getVote_average()) + Constants.AVGSTR;
        String date = ParseDate(movie.getRelease_date());
        mPlot.setText(plot);
        //mThumbnail
        Picasso.with(getActivity()).load(imageurl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.nowplayingicon)
                .into(mThumbnail);
        mRating.setText(rating);
        //parse date to mm-dd-yyyy
        mDate.setText(date.split(" ")[2]);
        if(movie.isFavorite)
        {
            mFavorite.setText(getResources().getString(R.string.marked_favorite));
        }
    }

    private String ParseDate(String dateStr)
    {
        String parsedDateStr = null;
        SimpleDateFormat inputformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputformat = new SimpleDateFormat("MMMM dd yyyy");
        java.util.Date date;
        try
        {
            date = inputformat.parse(dateStr);
            parsedDateStr = outputformat.format(date);
        }
        catch (Exception e)
        {
            Log.e(TAG, Constants.DATEPARSEERROR);
        }
        return parsedDateStr;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.mfavorite)
    void onClick(View view)
    {
        TextView favoriteText = (TextView)view;
        if(favoriteText.getText().equals(getResources().getString(R.string.mark_favorite)))
        {
            AddMovie(movie);
            favoriteText.setText(getResources().getString(R.string.marked_favorite));
        }
    }

    public void AddMovie(Movies movie)
    {
        ContentValues values = new ContentValues();
        values.put(Constants._ID, movie.getId());
        values.put(Constants.COLUMN_THUMBNAIL, movie.getPoster_path());
        values.put(Constants.COLUMN_RELEASE_DATE, movie.getRelease_date());
        values.put(Constants.COLUMN_TITLE, movie.getTitle());
        values.put(Constants.COLUMN_PLOT, movie.getOverview());
        values.put(Constants.COLUMN_POPULARITY, movie.getPopularity());
        values.put(Constants.COLUMN_RATING, movie.getVote_average());
        values.put(Constants.COLUMN_FAVORITE, movie.isFavorite());
        Uri uri = getActivity().getContentResolver().insert(MoviesProvider.CONTENT_URI, values);
    }
}
