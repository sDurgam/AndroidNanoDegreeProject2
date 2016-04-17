package nanoproject1.durga.sph.com.androidnanodegreeproject1.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.BaseFragment;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.Constants;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.FetchMovieTrailer;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.R;

/**
 * Created by durga on 4/16/16.
 */
public class TrailersFragment extends BaseFragment
{
    @Bind(R.id.trailersList)
    ListView trailersList;
    @Bind(R.id.notrailersView)
    TextView notrailersView;
    @Bind(R.id.trailerprogressbar)
    ProgressBar trailerProgressbar;
    ArrayList<Trailers> trailersArrayList;
    String title;
    ViewPager pager;
    String movieId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_trailers, container, false);
        title = getArguments().getString(Constants.MOVIETITLE);
        pager = (ViewPager) container;
        ButterKnife.bind(this, view);
        movieId = getArguments().getString(Constants.MOVIEID);
        return view;
    }

    public static TrailersFragment newInstance(String Id, String title)
    {
        TrailersFragment fragment = new TrailersFragment();
        Bundle args = new Bundle();
        args.putString(Constants.MOVIEID, Id);
        args.putString(Constants.MOVIETITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    private void FetchTrailers(String id)
    {
        trailersArrayList = new ArrayList<>();
        new FetchMovieTrailer(this.getActivity(), id, trailersList, trailerProgressbar, notrailersView).execute();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        FetchTrailers(movieId);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
