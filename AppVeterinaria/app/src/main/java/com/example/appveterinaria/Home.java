package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    Button btRegistrarC, btListarC, btRegistrarM, btListarM;

    //Constante
    final String URL= "http://192.168.1.41/veterinaria/controllers/cliente.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Referencias
        loadUI();

        btRegistrarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(Registrar.class);
            }
        });

        btListarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(ListarCliente.class);
            }
        });

        btRegistrarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(RegistrarMascota.class);
            }
        });

        btListarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(ListarMascota.class);
            }
        });
    }

    private void openActivity(Class nameActivity){
        Intent intent = new Intent(getApplicationContext(), nameActivity);
        startActivity(intent);
    }

    private void loadUI(){
        //Botones
        btRegistrarC = findViewById(R.id.btRegistrarC);
        btListarC = findViewById(R.id.btListarC);
        btRegistrarM = findViewById(R.id.btRegistrarM);
        btListarM = findViewById(R.id.btListarM);
    }
}