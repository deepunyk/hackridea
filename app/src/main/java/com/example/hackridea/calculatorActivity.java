package com.example.hackridea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class calculatorActivity extends AppCompatActivity {
    EditText etAcre;
    TextView tvNit,tvPhosp,tvpotass;
    Button btnSub;
    String str="Paddy";
    String acre;
    double n,p,k,n1,p1,k1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        etAcre=(EditText)findViewById(R.id.etAcres);
        tvNit=(TextView)findViewById(R.id.tvNit);
        tvPhosp=(TextView)findViewById(R.id.tvPhosp);
        tvpotass=(TextView)findViewById(R.id.tvPotass);
        btnSub=(Button)findViewById(R.id.btnCalcSub);
        switch(str)
        {
            case "Paddy": n=0.4;
                          p=0.3;
                          k=0.5;
                          break;
            case "Wheat": n=0.5;
                p=0.6;
                k=0.8;
                break;
            case "Red gram": n=0.5;
                p=0.4;
                k=0.3;
                break;
            case "Green gram": n=0.4;
                p=0.8;
                k=0.3;
                break;
            case "Ground nut": n=0.4;
                p=0.6;
                k=0.4;
                break;
            case "Ragi": n=0.6;
                p=0.7;
                k=0.5;
                break;
        }

      btnSub.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              int val = Integer.parseInt( etAcre.getText().toString() );
              n1=val*n;
              p1=val*p;
              k1=val*k;
              tvNit.setText(String.format("Value of N: %.2f Kg",n1));
              tvPhosp.setText(String.format("Value of P: %.2f Kg",p1));
              tvpotass.setText(String.format("Value of K: %.2f Kg",k1));
          }
      });
    }
}
