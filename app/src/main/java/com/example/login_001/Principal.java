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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        reali_test=(Button) findViewById(R.id.btnrealizartest);
        mis_test=(Button) findViewById(R.id.btnRevisarTest);

       // mis_test.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   Intent intent = new Intent (v.getContext(), Mis_Test.class);
               // startActivity(intent);
            //}
        //});



    }

    public void Mis_Test(View view){
        Intent m_test=new Intent(this,Mis_Test.class);
        startActivity(m_test);
    }
    public void Realizar_test(View view){
        Intent m_test=new Intent(this,Realizar_Test.class);
        startActivity(m_test);
    }
}