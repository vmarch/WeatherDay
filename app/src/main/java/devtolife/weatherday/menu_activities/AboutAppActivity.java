package devtolife.weatherday.menu_activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import devtolife.weatherday.LocaleHelper;
import devtolife.weatherday.R;

public class AboutAppActivity extends AppCompatActivity {
    TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LocaleHelper.onAttach(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app_layout);

        Toolbar myToolbar = findViewById(R.id.my_toolbar_about_app);
        setSupportActionBar(myToolbar);


        myToolbar.setNavigationIcon(R.drawable.outline_arrow_back_black_24);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            getSupportActionBar().setTitle(getString(R.string.action_about_app));
        } catch (Exception e) {
        }

        String versionName = null;
        try {
            versionName = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tv_version = findViewById(R.id.version_of_app);
        tv_version.setText("Version of APP: " + versionName);

    }
}
