package com.example.login_001;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Realizar_Test extends AppCompatActivity {
    private PieChart pieChart;
    private String[]months=new String[]{"Test completados", "Test por realizar"};
    RequestQueue datos;
    // se deberia llenar conlos datos de estadso de la base de datos, peroo no funciona se cae...
    private int[] colors = new int[]{Color.rgb(26, 188, 156),Color.rgb(136, 78, 160)};

    //ArrayList<Integer> sale=new ArrayList<Integer>();

    public String n1 ;
    public String n2 ;
    //int n2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_test);
        pieChart=(PieChart) findViewById(R.id.pieChart);

        datos= Volley.newRequestQueue(this);
       // sale();
        //int lol = Integer.parseInt(n1);
        lln1();
        //Toast.makeText(Realizar_Test.this,lln1(),Toast.LENGTH_LONG).show();

    }
    private Chart getSameChart(Chart chart, String description, int textColor, int background, int animateY){
        chart.getDescription().setText(description);
        chart.getDescription().setTextColor(textColor);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);

        return chart;
    }

    private ArrayList<PieEntry>getPieEntries(int[] sale){
        //sale();
        //lln1();

        ArrayList<PieEntry> entries= new ArrayList<>();
        for(int i =0; i<sale.length; i++)
            entries.add(new PieEntry(sale[i]));
        return entries;
    }

    public void createCharts(int valor, int valor2){

        pieChart=(PieChart) getSameChart(pieChart, " ", Color.GRAY,Color.rgb(227, 237, 247), 3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData(valor, valor2));
        pieChart.invalidate();
        pieChart.setDrawHoleEnabled(false);
    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(24);
        return dataSet;
    }

    private PieData getPieData(int valor, int valor2){
        PieDataSet pieDataSet=(PieDataSet) getData(new PieDataSet(getPieEntries(new int[]{valor,valor2}),"Verde: Test completados - Morado: Test por realizar"));
        pieDataSet.setSliceSpace(3);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }


    private void sale()
    {

        String valor= getIntent().getStringExtra("nombre");
        String url="https://psicofast.space/Sycofast/graficos_android.php?nombre="+valor;
        StringRequest request=new StringRequest(Request.Method.GET, url,new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try{
                    JSONArray json = new JSONArray(response);
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject registro= json.getJSONObject(i);
                        String estado=registro.getString("Estado");


                        if(estado.equals("1"))
                        {
                           // n1 = n1+ 1;
                        }if(estado.equals("2")){
                            //n2 = n2 +1;
                            //Toast.makeText(Realizar_Test.this,n2,Toast.LENGTH_LONG).show();
                        }
                        //sale = new int[] {registro.getInt("estado")};
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        datos.add(request);


    }

    public String lln1()
    {
        String valor= getIntent().getStringExtra("nombre");
        String n3 = "";
        String url="https://psicofast.space/Sycofast/androidDatos.php?nombre="+valor;
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try{

                     n1=response.getString("estado");

                    //Toast.makeText(Realizar_Test.this,n1,Toast.LENGTH_LONG).show();
                    //int lol = Integer.valueOf(n1);
                    //Toast.makeText(Realizar_Test.this,lol,Toast.LENGTH_LONG).show();
                    //int[] sale = new int[]{Integer.parseInt(n1),30};
                    //createCharts(Integer.parseInt(n1));
                    lln2(Integer.parseInt(n1));

                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        datos.add(request);
        return n1;
    }
    public String lln2(int nn){
        String valor= getIntent().getStringExtra("nombre");
        String n3 = "";
        String url="https://psicofast.space/Sycofast/androidDatos2.php?nombre="+valor;
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try{

                    n2=response.getString("estado");

                    //Toast.makeText(Realizar_Test.this,n1,Toast.LENGTH_LONG).show();
                    //int lol = Integer.valueOf(n1);
                    //Toast.makeText(Realizar_Test.this,lol,Toast.LENGTH_LONG).show();
                    //int[] sale = new int[]{Integer.parseInt(n1),30};
                    createCharts(nn, Integer.parseInt(n2));

                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        datos.add(request);
        return n2;
    }




}