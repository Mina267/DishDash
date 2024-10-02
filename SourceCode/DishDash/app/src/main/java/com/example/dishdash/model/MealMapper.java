package com.example.dishdash.model;


public class MealMapper {



    // Method to map MealPlan (which extends Meal) to a database MealPlan entity
    public static MealPlan mapMealToMealPlan(Meal response, String date) {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setIdMeal(response.getIdMeal());
        mealPlan.setStrMeal(response.getStrMeal());
        mealPlan.setStrDrinkAlternate(response.getStrDrinkAlternate());
        mealPlan.setStrCategory(response.getStrCategory());
        mealPlan.setStrArea(response.getStrArea());
        mealPlan.setStrInstructions(response.getStrInstructions());
        mealPlan.setStrMealThumb(response.getStrMealThumb());
        mealPlan.setStrTags(response.getStrTags());
        mealPlan.setStrYoutube(response.getStrYoutube());

        // Map all ingredients
        mealPlan.setStrIngredient1(response.getStrIngredient1());
        mealPlan.setStrIngredient2(response.getStrIngredient2());
        mealPlan.setStrIngredient3(response.getStrIngredient3());
        mealPlan.setStrIngredient4(response.getStrIngredient4());
        mealPlan.setStrIngredient5(response.getStrIngredient5());
        mealPlan.setStrIngredient6(response.getStrIngredient6());
        mealPlan.setStrIngredient7(response.getStrIngredient7());
        mealPlan.setStrIngredient8(response.getStrIngredient8());
        mealPlan.setStrIngredient9(response.getStrIngredient9());
        mealPlan.setStrIngredient10(response.getStrIngredient10());
        mealPlan.setStrIngredient11(response.getStrIngredient11());
        mealPlan.setStrIngredient12(response.getStrIngredient12());
        mealPlan.setStrIngredient13(response.getStrIngredient13());
        mealPlan.setStrIngredient14(response.getStrIngredient14());
        mealPlan.setStrIngredient15(response.getStrIngredient15());
        mealPlan.setStrIngredient16(response.getStrIngredient16());
        mealPlan.setStrIngredient17(response.getStrIngredient17());
        mealPlan.setStrIngredient18(response.getStrIngredient18());
        mealPlan.setStrIngredient19(response.getStrIngredient19());
        mealPlan.setStrIngredient20(response.getStrIngredient20());

        // Map all measures
        mealPlan.setStrMeasure1(response.getStrMeasure1());
        mealPlan.setStrMeasure2(response.getStrMeasure2());
        mealPlan.setStrMeasure3(response.getStrMeasure3());
        mealPlan.setStrMeasure4(response.getStrMeasure4());
        mealPlan.setStrMeasure5(response.getStrMeasure5());
        mealPlan.setStrMeasure6(response.getStrMeasure6());
        mealPlan.setStrMeasure7(response.getStrMeasure7());
        mealPlan.setStrMeasure8(response.getStrMeasure8());
        mealPlan.setStrMeasure9(response.getStrMeasure9());
        mealPlan.setStrMeasure10(response.getStrMeasure10());
        mealPlan.setStrMeasure11(response.getStrMeasure11());
        mealPlan.setStrMeasure12(response.getStrMeasure12());
        mealPlan.setStrMeasure13(response.getStrMeasure13());
        mealPlan.setStrMeasure14(response.getStrMeasure14());
        mealPlan.setStrMeasure15(response.getStrMeasure15());
        mealPlan.setStrMeasure16(response.getStrMeasure16());
        mealPlan.setStrMeasure17(response.getStrMeasure17());
        mealPlan.setStrMeasure18(response.getStrMeasure18());
        mealPlan.setStrMeasure19(response.getStrMeasure19());
        mealPlan.setStrMeasure20(response.getStrMeasure20());

        mealPlan.setStrSource(response.getStrSource());
        mealPlan.setStrImageSource(response.getStrImageSource());
        mealPlan.setStrCreativeCommonsConfirmed(response.getStrCreativeCommonsConfirmed());
        mealPlan.setDateModified(response.getDateModified());

        // Set the date for MealPlan
        mealPlan.setDate(date);

        return mealPlan;
    }


    // Method to map MealPlan entity to a Meal object
    public static Meal mapMealPlanToMeal(MealPlan mealPlan) {
        Meal meal = new Meal();

        meal.setIdMeal(mealPlan.getIdMeal());
        meal.setStrMeal(mealPlan.getStrMeal());
        meal.setStrDrinkAlternate(mealPlan.getStrDrinkAlternate());
        meal.setStrCategory(mealPlan.getStrCategory());
        meal.setStrArea(mealPlan.getStrArea());
        meal.setStrInstructions(mealPlan.getStrInstructions());
        meal.setStrMealThumb(mealPlan.getStrMealThumb());
        meal.setStrTags(mealPlan.getStrTags());
        meal.setStrYoutube(mealPlan.getStrYoutube());

        // Map all ingredients
        meal.setStrIngredient1(mealPlan.getStrIngredient1());
        meal.setStrIngredient2(mealPlan.getStrIngredient2());
        meal.setStrIngredient3(mealPlan.getStrIngredient3());
        meal.setStrIngredient4(mealPlan.getStrIngredient4());
        meal.setStrIngredient5(mealPlan.getStrIngredient5());
        meal.setStrIngredient6(mealPlan.getStrIngredient6());
        meal.setStrIngredient7(mealPlan.getStrIngredient7());
        meal.setStrIngredient8(mealPlan.getStrIngredient8());
        meal.setStrIngredient9(mealPlan.getStrIngredient9());
        meal.setStrIngredient10(mealPlan.getStrIngredient10());
        meal.setStrIngredient11(mealPlan.getStrIngredient11());
        meal.setStrIngredient12(mealPlan.getStrIngredient12());
        meal.setStrIngredient13(mealPlan.getStrIngredient13());
        meal.setStrIngredient14(mealPlan.getStrIngredient14());
        meal.setStrIngredient15(mealPlan.getStrIngredient15());
        meal.setStrIngredient16(mealPlan.getStrIngredient16());
        meal.setStrIngredient17(mealPlan.getStrIngredient17());
        meal.setStrIngredient18(mealPlan.getStrIngredient18());
        meal.setStrIngredient19(mealPlan.getStrIngredient19());
        meal.setStrIngredient20(mealPlan.getStrIngredient20());

        // Map all measures
        meal.setStrMeasure1(mealPlan.getStrMeasure1());
        meal.setStrMeasure2(mealPlan.getStrMeasure2());
        meal.setStrMeasure3(mealPlan.getStrMeasure3());
        meal.setStrMeasure4(mealPlan.getStrMeasure4());
        meal.setStrMeasure5(mealPlan.getStrMeasure5());
        meal.setStrMeasure6(mealPlan.getStrMeasure6());
        meal.setStrMeasure7(mealPlan.getStrMeasure7());
        meal.setStrMeasure8(mealPlan.getStrMeasure8());
        meal.setStrMeasure9(mealPlan.getStrMeasure9());
        meal.setStrMeasure10(mealPlan.getStrMeasure10());
        meal.setStrMeasure11(mealPlan.getStrMeasure11());
        meal.setStrMeasure12(mealPlan.getStrMeasure12());
        meal.setStrMeasure13(mealPlan.getStrMeasure13());
        meal.setStrMeasure14(mealPlan.getStrMeasure14());
        meal.setStrMeasure15(mealPlan.getStrMeasure15());
        meal.setStrMeasure16(mealPlan.getStrMeasure16());
        meal.setStrMeasure17(mealPlan.getStrMeasure17());
        meal.setStrMeasure18(mealPlan.getStrMeasure18());
        meal.setStrMeasure19(mealPlan.getStrMeasure19());
        meal.setStrMeasure20(mealPlan.getStrMeasure20());

        meal.setStrSource(mealPlan.getStrSource());
        meal.setStrImageSource(mealPlan.getStrImageSource());
        meal.setStrCreativeCommonsConfirmed(mealPlan.getStrCreativeCommonsConfirmed());
        meal.setDateModified(mealPlan.getDateModified());

        return meal;
    }

}
