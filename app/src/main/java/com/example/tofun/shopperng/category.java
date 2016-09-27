package com.example.tofun.shopperng;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        innerCategoryFragment homeFragment = new innerCategoryFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mainCategoryContainer, homeFragment).commit();

    }
    public void toPhonesTablets(View view){
        phonesTabletsFragment phonesTabletsFragment = new phonesTabletsFragment();
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.mainCategoryContainer, phonesTabletsFragment).commit();
    }
}
