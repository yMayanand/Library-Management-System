package com.project.myapplication4;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {


 SearchView srchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        srchView = findViewById(R.id.etrx);



        //if user presses on login
        //calling the method login
        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookSearch();
            }
        });


    }

    private void bookSearch() {
        //first getting the values
        final String searchQuery = srchView.getQuery().toString();




        //if everything is fine

        class BookSearch extends AsyncTask<Void, Void, String> {

           ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
               progressBar = (ProgressBar) findViewById(R.id.progressSearchBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                         finish();
                        startActivity(new Intent(getApplicationContext(), FoundUnavailable.class));
                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        if (!obj.getBoolean("error2")) {
                             finish();
                            startActivity(new Intent(getApplicationContext(), FoundAvailable.class));

                        }else{
                             finish();
                            startActivity(new Intent(getApplicationContext(), NotFound.class));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("book", searchQuery);


                //returning the response
                return requestHandler.sendPostRequest(URLs.URL_SEARCH, params);
            }
        }

        BookSearch ul = new BookSearch();
        ul.execute();
    }
}