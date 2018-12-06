package com.sdzshn3.android.demoapp.Home.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.sdzshn3.android.demoapp.Database.Profile.Profile;
import com.sdzshn3.android.demoapp.Database.Profile.ProfilesViewModel;
import com.sdzshn3.android.demoapp.Home.Fragments.HomeFragment;
import com.sdzshn3.android.demoapp.LoginActivity;
import com.sdzshn3.android.demoapp.PrefManager;
import com.sdzshn3.android.demoapp.Profiles.ProfilesFragment;
import com.sdzshn3.android.demoapp.R;
import com.sdzshn3.android.demoapp.UserDetails.UserDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    PrefManager prefManager;
    Spinner profileSwitchSpinner;
    ProfilesViewModel profilesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileSwitchSpinner = findViewById(R.id.profile_switch_spinner);
        prefManager = new PrefManager(this);

        /*profilesViewModel = ViewModelProviders.of(this).get(ProfilesViewModel.class);
        profilesViewModel.getAllProfiles().observe(this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profiles) {
                if (profiles != null) {
                    list.clear();
                    for (int i = 0; i <= profiles.size(); i++) {
                        try {
                            list.add(profiles.get(i).getName());
                        } catch (IndexOutOfBoundsException e) {
                            Log.e("Ignore", "ignore");
                        }
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                profileSwitchSpinner.setAdapter(adapter);
            }
        });*/

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            setFragment(new HomeFragment());
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_notification:
                Toast.makeText(this, "Notification toggled", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings_action:
                startActivity(new Intent(MainActivity.this, UserDetailsActivity.class));
                return true;
            case R.id.profiles_action:
                setFragment(new ProfilesFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_item_home:
                setFragment(new HomeFragment());
                break;
            case R.id.nav_item_profiles:
                setFragment(new ProfilesFragment());
                break;
            case R.id.nav_item_settings:
                startActivity(new Intent(MainActivity.this, UserDetailsActivity.class));
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_item_about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_item_logout:
                prefManager.setIsLoggedIn(false);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();
        }
        drawer.closeDrawer(GravityCompat.START);
    }
}
