package com.example.tofun.shopperng;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class phonesTabletsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        new getPhonesTablets().execute();
        return inflater.inflate(R.layout.fragment_phones_tablets, container, false);
    }

    public class getPhonesTablets extends AsyncTask<String, Void, String> {
        ArrayList<String> itemsArray;
        @Override
        protected String doInBackground(String... params) {
            try {
                URL getInfo = new URL("https://shopperng.herokuapp.com/snippet/phonesTablets");
                HttpURLConnection connection = (HttpURLConnection) getInfo.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response= reader.readLine();
                JSONObject queryResponse= new JSONObject(response);
                JSONArray itemsArrayJSON= queryResponse.getJSONArray("names");

                itemsArray = new ArrayList<String>();
                for(int i=0; i<itemsArrayJSON.length();i++){
                    itemsArray.add(i, itemsArrayJSON.get(i).toString());
                }

            }
            catch(Exception e){
                System.out.println("Error ");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Button[] itemButtons= new Button[itemsArray.size()];
//             myItemsLayout= (ViewGroup) getActivity().findViewById(R.id.phonesTabletsItemsGrid);

//            for(int i=0; i<itemsArray.size(); i++){
//////                itemButtons[i].setText(itemsArray.get(i));
////                myItemsLayout.addView(itemButtons[i]);
//                Log.d("myArray",itemsArray.get(i));
//                Log.d("Error", myItemsLayout.toString());
//                itemButtons[i] = new Button(getActivity().getApplicationContext());
//                itemButtons[i]=(Button)getActivity().getLayoutInflater().inflate(R.layout.buttontemplate, null);
////                itemButtons[i].setBackground(getContext().getResources().getDrawable(R.drawable.homeiconborder));
//                itemButtons[i].setText(itemsArray.get(i));
//
////                myItemsLayout.addView(myButton, i);
//            }
//            myItemsLayout.addView(itemButtons[0]);
//            myItemsLayout.addView(itemButtons[1]);
//            myItemsLayout.addView(itemButtons[2]);

//            for(int i=0; i<itemButtons.length; i++){
//                myItemsLayout.addView(itemButtons[i]);
//            }

            
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
