package devtolife.weatherday.menu_activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import devtolife.weatherday.R;

public class PrivacyPolicy extends AppCompatActivity implements View.OnClickListener {
    private TextView privacy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        SharedPreferences mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        setTheme(mSharedPref.getInt("mytheme", 0));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setNavigationIcon(R.drawable.outline_arrow_back_black_24);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        try {
            getSupportActionBar().setTitle(getString(R.string.action_policy));
        } catch (Exception e) {
        }

        privacy = findViewById(R.id.textViewPrivacyFull);
        privacy.setText(Html.fromHtml(getString(R.string.privacy_full_eng)));
        privacy.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
    }
}

