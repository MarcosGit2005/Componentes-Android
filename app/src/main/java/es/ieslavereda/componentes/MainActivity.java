package es.ieslavereda.componentes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private TextView textViewCB;
    private TextView textViewS;
    private RadioGroup sexoRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = findViewById(R.id.checkBox);
        textViewCB = findViewById(R.id.textCheckBox);
        sexoRG = findViewById(R.id.radioGroup);
        textViewS = findViewById(R.id.textSeleccion);

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
            }
            else{
                textViewCB.setText("CheckBox desactivado");
                textViewCB.setBackgroundColor(0xFFFABAB2);
            }
        });
    }
}