package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        mTitle.setText(movie.getTitle());
        mPlot.setText(Constants.PLOTSTR + movie.getOverview());
        //mThumbnail
        Picasso.with(getActivity()).load(url+movie.getPoster_path())
                .into(mThumbnail);
        mRating.setText(Constants.AVGSTR + String.valueOf(movie.getVote_average()));
        mDate.setText(Constants.RELEASESTR +movie.getRelease_date());
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
