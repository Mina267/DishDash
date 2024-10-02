package com.example.dishdash.mealplan.view;



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
import com.example.dishdash.model.MealMapper;
import com.example.dishdash.model.MealPlan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder> {
    private static final String TAG = "AllMealsAdapter";
    private Context context;
    private List<MealPlan> mealsList;
    private OnMealPlanClickListener listener;

    public DaysAdapter(Context context, List<MealPlan> mealsList, OnMealPlanClickListener listener) {
        this.context = context;
        this.mealsList = mealsList;
        this.listener = listener;
    }

    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_mealplan, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int currentPosition = holder.getAdapterPosition();
        MealPlan meal = mealsList.get(currentPosition);

        Glide.with(context)
                .load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.nophotoavailable)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img_card_mealplan);
        holder.txt_card_mealplan.setText(meal.getStrMeal());


        holder.mealplan_remove_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMealPlanClick(meal);
            }
        });
        

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main);
                Bundle bundle = new Bundle();
                /* Pass the MealPlan object using a Bundle */
                Meal detailMeal = MealMapper.mapMealPlanToMeal(meal);
                bundle.putParcelable("meal", detailMeal);
                navController.navigate(R.id.action_navigation_mealplan_to_detailsRecipesFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    public void updateData(List<MealPlan> mealsList) {
        this.mealsList.clear();  // Clear the old data
        this.mealsList.addAll(mealsList);  // Add the new data
        notifyDataSetChanged();  // Notify the adapter to refresh the view
        Log.i(TAG, "updateData: " + mealsList);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_card_mealplan;
        private TextView txt_card_mealplan;
        private FloatingActionButton mealplan_remove_favorite;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_card_mealplan = itemView.findViewById(R.id.img_card_mealplan);
            txt_card_mealplan = itemView.findViewById(R.id.txt_card_mealplan);
            mealplan_remove_favorite = itemView.findViewById(R.id.mealplan_remove_favorite);
        }
    }
}
