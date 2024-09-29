package com.example.dishdash.search.view;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdash.R;
import com.example.dishdash.model.Categories;
import com.example.dishdash.model.Meal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriesSearchAdapter extends RecyclerView.Adapter<CategoriesSearchAdapter.ViewHolder> {
    private static final String TAG = "AllMealsAdapter";
    private Context context;
    List<Categories> categoriesList;
    private onSearchClickListener listener;

    public CategoriesSearchAdapter(Context context,  List<Categories> categoriesList, onSearchClickListener listener) {
        this.context = context;
        this.categoriesList = categoriesList;
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
        Categories categorie = this.categoriesList.get(currentPosition);

        Glide.with(context)
                .load(categorie.getStrCategoryThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.nophotoavailable)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.imgSearchCard);

        holder.txtSearchCard.setText(categorie.getStrCategory());





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCategoryClickListener(categorie.getStrCategory());



            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public void updateData(List<Categories> categories) {
        this.categoriesList.clear();  // Clear the old data
        this.categoriesList.addAll(categories);  // Add the new data
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
