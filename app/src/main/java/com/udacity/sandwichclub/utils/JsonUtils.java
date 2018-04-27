package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONArray jsonArray;
        JSONObject jsonObject;
        String mainName;
        ArrayList<String> alsoKnownAsList;
        ArrayList<String> ingredientsList;
        String placeOfOrigin;
        String description;
        String image;
        try {
            alsoKnownAsList = new ArrayList<String>();
            ingredientsList = new ArrayList<String>();

            jsonObject = new JSONObject(json);

            JSONObject nameArray = jsonObject.getJSONObject("name");
            mainName = nameArray.getString("mainName");
            Log.d("JsonUtils", "mainName is " + mainName);
            JSONArray akaArray = nameArray.getJSONArray("alsoKnownAs");

            for (int i=0; i<akaArray.length(); i++) {
                alsoKnownAsList.add(akaArray.getString(i));
            }

            placeOfOrigin = jsonObject.getString("placeOfOrigin");
            Log.d("JsonUtils", "placeOfOrigin is " + placeOfOrigin);
            description = jsonObject.getString("description");
            Log.d("JsonUtils", "description is " + description);
            image = jsonObject.getString("image");
            Log.d("JsonUtils", "image is " + image);

            JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
            for (int i=0; i<ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
    }
}
