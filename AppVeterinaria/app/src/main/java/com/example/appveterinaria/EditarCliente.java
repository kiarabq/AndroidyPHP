package com.example.appveterinaria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class EditarCliente extends AppCompatActivity {

    EditText idApellidos, idNombres, idDni;
    Button btActualizarCliente, btCancelarActualizar;

    final String URL = "http://192.168.1.41/veterinaria/controllers/cliente.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);

        loadUI();

        Bundle parametros = this.getIntent().getExtras();
        if (parametros !=null) {
            getData(parametros.getInt("idcliente"));
        }

        btActualizarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btActualizarCliente();
            }
        });

        btCancelarActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void btActualizarCliente(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Clientes");
        dialogo.setMessage("¿Está seguro de actualizar este registro?");
        dialogo.setCancelable(false);

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                actualizarCliente();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogo.show();
    }

    private void actualizarCliente(){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("")) {
                    resetUI();
                    idApellidos.requestFocus();
                    Toast.makeText(getApplicationContext(), "Actualizado correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }) {

        };
    }

    private void resetUI(){
        idApellidos.setText(null);
        idNombres.setText("");
        idDni.setText("");
    }

    private void getData(int idcliente) {
        Uri.Builder URLFull = Uri.parse(URL).buildUpon();
        URLFull.appendQueryParameter("operacion", "getData");
        URLFull.appendQueryParameter("idcliente", String.valueOf(idcliente));
        String URLUpdate = URLFull.build().toString();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLUpdate, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    idApellidos.setText(jsonObject.getString("apellidos"));
                    idNombres.setText(jsonObject.getString("nombres"));
                    idDni.setText(jsonObject.getString("dni"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private String isNULL(String value){
        if (value.equals("null"))
            return "";
        else
            return value;
    }

    private void loadUI(){
        idApellidos = findViewById(R.id.idUpdApellidos);
        idNombres = findViewById(R.id.idUpdNombres);
        idDni = findViewById(R.id.idUpdDni);
    }
}