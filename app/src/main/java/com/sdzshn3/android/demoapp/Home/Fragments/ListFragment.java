package com.sdzshn3.android.demoapp.Home.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sdzshn3.android.demoapp.Database.Item.Item;
import com.sdzshn3.android.demoapp.Database.Item.ItemsViewModel;
import com.sdzshn3.android.demoapp.Database.Profile.Profile;
import com.sdzshn3.android.demoapp.Database.Profile.ProfilesViewModel;
import com.sdzshn3.android.demoapp.Home.Activities.DetailsPageActivity;
import com.sdzshn3.android.demoapp.Home.Adapters.ItemsListAdapter;
import com.sdzshn3.android.demoapp.ItemClickSupport;
import com.sdzshn3.android.demoapp.PrefManager;
import com.sdzshn3.android.demoapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    public static final String POSITION = "title";
    private Context context;
    ItemsViewModel itemsViewModel;
    ProfilesViewModel profilesViewModel;
    private ArrayList<Item> itemList = new ArrayList<>();
    PrefManager prefManager;

    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_layout, container, false);
        context = getActivity();

        final ItemsListAdapter itemsListAdapter = new ItemsListAdapter(context);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        //recyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));
        recyclerView.setAdapter(itemsListAdapter);
        prefManager = new PrefManager(context);


        profilesViewModel = ViewModelProviders.of(this).get(ProfilesViewModel.class);
        profilesViewModel.getAllProfiles().observe(this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profiles) {
                if (prefManager.getCurrentActiveProfileId() == -1) {
                    if (profiles != null) {
                        if (profiles.get(0) != null) {
                            prefManager.setCurrentActiveProfileId(profiles.get(0).getId());
                        }
                    }
                }
            }
        });

        itemsViewModel = ViewModelProviders.of(ListFragment.this).get(ItemsViewModel.class);
        itemsViewModel.getAllItems().observe(ListFragment.this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                for (int i=1; i<=items.size(); i++) {
                    try {
                        if (items.get(i).getProfileId() == prefManager.getCurrentActiveProfileId()) {
                            itemList.add(items.get(i));
                        }
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    itemsListAdapter.submitList(itemList);
                }
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(context, DetailsPageActivity.class);
                intent.putExtra(POSITION, position);
                startActivity(intent);
            }
        });

        return rootView;
    }
    class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
