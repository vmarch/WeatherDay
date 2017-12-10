package devtolife.weatherday.menu_activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import devtolife.weatherday.R;

public class PrivacyPolicy extends AppCompatActivity implements View.OnClickListener {
    private TextView privacy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(mSharedPref.getInt("mytheme", 0));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy);

          try {
            getSupportActionBar().setTitle("Policy");
        } catch (Exception e) {
        }

        privacy = findViewById(R.id.textViewPrivacyFull);
        privacy.setText(Html.fromHtml(getString(R.string.privacy_full_ukr)));
        privacy.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        getMenuInflater().inflate(R.menu.policy_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_eng: {
                privacy.setText(Html.fromHtml(getString(R.string.privacy_full_eng)));
                break;
            }
            case R.id.menu_ukr: {
                privacy.setText(Html.fromHtml(getString(R.string.privacy_full_ukr)));
                break;
            }
            case R.id.menu_rus: {
                privacy.setText(Html.fromHtml(getString(R.string.privacy_full_rus)));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
    }
}

