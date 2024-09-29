package com.example.dishdash.searchresult.view;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private static final String TAG = "AllMealsAdapter";
    private Context context;
    private List<Meal> mealsList;
    private OnSearchResultClickListener listener;

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

        holder.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMealMarkClick(meal);
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main);
                Bundle bundle = new Bundle();
                bundle.putParcelable("meal", meal); // Pass the Meal object using a Bundle
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
