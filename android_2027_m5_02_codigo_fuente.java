package com.example.actividad_m5_02;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Actividad_M5_02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calcularMaxCalorias();
    }

    private void calcularMaxCalorias() {
        int maxCalorias = 0;
        int caloriasActuales = 0;
        int elfoActual = 1;
        int elfoGanador = 1;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getAssets().open("input-M5-02.txt")))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    // Termina el inventario del elfo actual
                    if (caloriasActuales > maxCalorias) {
                        maxCalorias = caloriasActuales;
                        elfoGanador = elfoActual;
                    }
                    caloriasActuales = 0;
                    elfoActual++;
                } else {
                    caloriasActuales += Integer.parseInt(linea);
                }
            }

            // Verificar también al último elfo del archivo
            if (caloriasActuales > maxCalorias) {
                maxCalorias = caloriasActuales;
                elfoGanador = elfoActual;
            }

            Log.d(TAG, "Total de elfos analizados: " + elfoActual);
            Log.d(TAG, "Elfo con más calorías: Elfo #" + elfoGanador);
            Log.d(TAG, "Calorías totales que lleva ese elfo: " + maxCalorias);

        } catch (IOException e) {
            Log.e(TAG, "Error al leer el archivo input-M5-02.txt", e);
        }
    }
}