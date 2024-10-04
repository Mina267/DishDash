package com.example.dishdash.detailsrecipes.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.example.dishdash.model.Ingredients;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealMapper;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.network.MealRemoteDataSourceImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsRecipesFragment extends Fragment implements DetailsRecipesView {
    private static final String TAG = "DetailsRecipesFragment";
    private ImageView imageMeal;
    private TextView textRecipeTitle;
    private TextView textCountry;
    private TextView textRecipeDescription;
    private TextView textStepsDescription;
    private FloatingActionButton floatingActionButtonFav;
    private Button btnAddToPlan;
    private WebView webViewDetails;
    private Meal meal;
    private DetailsRecipesPresenter detailsRecipesPresenter;
    private List<Ingredients> ListIngredients  = new ArrayList<>();;
    private List<Meal> savedMeals = new ArrayList<>();
    private Map<String, Bitmap> bitmapList;


    private DetailsIngredientAdapter detailsIngredientAdapter;
    private RecyclerView recyclerViewIngredients;
    LinearLayoutManager layoutManager;
    FragmentManager mgr;

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

        /*
         * Retrieve the meal passed from the previous fragment
         */
        meal = getArguments().getParcelable("meal");

        /* Initialize views */
        imageMeal = view.findViewById(R.id.imageMeal);
        textRecipeTitle = view.findViewById(R.id.textRecipeTitle);
        textCountry = view.findViewById(R.id.textCountry);
        textRecipeDescription = view.findViewById(R.id.textRecipeDescription);
        recyclerViewIngredients = view.findViewById(R.id.recyclerViewIngredients);
        textStepsDescription = view.findViewById(R.id.textStepsDescription);
        btnAddToPlan = view.findViewById(R.id.btnAddToPlan);
        webViewDetails = view.findViewById(R.id.webViewDetails);
        floatingActionButtonFav = view.findViewById(R.id.fab_add_details);

        /* Initialize webView for youtube video */
        webViewDetails = view.findViewById(R.id.webViewDetails);
        WebSettings webSettings = webViewDetails.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewDetails.setWebViewClient(new WebViewClient());
        /* Load the YouTube video in the WebView */
        String youtubeEmbedUrl = "https://www.youtube.com/embed/" + getYoutubeVideoId(meal.getStrYoutube());
        /* loadUrl method loads the URL in the WebView */
        webViewDetails.loadUrl(youtubeEmbedUrl);




        /* get Fragment manager */
        mgr = getChildFragmentManager();

        return view;
    }

    /* get youtube video id */
    private String getYoutubeVideoId(String url) {
        String videoId = "";
        /* Extract the video ID from the URL */
        if (url != null && url.contains("v=")) {
            String[] parts = url.split("v=");
            /* Get the first part of the URL */
            videoId = parts[1].split("&")[0];
        }

        return videoId;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // Handle back button action
        setHasOptionsMenu(true);


        /* add to plan button that will navigate to select day fragment to add to plan */
        btnAddToPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                Bundle bundle = new Bundle();
                /* Pass the MealPlan object using a Bundle */
                bundle.putParcelable("meal", meal);
                navController.navigate(R.id.action_detailsRecipesFragment_to_selectDayFragment, bundle);
            }
        });

        /* meal image */
        Glide.with(getContext())
                .load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.nophotoavailable)
                        .error(R.drawable.ic_launcher_foreground))
                .into(imageMeal);

        /* meal text */
        textRecipeTitle.setText(meal.getStrMeal());
        textCountry.setText(meal.getStrArea());


        /* meal description */
        if (meal.getStrTags() == null){
            textRecipeDescription.setText(meal.getStrCategory());
        }
        else {
            textRecipeDescription.setText(meal.getStrCategory() + " - " + meal.getStrTags());
        }

        /* meal steps */
        textStepsDescription.setText(meal.getStrInstructions());

        /* Initialize presenter */
        detailsRecipesPresenter = new DetailsRecipesPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));
        /* Initialize adapter */
        detailsIngredientAdapter = new DetailsIngredientAdapter(getContext(), new ArrayList<>(), new HashMap<>());



        layoutManager = new LinearLayoutManager(  getContext(), RecyclerView.VERTICAL, false);
        detailsRecipesPresenter = new DetailsRecipesPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));


        detailsRecipesPresenter.getSavedMeals();
        /* Initialize adapter */
        recyclerViewIngredients.setLayoutManager(layoutManager);
        recyclerViewIngredients. setAdapter(detailsIngredientAdapter);

        /* Initialize ingredient list */
        detailsRecipesPresenter.getImgOfIngredient(meal);






        /*
         * Initialize floating action button
         * That will change the image of the floating action button according to the state of the meal
         * Saved or not saved to favorites, button will insert and delete mael from favorites
         */
        floatingActionButtonFav.setOnClickListener(v -> {
            for (Meal savedmeal : savedMeals) {
                Log.i(TAG, "setOnClick saved: " + savedmeal.getStrMeal());
            }
            Log.i(TAG, "meal: " + meal.getStrMeal());
            if (savedMeals.contains(meal)) {
                /* Meal is already saved, remove it from favorites */
                detailsRecipesPresenter.deleteMeal(meal);
                floatingActionButtonFav.setImageResource(R.drawable.bookmarkadd);
                Log.i(TAG, "this.savedMeals.remove(meal); ");
                Toast.makeText(getContext().getApplicationContext(), meal.getStrMeal() + " Removed from Favorites", Toast.LENGTH_SHORT).show();
                this.savedMeals.remove(meal);

            } else {
                /* Meal is not saved, add it to favorites to add to favorites */
                detailsRecipesPresenter.AddMealToFav(meal);
                floatingActionButtonFav.setImageResource(R.drawable.bookmarkadded);
                Toast.makeText(getContext().getApplicationContext(), meal.getStrMeal() + "Add to Favorite", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "this.savedMeals.add(meal); ");
                this.savedMeals.add(meal);


            }
        });






    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /* Check if the clicked item is the back arrow (home button) */
        if (item.getItemId() == android.R.id.home) {
            /* Handle back arrow press - navigate up  */
            NavController navController = Navigation.findNavController(getView());
            navController.navigateUp();
            /* Indicate that the event was handled */
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onGetImgOfIngredient(Map<String, Bitmap> ingredientBitmapMap, List<Ingredients> ListIngredients) {
        if (ingredientBitmapMap != null) {
            bitmapList = ingredientBitmapMap;
            /* set adapter with new data of ingredients and bitmaps */
            detailsIngredientAdapter.updateData(ListIngredients, bitmapList);
            detailsIngredientAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void markSavedMeals(LiveData<List<Meal>> meals) {
        Observer<List<Meal>> observer = new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealsList) {

                for (Meal meal : mealsList) {
                    Log.i(TAG, "Details onChanged: " + meal.getStrMeal());
                }
                /* Update the savedMeals list with the new data */
                /* Check of the meal is already saved or not to set the image of the floating action button accordingly */
                savedMeals.clear();
                savedMeals.addAll(mealsList);
                if (savedMeals.contains(meal)) {
                    Log.i(TAG, "onBindViewHolder: onAddFromFavoriteClick");
                    floatingActionButtonFav.setImageResource(R.drawable.bookmarkadded);
                } else {
                    Log.i(TAG, "onBindViewHolder: bookmarkadd");
                    floatingActionButtonFav.setImageResource(R.drawable.bookmarkadd);
                }

            }
        };

        meals.observe(this, observer);
    }



}
