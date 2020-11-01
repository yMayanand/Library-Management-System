package com.project.myapplication4;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ProfileActivity extends AppCompatActivity {

    TextView textViewUsername;
    Button bkScan;
    static int helpId;
    static String helpId2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //if the user is not logged in
        //starting the login activity
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }



        textViewUsername = (TextView) findViewById(R.id.textViewUsername);




        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews

        textViewUsername.setText(user.getUsername());

        helpId = user.getId();
        helpId2 = user.getUsername();

        bkScan = (Button)findViewById(R.id.book_issue);

        bkScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                
                Intent intent = new Intent(getApplicationContext(), Scanner.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.book_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 finish();
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });



        //when the user presses logout button
        //calling the logout method
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });
    }
}