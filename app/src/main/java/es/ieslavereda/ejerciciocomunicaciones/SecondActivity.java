package es.ieslavereda.ejerciciocomunicaciones;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class SecondActivity extends AppCompatActivity {
    private TextView mensajeTV;
    private EditText nombreET;
    private EditText edadET;
    private Button aceptarB;
    private Button cancelarB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mensajeTV = findViewById(R.id.mensajeTV);
        nombreET = findViewById(R.id.editTextNombre);
        edadET = findViewById(R.id.editTextEdad);
        aceptarB = findViewById(R.id.aceptarButton);
        cancelarB = findViewById(R.id.cancelarButton);

        Bundle extras = getIntent().getExtras();
        String s = extras.getString("actividad");

        mensajeTV.setText("Tu actividad preferida es: " + s + " introduce nombre completo y edad: ");

        aceptarB.setOnClickListener(view -> {
            String nombre = nombreET.getText().toString();
            int edad = 0;
            if (!edadET.getText().toString().equals(""))
                edad = Integer.parseInt(edadET.getText().toString());
            Intent intent = new Intent();
            Usuario usuario = new Usuario(nombre,edad,s);
            intent.putExtra("usuario", usuario);
            setResult(RESULT_OK,intent);
            finish();
        });

        cancelarB.setOnClickListener( view -> {
            Intent intent = new Intent();
            intent.putExtra("resultado", "cancelado por el usuario");
            setResult(RESULT_CANCELED,intent);
            finish();
        });

    }
}
