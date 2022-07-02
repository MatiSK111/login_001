package com.example.login_001;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class Realizar_Test extends AppCompatActivity {
    private PieChart pieChart;
    private String[]months=new String[]{"Enero","Febrero","Marzo", "Abril", "Mayo"};
    private int[] sale = new int[]{25,20,38,10,15};
    private int[] colors = new int[]{Color.BLUE,Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_test);
        pieChart=(PieChart) findViewById(R.id.pieChart);
        createCharts();
    }
    private Chart getSameChart(Chart chart, String description, int textColor, int background, int animateY){
        chart.getDescription().setText(description);
        chart.getDescription().setTextColor(textColor);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);

        return chart;
    }

    private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry> entries= new ArrayList<>();
        for(int i =0; i<sale.length; i++)
            entries.add(new PieEntry(sale[i]));
        return entries;
    }

    public void createCharts(){

        pieChart=(PieChart) getSameChart(pieChart, " ", Color.GRAY,Color.WHITE, 3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        pieChart.setDrawHoleEnabled(false);
    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(20);
        return dataSet;
    }

    private PieData getPieData(){
        PieDataSet pieDataSet=(PieDataSet) getData(new PieDataSet(getPieEntries(),""));
        pieDataSet.setSliceSpace(3);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }

}