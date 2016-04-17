package nanoproject1.durga.sph.com.androidnanodegreeproject1;


import android.support.v4.app.Fragment;
import android.widget.TextView;

/**
 * Created by durga on 4/16/16.
 */
public class BaseFragment extends Fragment
{
    public void SetupToolbar(TextView toolbar_title, String title)
    {
        toolbar_title.setText(title);
    }
}
