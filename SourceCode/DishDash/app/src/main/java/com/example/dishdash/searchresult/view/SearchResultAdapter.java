package com.example.dishdash.searchresult.view;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdash.R;
import com.example.dishdash.model.Meal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private static final String TAG = "AllMealsAdapter";
    private Context context;
    private List<Meal> mealsList;
    private OnSearchResultClickListener listener;
    private List<Meal> savedMeals = new ArrayList<>();

    public SearchResultAdapter(Context context, List<Meal> mealsList, OnSearchResultClickListener listener) {
        this.context = context;
        this.mealsList = mealsList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_result, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int currentPosition = holder.getAdapterPosition();
        Meal meal = mealsList.get(currentPosition);

        Glide.with(context)
                .load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.nophotoavailable)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img_card);
        holder.txt_card.setText(meal.getStrMeal());




        if (savedMeals.contains(meal)) {
            Log.i(TAG, "onBindViewHolder: onAddFromFavoriteClick");
            holder.floatingActionButton.setImageResource(R.drawable.bookmarkadded);
        } else {
            Log.i(TAG, "onBindViewHolder: bookmarkadd");
            holder.floatingActionButton.setImageResource(R.drawable.bookmarkadd);
        }


        holder.floatingActionButton.setOnClickListener(v -> {
            for (Meal savedmeal : savedMeals) {
                Log.i(TAG, "setOnClick saved: " + savedmeal.getStrMeal());
            }
            Log.i(TAG, "meal: " + meal.getStrMeal());
            if (savedMeals.contains(meal)) {
                /* Meal is already saved, remove it from favorites */
                listener.onRemoveFromFavoriteClick(meal);
                holder.floatingActionButton.setImageResource(R.drawable.bookmarkadd);
                Log.i(TAG, "this.savedMeals.remove(meal); ");
                this.savedMeals.remove(meal);

            } else {
                /* Meal is not saved, add it to favorites to add to favorites */
                listener.onAddToFavoriteClick(meal);
                holder.floatingActionButton.setImageResource(R.drawable.bookmarkadded);
                Log.i(TAG, "this.savedMeals.add(meal); ");
                this.savedMeals.add(meal);


            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main);
                Bundle bundle = new Bundle();
                bundle.putParcelable("meal", meal);
                navController.navigate(R.id.action_searchResultFragment_to_detailsRecipesFragment, bundle);



            }
        });
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    public void updateData(List<Meal> mealsList) {
        this.mealsList.clear();  // Clear the old data
        this.mealsList.addAll(mealsList);  // Add the new data
        notifyDataSetChanged();  // Notify the adapter to refresh the view
    }

    public void setSavedMeals(List<Meal> savedMeals) {
        Log.i(TAG, "setSavedMeals: ");
        for (Meal meal : savedMeals) {
            Log.i(TAG, "setSavedMeals: " + meal.getStrMeal());
        }
        this.savedMeals.clear();
        this.savedMeals.addAll(savedMeals);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_card;
        private TextView txt_card;
        private FloatingActionButton floatingActionButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_card = itemView.findViewById(R.id.img_card);
            txt_card = itemView.findViewById(R.id.txt_card);
            floatingActionButton = itemView.findViewById(R.id.fab_add_favorite);
        }
    }
}
