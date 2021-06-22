package com.example.inventariofisico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    EditText ctScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnScan;


        btnScan = (Button) findViewById(R.id.button);
        ctScan =(EditText) findViewById(R.id.editTextTextPersonName);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator _Integrator = new IntentIntegrator(MainActivity.this);
                
                _Integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                _Integrator.setPrompt("Lector - Leoni");
                _Integrator.setCameraId(0);
                _Integrator.setBeepEnabled(true);
                _Integrator.setBarcodeImageEnabled(true);
                _Integrator.initiateScan();
                
            }
        });



    }
    
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {

        IntentResult _IntentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        
        if(_IntentResult!=null){
            if(_IntentResult.getContents()==null){
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, _IntentResult.getContents(), Toast.LENGTH_SHORT).show();
                ctScan.setText(_IntentResult.getContents());

            }
        }
        
        super.onActivityResult(requestCode, resultCode, data);
    }
    
}