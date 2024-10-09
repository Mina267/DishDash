package com.example.dishdash.foryou.view;



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

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.ViewHolder> {
    private static final String TAG = "AllMealsAdapter";
    private Context context;
    private List<Meal> mealsList;
    private OnMealClickListener listener;
    private List<Meal> savedMeals = new ArrayList<>();


    public ForYouAdapter(Context context, List<Meal> mealsList, OnMealClickListener listener) {
        this.context = context;
        this.mealsList = mealsList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_recipe, parent, false);
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
            Log.i(TAG, "onBindViewHolder: bookmarkadded ForYouAdapter");
            holder.floatingActionButtonFav.setImageResource(R.drawable.bookmarkadded); // Set the 'saved' bookmark icon
        } else {
            Log.i(TAG, "onBindViewHolder: bookmarkadd ForYouAdapter");
            holder.floatingActionButtonFav.setImageResource(R.drawable.bookmarkadd); // Set the 'add' bookmark icon
        }


        holder.floatingActionButtonFav.setOnClickListener(v -> {
            for (Meal savedmeal : savedMeals) {
                Log.i(TAG, "setOnClick saved: " + savedmeal.getStrMeal());
            }
            Log.i(TAG, "meal: " + meal.getStrMeal());
            if (savedMeals.contains(meal)) {
                /* Meal is already saved, remove it from favorites */
                listener.onRemoveFromFavoriteClick(meal);
                holder.floatingActionButtonFav.setImageResource(R.drawable.bookmarkadd);
                Log.i(TAG, "this.savedMeals.remove(meal); ");
                this.savedMeals.remove(meal);

            } else {
                /* Meal is not saved, add it to favorites to add to favorites */
                listener.onAddToFavoriteClick(meal);
                holder.floatingActionButtonFav.setImageResource(R.drawable.bookmarkadded);
                Log.i(TAG, "this.savedMeals.add(meal); ");
                this.savedMeals.add(meal);


            }
        });

        holder.floatingActionButtonMealPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main);
                Bundle bundle = new Bundle();
                bundle.putParcelable("meal", meal); // Pass the Meal object using a Bundle
                navController.navigate(R.id.action_navigation_home_to_selectDayFragment, bundle);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main);
                Bundle bundle = new Bundle();
                bundle.putParcelable("meal", meal); // Pass the Meal object using a Bundle
                navController.navigate(R.id.action_navigation_home_to_detailsRecipesFragment, bundle);



            }
        });
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    public void updateData(List<Meal> mealsList) {
        this.mealsList.clear();
        this.mealsList.addAll(mealsList);
        notifyDataSetChanged();
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
    public void clearData() {
        this.mealsList.clear();
        notifyDataSetChanged();
    }

    public void updateDataMeal(Meal meal) {
        this.mealsList.add(meal);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_card;
        private TextView txt_card;
        private FloatingActionButton floatingActionButtonFav;
        private FloatingActionButton floatingActionButtonMealPlan;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_card = itemView.findViewById(R.id.img_card);
            txt_card = itemView.findViewById(R.id.txt_card);
            floatingActionButtonFav = itemView.findViewById(R.id.fab_add_favorite);
            floatingActionButtonMealPlan = itemView.findViewById(R.id.fab_add_mealplan);



        }
    }
}
