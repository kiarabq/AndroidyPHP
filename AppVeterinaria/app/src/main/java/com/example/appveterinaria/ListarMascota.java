package com.example.appveterinaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarMascota extends AppCompatActivity {
    ListView lvMascotas;

    private List<String> dataList = new ArrayList<>();
    private List<Integer> dataID = new ArrayList<>();
    private CustomAdapter adapter;

    final String URL = "http://192.168.1.41/veterinaria/controllers/mascota.php";

    private String[] opciones = {"Editar", "Eliminar", "Cancelar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_mascota);

        loadUI();

        getData();

        lvMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                showAlertOptions(dataID.get(position), position);
            }
        });
    }

    private void showAlertOptions(int primaryKey, int positionIndex){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle(dataList.get(positionIndex));
        dialogo.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int itemIndex) {
                switch (itemIndex){
                    case 0:
                        //Editar
                        Intent intent = new Intent(getApplicationContext(), EditarMascota.class);
                        intent.putExtra("idmascota", primaryKey);
                        startActivity(intent);
                        break;

                    case 1:
                        showConfirmDelete(primaryKey);
                        break;
                    case 2:
                        dialogInterface.dismiss();
                        break;
                }
            }
        });
        dialogo.show();
    }

    private void showConfirmDelete(int pkDelete){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Eliminación");
        dialogo.setMessage("¿Está seguro de eliminar el registro");
        dialogo.setCancelable(false);

        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteRegister(pkDelete);
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogo.show();
    }

    private void deleteRegister(int pkDelete){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getData();
                Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("operacion", "delete");
                parametros.put("idmascota", String.valueOf(pkDelete));
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getData(){
        dataID.clear();

        dataList.clear();
        adapter = new CustomAdapter(this, dataList);
        lvMascotas.setAdapter(adapter);

        Uri.Builder URLFull = Uri.parse(URL).buildUpon();
        URLFull.appendQueryParameter("operacion", "list");
        String URLUpdate = URLFull.build().toString();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLUpdate, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = new JSONObject(response.getString(i));
                        dataList.add(jsonObject.getString("especie") + " " + jsonObject.getString("raza"));
                        dataID.add(jsonObject.getInt("idmascota"));
                    }

                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                    }
                });

        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
    private void loadUI(){
        lvMascotas = findViewById(R.id.lvMascotas);
    }
}