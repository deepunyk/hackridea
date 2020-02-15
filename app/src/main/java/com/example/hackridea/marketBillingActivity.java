package com.example.hackridea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class marketBillingActivity extends AppCompatActivity {

    EditText amtTxt;
    TextView totalTxt;
    Button billBut;
    String finalamt = "";
    int res, val1, val2 = 225;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_billing);

        billBut = (Button)findViewById(R.id.billBut);
        totalTxt = (TextView)findViewById(R.id.totalTxt);
        amtTxt = (EditText)findViewById(R.id.priceTxt);

        amtTxt.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                billBut.setVisibility(View.VISIBLE);

                                return true;
                            }
                        }
                        return false;
                    }
                }
        );

        billBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val1 = Integer.parseInt(amtTxt.getText().toString());
                res = val1*val2;
                totalTxt.setText("Final amount is : "+res);
            }
        });
    }
}
