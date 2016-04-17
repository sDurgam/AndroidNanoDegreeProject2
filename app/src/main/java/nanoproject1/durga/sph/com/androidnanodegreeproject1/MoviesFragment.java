package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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
import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.Movies;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>
{

    AppCompatActivity activity;
    @Bind(R.id.home_appbarlayout)
    AppBarLayout appbarLayout;
    @Bind(R.id.moviesView)GridView moviesView;
    @BindString(R.string.pref_sort_key) String sortkey;
    @BindString(R.string.pref_sort_default_value) String sortdefault;
    MoviesAdapter madapter;
    CursorLoader cursorLoader;
    Toolbar toolbar;

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
        setHasOptionsMenu(true);
        madapter = new MoviesAdapter(activity, new ArrayList<Movies>());
        moviesView.setAdapter(madapter);
        toolbar = (Toolbar) appbarLayout.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
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
        if(sortorder.equals(Constants.FAVORITES))
        {
            madapter.moviesList.clear();
            getActivity().getSupportLoaderManager().initLoader(1, null, this);
        }
        else
        {
            new FetchMoviesTask(activity, madapter, Constants.sortOrder.valueOf(sortorder)).execute();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1)
    {
        cursorLoader= new CursorLoader(this.getActivity(), MoviesContract.MovieEntry.CONTENT_URI, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor)
    {
        cursor.moveToFirst();
        StringBuilder res=new StringBuilder();
        ArrayList<Movies> moviesArrayList = new ArrayList<>();
        Movies mmovie;
        while (!cursor.isAfterLast())
        {
            mmovie = new Movies();
            mmovie.setId(cursor.getString(cursor.getColumnIndex(Constants._ID)));
            mmovie.setOverview(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PLOT)));
            mmovie.setPoster_path(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_THUMBNAIL)));
            mmovie.setRelease_date(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_RELEASE_DATE)));
            mmovie.setTitle(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_TITLE)));
            mmovie.setPopularity(cursor.getDouble(cursor.getColumnIndex(Constants.COLUMN_POPULARITY)));
            mmovie.setVote_average(cursor.getDouble(cursor.getColumnIndex(Constants.COLUMN_RATING)));
            moviesArrayList.add(mmovie);
            cursor.moveToNext();
        }
        madapter.moviesList = moviesArrayList;
        madapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0)
    {

    }
}
