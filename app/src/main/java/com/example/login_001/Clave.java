package com.example.login_001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Clave extends AppCompatActivity {
    EditText correo;
    Button enviar;
    RequestQueue datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clave);
        correo=(EditText) findViewById(R.id.Email);
        enviar=(Button) findViewById(R.id.enviar_correo);
        datos= Volley.newRequestQueue(this);

        enviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cambiar_Clave(correo.getText().toString());

            }
        });
    }
    public void Cambiar_Clave(String corr)
    {
        String url="https://psicofast.space/Sycofast/CorreoAndroid.php?mail="+corr;
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try{

                    String estado=response.getString("estado");
                    if(estado.equals("1"))
                    {
                        Toast.makeText(Clave.this,"Revise su correo electronico",Toast.LENGTH_LONG).show();
                    }if(estado.equals("2")){
                        Toast.makeText(Clave.this,"Correo NO enviado = 2",Toast.LENGTH_LONG).show();
                    }if(estado.equals("3")){
                        Toast.makeText(Clave.this,"Correo NO enviado = 3",Toast.LENGTH_LONG).show();
                    }
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



    }
}