package nanoproject1.durga.sph.com.androidnanodegreeproject1.movie;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by durga on 4/16/16.
 */
public class MovieDetailPagerAdapter extends FragmentStatePagerAdapter
{
    int PAGE_COUNT;
    Context mContext;
    Movies movieObject;

    public Movies getMovieObject() {
        return movieObject;
    }

    public void setMovieObject(Movies movieObject) {
        this.movieObject = movieObject;
    }

    public int getPAGE_COUNT() {
        return PAGE_COUNT;
    }

    public void setPAGE_COUNT(int PAGE_COUNT) {
        this.PAGE_COUNT = PAGE_COUNT;
    }

    public MovieDetailPagerAdapter(Context ctx, FragmentManager fm, Movies movie)
    {
        super(fm);
        mContext = ctx;
        movieObject = movie;
        if(movieObject == null)
        {
            PAGE_COUNT = 0;
        }
        else
        {
            PAGE_COUNT = 3;
        }
    }

    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0) {
            fragment = TrailersFragment.newInstance(movieObject.getId(), movieObject.getTitle());
        } else if (position == 1) {
            fragment = MovieDetailFragment.newInstance(movieObject);
        } else if (position == 2) {
            fragment = ReviewsFragment.newInstance(movieObject.getId(), movieObject.getTitle());
        }
        return fragment;
    }

    @Override
    public int getCount()
    {
        return  PAGE_COUNT;
    }
}
