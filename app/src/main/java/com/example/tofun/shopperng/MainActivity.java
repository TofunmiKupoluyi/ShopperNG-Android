package com.example.tofun.shopperng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Boolean loggedIn= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        loggedIn= intent.getBooleanExtra("loggedIn", false);
        if(loggedIn==true){
            String customerID= intent.getStringExtra("customerID");
            String firstName= intent.getStringExtra("firstName");
            String lastName= intent.getStringExtra("lastName");
            TextView text= new TextView(this);
            text.setText(customerID+firstName+lastName);
            ViewGroup view= (ViewGroup) findViewById(R.id.mainActivityLayout);
            view.addView(text);
        }

    }
    public void toLogin(View view){
        if(loggedIn==true){

        }
        else {
            Intent intent = new Intent(this, loginActivity.class);
            startActivity(intent);
        }
    }

    public void toCategory(View view){
        Intent intent = new Intent(this, category.class);
    }
}
