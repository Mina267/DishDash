package com.example.dishdash.ingredients.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.dishdash.R;
import com.example.dishdash.db.MealLocalDataSourceImpl;
import com.example.dishdash.db.MealPlanLocalDataSourceImpl;
import com.example.dishdash.ingredients.presenter.IngredientsPresenter;
import com.example.dishdash.ingredients.presenter.IngredientsPresenterImpl;
import com.example.dishdash.model.ListAllIngredient;
import com.example.dishdash.model.Meal;
import com.example.dishdash.model.MealRepositoryImpl;
import com.example.dishdash.network.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IngredientsFragment extends Fragment implements IngredientsMealsView, onIngredientsClickListener {
    private static final String TAG = "IngredientsFragment";
    RecyclerView recyclerViewIngredients;
    IngredientsAdapter ingredientsAdapter;
    GridLayoutManager ingredientsLayoutManager;
    IngredientsPresenter ingredientsPresenter;
    FragmentManager mgr;
    AutoCompleteTextView autoCompleteTextView;

    public IngredientsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        setHasOptionsMenu(true);

        /* get Fragment manager */
        mgr = getChildFragmentManager();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ingredientsPresenter = new IngredientsPresenterImpl(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext()), MealPlanLocalDataSourceImpl.getInstance(getContext()) ));
        ingredientsAdapter = new IngredientsAdapter(getContext(), new ArrayList<>(), this);
        recyclerViewIngredients = view.findViewById(R.id.recyclerViewIngredientsList);
        ingredientsLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerViewIngredients.setLayoutManager(ingredientsLayoutManager);
        recyclerViewIngredients.setAdapter(ingredientsAdapter);
        ingredientsPresenter.getMealIngredients();


        autoCompleteTextView = view.findViewById(R.id.autoCompleteSearch);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, ingredientsNames);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Retrieve the selected item as a String (assuming it's a String)
                String selectedIngredient = (String) adapterView.getItemAtPosition(i);

                // Pass the selected ingredient to the presenter
                ingredientsPresenter.getMealByIngredient(selectedIngredient);
            }
        });

        autoCompleteTextView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (autoCompleteTextView.getRight() - autoCompleteTextView.getCompoundDrawables()[2].getBounds().width())) {
                    autoCompleteTextView.setText("");
                    return true;
                }
            }
            return false;
        });

        


    }
    
    

    @Override
    public void showAllIngredients(List<ListAllIngredient> ingredientList) {
        for (int i = 0; i < ingredientList.size(); i++) {
            Log.i(TAG, "\"" + ingredientList.get(i).getStrIngredient() + "\",");
        }
        ingredientsAdapter.updateData(ingredientList);

    }

    @Override
    public void showSearchResult(List<Meal> meal) {
        Log.i(TAG, "showSearchResult: ");
        NavController navController = Navigation.findNavController((Activity) getActivity(), R.id.nav_host_fragment_activity_main);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("meals", new ArrayList<>(meal));
        navController.navigate(R.id.action_ingredientsFragment_to_searchResultFragment, bundle);
    }

    @Override
    public void onIngredientClickListener(String ingredient) {
        ingredientsPresenter.getMealByIngredient(ingredient);

    }

    private String[] ingredientsNames = {"Chicken",
            "Salmon",
            "Beef",
            "Pork",
            "Avocado",
            "Apple Cider Vinegar",
            "Asparagus",
            "Aubergine",
            "Baby Plum Tomatoes",
            "Bacon",
            "Baking Powder",
            "Balsamic Vinegar",
            "Basil",
            "Basil Leaves",
            "Basmati Rice",
            "Bay Leaf",
            "Bay Leaves",
            "Beef Brisket",
            "Beef Fillet",
            "Beef Gravy",
            "Beef Stock",
            "Bicarbonate Of Soda",
            "Biryani Masala",
            "Black Pepper",
            "Black Treacle",
            "Borlotti Beans",
            "Bowtie Pasta",
            "Bramley Apples",
            "Brandy",
            "Bread",
            "Breadcrumbs",
            "Broccoli",
            "Brown Lentils",
            "Brown Rice",
            "Brown Sugar",
            "Butter",
            "Cacao",
            "Cajun",
            "Canned Tomatoes",
            "Cannellini Beans",
            "Cardamom",
            "Carrots",
            "Cashew Nuts",
            "Cashews",
            "Caster Sugar",
            "Cayenne Pepper",
            "Celeriac",
            "Celery",
            "Celery Salt",
            "Challots",
            "Charlotte Potatoes",
            "Cheddar Cheese",
            "Cheese",
            "Cheese Curds",
            "Cherry Tomatoes",
            "Chestnut Mushroom",
            "Chicken Breast",
            "Chicken Breasts",
            "Chicken Legs",
            "Chicken Stock",
            "Chicken Thighs",
            "Chickpeas",
            "Chili Powder",
            "Chilled Butter",
            "Chilli",
            "Chilli Powder",
            "Chinese Broccoli",
            "Chocolate Chips",
            "Chopped Onion",
            "Chopped Parsley",
            "Chopped Tomatoes",
            "Chorizo",
            "Christmas Pudding",
            "Cilantro",
            "Cinnamon",
            "Cinnamon Stick",
            "Cloves",
            "Coco Sugar",
            "Cocoa",
            "Coconut Cream",
            "Coconut Milk",
            "Colby Jack Cheese",
            "Cold Water",
            "Condensed Milk",
            "Coriander",
            "Coriander Leaves",
            "Coriander Seeds",
            "Corn Tortillas",
            "Cornstarch",
            "Cream",
            "Creme Fraiche",
            "Cubed Feta Cheese",
            "Cucumber",
            "Cumin",
            "Cumin Seeds",
            "Curry Powder",
            "Dark Brown Sugar",
            "Dark Soft Brown Sugar",
            "Dark Soy Sauce",
            "Demerara Sugar",
            "Diced Tomatoes",
            "Digestive Biscuits",
            "Dill",
            "Doner Meat",
            "Double Cream",
            "Dried Oregano",
            "Dry White Wine",
            "Egg Plants",
            "Egg Rolls",
            "Egg White",
            "Egg Yolks",
            "Eggs",
            "Enchilada Sauce",
            "English Mustard",
            "Extra Virgin Olive Oil",
            "Fajita Seasoning",
            "Farfalle",
            "Fennel Bulb",
            "Fennel Seeds",
            "Fenugreek",
            "Feta",
            "Fish Sauce",
            "Flaked Almonds",
            "Flax Eggs",
            "Flour",
            "Flour Tortilla",
            "Floury Potatoes",
            "Free-range Egg, Beaten",
            "Free-range Eggs, Beaten",
            "French Lentils",
            "Fresh Basil",
            "Fresh Thyme",
            "Freshly Chopped Parsley",
            "Fries",
            "Full Fat Yogurt",
            "Garam Masala",
            "Garlic",
            "Garlic Clove",
            "Garlic Powder",
            "Garlic Sauce",
            "Ghee",
            "Ginger",
            "Ginger Cordial",
            "Ginger Garlic Paste",
            "Ginger Paste",
            "Golden Syrup",
            "Gouda Cheese",
            "Granulated Sugar",
            "Grape Tomatoes",
            "Greek Yogurt",
            "Green Beans",
            "Green Chilli",
            "Green Olives",
            "Green Red Lentils",
            "Green Salsa",
            "Ground Almonds",
            "Ground Cumin",
            "Ground Ginger",
            "GruyÃ¨re",
            "Hard Taco Shells",
            "Harissa Spice",
            "Heavy Cream",
            "Honey",
            "Horseradish",
            "Hot Beef Stock",
            "Hotsauce",
            "Ice Cream",
            "Italian Fennel Sausages",
            "Italian Seasoning",
            "Jalapeno",
            "Jasmine Rice",
            "Jerusalem Artichokes",
            "Kale",
            "Khus Khus",
            "King Prawns",
            "Kosher Salt",
            "Lamb",
            "Lamb Loin Chops",
            "Lamb Mince",
            "Lasagne Sheets",
            "Lean Minced Beef",
            "Leek",
            "Lemon",
            "Lemon Juice",
            "Lemon Zest",
            "Lemons",
            "Lettuce",
            "Lime",
            "Little Gem Lettuce",
            "Macaroni",
            "Mackerel",
            "Madras Paste",
            "Marjoram",
            "Massaman Curry Paste",
            "Medjool Dates",
            "Meringue Nests",
            "Milk",
            "Minced Garlic",
            "Miniature Marshmallows",
            "Mint",
            "Monterey Jack Cheese",
            "Mozzarella Balls",
            "Muscovado Sugar",
            "Mushrooms",
            "Mustard",
            "Mustard Powder",
            "Mustard Seeds",
            "Nutmeg",
            "Oil",
            "Olive Oil",
            "Onion Salt",
            "Onions",
            "Orange",
            "Orange Zest",
            "Oregano",
            "Oyster Sauce",
            "Paprika",
            "Parma Ham",
            "Parmesan",
            "Parmesan Cheese",
            "Parmigiano-reggiano",
            "Parsley",
            "Peanut Butter",
            "Peanut Oil",
            "Peanuts",
            "Peas",
            "Pecorino",
            "Penne Rigate",
            "Pepper",
            "Pine Nuts",
            "Pitted Black Olives",
            "Plain Chocolate",
            "Plain Flour",
            "Plum Tomatoes",
            "Potato Starch",
            "Potatoes",
            "Prawns",
            "Puff Pastry",
            "Raspberry Jam",
            "Raw King Prawns",
            "Red Chilli Flakes",
            "Red Chilli",
            "Red Chilli Powder",
            "Red Onions",
            "Red Pepper",
            "Red Pepper Flakes",
            "Red Wine",
            "Refried Beans",
            "Rice",
            "Rice Noodles",
            "Rice Stick Noodles",
            "Rice Vermicelli",
            "Rigatoni",
            "Rocket",
            "Rolled Oats",
            "Saffron",
            "Sage",
            "Sake",
            "Salsa",
            "Salt",
            "Salted Butter",
            "Sausages",
            "Sea Salt",
            "Self-raising Flour",
            "Semi-skimmed Milk",
            "Sesame Seed",
            "Shallots",
            "Shredded Mexican Cheese",
            "Shredded Monterey Jack Cheese",
            "Small Potatoes",
            "Smoked Paprika",
            "Smoky Paprika",
            "Sour Cream",
            "Soy Sauce",
            "Soya Milk",
            "Spaghetti",
            "Spinach",
            "Spring Onions",
            "Squash",
            "Stir-fry Vegetables",
            "Strawberries",
            "Sugar",
            "Sultanas",
            "Sunflower Oil",
            "Tamarind Ball",
            "Tamarind Paste",
            "Thai Fish Sauce",
            "Thai Green Curry Paste",
            "Thai Red Curry Paste",
            "Thyme",
            "Tomato Ketchup",
            "Tomato Puree",
            "Tomatoes",
            "Toor Dal",
            "Tuna",
            "Turmeric",
            "Turmeric Powder",
            "Turnips",
            "Vanilla",
            "Vanilla Extract",
            "Veal",
            "Vegan Butter",
            "Vegetable Oil",
            "Vegetable Stock",
            "Vegetable Stock Cube",
            "Vinaigrette Dressing",
            "Vine Leaves",
            "Vinegar",
            "Water",
            "White Chocolate Chips",
            "White Fish",
            "White Fish Fillets",
            "White Vinegar",
            "White Wine",
            "Whole Milk",
            "Whole Wheat",
            "Wholegrain Bread",
            "Worcestershire Sauce",
            "Yogurt",
            "Zucchini",
            "Pretzels",
            "Cream Cheese",
            "Icing Sugar",
            "Toffee Popcorn",
            "Caramel",
            "Caramel Sauce",
            "Tagliatelle",
            "Fettuccine",
            "Clotted Cream",
            "Corn Flour",
            "Mussels",
            "Fideo",
            "Monkfish",
            "Vermicelli Pasta",
            "Baby Squid",
            "Squid",
            "Fish Stock",
            "Pilchards",
            "Black Olives",
            "Onion",
            "Tomato",
            "Duck",
            "Duck Legs",
            "Chicken Stock Cube",
            "Pappardelle Pasta",
            "Paccheri Pasta",
            "Linguine Pasta",
            "Sugar Snap Peas",
            "Crusty Bread",
            "Fromage Frais",
            "Clams",
            "Passata",
            "Rapeseed Oil",
            "Stilton Cheese",
            "Lamb Leg",
            "Lamb Shoulder",
            "Apricot",
            "Butternut Squash",
            "Couscous",
            "Minced Beef",
            "Turkey Mince",
            "Barbeque Sauce",
            "Sweetcorn",
            "Goose Fat",
            "Duck Fat",
            "Fennel",
            "Red Wine Vinegar",
            "Haricot Beans",
            "Rosemary",
            "Butter Beans",
            "Pinto Beans",
            "Tomato Sauce",
            "Mascarpone",
            "Mozzarella",
            "Ricotta",
            "Dried Apricots",
            "Capers",
            "Banana",
            "Raspberries",
            "Blueberries",
            "Walnuts",
            "Pecan Nuts",
            "Maple Syrup",
            "Light Brown Soft Sugar",
            "Dark Brown Soft Sugar",
            "Dark Chocolate Chips",
            "Milk Chocolate",
            "Dark Chocolate",
            "Pumpkin",
            "Shortcrust Pastry",
            "Peanut Cookies",
            "Gelatine Leafs",
            "Peanut Brittle",
            "Peaches",
            "Yellow Pepper",
            "Green Pepper",
            "Courgettes",
            "Tinned Tomatos",
            "Chestnuts",
            "Wild Mushrooms",
            "Truffle Oil",
            "Paneer",
            "Naan Bread",
            "Lentils",
            "Roasted Vegetables",
            "Kidney Beans",
            "Mixed Grain",
            "Tahini",
            "Tortillas",
            "Udon Noodles",
            "Cabbage",
            "Shiitake Mushrooms",
            "Mirin",
            "Sesame Seed Oil",
            "Baguette",
            "Vine Tomatoes",
            "Suet",
            "Swede",
            "Ham",
            "Oysters",
            "Stout",
            "Lard",
            "Lamb Kidney",
            "Beef Kidney",
            "Haddock",
            "Smoked Haddock",
            "Brussels Sprouts",
            "Raisins",
            "Currants",
            "Custard",
            "Mixed Peel",
            "Redcurrants",
            "Blackberries",
            "Hazlenuts",
            "Braeburn Apples",
            "Red Food Colouring",
            "Pink Food Colouring",
            "Blue Food Colouring",
            "Yellow Food Colouring",
            "Apricot Jam",
            "Marzipan",
            "Almonds",
            "Black Pudding",
            "Baked Beans",
            "White Flour",
            "Yeast",
            "Fruit Mix",
            "Dried Fruit",
            "Cherry",
            "Glace Cherry",
            "Treacle",
            "Oatmeal",
            "Oats",
            "Egg",
            "Beef Shin",
            "Bouquet Garni",
            "Single Cream",
            "Red Wine Jelly",
            "Apples",
            "Goats Cheese",
            "Prosciutto",
            "Unsalted Butter",
            "Brie",
            "Tarragon Leaves",
            "Chives",
            "Pears",
            "White Chocolate",
            "Star Anise",
            "Tiger Prawns",
            "Custard Powder",
            "Desiccated Coconut",
            "Frozen Peas",
            "Minced Pork",
            "Creamed Corn",
            "Sun-Dried Tomatoes",
            "Dijon Mustard",
            "Tabasco Sauce",
            "Canola Oil",
            "Cod",
            "Salt Cod",
            "Ackee",
            "Jerk",
            "Ground Beef",
            "Baby Aubergine",
            "Paella Rice",
            "Scotch Bonnet",
            "Oxtail",
            "Broad Beans",
            "Red Snapper",
            "Malt Vinegar",
            "Rice Vinegar",
            "Water Chestnut",
            "Tofu",
            "Doubanjiang",
            "Fermented Black Beans",
            "Scallions",
            "Sichuan Pepper",
            "Wonton Skin",
            "Starch",
            "Rice wine",
            "Cooking wine",
            "Duck Sauce",
            "Gochujang",
            "Bean Sprouts",
            "Noodles",
            "Wood Ear Mushrooms",
            "Dark Rum",
            "Light Rum",
            "Rum",
            "English Muffins",
            "Muffins",
            "White Wine Vinegar",
            "Smoked Salmon",
            "Mars Bar",
            "Rice Krispies",
            "Ancho Chillies",
            "Almond Milk",
            "Allspice",
            "Almond Extract",
            "Tripe",
            "Goat Meat",
            "Black Beans",
            "Anchovy Fillet",
            "Ras el hanout",
            "Filo Pastry",
            "Orange Blossom Water",
            "Candied Peel",
            "Grand Marnier",
            "Sherry",
            "Rose water",
            "Mixed Spice",
            "Mincemeat",
            "Sweet Potatoes",
            "Bread Rolls",
            "Bun",
            "Potatoe Buns",
            "Ground Pork",
            "Pork Chops",
            "Yukon Gold Potatoes",
            "Yellow Onion",
            "Beef Stock Concentrate",
            "Chicken Stock Concentrate",
            "Persian Cucumber",
            "Mayonnaise",
            "Sriracha",
            "Rhubarb",
            "Pita Bread",
            "Bulgur Wheat",
            "Quinoa",
            "Dill Pickles",
            "Sesame Seed Burger Buns",
            "Buns",
            "Iceberg Lettuce",
            "Gherkin Relish",
            "Cheese Slices",
            "Shortening",
            "Warm Water",
            "Boiling Water",
            "Pickle Juice",
            "Powdered Sugar",
            "Kielbasa",
            "Polish Sausage",
            "Sauerkraut",
            "Caraway Seed",
            "Herring",
            "Jam",
            "Mulukhiyah",
            "Sushi Rice",
            "Figs",
            "Cider",
            "Beetroot",
            "Sardines",
            "Ciabatta",
            "Buckwheat",
            "Prunes"};
    // Handle back button action

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

}