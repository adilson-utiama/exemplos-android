package com.asuprojects.testescomponentes.calendarview;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.asuprojects.testescomponentes.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class CalendarViewLayoutCalendarPickerFragment extends Fragment{

    CalendarView calendarView;

    public CalendarViewLayoutCalendarPickerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_calendar_view_layout_calendar_picker, container, false);

        calendarView = view.findViewById(R.id.calendarView_manyDaysPicker);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar data = eventDay.getCalendar();
                DateFormat dateInstance = DateFormat.getDateInstance();
                String dataFormatada = dateInstance.format(data.getTime());

                Snackbar.make(view, "Data Selecionada " + dataFormatada,
                        Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
