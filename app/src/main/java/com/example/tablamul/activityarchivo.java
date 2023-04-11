package com.example.tablamul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class activityarchivo extends AppCompatActivity {

    private EditText et1buscar;
    private TextView tvresul;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityarchivo);
        et1buscar = (EditText) findViewById(R.id.etbuscar);
        tvresul = (TextView) findViewById(R.id.tvresultado);
    }
    public void consultar(View view){
        String nombre = et1buscar.getText().toString();

        try {
            File tarjetaSD = Environment.getExternalStorageDirectory();
            File rutaArchivo = new File(tarjetaSD.getPath(),nombre);
            InputStreamReader abrirArchivo = new InputStreamReader(openFileInput(nombre));

            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = leerArchivo.readLine();
            String contenidoCompleto = "";

            while (linea != null){
                contenidoCompleto = contenidoCompleto + linea +"\n";
            }
            leerArchivo.close();
            abrirArchivo.close();
            tvresul.setText(contenidoCompleto);

        }catch (IOException e){
            Toast.makeText(this, "error al leer el archivo", Toast.LENGTH_LONG).show();
        }
    }
    public void volver(View view){
     finish();
    }
}