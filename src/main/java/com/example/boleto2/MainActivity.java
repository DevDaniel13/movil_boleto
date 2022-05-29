package com.example.boleto2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spnPaises;
    private EditText txtNum;
    private EditText txtNombre;
    private EditText txtEdad;
    private EditText txtPrecio;
    private RadioButton rbSencillo, rbDoble;
    private String destino;
    private EditText txtFecha;
    private TextView lblDatos;
    private Button btnEnviar;
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
        txtNombre=(EditText) findViewById(R.id.txtNombre);
        txtPrecio=(EditText) findViewById(R.id.txtPrecio);
        txtNum=(EditText) findViewById(R.id.txtNum);
        btnEnviar=(Button) findViewById(R.id.btnEnviar);
        txtFecha=(EditText) findViewById(R.id.txtFecha);
        rbSencillo=(RadioButton) findViewById(R.id.rbSencillo);
        rbDoble=(RadioButton) findViewById(R.id.rbDoble);
        lblDatos=(TextView) findViewById(R.id.lblBoletoDatos);
        txtEdad=(EditText) findViewById(R.id.txtEdad);
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

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNombre.getText().toString().matches("") || txtEdad.getText().toString().matches("") || txtPrecio.getText().toString().matches("") || txtNum.getText().toString().matches("") || txtFecha.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"Existe un dato invalido",Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(txtNum.getText().toString());
                    String nombre = txtNombre.getText().toString();
                    int edad = Integer.parseInt(txtEdad.getText().toString());
                    int precio = Integer.parseInt(txtPrecio.getText().toString());
                    String fecha = txtFecha.getText().toString();
                    int viaje = 0;
                    if(rbSencillo.isChecked() == true){
                        viaje = 1;
                    }else if (rbDoble.isChecked() == true){
                        viaje = 2;
                    }
                    Boleto b = new Boleto(id, nombre, destino, viaje, precio, fecha);
                    double subtotal = b.calcularSubtotal();
                    double impuesto = b.calcularImpuesto();
                    double descuento = b.calcularDescuento(edad);
                    double total = b.calcularTotal(subtotal, impuesto, descuento);
                    lblDatos.setText(b.Imprimir(subtotal, impuesto, descuento, total));
                }
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Cerrar();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                txtNum.setText("");
                txtNombre.setText("");
                txtEdad.setText("");
                txtPrecio.setText("");
                txtFecha.setText("");
                lblDatos.setText("");
            }
        });
    }
}
