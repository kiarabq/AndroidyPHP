package com.example.appveterinaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrarMascota extends AppCompatActivity {
    Spinner idGenero;
    Button btGuardarMascota;
    EditText idNombreMascota, idColor, idEspecie, idRaza;
    String nombre, color, especie, raza, genero;

    final String URL = "http://192.168.1.41/veterinaria/controllers/mascota.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);

        loadUI();
        String[] genero = {"Seleccione", "H", "M"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genero);
        idGenero.setAdapter(adapter);

        btGuardarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarControles();
            }
        });
    }

    private void validarControles(){
        nombre = idNombreMascota.getText().toString().trim();
        color = idColor.getText().toString().trim();
        especie = idEspecie.getText().toString().trim();
        raza = idRaza.getText().toString().trim();
        genero = idGenero.getSelectedItem().toString().trim();

        if (nombre.isEmpty()){
            idNombreMascota.setError("Complete este campo");
            idNombreMascota.requestFocus();
        }else if (color.isEmpty()){
            idColor.setError("Complete este campo");
            idColor.requestFocus();
        }else if (especie.isEmpty()){
            idEspecie.setError("Complete este campo");
            idEspecie.requestFocus();
        }else if (raza.isEmpty()){
            idRaza.setError("Complete este campo");
            idRaza.requestFocus();
        }else{
            mostrarDialogoRegistro();
        }
    }

    private void mostrarDialogoRegistro(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Mascota");
        dialogo.setMessage("¿Está seguro de registrar?");
        dialogo.setCancelable(false);

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                registrarMascota();
            }
        });

        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogo.show();
    }

    private void registrarMascota(){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("")) {
                    resetUI();
                    idGenero.requestFocus();
                    Toast.makeText(getApplicationContext(), "Guardado correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error comunicación", error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("operacion", "add");
                parametros.put("nombre" , nombre);
                parametros.put("color", color);
                parametros.put("especie", especie);
                parametros.put("raza", raza);
                parametros.put("genero", genero);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void resetUI(){
        idNombreMascota.setText("");
        idColor.setText("");
        idEspecie.setText("");
        idRaza.setText("");
    }
    private void loadUI(){
        idNombreMascota = findViewById(R.id.idNombreMascota);
        idColor = findViewById(R.id.idColor);
        idEspecie = findViewById(R.id.idEspecie);
        idRaza = findViewById(R.id.idRaza);
        idGenero = findViewById(R.id.idGenero);

        btGuardarMascota = findViewById(R.id.btGuardarMascota);
    }
}