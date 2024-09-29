package com.example.dishdash.detailsrecipes.view;



import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.model.Ingredients;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsIngredientAdapter extends RecyclerView.Adapter<DetailsIngredientAdapter.ViewHolder> {
    private static final String TAG = "AllMealsAdapter";
    private Context context;
    private List<Ingredients> ListIngredients  = new ArrayList<>();;
    private Map<String, Bitmap> bitmapList;



    public DetailsIngredientAdapter(Context context, List<Ingredients> ListIngredients, Map<String, Bitmap> bitmapList) {
        this.context = context;
        this.ListIngredients = ListIngredients;
        this.bitmapList = bitmapList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_ingredient, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredients ingredient = ListIngredients.get(position);

        // Get the bitmap associated with the ingredient name from the map
        Bitmap bitmap = bitmapList.get(ingredient.getIngredientName());
        holder.imgIngredient.setImageBitmap(bitmap);



        // Set the ingredient details
        holder.txtIngredient.setText(ingredient.getIngredientMeasure() + " - " + ingredient.getIngredientName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click events if needed
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListIngredients.size();
    }

    public void updateData(List<Ingredients> ListIngredients, Map<String, Bitmap> bitmapList) {
        this.ListIngredients.clear();  // Clear the old data
        this.ListIngredients.addAll(ListIngredients);  // Add the new data
        this.bitmapList.clear();  // Clear the old data
        this.bitmapList = bitmapList;  // Add the new data
        notifyDataSetChanged();  // Notify the adapter to refresh the view
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgIngredient;
        private TextView txtIngredient;
        //private ImageView imgIngredient;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIngredient = itemView.findViewById(R.id.imgSearchCard);
            txtIngredient = itemView.findViewById(R.id.txtSearchCard);

        }
    }
}
