package com.example.login_001;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mis_Test extends AppCompatActivity {

    ListView simpleList;
    RequestQueue datos;

    public void onCreate(Bundle icicle) {
        getSupportActionBar().setTitle("Mis test");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(icicle);
        datos= Volley.newRequestQueue(this);
        setContentView(R.layout.activity_mis_test);
        simpleList = (ListView)findViewById(R.id.ListadoUsuario);
        CargaLista();

    }

    private void CargaLista()
    {
        ArrayList<String> listausu=new ArrayList<String>();
        String valor= getIntent().getStringExtra("nombre");
        String url="https://psicofast.space/Sycofast/consulta.php?nombre="+valor;
        StringRequest request=new StringRequest(Request.Method.GET, url,new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try{
                    JSONArray json = new JSONArray(response);
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject registro= json.getJSONObject(i);
                        String estado=registro.getString("estado");
                        String Completado;
                        if(estado.equals("2")){
                          Completado="Completado";
                        }else{
                            Completado="No completado";
                        }
                        String tes=registro.getString("Test_idtest");
                        String tess;
                        if(estado.equals("2")){
                            tess="Test de Beck";
                        }else{
                            tess="Test de prueba";
                        }
                        listausu.add("             "+registro.getString("fecha")+"  "+tess+"  "+Completado);

                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Mis_Test.this, R.layout.activity_mis_test, R.id.ttt, listausu);
                simpleList.setAdapter(arrayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        datos.add(request);
    }


}