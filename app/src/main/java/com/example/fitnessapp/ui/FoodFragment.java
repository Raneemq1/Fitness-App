package com.example.fitnessapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fitnessapp.R;
import com.example.fitnessapp.adapter.MealRecyclerAdapter;
import com.example.fitnessapp.databinding.FragmentFoodBinding;
import com.example.fitnessapp.model.Meal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FoodFragment extends Fragment {

    private FragmentFoodBinding binding;

    private RecyclerView recyclerView;
    private SearchView searchView;
    private Button btnSearch;
    private RequestQueue queue;
    private ArrayList<Meal> meals = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {;

        binding = FragmentFoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.meals_recycler_view);
        searchView = root.findViewById(R.id.text_search_meal);
        btnSearch = root.findViewById(R.id.btn_search_for_meal);
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
        getMealsFromApi(url);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "";
                query = searchView.getQuery().toString();
                String parameterName = "s="; // search by string
                if (query.length() == 1) // search by first char
                    parameterName = "f=";

                String url = "https://www.themealdb.com/api/json/v1/1/search.php?"+ parameterName +""+ query;
                getMealsFromApi(url);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getMealsFromApi(String url){
        meals = new ArrayList<>();
        queue = Volley.newRequestQueue((HomeActivity) getActivity());

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arr = response.getJSONArray("meals");
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                String name = obj.getString("strMeal");
                                String category = obj.getString("strCategory");
                                String area = obj.getString("strArea");
                                String instructions = obj.getString("strInstructions");
                                String imgURL = obj.getString("strMealThumb");
                                String videoLink = obj.getString("strYoutube");
                                ArrayList<String> ingredients = new ArrayList<>();
                                ArrayList<String> ingredientsAmounts = new ArrayList<>();
                                for(int j = 0 ; j < 20 ; ++j){
                                    if (obj.getString("strIngredient"+(j+1)).equals("") || obj.getString("strIngredient"+(j+1)) == null)
                                        break;
                                    else {
                                        ingredients.add(obj.getString("strIngredient"+(j+1)));
                                        ingredientsAmounts.add(obj.getString("strMeasure"+(j+1)));
                                    }
                                }
                                Meal meal = new Meal(name, category, area, instructions, imgURL, videoLink, ingredients, ingredientsAmounts);
                                meals.add(meal);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager((HomeActivity) getActivity()));
                        MealRecyclerAdapter adapter = new MealRecyclerAdapter(meals,(HomeActivity) getActivity() );
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        queue.add(request);
    }
}