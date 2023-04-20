package es.ieslavereda.ejerciciocomunicaciones;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView resultadoTV;
    private Button enviarB;
    private RadioGroup radioGP;
    private Spinner spinner;
    private String actividad;
    private List<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultadoTV = findViewById(R.id.ResultadotextView);
        enviarB = findViewById(R.id.enviarBoton);
        radioGP = findViewById(R.id.radioGroup);
        spinner = findViewById(R.id.spinner);

        actividad = "vacío";
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Xavier Rosillo", 50, "Video Juegos"));
        usuarios.add(new Usuario("Joaquín Alonso", 46, "Ciclismo"));

        ArrayAdapter<Usuario> miAdaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, usuarios);
        spinner.setAdapter(miAdaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                resultadoTV.setText(usuarios.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        radioGP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButtonCiclismo)
                    actividad = "Ciclismo";
                else if (i == R.id.radioButtonNatacion)
                    actividad = "Natación";
                else if (i == R.id.radioButtonGames)
                    actividad = "Video Juegos";
                else if (i == R.id.radioButtonNa)
                    actividad = "No hacer nada viendo la vida pasar";
                else
                    actividad = "vacío";
            }
        });

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Intent data = result.getData();
                        String resultado = data.getExtras().getString("resultado");
                        resultadoTV.setText(resultado);
                    } else if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String nombre = data.getExtras().getString("nombre");
                        int edad = data.getExtras().getInt("edad");
                        String actividad = data.getExtras().getString("actividad");
                        Usuario usuario = new Usuario(nombre, edad, actividad);
                        usuarios.add(usuario);
                        resultadoTV.setText(usuario.toString());
                    }

                });

        enviarB.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            intent.putExtra("actividad", actividad);
            someActivityResultLauncher.launch(intent);
        });

    }
}