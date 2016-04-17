package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.MovieDetailFragment;
import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.Movies;

/**
 * Created by durga on 2/27/16.
 */
public class MovieDetailActivity extends AppCompatActivity
{
    @Bind(R.id.detail_toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        SetSupportToolbar();
        if(getIntent().getExtras() != null && getIntent().getExtras().get(Constants.MOVIEARGS) != null)
        {
            Movies movie = (Movies) getIntent().getExtras().get(Constants.MOVIEARGS);
            MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(movie);
            if (savedInstanceState == null)
            {
                getSupportFragmentManager().beginTransaction().
                        add(R.id.movie_detail_container_pager, movieDetailFragment).
                        addToBackStack(null)
                        .commit();
            }
            else
            {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.movie_detail_container_pager, movieDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }

        }
    }

    private void SetSupportToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_settings).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch(id)
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
