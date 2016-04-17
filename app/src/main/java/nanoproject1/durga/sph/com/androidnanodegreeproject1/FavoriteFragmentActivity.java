package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.TextView;

/**
 * Created by durga on 3/26/16.
 */
public class FavoriteFragmentActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor>
{
    TextView resultView=null;
    CursorLoader cursorLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        resultView= (TextView) findViewById(R.id.moviesText);
        onClickDisplayNames(resultView);
    }

    public void onClickDisplayNames(View view)
    {
        getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1)
    {
        cursorLoader= new CursorLoader(this, MoviesContract.MovieEntry.CONTENT_URI, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor)
    {
        cursor.moveToFirst();
        StringBuilder res=new StringBuilder();
        while (!cursor.isAfterLast())
        {
            res.append("\n"+cursor.getString(cursor.getColumnIndex(Constants._ID))+ "-"+ cursor.getString(cursor.getColumnIndex(Constants.COLUMN_TITLE)));
            cursor.moveToNext();
        }
        resultView.setText(res);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0)
    {

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
