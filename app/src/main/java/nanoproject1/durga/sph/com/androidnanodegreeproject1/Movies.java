package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by durga on 2/26/16.
 */
public class Movies implements Parcelable
{
    String id;
    String poster_path;
    String overview;
    String release_date;
    String title;
    double popularity;
    double vote_average;

    public Movies()
    {

    }

    public double getPopularity()
    {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(id);
        out.writeString(poster_path);
        out.writeString(overview);
        out.writeString(release_date);
        out.writeString(title);
        out.writeDouble(popularity);
        out.writeDouble(vote_average);
    }

    public static final Parcelable.Creator<Movies> CREATOR
            = new Parcelable.Creator<Movies>() {
        public Movies createFromParcel(Parcel source)
        {
            Movies mMovie = new Movies();
            mMovie.id = source.readString();
            mMovie.poster_path = source.readString();
            mMovie.overview = source.readString();
            mMovie.release_date = source.readString();
            mMovie.title = source.readString();
            mMovie.popularity = source.readDouble();
            mMovie.vote_average = source.readDouble();
            return mMovie;
        }

        public Movies[] newArray(int size)
        {
            return new Movies[size];
        }
    };

    private Movies(Parcel in)
    {
        id = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        title = in.readString();
        popularity = in.readDouble();
        vote_average = in.readDouble();
    }

}
