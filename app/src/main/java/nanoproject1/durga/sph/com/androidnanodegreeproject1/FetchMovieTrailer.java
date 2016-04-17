package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.Trailers;

/**
 * Created by durga on 3/25/16.
 */
public class FetchMovieTrailer extends AsyncTask<Void, Void, ArrayList<Trailers>>
{
    private final String LOG_TAG = FetchMovieTrailer.class.getSimpleName();
    private Context mContext;
    String mId;
    ListView mtrailersList;
    ProgressBar mProgressbar;
    TextView mnotrailersView;

    public FetchMovieTrailer(Context ctx, String id, ListView trailersList, ProgressBar progressbar, TextView notrailersView)
    {
        mContext = ctx;
        mId = id;
        mtrailersList = trailersList;
        mProgressbar = progressbar;
        mnotrailersView = notrailersView;
    }

    @Override
    protected ArrayList<Trailers> doInBackground(Void... params)
    {
        String MOVIE_BASE_URL =
                "https://api.themoviedb.org/3/movie/%s/videos";
        String moviebaseurl = String.format(MOVIE_BASE_URL, mId);
        final String API_PARAM = "api_key";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        // Will contain the raw JSON response as a string.
        String moviesJsonStr = null;
        ArrayList<Trailers> trailersArrayList;
        trailersArrayList = new ArrayList<>();
        JSONObject trailerObject;
        Trailers trailer;
        try {
            Uri builtUri = Uri.parse(moviebaseurl).buildUpon()
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
            if (inputStream == null)
            {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            JSONArray trailersArray;
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0)
            {
                return null;
            }
            moviesJsonStr = buffer.toString();
            trailersArray = ((JSONArray)(new JSONObject(moviesJsonStr).get("results")));
            for(int i =0; i < trailersArray.length(); i++)
            {
                trailer = new Trailers();
                trailerObject = new JSONObject(trailersArray.get(i).toString());
                trailer.setId(trailerObject.getString(Constants.TRAILERID));
                trailer.setKey(trailerObject.getString(Constants.TRAILERKEY));
                trailer.setName(trailerObject.getString(Constants.TRAILERNAME));
                trailer.setType(trailerObject.getString(Constants.TRAILERTYPE));
                trailer.setSite(trailerObject.getString(Constants.TRAILERSITE));
                trailer.setSize(trailerObject.getInt(Constants.TRAILERSIZE));
                trailersArrayList.add(trailer);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return trailersArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Trailers> trailers)
    {
        super.onPostExecute(trailers);
        if(trailers != null && trailers.size() > 0)
        {
            TrailerAdapter adapter = new TrailerAdapter(mContext, trailers);
            mtrailersList.setAdapter(adapter);
            mProgressbar.setVisibility(View.GONE);
        }
        else
        {
            mtrailersList.setVisibility(View.GONE);
            mProgressbar.setVisibility(View.GONE);
            mnotrailersView.setVisibility(View.VISIBLE);
        }
    }
}
