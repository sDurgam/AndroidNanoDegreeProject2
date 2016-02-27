package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * Created by durga on 2/27/16.
 */
public class MovieDetailFragment extends Fragment
{
    @BindString(R.string.movie_absolutepath)
    String url;
    @Bind(R.id.mtitle)
    TextView mTitle;
    @Bind(R.id.mplot)
    TextView mPlot;
    @Bind(R.id.mthumbnail)
    ImageView mThumbnail;
    @Bind(R.id.mrating)
    TextView mRating;
    @Bind(R.id.mdate)
    TextView mDate;
    private final static String TAG = "MovieDetailFragment";
    public MovieDetailFragment()
    {

    }

    public static MovieDetailFragment newInstance(Movies movie)
    {
        Bundle args = new Bundle();
        args.putSerializable(Constants.MOVIEARGS, movie);
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

    @Override
    public void onResume()
    {
        super.onResume();
        //get arguments
        if(getArguments() != null)
        {
            Movies movie = (Movies) this.getArguments().get(Constants.MOVIEARGS);
            PopulateMovieView(movie);
        }
    }

    protected void UpdateContent(Movies movie)
    {
        PopulateMovieView(movie);
    }

    private void PopulateMovieView(Movies movie)
    {
        String title = movie.getTitle();
        String plot = Constants.PLOTSTR + movie.getOverview();
        String imageurl = url+movie.getPoster_path();
        String rating = Constants.AVGSTR + String.valueOf(movie.getVote_average());
        String date = Constants.RELEASESTR + ParseDate(movie.getRelease_date());
        mTitle.setText(title);
        mPlot.setText(plot);
        //mThumbnail
        Picasso.with(getActivity()).load(imageurl)
                .into(mThumbnail);
        mRating.setText(rating);
        //parse date to mm-dd-yyyy
        mDate.setText(date);
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
        // ShareActionProvider sharedprovider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.share));
        // sharedprovider.setShareIntent(DisplayShareIntent());
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        return super.onOptionsItemSelected(item);
    }
}
