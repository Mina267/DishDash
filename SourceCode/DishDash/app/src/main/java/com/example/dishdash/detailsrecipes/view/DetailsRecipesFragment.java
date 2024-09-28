package com.example.dishdash.detailsrecipes.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdash.R;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.detailsrecipes.presenter.DetailsRecipesPresenter;
import com.example.dishdash.detailsrecipes.presenter.DetailsRecipesPresenterImpl;
import com.example.dishdash.favrecipes.view.FavRecipesView;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.network.MealRemoteDataSourceImpl;

public class DetailsRecipesFragment extends Fragment implements DetailsRecipesView {

    private ImageView imageMeal;
    private TextView textRecipeTitle;
    private TextView textCountry;
    private TextView textRecipeDescription;
    private TextView textStepsDescription;
    private RecyclerView recyclerViewIngredients;
    private Button btnAddToPlan;
    private Button btnAddToFavorites;
    private WebView webViewDetails;
    private Meal meal;
    private DetailsRecipesPresenter detailsRecipesPresenter;


    private ImageView imageViewtest;

    public DetailsRecipesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_recipes, container, false);

        // Retrieve the meal passed from the previous fragment
        meal = getArguments().getParcelable("meal");

        btnAddToFavorites = view.findViewById(R.id.btnAddToFavorites);
        imageMeal = view.findViewById(R.id.imageMeal);
        textRecipeTitle = view.findViewById(R.id.textRecipeTitle);
        textCountry = view.findViewById(R.id.textCountry);
        textRecipeDescription = view.findViewById(R.id.textRecipeDescription);
        recyclerViewIngredients = view.findViewById(R.id.recyclerViewIngredients);
        textStepsDescription = view.findViewById(R.id.textStepsDescription);
        btnAddToPlan = view.findViewById(R.id.btnAddToPlan);
        btnAddToFavorites = view.findViewById(R.id.btnAddToFavorites);
        webViewDetails = view.findViewById(R.id.webViewDetails);

        imageViewtest = view.findViewById(R.id.imageViewtest);

        webViewDetails = view.findViewById(R.id.webViewDetails);
        WebSettings webSettings = webViewDetails.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewDetails.setWebViewClient(new WebViewClient());

        String youtubeEmbedUrl = "https://www.youtube.com/embed/" + getYoutubeVideoId(meal.getStrYoutube());
        webViewDetails.loadUrl(youtubeEmbedUrl);


        btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCiclkDetailsRecipesAddToFav(meal);
            }
        });

        return view;
    }

    private String getYoutubeVideoId(String url) {
        String videoId = "";
        if (url != null && url.contains("v=")) {
            String[] parts = url.split("v=");
            videoId = parts[1].split("&")[0];
        }
        return videoId;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Enable back arrow in the action bar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Handle back button action
        setHasOptionsMenu(true);

        // Load meal image with Glide
        Glide.with(getContext())
                .load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.nophotoavailable)
                        .error(R.drawable.ic_launcher_foreground))
                .into(imageMeal);

        // Set text values from the meal object
        textRecipeTitle.setText(meal.getStrMeal());
        textCountry.setText(meal.getStrArea());
        textRecipeDescription.setText(meal.getStrCategory() + " - " + meal.getStrTags());
        textStepsDescription.setText(meal.getStrInstructions());


        detailsRecipesPresenter = new DetailsRecipesPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));

        detailsRecipesPresenter.getImgOfIngredient("Lime");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Check if the clicked item is the back arrow (home button)
        if (item.getItemId() == android.R.id.home) {
            // Handle back arrow press - navigate up
            NavController navController = Navigation.findNavController(getView());
            navController.navigateUp();
            return true; // Indicate that the event was handled
        }
        return super.onOptionsItemSelected(item); // Let other menu items be handled normally
    }



    @Override
    public void onCiclkDetailsRecipesAddToFav(Meal meal) {
        detailsRecipesPresenter.AddMealToFav(meal);
    }

    @Override
    public void onGetImgOfIngredient(Bitmap bitmap) {
        imageViewtest.setImageBitmap(bitmap);
    }
}
