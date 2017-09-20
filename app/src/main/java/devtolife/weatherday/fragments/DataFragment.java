package devtolife.weatherday.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private int curentTime;

    Handler handler;

    public DataFragment() {
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        updateWeatherData(new CityPreference(getActivity()).getCity());

        myLocale = new Locale("bel", "BY");
        View rootView = inflater.inflate(R.layout.data_fragment, container, false);
        dateField = (TextView) rootView.findViewById(R.id.text_date_value);
        cityField = (TextView) rootView.findViewById(R.id.text_city_value);
        temperField = (TextView) rootView.findViewById(R.id.text_temper_value);
        cloudsField = (TextView) rootView.findViewById(R.id.text_clouds_value);
        humidityField = (TextView) rootView.findViewById(R.id.text_humidity_value);
        pressureField = (TextView) rootView.findViewById(R.id.text_pressure_value);
        windField = (TextView) rootView.findViewById(R.id.text_wind_value);

        return rootView;
    }

    private void updateWeatherData(final String city) {
        new Thread() {
            public void run() {
                final JSONObject json = RemoteFetch.getJSON(getActivity(), city);
                if (json == null) {
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(),
                                    getActivity().getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        public void run() {
                            renderWeather(json);
                            getCurrentDayTime(json);

                        }
                    });
                }
            }

        }.start();
    }


    private void renderWeather(JSONObject json) {
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.US) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            JSONObject wind = json.getJSONObject("wind");

            setWeatherIcon(details.getInt("id"), dayTime);

            temperField.setText(String.format("%.2f", main.getDouble("temp")) + " ℃");

            cloudsField.setText(details.getString("description").toUpperCase(Locale.US) + "");

            humidityField.setText(main.getString("humidity") + "%");
            pressureField.setText(main.getString("pressure") + " hPa");
            windField.setText(wind.getString("speed") + " m/c");

            dateField.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));

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
        int icon = R.drawable.clear_sky0;
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
                    if (daySun == 0) {
                        icon = R.drawable.clouds0;
                    } else {
                        icon = R.drawable.clouds1;
                    }
                    break;
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
            switch (actualId) {
                case 300:
                    if (daySun == 0) {
                        icon = R.drawable.drizzle0;
                    } else {
                        icon = R.drawable.drizzle1;
                    }
                    break;
                case 301:
                    if (daySun == 0) {
                        icon = R.drawable.drizzle0;
                    } else {
                        icon = R.drawable.drizzle1;
                    }
                    break;
                case 302:
                    if (daySun == 0) {
                        icon = R.drawable.drizzle0;
                    } else {
                        icon = R.drawable.drizzle1;
                    }
                    break;
                case 310:
                    if (daySun == 0) {
                        icon = R.drawable.drizzle0;
                    } else {
                        icon = R.drawable.drizzle1;
                    }
                    break;
                case 311:
                    if (daySun == 0) {
                        icon = R.drawable.drizzle0;
                    } else {
                        icon = R.drawable.drizzle1;
                    }
                    break;
                case 312:
                    if (daySun == 0) {
                        icon = R.drawable.drizzle0;
                    } else {
                        icon = R.drawable.drizzle1;
                    }
                    break;
                case 313:
                    if (daySun == 0) {
                        icon = R.drawable.drizzle0;
                    } else {
                        icon = R.drawable.drizzle1;
                    }
                    break;
                case 314:
                    if (daySun == 0) {
                        icon = R.drawable.drizzle0;
                    } else {
                        icon = R.drawable.drizzle1;
                    }
                    break;
                case 321:
                    if (daySun == 0) {
                        icon = R.drawable.drizzle0;
                    } else {
                        icon = R.drawable.drizzle1;
                    }
                    break;
            }

//            300	light intensity drizzle	легка мряка
//            301	drizzle	мряка
//            302	heavy intensity drizzle	сильна мряка
//            310	light intensity drizzle rain	легкий дощ з мрякою
//            311	drizzle rain	дощ і мряка
//            312	heavy intensity drizzle rain	сильний дощ і мряка
//            313	shower rain and drizzle	проливний дощ і мряка
//            314	heavy shower rain and drizzle	сильний проливний дощ з мрякою
//            321	shower drizzle	проливна мряка


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
                    if (daySun == 0) {
                        icon = R.drawable.showers0;
                    } else {
                        icon = R.drawable.showers1;
                    }
                    break;
                case 502:
                    if (daySun == 0) {
                        icon = R.drawable.showers0;
                    } else {
                        icon = R.drawable.showers1;
                    }
                    break;
                case 503:
                    icon = R.drawable.showers;
                    break;
                case 504:
                    icon = R.drawable.showers;
                    break;
                case 511:
                    icon = R.drawable.freezing_rain;
                    break;
                case 520:
                    icon = R.drawable.showers_scattered;
                    break;
                case 521:
                    icon = R.drawable.showers;
                    break;
                case 522:
                    icon = R.drawable.showers;
                    break;
                case 531:
                    icon = R.drawable.showers_scattered;
                    break;

//            500	light rain	невеликий дощ
//            501	moderate rain	помірний дощ
//            502	heavy intensity rain	сильний дощ
//            503	very heavy rain	дуже сильний дощ
//            504	extreme rain	надзвичайно сильний дощ
//            511	freezing rain	дощ з обледенінням
//            520	light intensity shower rain	легкий проливний дощ
//            521	shower rain	проливний дощ
//            522	heavy intensity shower rain	сильний проливний дощ
//            531	ragged shower rain	поривчастий проливний дощ
            }

        } else if (id == 6) {
            switch (actualId) {
                case 600:
                    if (daySun == 0) {
                        icon = R.drawable.snow_scattered0;
                    } else {
                        icon = R.drawable.snow_scattered1;
                    }
                    break;
                case 601:
                    if (daySun == 0) {
                        icon = R.drawable.snow_scattered0;
                    } else {
                        icon = R.drawable.snow_scattered1;
                    }
                    break;
                case 602:
                    if (daySun == 0) {
                        icon = R.drawable.snow_scattered0;
                    } else {
                        icon = R.drawable.snow_scattered1;
                    }
                    break;
                case 611:
                    icon = R.drawable.snow_rain;
                    break;
                case 612:
                    icon = R.drawable.snow_rain;
                    break;
                case 615:
                    icon = R.drawable.snow_rain;
                    break;
                case 616:
                    icon = R.drawable.snow_rain;
                    break;
                case 620:
                    icon = R.drawable.snow;
                    break;
                case 621:
                    icon = R.drawable.snow;
                    break;
                case 622:
                    icon = R.drawable.snow;
                    break;
//                600	light snow	легкий сніг
//                601	snow	сніг
//                602	heavy snow	сильний сніг
//                611	sleet	дощ зі снігом, ожеледиця
//                612	shower sleet	проливний дощ зі снігом, ожеледиця
//                615	light rain and snow	невеликий дощ зі снігом
//                616	rain and snow	дощ зі снігом
//                620	light shower snow	легкий рясний сніг
//                621	shower snow	рясний сніг
//                622	heavy shower snow	сильний рясний сніг

            }
        } else if (id == 7) {
            switch (actualId) {
                case 701:
                    if (daySun == 0) {
                        icon = R.drawable.fog0;
                    } else {
                        icon = R.drawable.fog1;
                    }
                    break;
                case 711:
                    if (daySun == 0) {
                        icon = R.drawable.fog0;
                    } else {
                        icon = R.drawable.fog1;
                    }
                    break;
                case 721:
                    if (daySun == 0) {
                        icon = R.drawable.fog0;
                    } else {
                        icon = R.drawable.fog1;
                    }
                    break;
                case 731:
                    if (daySun == 0) {
                        icon = R.drawable.fog0;
                    } else {
                        icon = R.drawable.fog1;
                    }
                    break;
                case 741:
                    if (daySun == 0) {
                        icon = R.drawable.fog0;
                    } else {
                        icon = R.drawable.fog1;
                    }
                    break;
                case 751:
                    if (daySun == 0) {
                        icon = R.drawable.fog0;
                    } else {
                        icon = R.drawable.fog1;
                    }
                    break;
                case 761:
                    if (daySun == 0) {
                        icon = R.drawable.fog0;
                    } else {
                        icon = R.drawable.fog1;
                    }
                    break;
                case 762:
                    if (daySun == 0) {
                        icon = R.drawable.fog0;
                    } else {
                        icon = R.drawable.fog1;
                    }
                    break;
                case 771:
                    icon = R.drawable.storm;
                    break;
                case 781:
                    icon = R.drawable.tornado;
                    break;

//            701	mist	туман
//            711	smoke	дим
//            721	haze	імла
//            731	sand, dust whirls	пісок, пилові вихори
//            741	fog	туман
//            751	sand	пісок
//            761	dust	пил
//            762	volcanic ash	вулканічний попіл
//            771	squalls	шквал
//            781	tornado	торнадо

            }
        } else if (id == 9) {
            switch (actualId) {
                case 900:
                    icon = R.drawable.tornado;
                    break;
                case 901:
                    icon = R.drawable.storm;
                    break;
                case 902:
                    icon = R.drawable.storm;
                    break;
                case 903:
                    icon = R.drawable.cold;
                    break;
                case 904:
                    icon = R.drawable.clear_sky0;
                    break;
                case 905:
                    if (daySun == 0) {
                        icon = R.drawable.clear_sky0;
                    } else {
                        icon = R.drawable.clear_sky1;
                    }
                    break;
                case 906:
                    icon = R.drawable.hail;
                    break;
                case 951:
                    if (daySun == 0) {
                        icon = R.drawable.clear_sky0;
                    } else {
                        icon = R.drawable.clear_sky1;
                    }
                    break;
                case 952:
                    if (daySun == 0) {
                        icon = R.drawable.clear_sky0;
                    } else {
                        icon = R.drawable.clear_sky1;
                    }
                    break;
                case 953:
                    if (daySun == 0) {
                        icon = R.drawable.clear_sky0;
                    } else {
                        icon = R.drawable.clear_sky1;
                    }
                    break;
                case 954:
                    if (daySun == 0) {
                        icon = R.drawable.clear_sky0;
                    } else {
                        icon = R.drawable.clear_sky1;
                    }
                    break;
                case 955:
                    if (daySun == 0) {
                        icon = R.drawable.clear_sky0;
                    } else {
                        icon = R.drawable.clear_sky1;
                    }
                    break;
                case 956:
                    if (daySun == 0) {
                        icon = R.drawable.storm0;
                    } else {
                        icon = R.drawable.storm1;
                    }
                    break;
                case 957:
                    if (daySun == 0) {
                        icon = R.drawable.storm0;
                    } else {
                        icon = R.drawable.storm1;
                    }
                    break;
                case 958:
                    if (daySun == 0) {
                        icon = R.drawable.storm0;
                    } else {
                        icon = R.drawable.storm1;
                    }
                    break;
                case 959:
                    icon = R.drawable.storm;
                    break;
                case 960:
                    icon = R.drawable.storm;
                    break;
                case 961:
                    icon = R.drawable.storm;
                    break;
                case 962:
                    icon = R.drawable.storm;
                    break;

//            900	tornado	торнадо
//            901	tropical storm	тропічний шторм
//            902	hurricane	ураган
//            903	cold	холодно
//            904	hot	гаряче
//            905	windy	вітряно
//            906	hail	град
//            951	calm	штиль
//            952	light breeze	легкий бриз
//            953	gentle breeze	ніжний бриз
//            954	moderate breeze	помірний бриз
//            955	fresh breeze	свіжий бриз
//            956	strong breeze	сильний бриз
//            957	high wind, near gale	сильний вітер, майже шторм
//            958	gale	шторм
//            959	severe gale	сильний шторм
//            960	storm	шторм
//            961	violent storm	сильна буря
//            962	hurricane	ураган
            }
        }
        ImageFragment.weatherIcon.setImageResource(icon);
    }

    public void changeCity(String city) {
        updateWeatherData(city);
    }


}



