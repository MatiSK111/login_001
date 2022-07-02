package com.example.login_001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public EditText usuario;
    EditText password;
    Button ingresar;
    RequestQueue datos;
    TextView qu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario= (EditText) findViewById(R.id.frmusuario);
        password= (EditText) findViewById(R.id.frmpass);
        ingresar=(Button) findViewById(R.id.btningresar);
        datos= Volley.newRequestQueue(this);
        qu= (TextView) findViewById(R.id.pregunta);



        ingresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                consultardatos(usuario.getText().toString(),password.getText().toString());

            }
        });

        qu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent vent=new Intent(MainActivity.this,Clave.class);
                startActivity(vent);

            }
        });
    }


    public void consultardatos(String usu, String pass)
    {
        String url="https://psicofast.space/Sycofast/consultardatos.php?usu="+usu+"&pass="+pass;
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try{
                            String estado=response.getString("estado");
                            if(estado.equals("0"))
                            {
                                Toast.makeText(MainActivity.this,"Usuario no Existe",Toast.LENGTH_LONG).show();
                            }else{
                                Intent ventana=new Intent(MainActivity.this,Principal.class);
                                ventana.putExtra("nombre", usu);
                                startActivity(ventana);
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