package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.Reviews;

/**
 * Created by durga on 4/17/16.
 */
public class ReviewsAdapter extends BaseAdapter
{
    Context mContext;
    ArrayList<Reviews> reviewsArrayList;

    public ReviewsAdapter(Context context, ArrayList<Reviews> reviewsList)
    {
        super();
        mContext = context;
        reviewsArrayList = reviewsList;
    }

    private static class ReviewsHolder
    {
        LinearLayout reviewsLayout;
        TextView author;
        TextView content;
        TextView url;
    }

    @Override
    public int getCount()
    {
        return reviewsArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return reviewsArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ReviewsHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ReviewsHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.movie_review_layout, parent, false);
            viewHolder.reviewsLayout = (LinearLayout) convertView.findViewById(R.id.reviewsLayout);
            viewHolder.author = (TextView) convertView.findViewById(R.id.reviewauthorTxt);
            viewHolder.content = (TextView) convertView.findViewById(R.id.reviewcontentTxt);
            viewHolder.url = (TextView) convertView.findViewById(R.id.reviewurlTxt);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ReviewsHolder) convertView.getTag();
        }
        viewHolder.author.setText(reviewsArrayList.get(position).getAuthor());
        viewHolder.content.setText(reviewsArrayList.get(position).getContent());
        viewHolder.url.setText(reviewsArrayList.get(position).getUrl());
        return convertView;
    }
}

