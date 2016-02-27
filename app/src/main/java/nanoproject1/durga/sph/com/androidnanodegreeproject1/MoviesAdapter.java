package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by durga on 2/26/16.
 */
public class MoviesAdapter extends BaseAdapter
{
    List<Movies> moviesList;
    static Context mContext;

    public MoviesAdapter(Context ctx, List<Movies> list)
    {
        mContext = ctx;
        moviesList = list;
    }

    @Override
    public int getCount()
    {
        if(moviesList != null)
        {
            return moviesList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position)
    {
        if(moviesList != null)
        {
            return moviesList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        if(moviesList != null)
        {
            return position;
        }
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        String path;
        ViewHolder holder;
        if (view != null)
        {
            holder = (ViewHolder) view.getTag();
        }
        else
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.movie_list_item, parent, false);
            holder = new ViewHolder(view);
        }
        view.setTag(holder);
        path = holder.url+moviesList.get(position).getPoster_path();
        Picasso.with(mContext).load(path)
                .into(holder.thumbnail);
        //store movie details as a tag
        holder.movies = moviesList.get(position);
        if(HomeActivity.mTwoPane && position == 0)
        {
            StartDetailActivity(holder.movies);
        }
        return view;
    }
    static class ViewHolder
    {
        @BindString(R.string.movie_absolutepath)
        String url;
        @Bind(R.id.movieImage)
        ImageView thumbnail;
        Movies movies;
        @OnClick(R.id.movieImage)
        public void onClick(View view)
        {
            //start detail activity
            StartDetailActivity(movies);
        }
        public ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }

    public static void StartDetailActivity(Movies movie)
    {
        if(HomeActivity.mTwoPane)
        {
            if(mContext instanceof HomeActivity)
            {
                MovieDetailFragment movieFrag = (MovieDetailFragment) ((HomeActivity)mContext).getSupportFragmentManager()
                        .findFragmentById(R.id.movie_detail_container);
                movieFrag.UpdateContent(movie);
            }

        }
        else
        {
            Intent detailsIntent = new Intent(mContext, MovieDetailActivity.class);
            detailsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            detailsIntent.putExtra(Constants.MOVIEARGS, movie);
            mContext.startActivity(detailsIntent);
        }
    }
}
