package devtolife.weatherday.fragments;

import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import devtolife.weatherday.CityPreference;
import devtolife.weatherday.R;
import devtolife.weatherday.RemoteFetch;

public class DataFragment extends Fragment {

    TextView dateField;
    TextView cityField;
    TextView temperField;
    TextView cloudsField;
    TextView pressureField;
    TextView windField;
    TextView humidityField;
    public Locale myLocale;
    private int dayTime;
    private ImageView weatherIcon;
    private int icon;
    Handler handler;


    public DataFragment() {
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getOldCityWeatherData();

        myLocale = new Locale("bel", "BY");

        View rootView = inflater.inflate(R.layout.data_fragment, container, false);
        weatherIcon = rootView.findViewById(R.id.image_weather);
        dateField = rootView.findViewById(R.id.text_date_value);
        cityField = rootView.findViewById(R.id.text_city_value);
        temperField = rootView.findViewById(R.id.text_temper_value);
        cloudsField = rootView.findViewById(R.id.text_clouds_value);
        humidityField = rootView.findViewById(R.id.text_humidity_value);
        pressureField = rootView.findViewById(R.id.text_pressure_value);
        windField = rootView.findViewById(R.id.text_wind_value);

        return rootView;
    }

    private void getOldCityWeatherData() {
        final String city =  new CityPreference(getActivity()).getCity();
        new Thread() {
            public void run() {
                final JSONObject json = RemoteFetch.getJSON(getActivity(), city);

                handler.post(new Runnable() {
                    public void run() {
                        getCurrentDayTime(json);
                        renderWeather(json);

                    }
                });
            }
        }.start();
    }

    private void updateWeatherData(final String city) {
        new Thread() {
            public void run() {
                final JSONObject json = RemoteFetch.getJSON(getActivity(), city);
                if (json == null) {
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(),
                                    "\"" + city + "\"" + " not found.",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        public void run() {
                            getCurrentDayTime(json);
                            renderWeather(json);

                        }
                    });
                }
            }

        }.start();
    }


    private void renderWeather(JSONObject json) {
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.US) +
                    ", " + json.getJSONObject("sys").getString("country") + " |");

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            JSONObject wind = json.getJSONObject("wind");

            setWeatherIcon(details.getInt("id"), dayTime);

            weatherIcon.setImageResource(icon);
            temperField.setText(String.format("%.2f", main.getDouble("temp")) + "℃");
            cloudsField.setText(details.getString("description").toUpperCase(Locale.US) + "");
            humidityField.setText(main.getString("humidity") + "%");
            pressureField.setText(main.getString("pressure") + " hPa");
            windField.setText(wind.getString("speed") + " m/c");
            dateField.setText(new SimpleDateFormat("dd.MM").format(new Date()));

        } catch (Exception e) {
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }


    private void getCurrentDayTime(JSONObject json) {
        long sunrise = 0;
        long sunset = 0;
        try {
            sunrise = json.getJSONObject("sys").getLong("sunrise") * 1000;
        } catch (Exception e) {
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
        try {
            sunset = json.getJSONObject("sys").getLong("sunset") * 1000;
        } catch (Exception e) {
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }

        long currentTime = new Date().getTime();

        if (currentTime >= sunrise && currentTime < sunset) {
            dayTime = 0;
        } else {
            dayTime = 1;
        }
    }

    private void setWeatherIcon(int actualId, int daySun) {
        int id = actualId / 100;
        if (id == 8) {
            switch (actualId) {
                case 800:
                    if (daySun == 0) {
                        icon = R.drawable.clear_sky0;
                    } else {
                        icon = R.drawable.clear_sky1;
                    }
                    break;
                case 801:
                    if (daySun == 0) {
                        icon = R.drawable.few_clouds0;
                    } else {
                        icon = R.drawable.few_clouds1;
                    }
                    break;
                case 802:
                case 803:
                    if (daySun == 0) {
                        icon = R.drawable.clouds0;
                    } else {
                        icon = R.drawable.clouds1;
                    }
                    break;
                case 804:
                    icon = R.drawable.many_clouds;
                    break;
            }
        } else if (id == 3) {

            if (daySun == 0) {
                icon = R.drawable.drizzle0;
            } else {
                icon = R.drawable.drizzle1;
            }

        } else if (id == 5) {
            switch (actualId) {
                case 500:
                    if (daySun == 0) {
                        icon = R.drawable.showers_scattered0;
                    } else {
                        icon = R.drawable.showers_scattered1;
                    }
                    break;
                case 501:
                case 502:
                    if (daySun == 0) {
                        icon = R.drawable.showers0;
                    } else {
                        icon = R.drawable.showers1;
                    }
                    break;

                case 511:
                    icon = R.drawable.freezing_rain;
                    break;
                case 503:
                case 504:
                case 521:
                case 522:
                    icon = R.drawable.showers;
                    break;
                case 520:
                case 531:
                    icon = R.drawable.showers_scattered;
                    break;

            }

        } else if (id == 6) {
            switch (actualId) {
                case 600:
                case 601:
                case 602:
                    if (daySun == 0) {
                        icon = R.drawable.snow_scattered0;
                    } else {
                        icon = R.drawable.snow_scattered1;
                    }
                    break;
                case 611:
                case 612:
                case 615:
                case 616:
                    icon = R.drawable.snow_rain;
                    break;

                case 620:
                case 621:
                case 622:
                    icon = R.drawable.snow;
                    break;

            }
        } else if (id == 7) {

            if (actualId >= 701 && actualId <= 762) {
                if (daySun == 0) {
                    icon = R.drawable.fog0;
                } else {
                    icon = R.drawable.fog1;

                }
            } else if (actualId == 771) {
                icon = R.drawable.storm;
            } else if (actualId == 781) {

                icon = R.drawable.tornado;
            }

        } else if (id == 9) {
            switch (actualId) {
                case 900:
                    icon = R.drawable.tornado;
                    break;
                case 903:
                    icon = R.drawable.cold;
                    break;
                case 904:
                    icon = R.drawable.clear_sky0;
                    break;
                case 906:
                    icon = R.drawable.hail;
                    break;
                case 905:
                case 951:
                case 952:
                case 953:
                case 954:
                case 955:
                    if (daySun == 0) {
                        icon = R.drawable.clear_sky0;
                    } else {
                        icon = R.drawable.clear_sky1;
                    }
                    break;
                case 956:
                case 957:
                case 958:
                    if (daySun == 0) {
                        icon = R.drawable.storm0;
                    } else {
                        icon = R.drawable.storm1;
                    }
                    break;
                case 901:
                case 902:
                case 959:
                case 960:
                case 961:
                case 962:
                    icon = R.drawable.storm;
                    break;
            }
        }
    }

    public void changeCityFragm(String city) {
        updateWeatherData(city);
    }


}



