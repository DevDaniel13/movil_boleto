package com.example.boleto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Spinner spnPaises;
    private EditText etNombre;
    private Button btnRecibo;
    private RadioButton radioButton1, radioButton2;
    private DatePicker dpFecha;
    private String destino;
    private TextView lblDatos;
    private EditText etEdad;
    private Button btnCerrar;
    private Button btnLimpiar;

    private void Cerrar(){
        AlertDialog.Builder confirmar=new AlertDialog.Builder(this);
        confirmar.setTitle("¿Cerrar APP?");
        confirmar.setMessage("Se descartara toda la informacion ingresada");
        confirmar.setPositiveButton("Confirmar",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface, int i){
                finish();
            }
        });
        confirmar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface, int i){

            }
        });
        confirmar.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnPaises =(Spinner) findViewById(R.id.spnPaises);
        etNombre=(EditText) findViewById(R.id.txtNombre);
        btnRecibo=(Button) findViewById(R.id.btnRecibo);
        dpFecha=(DatePicker) findViewById(R.id.dpFecha);
        radioButton1=(RadioButton) findViewById(R.id.rbSencillo);
        radioButton2=(RadioButton) findViewById(R.id.rbDoble);
        lblDatos=(TextView) findViewById(R.id.lblBoletoDatos);
        etEdad=(EditText) findViewById(R.id.txtEdad);
        btnCerrar=(Button) findViewById(R.id.btnCerrar);
        btnLimpiar=(Button) findViewById(R.id.btnLimpiar);
        ArrayAdapter<String> Adaptador=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.paises));
        spnPaises.setAdapter(Adaptador);
        spnPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"Selecciono el pais " + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                destino=adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }});
        btnRecibo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String nombre=etNombre.getText().toString();
                int edad= Integer.parseInt(etEdad.getText().toString());
                int year=dpFecha.getYear();
                int mes=dpFecha.getMonth()-1;
                int dia=dpFecha.getDayOfMonth();
                Date fecha=new Date(year,mes,dia);
                int tipoViaje;
                if(radioButton1.isChecked()==true){
                    tipoViaje=1;
                }
                else{
                    tipoViaje=2;
                }
                Boleto boleto=new Boleto(nombre,destino,tipoViaje,fecha);
                lblDatos.setText(boleto.toString()+"\nSubtotal: $" +boleto.calcularSubtotal()
                        +"\nImpuesto: $"+boleto.calcularImpuesto()+
                        "\nDescuento: $"+boleto.calcularDescuento(edad)+
                        "\nTotal a pagar: $"+boleto.calcularTotal(boleto.calcularDescuento(edad)));
            }
        });
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Cerrar();
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                etNombre.setText("");
                etEdad.setText("");
                lblDatos.setText("");
            }
        });
    }
}
