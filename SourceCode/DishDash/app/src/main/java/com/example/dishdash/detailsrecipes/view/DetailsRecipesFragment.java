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

import android.telecom.Call;
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

        imageMeal = view.findViewById(R.id.imageMeal);
        textRecipeTitle = view.findViewById(R.id.textRecipeTitle);
        textCountry = view.findViewById(R.id.textCountry);
        textRecipeDescription = view.findViewById(R.id.textRecipeDescription);
        recyclerViewIngredients = view.findViewById(R.id.recyclerViewIngredients);
        textStepsDescription = view.findViewById(R.id.textStepsDescription);
        btnAddToPlan = view.findViewById(R.id.btnAddToPlan);
        webViewDetails = view.findViewById(R.id.webViewDetails);
        floatingActionButtonFav = view.findViewById(R.id.fab_add_details);

        webViewDetails = view.findViewById(R.id.webViewDetails);
        WebSettings webSettings = webViewDetails.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewDetails.setWebViewClient(new WebViewClient());

        String youtubeEmbedUrl = "https://www.youtube.com/embed/" + getYoutubeVideoId(meal.getStrYoutube());
        webViewDetails.loadUrl(youtubeEmbedUrl);




        /* get Fragment manager */
        mgr = getChildFragmentManager();

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



        // Handle back button action
        setHasOptionsMenu(true);



        btnAddToPlan = view.findViewById(R.id.btnAddToPlan);
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
        if (meal.getStrTags() == null){
            textRecipeDescription.setText(meal.getStrCategory());
        }
        else {
            textRecipeDescription.setText(meal.getStrCategory() + " - " + meal.getStrTags());
        }
        textStepsDescription.setText(meal.getStrInstructions());


        detailsRecipesPresenter = new DetailsRecipesPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));
        detailsIngredientAdapter = new DetailsIngredientAdapter(getContext(), new ArrayList<>(), new HashMap<>());



        recyclerViewIngredients = (RecyclerView) view.findViewById(R.id.recyclerViewIngredients);
        layoutManager = new LinearLayoutManager(  getContext(), RecyclerView.VERTICAL, false);


        detailsRecipesPresenter = new DetailsRecipesPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));

        recyclerViewIngredients.setLayoutManager(layoutManager);
        recyclerViewIngredients. setAdapter(detailsIngredientAdapter);

        createIngredientList();
        detailsRecipesPresenter.getImgOfIngredient(ListIngredients);




        if (savedMeals.contains(meal)) {
            Log.i(TAG, "onBindViewHolder: onAddFromFavoriteClick");
            floatingActionButtonFav.setImageResource(R.drawable.bookmarkadded); // Set the 'saved' bookmark icon
        } else {
            Log.i(TAG, "onBindViewHolder: bookmarkadd");
            floatingActionButtonFav.setImageResource(R.drawable.bookmarkadd); // Set the 'add' bookmark icon
        }


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
                this.savedMeals.remove(meal);

            } else {
                /* Meal is not saved, add it to favorites to add to favorites */
                detailsRecipesPresenter.AddMealToFav(meal);
                floatingActionButtonFav.setImageResource(R.drawable.bookmarkadded);
                Log.i(TAG, "this.savedMeals.add(meal); ");
                this.savedMeals.add(meal);


            }
        });


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
    public void onGetImgOfIngredient(Map<String, Bitmap> ingredientBitmapMap) {
        if (ingredientBitmapMap != null) {
            bitmapList = ingredientBitmapMap;
            detailsIngredientAdapter.updateData(ListIngredients, bitmapList);
            detailsIngredientAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void markSavedMeals(LiveData<List<Meal>> meals) {
        Observer<List<Meal>> observer = new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealsList) {
                Log.i(TAG, "onChanged: ");
                savedMeals = mealsList;

                meals.removeObserver(this);
            }
        };

        meals.observe(this, observer);
    }


    private void createIngredientList() {

        ListIngredients.clear();

        if (meal.getStrIngredient1() != null && !meal.getStrIngredient1().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient1(), meal.getStrMeasure1()));
        }
        if (meal.getStrIngredient2() != null && !meal.getStrIngredient2().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient2(), meal.getStrMeasure2()));
        }
        if (meal.getStrIngredient3() != null && !meal.getStrIngredient3().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient3(), meal.getStrMeasure3()));
        }
        if (meal.getStrIngredient4() != null && !meal.getStrIngredient4().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient4(), meal.getStrMeasure4()));
        }
        if (meal.getStrIngredient5() != null && !meal.getStrIngredient5().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient5(), meal.getStrMeasure5()));
        }
        if (meal.getStrIngredient6() != null && !meal.getStrIngredient6().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient6(), meal.getStrMeasure6()));
        }
        if (meal.getStrIngredient7() != null && !meal.getStrIngredient7().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient7(), meal.getStrMeasure7()));
        }
        if (meal.getStrIngredient8() != null && !meal.getStrIngredient8().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient8(), meal.getStrMeasure8()));
        }
        if (meal.getStrIngredient9() != null && !meal.getStrIngredient9().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient9(), meal.getStrMeasure9()));
        }
        if (meal.getStrIngredient10() != null && !meal.getStrIngredient10().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient10(), meal.getStrMeasure10()));
        }
        if (meal.getStrIngredient11() != null && !meal.getStrIngredient11().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient11(), meal.getStrMeasure11()));
        }
        if (meal.getStrIngredient12() != null && !meal.getStrIngredient12().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient12(), meal.getStrMeasure12()));
        }
        if (meal.getStrIngredient13() != null && !meal.getStrIngredient13().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient13(), meal.getStrMeasure13()));
        }
        if (meal.getStrIngredient14() != null && !meal.getStrIngredient14().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient14(), meal.getStrMeasure14()));
        }
        if (meal.getStrIngredient15() != null && !meal.getStrIngredient15().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient15(), meal.getStrMeasure15()));
        }
        if (meal.getStrIngredient16() != null && !meal.getStrIngredient16().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient16(), meal.getStrMeasure16()));
        }
        if (meal.getStrIngredient17() != null && !meal.getStrIngredient17().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient17(), meal.getStrMeasure17()));
        }
        if (meal.getStrIngredient18() != null && !meal.getStrIngredient18().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient18(), meal.getStrMeasure18()));
        }
        if (meal.getStrIngredient19() != null && !meal.getStrIngredient19().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient19(), meal.getStrMeasure19()));
        }
        if (meal.getStrIngredient20() != null && !meal.getStrIngredient20().isEmpty()) {
            ListIngredients.add(new Ingredients(meal.getStrIngredient20(), meal.getStrMeasure20()));
        }

        Log.i(TAG, "createIngredientList: " + ListIngredients);
    }
}
