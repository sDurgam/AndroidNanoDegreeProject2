package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.MovieDetailPagerAdapter;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.Movies;

/**
 * Created by durga on 4/17/16.
 */
public class MoviePagerFragment extends BaseFragment
{
    @Bind(R.id.detailpagertoolbar)
    Toolbar toolbar;
    @Bind(R.id.moviePager)
    ViewPager pager;
    MovieDetailPagerAdapter adapter;
    Movies moviesObj;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_movie_pager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    public void UpdateContent(Movies movie)
    {
        toolbar.setTitle(movie.getTitle());
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        adapter = new MovieDetailPagerAdapter(this.getContext(), getActivity().getSupportFragmentManager(), movie);
        adapter.notifyDataSetChanged();
        pager.invalidate();
        pager.setAdapter(adapter);
    }
}
