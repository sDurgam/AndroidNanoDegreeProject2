package nanoproject1.durga.sph.com.androidnanodegreeproject1.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import nanoproject1.durga.sph.com.androidnanodegreeproject1.FetchMovieReviews;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.R;

/**
 * Created by durga on 4/16/16.
 */
public class ReviewsFragment extends BaseFragment
{
    String movieId;
    String title;
    @Bind(R.id.reviewsList)
    ListView reviewsList;
    @Bind(R.id.reviewsProgressBar)
    ProgressBar mReviewsBar;
    @Bind(R.id.noreviewsView)
    TextView noreviewsView;
    ArrayList<Reviews> reviewsArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(Constants.MOVIETITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static ReviewsFragment newInstance(String id, String title)
    {
        ReviewsFragment fragment = new ReviewsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.MOVIEID, id);
        args.putString(Constants.MOVIETITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    private void FetchReviews(String id)
    {
        reviewsArrayList = new ArrayList<>();
        new FetchMovieReviews(this.getContext(), id, reviewsList, mReviewsBar, noreviewsView).execute();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        movieId = getArguments().getString(Constants.MOVIEID);
        FetchReviews(movieId);
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

}
