package com.example.dialgo.Graph;

import android.content.Context;
import android.graphics.Color;

import com.example.dialgo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private LineChart lineChart;
    private Context context;
    private List<Entry> lineEntries = new ArrayList<>();
    private LineData lineData;
    public Graph(LineChart lineChart, Context context){
        this.lineChart = lineChart;
        this.context  = context;
    }
    public void drawLineChart() {
        List<Entry> lineEntries = getData();
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "f(x)");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setHighlightEnabled(false);
        lineDataSet.setLineWidth(2);
        lineDataSet.setColor(context.getResources().getColor(R.color.graphColor));
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setCircleRadius(0);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setDrawValues(false);
        lineDataSet.setValueTextColor(Color.DKGRAY);

        LineData lineData = new LineData(lineDataSet);
        lineChart.getDescription().setText("f(x)");
        lineChart.getDescription().setTextSize(12);
        lineChart.setDrawMarkers(true);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        lineChart.animateY(1000);
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(1.0f);
        lineChart.getXAxis().setLabelCount(lineDataSet.getEntryCount());
        lineChart.setData(lineData);
    }

    private List<Entry> getData() {
        return lineEntries;
    }
    public void addPoint(float x, float y){
        lineEntries.add(new Entry(x,y));
    }
    public void clear(){
        lineData = null;
        lineEntries.clear();
    }
    public void putData(ArrayList<Entry> input){
        lineEntries = input;
    }
}
