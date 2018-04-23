package com.example.sanket.recepies;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sanket on 9/25/2017.
 */

public class ReceipeUtilts {

    static public class ReceipeParser{


       static ArrayList<Receipes> parseReceipse(String s) throws JSONException {

           ArrayList<Receipes> receipesArrayList = new ArrayList<Receipes>();
           JSONObject root = new JSONObject(s);
           JSONArray receipseArray = root.getJSONArray("results");

           for(int i=0;i<receipseArray.length();i++){

               JSONObject recepie = receipseArray.getJSONObject(i);
               Receipes receipes = new Receipes();
               receipes.setTitle(recepie.getString("title"));
               receipes.setIngredient(recepie.getString("ingredients"));
               receipes.setURL(recepie.getString("href"));
               receipes.setImageUrl(recepie.getString("thumbnail"));
               receipesArrayList.add(receipes);

           }
           return receipesArrayList;
       }

    }


}
