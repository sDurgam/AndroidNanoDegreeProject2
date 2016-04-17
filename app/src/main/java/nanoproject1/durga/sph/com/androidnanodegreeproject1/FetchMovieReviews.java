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

import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.Reviews;

/**
 * Created by durga on 4/17/16.
 */
public class FetchMovieReviews extends AsyncTask<Void, Void, ArrayList<Reviews>>
{
    private final String LOG_TAG = FetchMovieReviews.class.getSimpleName();
    private Context mContext;
    String mId;
    ListView mreviewsList;
    ProgressBar mProgressbar;
    TextView mnoreviewsView;

    public FetchMovieReviews(Context ctx, String id, ListView reviewsList, ProgressBar progressbar, TextView noReviewsView)
    {
        mContext = ctx;
        mId = id;
        mreviewsList = reviewsList;
        mProgressbar = progressbar;
        mnoreviewsView = noReviewsView;
    }

    @Override
    protected ArrayList<Reviews> doInBackground(Void... params)
    {
        String MOVIE_BASE_URL =
                "https://api.themoviedb.org/3/movie/%s/reviews";
        String moviebaseurl = String.format(MOVIE_BASE_URL, mId);
        final String API_PARAM = "api_key";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        // Will contain the raw JSON response as a string.
        String moviesJsonStr = null;
        ArrayList<Reviews> reviewsArrayList;
        reviewsArrayList = new ArrayList<>();
        JSONObject reviewsObject;
        Reviews review;
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
            JSONArray reviewsArray;
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0)
            {
                return null;
            }
            moviesJsonStr = buffer.toString();
            reviewsArray = ((JSONArray)(new JSONObject(moviesJsonStr).get("results")));
            for(int i =0; i < reviewsArray.length(); i++)
            {
                review = new Reviews();
                reviewsObject = new JSONObject(reviewsArray.get(i).toString());
                review.setId(reviewsObject.getString(Constants.REVIEWSID));
                review.setAuthor(reviewsObject.getString(Constants.REVIEWSAUTHOR));
                review.setContent(reviewsObject.getString(Constants.REVIEWSCONTENT));
                review.setUrl(reviewsObject.getString(Constants.REVIEWSURL));
                reviewsArrayList.add(review);
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
        return reviewsArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Reviews> reviewsList)
    {
        super.onPostExecute(reviewsList);
        if(reviewsList != null && reviewsList.size() > 0)
        {
            ReviewsAdapter adapter = new ReviewsAdapter(mContext, reviewsList);
            mreviewsList.setAdapter(adapter);
            mProgressbar.setVisibility(View.GONE);
        }
        else
        {
            mreviewsList.setVisibility(View.GONE);
            mProgressbar.setVisibility(View.GONE);
            mnoreviewsView.setVisibility(View.VISIBLE);
        }
    }
}
