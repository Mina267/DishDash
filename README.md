

# DishDash - Mobile Application

DishDash is an intuitive and feature-rich mobile application designed to elevate your meal-planning experience. With a focus on user convenience and a seamless interface, DishDash offers personalized meal recommendations, easy access to favorite meals, and weekly meal planning, all within an engaging, beautifully designed app. Below are some of the core features that make DishDash stand out.

---

## Features

### 1. Splash Screen

The DishDash experience begins with a visually captivating **Splash Screen**, designed using **Figma** to offer a polished and professional introduction. This screen greets users with a clean and modern aesthetic, making the first impression both memorable and inviting.

To enhance the user experience, smooth animations were implemented using **Aninix**, ensuring that the transition from the splash screen to the app’s main content is fluid and engaging. The animation adds a dynamic touch that aligns with modern design standards, creating a visually appealing flow that sets the tone for the rest of the application.

**Key Highlights:**
- **Custom Design**: Built with Figma for a high-quality user interface.
- **Smooth Transitions**: Leveraged Aninix for animated transitions, providing a sleek user experience.

**Technologies Used:**
- **Figma**: For splash screen design.
- **Aninix**: For animation and transitions.

<p align="center">
	<img src="https://github.com/user-attachments/assets/f5f79b4f-4bbf-44f3-99b8-7cf47b03567f" width=20% height=20% />
</p>


---

### 2. For You Page

The **For You** page is a personalized hub where users can explore curated daily meal inspirations and recommendations. This page is central to DishDash’s value, offering tailored suggestions including **vegan options** and **Egyptian cuisine**, delivering both variety and cultural relevance.

The UI is powered by **RecyclerViews**, ensuring an efficient and visually appealing layout that enhances usability across different devices. The page is built with a focus on user engagement and ease of interaction, offering seamless options to manage meal details, add favorites, and plan meals for the week.

**Key Features:**
- **Daily Inspiration**: Every day, users are presented with a new meal suggestion to inspire their culinary choices.
- **Vegan & Egyptian Recommendations**: Special categories highlight healthy vegan options and traditional Egyptian meals, ensuring diversity and inclusivity.
- **Meal Details**: Users can tap on any meal to view detailed information such as ingredients, instructions, and nutritional content, making meal prep easy and informative.
- **Favorite & Meal Planning**: With simple interaction options, users can:
  - Add meals to their favorites list for easy future reference.
  - Add meals to their weekly meal plan, helping organize daily meal prep effortlessly.

The interface is designed for **user-friendliness**, with an intuitive layout and modern design that ensures users can navigate the app seamlessly, whether they’re browsing meal ideas or managing their meal plan.

**Key Technologies:**
- **RecyclerViews**: Used to present meal suggestions in a scrollable, efficient layout.
- **User-Centric Design**: The interface is designed to provide a fluid and pleasant experience, prioritizing user interaction and accessibility.







align="center">
    <img src="https://github.com/user-attachments/assets/559e8274-896d-4bc5-9b2e-cce224263f5a" height="400px" style="display: inline-block; margin-right: 20px; vertical-align: middle;" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/ba853e0d-90f9-4ef0-aae1-a776ca813bd7" height="400px" style="display: inline-block; vertical-align: middle;" />
</p>

---


### 3. Favorites

The **Favorites** feature in DishDash allows users to save meals they love, providing quick access to their favorite dishes whether they are online or offline. This functionality ensures that users can access their saved meals anytime, even without an internet connection, making meal planning more convenient.

With this feature, users can:
- **View Meal Details**: See all relevant information for each saved meal, including ingredients, cooking instructions, and nutritional content.
- **Remove from Favorites**: Easily remove any meal from their favorites list with a single action.
- **Add to Meal Plan**: Users can directly add their favorite meals to their weekly meal plan, streamlining the process of planning their meals.

**Key Highlights:**
- **Offline Access**: Users can view their favorite meals without the need for internet connectivity, ensuring uninterrupted access.
- **Flexible Interaction**: View, remove, or add meals to the weekly meal plan seamlessly, enhancing user convenience.

**Technologies Used:**
- **Local Storage**: Used to store favorite meals for offline access.
- **Online Sync**: Favorites are synchronized when online, ensuring up-to-date data across devices.

<br>


<p align="center">
    <img src="https://github.com/user-attachments/assets/0cd4de6d-c105-4607-a98b-41b3630440e2" height="400px" style="display: inline-block; vertical-align: middle;" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/f2946805-d416-4aca-91cc-996d0005ca6c" height="400px" style="display: inline-block; vertical-align: middle;" />
</p>

---

### 4. Meal Plan

DishDash offers a powerful **Meal Plan** feature, which allows users to plan their meals for the entire month. The meal planner not only provides an overview of the current week but also extends to the **next three weeks**, ensuring users have a clear and organized meal schedule for the upcoming month.

Users can:
- **View Weekly Plans**: The meal plan displays meals for the current week and the next three weeks, giving users a long-term view of their upcoming meals.
- **Remove or Edit Details**: Users can easily remove meals from their plan or view details for each meal, making it easy to adjust their schedule on the go.

**Key Highlights:**
- **Monthly Meal Planning**: Plan meals for the next month with an overview of the current and upcoming weeks.
- **Editable Plans**: Flexibility to view, edit, or remove meals from the meal plan as needed.

**Technologies Used:**
- **LiveData**: Used to dynamically update the meal plan in real time.
- **RecyclerViews**: For displaying the weekly meal plan in a structured, responsive layout.

<br>
<p align="center">
    <img src="https://github.com/user-attachments/assets/4f2e33f4-3f34-4005-ab33-ea957af3b913" height="400px" style="display: inline-block; vertical-align: middle;" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/0224aa62-7701-4374-b17e-2ecd35dd92e6" height="400px" style="display: inline-block; vertical-align: middle;" />
</p>

---

### 5. Search

The **Search** feature in DishDash is designed to offer users an intuitive and versatile way to discover meals, recipes, and ingredients. With a focus on ease of use and quick access to information, this feature incorporates several powerful search options to help users find exactly what they are looking for.

**Key Search Options:**
- **Live Search by Meal Name**: The search bar supports live search functionality, allowing users to quickly find meals as they type, with instant results displayed for easy selection.
- **Country-Specific Recipes**: Users can browse meals by country using a **RecyclerView** that provides a list of countries. Selecting a country will display all recipes associated with that cuisine, giving users a rich culinary experience.
- **Category Search**: Users can search for meals by category, exploring different meal types such as vegan, desserts, or main courses.
- **Ingredient Search**: A dedicated search bar allows users to find meals based on specific ingredients, streamlining meal discovery based on available ingredients.

**View All Ingredients**: In addition to the ingredient search, users have the option to "View All" ingredients in a structured list, making it easier to explore recipes by key components.

**Key Highlights:**
- **Live Meal Search**: Fast, real-time search results for meals.
- **Country, Category, and Ingredient Search**: Offers flexible options for users to explore meals based on origin, type, or ingredients.
- **"View All" Ingredients**: Easy browsing of all available ingredients for recipe discovery.

**Technologies Used:**
- **RecyclerView**: For country, category, and ingredient selection, ensuring a responsive and interactive experience.
- **SearchView**: Integrated with live search functionality to provide instant results.



<br>

<p align="center">
	<img src="https://github.com/user-attachments/assets/1eddd1aa-7cb2-4c10-8246-73a6edb3c08e" width=70% height=70% />
</p>



---

### 6. Meal Details

The **Meal Details** feature in DishDash offers users a comprehensive view of each meal, ensuring they have all the information needed to prepare delicious dishes. Users can explore the meal’s ingredients, their precise measurements, and the step-by-step preparation process, making it easy to follow along.

Additionally, if a video tutorial is available for the recipe, it will be included, giving users visual guidance on how to prepare the meal. Beyond just viewing details, users can easily manage the meal by adding or removing it from their favorites or directly adding it to their weekly meal plan for future reference.

**Key Features:**
- **Ingredients & Measurements**: A clear list of all ingredients, along with the exact quantities required for the recipe.
- **Preparation Steps**: A step-by-step guide on how to cook the meal, ensuring a smooth cooking experience.
- **Recipe Video**: When available, users can watch a video tutorial to better understand the preparation process.
- **Favorite & Meal Planning**: Users can add the meal to their favorites or include it in their weekly meal plan with a single tap.

**Technologies Used:**
- **Video Integration**: For seamless recipe video playback.
- **LiveData**: Ensures dynamic updates to favorites and meal plans.

<br>

<p align="center">
    <img src="https://github.com/user-attachments/assets/f72e74df-df49-48a2-b2b1-539c9c5a7d5f" height="400px" style="display: inline-block; vertical-align: middle;" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/76e5db5e-4b1c-4502-a82e-80b1c9b49b2c" height="400px" style="display: inline-block; vertical-align: middle;" />
</p>


---

### 7. Select Day for Meal Plan

The **Select Day for Meal Plan** feature is designed to help users organize their meals efficiently for the upcoming month. Users can add meals to specific days in their plan for the next four weeks. Once added, these meals are reflected in the **Meal Plan** feature, where users can view, adjust, or remove meals as needed.

By using this feature, users can ensure that they are well-prepared for the days ahead, making meal planning a breeze.

**Key Features:**
- **Add Meals to Plan**: Select meals for any day within the next month and seamlessly add them to the meal plan.
- **View in Meal Plan**: Once a meal is added, it will automatically appear in the meal plan overview, where users can manage their selections.

**Technologies Used:**
- **Date Selection**: Allows users to pick specific days for meal scheduling.
- **RecyclerView**: For smooth navigation and meal selection.


<br>

<p align="center">
    <img src="https://github.com/user-attachments/assets/7255719a-b75f-4c57-baa6-9e59c24122b4" height="400px" style="display: inline-block; vertical-align: middle;" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/19515416-c13b-4209-88e2-f095ae3c0db3" height="400px" style="display: inline-block; vertical-align: middle;" />
</p>

---



### 8. Offline Access with Network Handling

The **Offline Access with Network Handling** feature ensures that users can continue interacting with their meal plans and favorites even if they lose their network connection. This feature is designed to provide uninterrupted access to critical data and functionality, whether the user is online or offline.

When a network connection is lost or unavailable, the app prompts the user with two options:
1. **Open Network Settings**: The app will guide the user to activate their network connection for full access to online features.
2. **Continue in Offline Mode**: Users can opt to proceed offline, where they will still be able to view and modify their **Meal Plan** and **Favorites**. All changes made during offline mode will be synchronized when the network is restored.

**Key Features:**
- **Offline Mode**: Users can access and modify both meal plans and favorites while offline, ensuring that they are never restricted by connectivity issues.
- **Network Prompt**: Upon entering the app without an active internet connection, users are prompted to either enable their network or continue offline.
- **Data Synchronization**: Any changes made to favorites or meal plans in offline mode are automatically synced when the app detects a restored connection.

**Technologies Used:**
- **Network Listener**: Detects the network status and prompts the user accordingly.
- **Local Storage**: Ensures data for meal plans and favorites is available offline and updated locally.


<p align="center">
    <img src="https://github.com/user-attachments/assets/ed1eea02-9e43-46e4-8840-973361e1b86d" height="400px" style="display: inline-block; margin-right: 20px; vertical-align: middle;" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/527d61fd-2f43-4155-ace0-ee859f8933da" height="400px" style="display: inline-block; vertical-align: middle;" />
</p>

---




# System Architecture

DishDash is built using the **MVP (Model-View-Presenter)** architecture, which ensures a clear separation of concerns and modularity. This structure makes the application easy to maintain, test, and scale by dividing responsibilities among various layers. Each feature within the app is encapsulated into its own module, allowing for isolated development and easier management.

#### Key Components:

1. **MVP Structure per Feature**:
   - **View Interface**: Each feature contains its own view interface, responsible for displaying data and user interactions.
   - **Presenter Interface**: Each feature includes a presenter interface that handles the business logic, communicates with the model layer, and updates the view accordingly.
   - **Adapter and OnClick Listener**: Each feature has its custom adapter and click listeners to handle user interaction within RecyclerViews.
   - **Navigation**: The app uses a **Navigation Controller** to move between screens (fragments) using **Navigation XML**, ensuring smooth navigation between different parts of the app.

2. **Networking (Remote Data Source)**:
   - The **network** package is dedicated to handling network operations. It utilizes **Retrofit** to fetch data from TheMealDB API.
   - **MealRemoteDataSource**: This interface defines methods for remote data fetching, with the implementation handled by **MealRemoteDataSourceImpl**.
   - **NetworkConnectionStatus** and **NetworkDelegate**: These components monitor and handle network connectivity, ensuring that users are prompted when the connection is lost.

3. **Database Layer (Local Data Source)**:
   - The **DB** package leverages **Room** to provide a local database for storing meals and meal plans. It defines two main tables: **Favorites** and **Meal Plan**, with corresponding **DAO** interfaces for querying data.
   - **MealLocalDataSource** and **MealPlanLocalDataSource** handle local data operations, ensuring that data can be accessed offline and synced when a connection is available.

4. **Model Layer**:
   - The **model** package contains all **POJOs (Plain Old Java Objects)** that represent the data fetched from the network (JSON responses) and the database.
   - These POJOs map to the response structure from TheMealDB API and also serve as entities for Room's database tables.

5. **Feature Modules**:
   - Each feature, such as **For You**, **Favorites**, **Meal Plan**, **Search**, and **Select Day**, has its own directory structure with separate **Presenter**, **View**, and **Model** components.
   - This modular approach isolates each feature, improving maintainability and enabling focused development for each part of the app.

6. **Presenter as Data Coordinator**:
   - The presenter acts as the **data coordinator** between the view and the model (network and local data sources). It is responsible for fetching data from the remote API or local database and delivering it to the view for presentation.
   - The presenter also manages business logic such as adding or removing meals from favorites, scheduling meals in the meal plan, and synchronizing offline changes.

#### Technologies Used:
- **Retrofit**: For making network requests to TheMealDB API.
- **Room**: For handling local data storage, ensuring that meals and meal plans are available offline.
- **Navigation Component**: To handle screen transitions and navigation flow within the app.
- **LiveData**: For observing changes in the data and updating the UI in real-time.

<br>

<p align="center">
	<img src="https://github.com/user-attachments/assets/57d6e356-1cc2-4116-ac4f-c972218ed74c" width=70% height=70% />
</p>
