package com.asuprojects.testescomponentes.calendarview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.asuprojects.testescomponentes.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class CalendarViewDialoPickerFragment extends Fragment {

    TextView selectedDay;

    public CalendarViewDialoPickerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar_view_dialog_picker, container, false);

        selectedDay = view.findViewById(R.id.tx_selected_day);

        view.findViewById(R.id.btn_oneDayPicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder builder = new DatePickerBuilder(getActivity(), listenerDialogOneDayPicker)
                        .date(Calendar.getInstance())
                        .pickerType(CalendarView.ONE_DAY_PICKER);

                DatePicker datePicker = builder.build();
                datePicker.show();
            }
        });

        view.findViewById(R.id.btn_manyDayPicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder builder = new DatePickerBuilder(getActivity(), listenerDialogManyDayPicker)
                        .date(Calendar.getInstance())
                        .pickerType(CalendarView.MANY_DAYS_PICKER);

                DatePicker datePicker = builder.build();
                datePicker.show();
            }
        });

        return view;
    }



    private OnSelectDateListener listenerDialogManyDayPicker = new OnSelectDateListener() {
        @Override
        public void onSelect(List<Calendar> calendar) {
            selectedDay.setText("");
            DateFormat dateInstance = DateFormat.getDateInstance();
            StringBuilder builder = new StringBuilder();
            builder.append("Selecionou as Datas: \n");
            for (Calendar c : calendar) {
                String dataFormatada = dateInstance.format(c.getTime());
                builder.append(dataFormatada).append("\n");
            }
            selectedDay.setText(builder.toString());
        }

    };

    private OnSelectDateListener listenerDialogOneDayPicker = new OnSelectDateListener() {
        @Override
        public void onSelect(List<Calendar> calendar) {
            selectedDay.setText("");
            Calendar data = calendar.get(0);
            DateFormat dateInstance = DateFormat.getDateInstance();
            StringBuilder builder = new StringBuilder();
            String dataFormatada = dateInstance.format(data.getTime());
            builder.append("Selecionou Data:\n ").append(dataFormatada);
            selectedDay.setText(builder.toString());
        }

    };

}
