package nanoproject1.durga.sph.com.androidnanodegreeproject1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by durga on 2/25/16.
 */
public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener
{
    SharedPreferences preferences;
    private AppCompatDelegate mDelegate;
    boolean isPreferenceChanged = false;
    @Bind(R.id.movie_toolbar) Toolbar appbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getDelegate().installViewFactory();
        getDelegate().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ButterKnife.bind(this);
        final Context mContext = this;
        if(appbar != null)
        {
//            appbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v)
//                {
//                    Intent MoviesActivity = new Intent(mContext, HomeActivity.class);
//                    MoviesActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    MoviesActivity.putExtra(Constants.isPreferenceChanged, isPreferenceChanged);
//                    mContext.startActivity(MoviesActivity);
//
//                }
//            });
            setSupportActionBar(appbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        addPreferencesFromResource(R.xml.preferences);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_sort_key)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Attaches a listener so the summary is always updated with the preference value.
     * Also fires the listener once, to initialize the summary (so it shows up before the value
     * is changed.)
     */
    private void bindPreferenceSummaryToValue(Preference preference)
    {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);
        // Trigger the listener immediately with the preference's
        // current value.
        onPreferenceChange(preference, preferences.getString(preference.getKey(), getString(R.string.pref_sort_default_value)));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value)
    {
        String stringValue = value.toString();
        if (preference instanceof ListPreference)
        {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0)
            {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
            isPreferenceChanged = true;
        }
        else
        {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getDelegate().onPostCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    private void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().setSupportActionBar(toolbar);
    }

    private ActionBar getSupportActionBar()
    {
        return getDelegate().getSupportActionBar();
    }

    private AppCompatDelegate getDelegate()
    {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(this, null);
        }
        return mDelegate;
    }
}
