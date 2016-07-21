package com.chadaga.sathwik.saarangschedule;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }

        boolean firstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstRun", true);
        //Add events to the database only once for the first time
        //In the actual app database should already be ready to the scheduler
        if (firstRun) {
            DatabaseHandler db = new DatabaseHandler(this);

            db.addEvent(new Event("Alankar", "Light Music", "1", "CLT, 9:30 AM"));
            db.addEvent(new Event("Decibels", "Western Music", "1", "SAC, 10:30 AM"));
            db.addEvent(new Event("Flash Fiction", "Writing", "1", "CRC 205, 11:05 AM"));
            db.addEvent(new Event("Solo Dance", "Classical Arts", "1", "Seminar Hall, 1:30 PM"));
            db.addEvent(new Event("India Quiz", "Quizzing", "1", "OAT, 2:00 PM"));


            db.addEvent(new Event("Raagapella", "Light Music", "2", "SAC, 10:30 AM"));
            db.addEvent(new Event("Streets", "Choreo", "2", "CLT, 9:30 AM"));
            db.addEvent(new Event("TinyTales WS", "Writing", "2", "CRC 205, 11:05 AM"));
            db.addEvent(new Event("Vocals", "Classical Arts", "2", "Seminar Hall, 1:30 PM"));
            db.addEvent(new Event("Lone Wolf", "Quizzing", "2", "OAT, 2:00 PM"));


            db.addEvent(new Event("Vox", "Western Music", "3", "CRC 205, 11:05 AM"));
            db.addEvent(new Event("Dance WS", "Choreo", "3", "OAT, 2:00 PM"));
            db.addEvent(new Event("Creative Writing", "Writing", "3", "SAC, 10:30 AM"));
            db.addEvent(new Event("Instrumentals ", "Classical Arts", "3", "Seminar Hall, 1:30 PM"));
            db.addEvent(new Event("Spent Quiz", "Quizzing", "3", "CLT, 9:30 AM"));


            Log.d("DATABASE: ", "Created!");
            Toast toast = Toast.makeText(getApplicationContext(), "Database Created!", Toast.LENGTH_LONG);
            toast.show();

            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstRun", false)
                    .commit();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), "Day 1");
        adapter.addFragment(new Fragment2(), "Day 2");
        adapter.addFragment(new Fragment3(), "Day 3");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}