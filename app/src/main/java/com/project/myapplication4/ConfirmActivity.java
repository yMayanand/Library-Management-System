package com.project.myapplication4;

import android.content.Intent;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue_confirm);

        //if user presses on login
        //calling the method login
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                issueBook();


            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }

        });


    }


    private void issueBook() {
        //first getting the values
        final String uniqueId = Scanner.intentData;
        final String bkId = Integer.toString(ProfileActivity.helpId);
        final String uName = ProfileActivity.helpId2;


        //if everything is fine

        class IssueBook extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar2);
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

                        //getting the user from the response
                        finish();
                        startActivity(new Intent(getApplicationContext(), ConfirmationPage.class));
                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        if (!obj.getBoolean("error2")) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), FoundUnavailable.class));

                        } else {
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
                params.put("uid", uniqueId);
                params.put("id", bkId);
                params.put("uname", uName);


                //returning the response
                return requestHandler.sendPostRequest(URLs.URL_ISSUE, params);
            }
        }

        IssueBook ul = new IssueBook();
        ul.execute();
    }
}