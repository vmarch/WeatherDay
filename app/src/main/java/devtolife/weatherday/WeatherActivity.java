package devtolife.weatherday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import devtolife.weatherday.fragments.DataFragment;
import devtolife.weatherday.privacy_policy.PrivacyPolicy;


public class WeatherActivity extends AppCompatActivity {
    EditText editCity;
    Button btnCheckWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        editCity = findViewById(R.id.edit_city);
        editCity.setInputType(InputType.TYPE_CLASS_TEXT);

        btnCheckWeather = findViewById(R.id.btn_check_weather);
        btnCheckWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newCity = editCity.getText().toString();
                if (!newCity.equals("")) {
                    changeCity(newCity);
                    editCity.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.weather_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        if (item.getItemId() == R.id.action_policy) {
            intent = new Intent(this, PrivacyPolicy.class);
            startActivity(intent);
            return true;
        }

        return false;

    }

    public void changeCity(String city) {
        DataFragment wf = (DataFragment) getSupportFragmentManager()
                .findFragmentById(R.id.data_fragment);
        wf.changeCityFragm(city);
        new CityPreference(this).setCity(city);
    }
}
