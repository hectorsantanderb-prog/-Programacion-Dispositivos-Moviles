package com.example.actividad_m4_01;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Actividad_M4_01";

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

        Log.d(TAG, "onCreate() -> La actividad se está creando.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() -> La actividad está a punto de hacerse visible.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() -> La actividad está en primer plano y lista para interactuar.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() -> La actividad está pasando a segundo plano (pausada).");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() -> La actividad ya no es visible para el usuario.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() -> La actividad se está reiniciando después de haber estado detenida.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() -> La actividad está a punto de ser destruida.");
    }
}