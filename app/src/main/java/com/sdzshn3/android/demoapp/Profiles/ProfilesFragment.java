package com.sdzshn3.android.demoapp.Profiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sdzshn3.android.demoapp.Database.Profile.Profile;
import com.sdzshn3.android.demoapp.Database.Profile.ProfilesViewModel;
import com.sdzshn3.android.demoapp.ItemClickSupport;
import com.sdzshn3.android.demoapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfilesFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton button;
    private ProfilesViewModel profilesViewModel;
    List<Profile> profiles;
    public static final String POSITION = "position";
    public static final String IS_EDIT = "is_edit";

    public ProfilesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profiles, container, false);

        final ProfilesAdapter adapter = new ProfilesAdapter();
        recyclerView = view.findViewById(R.id.recycler_view_profiles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        profilesViewModel = ViewModelProviders.of(this).get(ProfilesViewModel.class);
        profilesViewModel.getAllProfiles().observe(this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profiles) {
                adapter.submitList(profiles);
                ProfilesFragment.this.profiles = profiles;
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), ProfileDetailsActivity.class);
                intent.putExtra(IS_EDIT, true);
                intent.putExtra(POSITION, position);
                startActivity(intent);
            }
        });

        button = view.findViewById(R.id.add_new_profile_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileDetailsActivity.class);
                intent.putExtra(IS_EDIT, false);
                startActivity(intent);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                profilesViewModel.delete(adapter.getProfileAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);
        return view;
    }
}
