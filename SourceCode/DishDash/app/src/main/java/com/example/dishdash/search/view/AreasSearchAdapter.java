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
import com.example.dishdash.model.Categories;
import com.example.dishdash.model.ListAllArea;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AreasSearchAdapter extends RecyclerView.Adapter<AreasSearchAdapter.ViewHolder> {
    private static final String TAG = "AllMealsAdapter";
    private Context context;
    List<ListAllArea> areassList;
    private onSearchClickListener listener;

    public AreasSearchAdapter(Context context, List<ListAllArea> areassList, onSearchClickListener listener) {
        this.context = context;
        this.areassList = areassList;
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
        ListAllArea area = this.areassList.get(currentPosition);



        holder.txtSearchCard.setText(area.getStrArea());

        String countryName = area.getStrArea();  // This would come from your JSON

        // Get the corresponding flag drawable and set it to the ImageView
        int flagDrawableId = getFlagDrawableId(context, countryName);
        holder.imgSearchCard.setImageResource(flagDrawableId);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onAreaClickListener(countryName);

            }
        });
    }


    public int getFlagDrawableId(Context context, String countryName) {
        countryName = countryName.toLowerCase();

        /* Get the resource ID for the drawable using the country name */
        int flagId = context.getResources().getIdentifier(countryName, "drawable", context.getPackageName());

        if (flagId == 0) {
            return R.drawable.nophotoavailable;
        }
        return flagId;
    }


    @Override
    public int getItemCount() {
        return areassList.size();
    }

    public void updateData(List<ListAllArea> areass) {
        this.areassList.clear();  // Clear the old data
        this.areassList.addAll(areass);  // Add the new data
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
