package com.vasic.example.komentarproject.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vasic.example.komentarproject.R;
import com.vasic.example.komentarproject.data.DataContainer;
import com.vasic.example.komentarproject.databinding.ActivityMainBinding;
import com.vasic.example.komentarproject.ui.adapter.menu.MenuAdapter;
import com.vasic.example.komentarproject.ui.fragment.HomeFragment;
import com.vasic.example.komentarproject.ui.fragment.LatestFragment;
import com.vasic.example.komentarproject.ui.fragment.SearchFragment;
import com.vasic.example.komentarproject.ui.fragment.VideoFragment;

public class HomeActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,HomeFragment.newInstance())
                .commit();

        binding.recyclerViewMenu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerViewMenu.setAdapter(new MenuAdapter(DataContainer.categoryList));

        binding.imageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.recyclerViewMenu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                binding.recyclerViewMenu.setAdapter(new MenuAdapter(DataContainer.categoryList));
                binding.drawerLayout.openDrawer(Gravity.LEFT);

            }
        });

        binding.imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.close();
            }
        });

         binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 Fragment selectedFragment = null;
                 switch (item.getItemId()){
                      case R.id.home:
                       selectedFragment = HomeFragment.newInstance();
                       break;
                      case R.id.latest:
                         selectedFragment = LatestFragment.newInstance();
                         break;
                     case R.id.video:
                         selectedFragment = VideoFragment.newInstance();
                         break;
                     case R.id.search:
                         selectedFragment = SearchFragment.newInstance();
                         break;

                 }
                 getSupportFragmentManager().beginTransaction()
                         .replace(R.id.container,selectedFragment)
                         .commit();
                 return true;
             }
         });
    }
}