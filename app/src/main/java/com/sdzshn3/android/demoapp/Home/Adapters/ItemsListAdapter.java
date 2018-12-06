package com.sdzshn3.android.demoapp.Home.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.sdzshn3.android.demoapp.Database.FavoriteItem.FavoriteItem;
import com.sdzshn3.android.demoapp.Database.FavoriteItem.FavoriteItemViewModel;
import com.sdzshn3.android.demoapp.Database.Item.Item;
import com.sdzshn3.android.demoapp.Database.Profile.ProfilesViewModel;
import com.sdzshn3.android.demoapp.PrefManager;
import com.sdzshn3.android.demoapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ItemsListAdapter extends ListAdapter<Item, ItemsListAdapter.ViewHolder> {

    private Context context;
    FavoriteItemViewModel favoriteItemViewModel;
    PrefManager prefManager;

    public ItemsListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getSubtitle1().equals(newItem.getSubtitle1()) &&
                    oldItem.getSubtitle2().equals(newItem.getSubtitle2()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getNote().equals(newItem.getNote());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Item currentItem = getItem(i);

        viewHolder.titleText.setText(currentItem.getTitle());
        viewHolder.subtitle1Text.setText(currentItem.getSubtitle1());
        viewHolder.subtitle2Text.setText(currentItem.getSubtitle2());
        viewHolder.noteText.setText(currentItem.getNote());

        if (viewHolder.toggleButton.isChecked()) {
            viewHolder.toggleButton.setBackground(viewHolder.fullStar);
        }

        if (!viewHolder.toggleButton.isChecked()) {
            viewHolder.toggleButton.setBackground(viewHolder.star);
        }

        favoriteItemViewModel = ViewModelProviders.of((AppCompatActivity) context).get(FavoriteItemViewModel.class);
        viewHolder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    viewHolder.toggleButton.setBackground(viewHolder.fullStar);
                    FavoriteItem favoriteItem = new FavoriteItem(2, SimpleDateFormat.getDateInstance().format(new Date()));
                    Toast.makeText(context, "" + SimpleDateFormat.getDateInstance().format(new Date()), Toast.LENGTH_SHORT).show();
                    favoriteItemViewModel.insert(favoriteItem);
                }
                if (!isChecked) {
                    viewHolder.toggleButton.setBackground(viewHolder.star);
                }
            }
        });
    }

    public Item getItemAt(int position) {
        return getItem(position);
    }

    /*@Override
    public int getItemCount() {
        return Objects.requireNonNull(favoriteItemViewModel.getAllFavoriteItems().getValue()).size();
    }*/

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView subtitle1Text;
        TextView subtitle2Text;
        TextView noteText;
        ToggleButton toggleButton;
        Drawable fullStar = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_star_full, null);
        Drawable star = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_star,null);

        ViewHolder(View listItemView) {
            super(listItemView);
            titleText = listItemView.findViewById(R.id.title_text);
            subtitle1Text = listItemView.findViewById(R.id.subtitle_1);
            subtitle2Text = listItemView.findViewById(R.id.subtitle_2);
            noteText = listItemView.findViewById(R.id.note_text);
            toggleButton = listItemView.findViewById(R.id.favorite_button);
        }
    }
}
