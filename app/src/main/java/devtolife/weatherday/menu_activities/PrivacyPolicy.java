package devtolife.weatherday.menu_activities;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import devtolife.weatherday.R;

public class PrivacyPolicy extends AppCompatActivity implements View.OnClickListener {
    private TextView privacy;
    private Toolbar myToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        SharedPreferences mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        setTheme(mSharedPref.getInt("mytheme", 0));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy);

        myToolbar = findViewById(R.id.my_toolbar_privacy);
        setupToolbar();

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

    private void setupToolbar() {
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            myToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);
            myToolbar.setNavigationContentDescription(getResources().getString(R.string.context_description_arrow_back));
            myToolbar.setTitle("Policy");
            myToolbar.setNavigationOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}

