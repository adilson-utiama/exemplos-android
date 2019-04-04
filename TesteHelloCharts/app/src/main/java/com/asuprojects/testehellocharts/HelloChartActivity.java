package com.asuprojects.testehellocharts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class HelloChartActivity extends AppCompatActivity {

    private PieChartView pieChartView;
    private PieChartData data;

    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean hasCenterCircle = false;
    private boolean hasCenterText1 = false;
    private boolean hasCenterText2 = false;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_chart);

        pieChartView = findViewById(R.id.chart);

        pieChartView.setOnValueTouchListener(new HelloChartActivity.ValueTouchListener());

        generateData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.pie_chart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_reset) {
            reset();
            generateData();
            return true;
        }
        if (id == R.id.action_explode) {
            explodeChart();
            return true;
        }
        if (id == R.id.action_center_circle) {
            hasCenterCircle = !hasCenterCircle;
            if (!hasCenterCircle) {
                hasCenterText1 = false;
                hasCenterText2 = false;
            }

            generateData();
            return true;
        }
        if (id == R.id.action_center_text1) {
            hasCenterText1 = !hasCenterText1;

            if (hasCenterText1) {
                hasCenterCircle = true;
            }

            hasCenterText2 = false;

            generateData();
            return true;
        }
        if (id == R.id.action_center_text2) {
            hasCenterText2 = !hasCenterText2;

            if (hasCenterText2) {
                hasCenterText1 = true;// text 2 need text 1 to by also drawn.
                hasCenterCircle = true;
            }

            generateData();
            return true;
        }
        if (id == R.id.action_toggle_labels) {
            toggleLabels();
            return true;
        }
        if (id == R.id.action_toggle_labels_outside) {
            toggleLabelsOutside();
            return true;
        }
        if (id == R.id.action_animate) {
            prepareDataAnimation();
            pieChartView.startDataAnimation();
            return true;
        }
        if (id == R.id.action_toggle_selection_mode) {
            toggleLabelForSelected();
            Toast.makeText(HelloChartActivity.this,
                    "Selection mode set to " + pieChartView.isValueSelectionEnabled() + " select any point.",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset() {
        pieChartView.setCircleFillRatio(1.0f);
        hasLabels = false;
        hasLabelsOutside = false;
        hasCenterCircle = false;
        hasCenterText1 = false;
        hasCenterText2 = false;
        isExploded = false;
        hasLabelForSelected = false;
    }

    private void generateData() {
        int numValues = 5;

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            sliceValue.setLabel("Teste " + (i + 1));
            values.add(sliceValue);
        }

        data = new PieChartData(values);
        //data.setHasLabels(hasLabels);
        data.setHasLabels(true);
        data.setCenterText1("Teste");
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);
        data.setHasLabelsOutside(hasLabelsOutside);
        data.setHasCenterCircle(hasCenterCircle);

        if (isExploded) {
            data.setSlicesSpacing(24);
        }

        if (hasCenterText1) {
            data.setCenterText1("Hello!");

        }

        if (hasCenterText2) {

        }

        pieChartView.setPieChartData(data);
    }

    private void explodeChart() {
        isExploded = !isExploded;
        generateData();

    }

    private void toggleLabelsOutside() {
        // has labels have to be true:P
        hasLabelsOutside = !hasLabelsOutside;
        if (hasLabelsOutside) {
            hasLabels = true;
            hasLabelForSelected = false;
            pieChartView.setValueSelectionEnabled(hasLabelForSelected);
        }

        if (hasLabelsOutside) {
            pieChartView.setCircleFillRatio(0.7f);
        } else {
            pieChartView.setCircleFillRatio(1.0f);
        }

        generateData();

    }

    private void toggleLabels() {
        hasLabels = !hasLabels;

        if (hasLabels) {
            hasLabelForSelected = false;
            pieChartView.setValueSelectionEnabled(hasLabelForSelected);

            if (hasLabelsOutside) {
                pieChartView.setCircleFillRatio(0.7f);
            } else {
                pieChartView.setCircleFillRatio(1.0f);
            }
        }

        generateData();
    }

    private void toggleLabelForSelected() {
        hasLabelForSelected = !hasLabelForSelected;

        pieChartView.setValueSelectionEnabled(hasLabelForSelected);

        if (hasLabelForSelected) {
            hasLabels = false;
            hasLabelsOutside = false;

            if (hasLabelsOutside) {
                pieChartView.setCircleFillRatio(0.7f);
            } else {
                pieChartView.setCircleFillRatio(1.0f);
            }
        }

        generateData();
    }

    /**
     * To animate values you have to change targets values and then call
     * method(don't confuse with View.animate()).
     */
    private void prepareDataAnimation() {
        for (SliceValue value : data.getValues()) {
            value.setTarget((float) Math.random() * 30 + 15);
        }
    }

    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(HelloChartActivity.this, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
