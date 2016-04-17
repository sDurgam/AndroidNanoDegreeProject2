package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by durga on 3/26/16.
 */
public class PlayMediaActivity extends Activity implements MediaPlayer.OnPreparedListener
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));
    }

        @Override
        public void onPrepared(MediaPlayer mp) {
        // TODO Auto-generated method stub

    }
}
