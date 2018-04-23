package com.example.sanket.recepies;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Receipes> receipes = new ArrayList<Receipes>();
    String s;
    int index=0;
    int flag =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

         s = "{\"results\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"Monterey Turkey Omelet\",\n" +
                "\t\t\t\"href\": \"http://allrecipes.com/Recipe/Monterey-Turkey-Omelet/Detail.aspx\",\n" +
                "\t\t\t\"ingredients\": \"butter, eggs, garlic, green pepper, monterey jack cheese, onions, turkey, water\",\n" +
                "\t\t\t\"thumbnail\": \"http://img.recipepuppy.com/5506.jpg\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"Canadian Bacon Omelet\",\n" +
                "\t\t\t\"href\": \"http://www.recipezaar.com/Canadian-Bacon-Omelet-309202\",\n" +
                "\t\t\t\"ingredients\": \"butter, canadian bacon, cheddar cheese, eggs, garlic, onions, potato, red pepper, sour cream\",\n" +
                "\t\t\t\"thumbnail\": \"\"\n" +
                "\t\t}]}";

      //  new getDataofJSON().execute("");

        try {
            receipes=   ReceipeUtilts.ReceipeParser.parseReceipse(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("demo",receipes.toString());
        for(index=0;index< receipes.size();index++) {
            new getImage().execute(receipes.get(index).getImageUrl());
        }

        index =0;
        final ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.setImageBitmap(receipes.get(0).getImage());

        final TextView tv1 = (TextView) findViewById(R.id.TitleName);
        tv1.setText(receipes.get(0).getTitle());

        final TextView tv2 = (TextView) findViewById(R.id.IngradName);
        tv2.setText(receipes.get(0).getIngredient());

        final TextView tv3 = (TextView) findViewById(R.id.URLName);
        tv3.setText(receipes.get(0).getURL());


        ImageView first = (ImageView)findViewById(R.id.first);
        ImageView previous = (ImageView)findViewById(R.id.previous);
        ImageView next = (ImageView)findViewById(R.id.next);
        ImageView last = (ImageView)findViewById(R.id.last);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                index =0;
                iv.setImageBitmap(receipes.get(0).getImage());

                tv1.setText(receipes.get(0).getTitle());
                tv2.setText(receipes.get(0).getIngredient());
                tv3.setText(receipes.get(0).getURL());
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            index--;

                if(index>0){

                    iv.setImageBitmap(receipes.get(index).getImage());

                    tv1.setText(receipes.get(index).getTitle());
                    tv2.setText(receipes.get(index).getIngredient());
                    tv3.setText(receipes.get(index).getURL());
                }else{

                    Toast.makeText(MainActivity.this, "first record", Toast.LENGTH_LONG).show();
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                index++;

                if(index>receipes.size()){

                    iv.setImageBitmap(receipes.get(index).getImage());

                    tv1.setText(receipes.get(index).getTitle());
                    tv2.setText(receipes.get(index).getIngredient());
                    tv3.setText(receipes.get(index).getURL());
                }else {

                    Toast.makeText(MainActivity.this, "last record", Toast.LENGTH_LONG).show();
                }
            }
        });

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = receipes.size()-1;
                iv.setImageBitmap(receipes.get(receipes.size()-1).getImage());

                tv1.setText(receipes.get(receipes.size()-1).getTitle());
                tv2.setText(receipes.get(receipes.size()-1).getIngredient());
                tv3.setText(receipes.get(receipes.size()-1).getURL());

            }
        });



    }
    private class getDataofJSON extends AsyncTask<String,Void,ArrayList<Receipes>>{

        @Override
        protected void onPostExecute(ArrayList<Receipes> s) {
            super.onPostExecute(s);
            receipes = s;
            if(s!=null){
                Log.d("demo",s.toString());
            }else{
                Log.d("demo","no data");
            }
        }

        @Override
        protected ArrayList<Receipes> doInBackground(String... strings) {
            BufferedReader reader = null;

            try {
                /*URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int status = con.getResponseCode();*/
              /*  if(status == HttpURLConnection.HTTP_OK) {
                    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while ((line = reader.readLine()) != null) {

                        sb.append(line + "\n");

                    }

                }*/
                return ReceipeUtilts.ReceipeParser.parseReceipse(s);
            }  catch (JSONException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(reader!=null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
            }
            return null;
        }
    }

    private class getImage extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            receipes.get(index).setImage(s);
            flag =0;


        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            BufferedReader reader = null;
            InputStream in = null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                in = con.getInputStream();
                Bitmap image = BitmapFactory.decodeStream(in);
                return  image;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

}
