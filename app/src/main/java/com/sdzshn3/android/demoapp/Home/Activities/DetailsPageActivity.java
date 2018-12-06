package com.sdzshn3.android.demoapp.Home.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sdzshn3.android.demoapp.Home.Fragments.ListFragment;
import com.sdzshn3.android.demoapp.R;
import com.sdzshn3.android.demoapp.UserDetails.UserDetailsActivity;

public class DetailsPageActivity extends AppCompatActivity {

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        setTitle("Detail Page");

        Intent intent = getIntent();
        position = intent.getIntExtra(ListFragment.POSITION, -1);

        TextView titleText = findViewById(R.id.title_text);
        TextView subtitleText = findViewById(R.id.subtitle_text);
        TextView descriptionText = findViewById(R.id.description_text);
        Button button = findViewById(R.id.button);

        titleText.setText("Tile");
        subtitleText.setText("Subtitle");
        descriptionText.setText("Description");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsPageActivity.this, "Ouch!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                finish();
                return true;
            case R.id.settings_action:
                startActivity(new Intent(DetailsPageActivity.this, UserDetailsActivity.class));
                return true;
            case R.id.profiles_action:
                //startActivity(new Intent(DetailsPageActivity.this, ProfilesActivity.class));
                //finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
