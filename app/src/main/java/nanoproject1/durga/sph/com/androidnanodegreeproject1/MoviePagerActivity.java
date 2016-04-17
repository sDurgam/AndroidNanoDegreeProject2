package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.MovieDetailPagerAdapter;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.Movies;

/**
 * Created by durga on 4/16/16.
 */
public class MoviePagerActivity extends AppCompatActivity
{
    @Bind(R.id.moviePager)
    ViewPager pager;
    MovieDetailPagerAdapter adapter;
    Movies moviesObj;
    @Bind(R.id.detailpagertoolbar)
    Toolbar mToolbar;
    @Bind(R.id.detailpagertoolbar_title)
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_pager);
        ButterKnife.bind(this);
        moviesObj = new Movies();
        if(getIntent().getExtras().getParcelable(Constants.MOVIEARGS) != null)
        {
            moviesObj = (Movies)getIntent().getExtras().getParcelable(Constants.MOVIEARGS);
        }
        adapter = new MovieDetailPagerAdapter(this, getSupportFragmentManager(), moviesObj);
        pager.setAdapter(adapter);
        SetSupportToolbar();
    }

    private void SetSupportToolbar()
    {
        mToolbar.setTitle(moviesObj.getTitle());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
