package com.example.ray.networkimage.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ray.networkimage.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    public static int pageCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newPage();
    }

    private void newPage() {
        pageCount++;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (pageCount != 1) {
            ft.addToBackStack(null);
        }
        ft.replace(R.id.container, MainFragment.newInstance(pageCount), MainFragment.class.getName()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        newPage();
        return true;
    }

    @Override
    public void onBackPressed() {
        pageCount--;
        super.onBackPressed();
    }
}
