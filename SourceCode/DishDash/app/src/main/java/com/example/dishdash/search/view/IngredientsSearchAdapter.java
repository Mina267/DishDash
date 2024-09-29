package com.example.dishdash.search.view;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdash.R;
import com.example.dishdash.model.ListAllIngredient;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IngredientsSearchAdapter extends RecyclerView.Adapter<IngredientsSearchAdapter.ViewHolder> {
    private static final String TAG = "AllMealsAdapter";
    private Context context;
    List<ListAllIngredient> ingredientsList;
    private onSearchClickListener listener;

    public IngredientsSearchAdapter(Context context, List<ListAllIngredient> ingredientsList, onSearchClickListener listener) {
        this.context = context;
        this.ingredientsList = ingredientsList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_search, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int currentPosition = holder.getAdapterPosition();
        ListAllIngredient ingredient = this.ingredientsList.get(currentPosition);

        String ingredientName = ingredient.getStrIngredient();
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredientName + "-Small.png";

        Glide.with(context)
                .load(imageUrl)
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.nophotoavailable)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.imgSearchCard);


        holder.txtSearchCard.setText(ingredient.getStrIngredient());





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onIngredientClickListener(ingredient.getStrIngredient());



            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public void updateData(List<ListAllIngredient> listAllIngredient) {
        this.ingredientsList.clear();  // Clear the old data
        this.ingredientsList.addAll(listAllIngredient);  // Add the new data
        notifyDataSetChanged();  // Notify the adapter to refresh the view
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgSearchCard;
        private TextView txtSearchCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSearchCard = itemView.findViewById(R.id.imgSearchCard);
            txtSearchCard = itemView.findViewById(R.id.txtSearchCard);


        }
    }
}
