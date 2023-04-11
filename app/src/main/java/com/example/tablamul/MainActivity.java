package com.example.tablamul;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText etnumero;
    private TextView etsalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etnumero = (EditText) findViewById(R.id.etnumero);
        etsalida = (TextView) findViewById(R.id.etsalida);
    }

    public void calcular(View view) {
        String cad = "";
        if (etnumero.getText().toString().equals("")) {
            Toast.makeText(this, "ingrese un numero", Toast.LENGTH_LONG).show();
        } else {
            int numero = Integer.parseInt(etnumero.getText().toString());
            for (int i = 1; i <= 10; i++) {
                cad += String.valueOf(numero) + "\t x \t" + i + " \t = \t" + (numero * i) + "\n";
            }
            etsalida.setText(cad);
        }
    }
    public void irArchivo(View view){
        Intent i = new Intent(this, activityarchivo.class);
        startActivity(i);
    }
    public void  guardar (View view){
        String nombre =  etnumero.getText().toString();
        String contenido = etsalida.getText().toString();

        try {
            //donde se encuentra la tarjeta SD
            File tarjetaSD = Environment.getExternalStorageDirectory();
            Toast.makeText(this, tarjetaSD.getPath(), Toast.LENGTH_LONG).show();
            /// creamos el archivo con el nombre que nos indica el archivo, y el Pach de la tarjera SD
            File rutaArchivo = new File(tarjetaSD.getPath(),nombre );

            /// abrimos el archivo
            OutputStreamWriter CrearArchivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));

            ///guarrdamos los datos del atchivo
            CrearArchivo.write(contenido);
            //limpiamos el Buffer
            CrearArchivo.flush();
            CrearArchivo.close();

            Toast.makeText(this, "se ha guardado correctamente", Toast.LENGTH_LONG).show();
            etnumero.setText("");
            etsalida.setText("");
        } catch (IOException e) {
            Toast.makeText(this, "no se pudo guardar el archivo", Toast.LENGTH_LONG).show();

        }
    }
}