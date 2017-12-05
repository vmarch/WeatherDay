package devtolife.weatherday.privacy_policy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import devtolife.weatherday.R;


public class PrivacyPolicy extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy);

        TextView privacyText = findViewById(R.id.textViewPrivacy);

        Button btn_open = findViewById(R.id.btn_privacy);
        btn_open.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://vmarch.github.io/privacy_policy_flashlight.html"));
        startActivity(intent);
    }
}
