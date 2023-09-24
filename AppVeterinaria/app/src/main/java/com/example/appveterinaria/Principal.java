package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {
    Button btentrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Referencias
        loadUI();

        //Eventos
        btentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(Home.class);
            }
        });

    }

    private void openActivity(Class nameActivity){
        Intent intent= new Intent(getApplicationContext(), nameActivity);
        startActivity(intent);
    }


    private void loadUI(){
        btentrar = findViewById(R.id.btentrar);
    }
}