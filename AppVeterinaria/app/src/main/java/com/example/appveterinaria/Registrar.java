package com.example.appveterinaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Registrar extends AppCompatActivity {

    EditText idApellidos, idNombres, idDni;
    Button btGuardar;
    String apellidos, nombres, dni;

    //Constante
    final String URL = "http://192.168.1.41/veterinaria/controllers/cliente.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        //Referencias

        loadUI();

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar();
            }
        });
    }

    private void validar(){
        apellidos = idApellidos.getText().toString().trim();
        nombres = idNombres.getText().toString().trim();
        dni = idDni.getText().toString().trim();

        if(apellidos.isEmpty()){
            idApellidos.setError("Complete este campo");
            idApellidos.requestFocus();
        }else if(nombres.isEmpty()){
            idNombres.setError("Complete este campo");
            idNombres.requestFocus();
        }else if(dni.isEmpty()){
            idDni.setError("Complete este campo");
            idDni.requestFocus();
        }else{
            DialogoRegistro();
        }
    }

    private void DialogoRegistro(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Clientes");
        dialogo.setMessage("¿Está seguro de registrar?");
        dialogo.setCancelable(false);

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                registrarCliente();

            }
        });

        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogo.show();
    }

    private void registrarCliente(){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("")) {
                    resetUI();
                    idApellidos.requestFocus();
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
                Map<String, String> parametros= new HashMap<String, String>();
                parametros.put("operacion", "add");
                parametros.put("apellidos", apellidos);
                parametros.put("nombres", nombres);
                parametros.put("dni", dni);
                return parametros;
            }
        };

        //Enviamos la consulta al WS
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


    private void resetUI(){
        idApellidos.setText(null);
        idNombres.setText("");
        idDni.setText("");
    }

    private void loadUI(){
        idApellidos = findViewById(R.id.idApellidos);
        idNombres = findViewById(R.id.idNombres);
        idDni = findViewById(R.id.idDni);

        btGuardar = findViewById(R.id.btGuardar);

    }
}