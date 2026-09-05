package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNumero1;
    EditText etNumero2;
    Spinner spOperacion;
    Button btnCalcular;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectar los elementos XML con Java
        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
        spOperacion = findViewById(R.id.spOperacion);
        btnCalcular = findViewById(R.id.btnCalcular);
        tvResultado = findViewById(R.id.tvResultado);

        // Crear las opciones del Spinner
        String[] operaciones = {"Suma", "Resta", "Multiplicacion", "Division"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                operaciones
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spOperacion.setAdapter(adapter);

        // Acción del botón Calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobar que los campos tengan datos
                if (etNumero1.getText().toString().isEmpty()
                        || etNumero2.getText().toString().isEmpty()) {

                    Toast.makeText(
                            MainActivity.this,
                            "Ingrese los dos números",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                // Obtener los números
                double numero1 = Double.parseDouble(
                        etNumero1.getText().toString()
                );

                double numero2 = Double.parseDouble(
                        etNumero2.getText().toString()
                );

                // Obtener la operación
                String operacion =
                        spOperacion.getSelectedItem().toString();

                double resultado = 0;

                // Realizar la operación
                switch (operacion) {

                    case "+":
                        resultado = numero1 + numero2;
                        break;

                    case "-":
                        resultado = numero1 - numero2;
                        break;

                    case "*":
                        resultado = numero1 * numero2;
                        break;

                    case "/":

                        if (numero2 == 0) {

                            Toast.makeText(
                                    MainActivity.this,
                                    "No se puede dividir entre cero",
                                    Toast.LENGTH_SHORT
                            ).show();

                            return;
                        }

                        resultado = numero1 / numero2;
                        break;
                }

                // Mostrar el resultado
                tvResultado.setText("Resultado: " + resultado);
            }
        });
    }
}