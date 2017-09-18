package devtolife.weatherday.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import devtolife.weatherday.R;

public class ImageFragment extends Fragment {
    static ImageView weatherIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewHierarchy = inflater.inflate(R.layout.image_fragment, container, false);
        weatherIcon = (ImageView) viewHierarchy.findViewById(R.id.imageWeather);
        return viewHierarchy;
    }
}
