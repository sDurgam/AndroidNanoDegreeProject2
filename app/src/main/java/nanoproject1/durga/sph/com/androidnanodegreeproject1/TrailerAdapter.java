package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nanoproject1.durga.sph.com.androidnanodegreeproject1.movie.Trailers;

/**
 * Created by durga on 3/26/16.
 */
public class TrailerAdapter extends BaseAdapter
{
    Context mContext;
    ArrayList<Trailers> trailersArrayList;

    public TrailerAdapter(Context context, ArrayList<Trailers> trailersList)
    {
        super();
        mContext = context;
        trailersArrayList = trailersList;
    }

    private static class TrailersHolder
    {
        LinearLayout trailersLayout;
        Button playButton;
        TextView title;
    }

    @Override
    public int getCount()
    {
        return trailersArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return trailersArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TrailersHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new TrailersHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.movie_trailer_layout, parent, false);
            viewHolder.trailersLayout = (LinearLayout) convertView.findViewById(R.id.trailersLayout);
            viewHolder.playButton = (Button) convertView.findViewById(R.id.mediaButton);
            viewHolder.trailersLayout.setTag(R.id.trailersLayout, trailersArrayList.get(position).getKey());
            viewHolder.trailersLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + v.getTag(R.id.trailersLayout))));
                }
            });
            viewHolder.title = (TextView) convertView.findViewById(R.id.trailerTxt);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (TrailersHolder) convertView.getTag();
        }
        viewHolder.title.setText(trailersArrayList.get(position).getName());
        return convertView;
    }
}
