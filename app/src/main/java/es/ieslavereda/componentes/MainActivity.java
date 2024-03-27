package es.ieslavereda.componentes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private TextView textViewCB;
    private TextView textViewS;
    private RadioGroup sexoRG;
    private EditText textoNombre;
    private EditText textoApellidos;
    private Button botonAñadir;
    private List<Usuario> usuarios;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = findViewById(R.id.checkBox);
        textViewCB = findViewById(R.id.textCheckBox);
        sexoRG = findViewById(R.id.radioGroup);
        textViewS = findViewById(R.id.textSeleccion);
        textoNombre = findViewById(R.id.textNombre);
        textoApellidos = findViewById(R.id.textApellidos);
        botonAñadir = findViewById(R.id.botonAñadir);
        spinner = findViewById(R.id.spinner);

        sexoRG.setOnCheckedChangeListener((radioGroup,i)->{
            if(i==R.id.radioHombre) {
                textViewS.setText("HOMBRE");
            } else if (i==R.id.radioMujer) {
                textViewS.setText("MUJER");
            } else if (i==R.id.radioOtro) {
                textViewS.setText("OTRO");
            } else {
                textViewS.setText("ERROR EN LA SELECCIÓN DE SEXO");
            }
        });

        checkBox.setOnClickListener(view -> {
            if (checkBox.isChecked()){
                textViewCB.setText("CheckBox activado");
                textViewCB.setBackgroundColor(0xFF86FF39);
                sexoRG.setVisibility(View.VISIBLE);
            }
            else{
                textViewCB.setText("CheckBox desactivado");
                textViewCB.setBackgroundColor(0xFFFABAB2);
                sexoRG.setVisibility(View.INVISIBLE);
            }
        });

        if (savedInstanceState==null){
            usuarios = new ArrayList<>();
            usuarios.add(new Usuario("Jose","Rodrigo"));
            usuarios.add(new Usuario("Marta","Serrano"));
            usuarios.add(new Usuario("Paula","Manuel"));
        } else { // Si no es nulo hay que deserializar lo que ha guardado
            usuarios = (ArrayList<Usuario>) savedInstanceState.getSerializable("claveUsuario");
        }


        ArrayAdapter<Usuario> miAdaptador = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item,usuarios
        );
        spinner.setAdapter(miAdaptador);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewS.setText(usuarios.get(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        botonAñadir.setOnClickListener(view -> {
            usuarios.add(new Usuario(textoNombre.getText().toString(),textoApellidos.getText().toString()));
            textoNombre.setText("");
            textoApellidos.setText("");
            miAdaptador.notifyDataSetChanged();
        });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("claveUsuario",(Serializable)usuarios);
    } // Para que guarde los usuarios cuando pasa de vertical a horizontal
}