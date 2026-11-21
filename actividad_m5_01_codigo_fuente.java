package com.example.actividad_m5_01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigInteger;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Caché para aplicar memoization y evitar recalcular valores previos
    private final HashMap<Integer, BigInteger> memo = new HashMap<>();
    private int indiceActual = 0;

    private TextView tvFibonacci;

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

        // Inicializar casos base en la caché de memoization
        memo.put(0, BigInteger.ZERO);
        memo.put(1, BigInteger.ONE);
        memo.put(2, BigInteger.ONE);

        // Enlazar componentes XML con Java
        tvFibonacci = findViewById(R.id.tvFibonacci);
        Button btnAvance = findViewById(R.id.btnAvance);
        Button btnRetroceso = findViewById(R.id.btnRetroceso);

        // Botón de avance: incrementa n y calcula Fibonacci
        btnAvance.setOnClickListener(v -> {
            indiceActual++;
            BigInteger resultado = calcularFibonacci(indiceActual);
            tvFibonacci.setText(resultado.toString());
        });

        // Botón de retroceso: decrementa n (sin bajar de 0) y consulta la caché
        btnRetroceso.setOnClickListener(v -> {
            if (indiceActual > 0) {
                indiceActual--;
                BigInteger resultado = calcularFibonacci(indiceActual);
                tvFibonacci.setText(resultado.toString());
            }
        });
    }

    /**
     * Calcula el n-ésimo número de Fibonacci usando ciclo for y memoization.
     */
    private BigInteger calcularFibonacci(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        for (int i = 3; i <= n; i++) {
            if (!memo.containsKey(i)) {
                BigInteger valor = memo.get(i - 1).add(memo.get(i - 2));
                memo.put(i, valor);
            }
        }

        return memo.get(n);
    }
}