package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesFragment extends Fragment
{

    AppCompatActivity activity;
    @Bind(R.id.home_toolbar) Toolbar toolbar;
    @Bind(R.id.moviesView)GridView moviesView;
    @BindString(R.string.pref_sort_key) String sortkey;
    @BindString(R.string.pref_sort_default_value) String sortdefault;
    MoviesAdapter madapter;


    public MoviesFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        madapter = new MoviesAdapter(activity.getApplicationContext(), new ArrayList<Movies>());
        moviesView.setAdapter(madapter);

        if(activity.getIntent().getExtras() != null && activity.getIntent().getExtras().get(Constants.isPreferenceChanged) != null)
        {
             activity.getIntent().getExtras().getBoolean(Constants.isPreferenceChanged);
        }
        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        UpdateMoviesData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            Intent settingsIntent = new Intent(activity, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void UpdateMoviesData()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String sortorder = preferences.getString(sortkey, sortdefault);
        new FetchMoviesTask(activity, madapter, Constants.sortOrder.valueOf(sortorder)).execute();
    }
}
