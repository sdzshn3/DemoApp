package com.sdzshn3.android.demoapp.Home.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sdzshn3.android.demoapp.Database.Item.ItemsRepository;
import com.sdzshn3.android.demoapp.Database.Item.ItemsViewModel;
import com.sdzshn3.android.demoapp.Database.Profile.Profile;
import com.sdzshn3.android.demoapp.Database.Profile.ProfilesViewModel;
import com.sdzshn3.android.demoapp.PrefManager;
import com.sdzshn3.android.demoapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ProfilesViewModel profilesViewModel;
    private ArrayList<String> spinnerList;
    private List<Profile> profileList;
    private Context context;
    private FloatingActionButton switchUserFabButton;
    ItemsViewModel itemsViewModel;
    private String profile;
    PrefManager prefManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_and_viewpager_layout, container, false);

        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SliderAdapter(getChildFragmentManager()));
        tabLayout = view.findViewById(R.id.sliding_tabs);
        switchUserFabButton = view.findViewById(R.id.switch_user_fab_button);
        context = getActivity();
        itemsViewModel = ViewModelProviders.of(this).get(ItemsViewModel.class);
        prefManager = new PrefManager(context);
        spinnerList = new ArrayList<>();
        getActivity().setTitle("Home");
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        profilesViewModel = ViewModelProviders.of(this).get(ProfilesViewModel.class);

        switchUserFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = getLayoutInflater();
                View dialogView = layoutInflater.inflate(R.layout.spinner_layout, null);
                builder.setView(dialogView);

                final Spinner spinner = dialogView.findViewById(R.id.profile_switch_spinner_dialog);

                profilesViewModel.getAllProfiles().observe(HomeFragment.this, new Observer<List<Profile>>() {
                    @Override
                    public void onChanged(List<Profile> profiles) {
                        if (profiles != null) {
                            profileList = profiles;
                            spinnerList.clear();
                            for (int i = 0; i <= profiles.size(); i++) {
                                try {
                                    spinnerList.add(profiles.get(i).getName());
                                } catch (IndexOutOfBoundsException e) {
                                    Log.e("Ignore", "ignore");
                                }
                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, spinnerList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                    }
                });

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        profile = spinnerList.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                builder.setTitle("Switch Profile");
                builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0; i<profileList.size(); i++) {
                            if (profile.equals(profileList.get(i).getName())) {
                                prefManager.setCurrentActiveProfileId(profileList.get(i).getId());
                                itemsViewModel.refreshData();
                            }
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });



        return view;
    }

    private class SliderAdapter extends FragmentPagerAdapter {
        final String tabs[] = {"LIST", "MAP"};

        public SliderAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new ListFragment();
                case 1:
                    return new MapFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}
