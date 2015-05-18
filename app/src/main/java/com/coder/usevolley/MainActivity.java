package com.coder.usevolley;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private static final String TAG=MainActivity.class.getSimpleName();

    private ArrayList<Movie> movies;
    private MovieAdapter adapter;
    private ListView movieList;

    private static final String url="http://www.jycoder.com/json/movies.json";

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList= (ListView) findViewById(R.id.movie_list);
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        movies=new ArrayList<Movie>();

        fetchMovies();
        adapter=new MovieAdapter(MainActivity.this,movies);

        movieList.setAdapter(adapter);
    }

    private void fetchMovies() {

        JsonArrayRequest req=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       hidePDialog();
                    }
                },1000);
                for(int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject object=jsonArray.getJSONObject(i);
                        Movie movie=new Movie();
                        movie.setImage(object.getString("image"));
                        movie.setName(object.getString("name"));
                        movie.setRating(object.getString("rating"));
                        movie.setYear(object.getString("year"));

                        movies.add(movie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                //注意刷新数据
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,"error:"+volleyError.getMessage());
                hidePDialog();
            }
        });
        ApplicationController.getInstance().addToRequestQueue(req);
    }

    public void hidePDialog(){
        if(pDialog!=null)
            pDialog.dismiss();
        pDialog=null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
