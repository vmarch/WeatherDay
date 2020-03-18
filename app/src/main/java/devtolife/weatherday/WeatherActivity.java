package devtolife.weatherday;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import devtolife.weatherday.fragments.DataFragment;
import devtolife.weatherday.privacy_policy.PrivacyPolicy;

public class WeatherActivity extends AppCompatActivity {
    EditText editCity;
    Button btnCheckWeather;
    CityPreference cityPreference;
    private String city;
    String newCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        cityPreference = new CityPreference(this);

        city = cityPreference.getCity();

        if (city.equals("")) {
            cityPreference.setCity("Kiev");
        }


        editCity = findViewById(R.id.edit_city);
        editCity.setInputType(InputType.TYPE_CLASS_TEXT);

        btnCheckWeather = findViewById(R.id.btn_check_weather);
        btnCheckWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newCity = editCity.getText().toString();
                if (!newCity.equals("")) {
                    changeCity(newCity);
                    editCity.setText("");
                }
            }
        });


        editCity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String newCity = editCity.getText().toString();

                if (!newCity.equals("")) {

                    changeCity(newCity);
                    editCity.setText("");

                    if (event != null && event.getKeyCode() == android.view.KeyEvent.KEYCODE_ENTER) {
                        changeCity(newCity);
                        editCity.setText("");
                        return true;
                    } else if (actionId == EditorInfo.IME_ACTION_GO) {
                        changeCity(newCity);
                        editCity.setText("");
                        return true;
                    } else if (actionId == EditorInfo.IME_ACTION_DONE) {
                        changeCity(newCity);
                        editCity.setText("");
                        return true;
                    }
                }
                return false;
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
        cityPreference.setCity(city);
    }
}
