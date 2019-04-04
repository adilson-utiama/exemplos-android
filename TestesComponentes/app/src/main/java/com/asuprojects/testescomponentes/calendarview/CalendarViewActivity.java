package com.asuprojects.testescomponentes.calendarview;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.asuprojects.testescomponentes.R;

public class CalendarViewActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    CalendarPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Material CalendarView ");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewPager = findViewById(R.id.calendarViewPager);
        adapter = new CalendarPagerAdapter(getSupportFragmentManager());
        adapter.addPage(new CalendarViewDialoPickerFragment(), "Dialog CalendarView");
        adapter.addPage(new CalendarViewLayoutCalendarPickerFragment(), "Layout CalendarView");

        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.calendarViewTabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
