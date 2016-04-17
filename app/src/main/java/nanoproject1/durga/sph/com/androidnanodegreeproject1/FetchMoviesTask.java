package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.Movies;

/**
 * Created by durga on 2/26/16.
 */
public class FetchMoviesTask extends AsyncTask<Void, Void, List<Movies>>
{
    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    private MoviesAdapter mmoviesAdapter;
    private Context mContext;
    Constants.sortOrder order;

    public FetchMoviesTask(Context ctx, MoviesAdapter moviesAdapter, Constants.sortOrder sortOrder)
    {
        mContext = ctx;
        mmoviesAdapter = moviesAdapter;
        order = sortOrder;
    }


    private List<Movies> getMoviesDataFromJson(String moviesJsonStr) throws JSONException
    {
        List<Movies> moviesList = new ArrayList<>();
        Movies movie;
        JSONObject movieObj;
        try
        {
            JSONObject moviesJson = new JSONObject(moviesJsonStr);
            if(moviesJson != null)
            {
                JSONArray moviesjsonArray = (JSONArray) moviesJson.get("results");
                if(moviesjsonArray != null)
                {
                    int length = moviesjsonArray.length();
                    int index =0;
                    for (int i = 0; i < moviesjsonArray.length(); i++)
                    {
                        movieObj = (JSONObject) moviesjsonArray.get(i);
                        if (movieObj != null)
                        {
                            movie = new Movies();
                            movie.setId(movieObj.getString(Constants._ID));
                            movie.setPoster_path(movieObj.getString(Constants.COLUMN_THUMBNAIL));
                            movie.setOverview(movieObj.getString(Constants.COLUMN_PLOT));
                            movie.setPopularity(movieObj.getDouble(Constants.COLUMN_POPULARITY));
                            movie.setRelease_date(movieObj.getString(Constants.COLUMN_RELEASE_DATE));
                            movie.setTitle(movieObj.getString(Constants.COLUMN_TITLE));
                            movie.setVote_average(movieObj.getDouble(Constants.COLUMN_RATING));
                            moviesList.add(movie);
                        }
                    }
                }
            }
            return moviesList;

        }
        catch (JSONException e)
        {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Movies> doInBackground(Void... params)
    {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        // Will contain the raw JSON response as a string.
        String moviesJsonStr = null;
        try
        {
            final String MOVIE_BASE_URL =
                    "http://api.themoviedb.org/3/movie/now_playing";
            final String API_PARAM = "api_key";

            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendQueryParameter(API_PARAM, BuildConfig.MY_MOVIES_DB_API_KEY)
                    .build();

            URL url = new URL(builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0)
            {
                // Stream was empty.  No point in parsing.
                return null;
            }
            moviesJsonStr = buffer.toString();
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        }
        finally
        {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (final IOException e)
                {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try
        {
            return getMoviesDataFromJson(moviesJsonStr);
        }
        catch (JSONException e)
        {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Movies> result)
    {
        if (result != null && mmoviesAdapter != null)
        {
            if(order.equals(Constants.sortOrder.popularity)) {
                Collections.sort(result, new MoviesPopularityComparator());
            }
            else
            {
                Collections.sort(result, new MoviesRatingComparator());
            }
            mmoviesAdapter.moviesList.clear();
            for(Movies movieStr : result)
            {
                mmoviesAdapter.moviesList.add(movieStr);
            }
            mmoviesAdapter.notifyDataSetChanged();
        }
    }
}
