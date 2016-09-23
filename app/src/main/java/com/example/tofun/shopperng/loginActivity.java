package com.example.tofun.shopperng;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Future;

public class loginActivity extends AppCompatActivity {
    String email;
    String password;
    String firstName;
    String lastName;
    String customerID;
    int errorState=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void loginAction(View view){
        EditText emailField = (EditText) findViewById(R.id.emailField);
        EditText passwordField= (EditText) findViewById(R.id.passwordField);
        email= emailField.getText().toString();
        password= passwordField.getText().toString();
        new verifyCredentials().execute();
    }
    public class verifyCredentials extends AsyncTask <String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                URL verifyURL = new URL("https://shopperng.herokuapp.com/login");
                HttpURLConnection conn = (HttpURLConnection) verifyURL.openConnection();
                conn.setRequestMethod("POST");
                String urlParameters = "email="+email+"&password="+password;
                DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
                writer.writeBytes(urlParameters);
                writer.flush();
                writer.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String jsonReturnStatement= reader.readLine();
                JSONObject myObject = new JSONObject(jsonReturnStatement);
                errorState= myObject.getInt("error");
                if(errorState == 0){
                    firstName = myObject.getString("FirstName");
                    lastName= myObject.getString("LastName");
                    customerID = myObject.getString("CustomerID");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    intent.putExtra("customerID", customerID);
                    intent.putExtra("loggedIn", true);
                    startActivity(intent);
                }
            }
            catch(Exception e){

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Button loginButton = (Button) findViewById(R.id.loginButton);
            loginButton.setEnabled(false);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Button loginButton = (Button) findViewById(R.id.loginButton);
            loginButton.setEnabled(true);
        }
    }
}
