package com.example.dishdash.foryou.view;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.detailsrecipes.view.DetailsRecipesFragment;
import com.example.dishdash.model.Meal;

import java.util.List;

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.ViewHolder> {
    private static final String TAG = "AllMealsAdapter";
    private Context context;
    private List<Meal> mealsList;
    private OnMealClickListener listener;
    private OnItemClickListener onItemClickListener;

    public ForYouAdapter(Context context, List<Meal> mealsList, OnMealClickListener listener, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.mealsList = mealsList;
        this.listener = listener;
        this.onItemClickListener = onItemClickListener;
    }

    // Define an interface for the click listener
    public interface OnItemClickListener {
        void onItemClick(Meal meal, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    Log.i(TAG, "onClick: "  + mealsList.get(currentPosition).getStrMeal());
                    onItemClickListener.onItemClick(meal, currentPosition);
                }

                DetailsRecipesFragment detailsRecipesFragment = new DetailsRecipesFragment();

                ((MainActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, detailsRecipesFragment)
                .addToBackStack(null).commit();

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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_card = itemView.findViewById(R.id.img_card);
            txt_card = itemView.findViewById(R.id.txt_card);

        }
    }
}
