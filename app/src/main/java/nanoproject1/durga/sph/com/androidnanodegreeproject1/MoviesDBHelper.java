package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by durga on 2/26/16.
 */
public class MoviesDBHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "movies.db";

    public MoviesDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        final String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE " + MoviesContract.MovieEntry.TABLE_NAME +
                "(" +
                MoviesContract.MovieEntry._ID + " INTEGER PRIMARY KEY,"+
                MoviesContract.MovieEntry.COLUMN_PLOT + " TEXT," +
                MoviesContract.MovieEntry.COLUMN_THUMBNAIL + " TEXT," +
                MoviesContract.MovieEntry.COLUMN_RELEASE_DATE + " DATE," +
                MoviesContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_POPULARITY + " REAL NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_RATING + " REAL NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_FAVORITE + " BOOLEAN NOT NULL DEFAULT 0" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
