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


public class Principal extends AppCompatActivity {

    Button reali_test, mis_test;
    TextView bienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        reali_test=(Button) findViewById(R.id.btnrealizartest);
        mis_test=(Button) findViewById(R.id.btnRevisarTest);
        String valor= getIntent().getStringExtra("nombre");

        bienvenido=(TextView) findViewById(R.id.bn);
        bienvenido.setText("Bienvenido "+valor);



    }

    public void Mis_Test(View view){
        String valor= getIntent().getStringExtra("nombre");
        Intent m_test=new Intent(this,Mis_Test.class);
        m_test.putExtra("nombre", valor);
        startActivity(m_test);
    }
    public void Realizar_test(View view){
        Intent m_test=new Intent(this,Realizar_Test.class);
        startActivity(m_test);
    }
}