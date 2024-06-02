package com.example.wordsearchapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tx, rfid;
    //scanBtn is the non functional button displaying found/ not found information
    Button scanBtn, clearBtn;
    SwitchCompat switch1;
    EditText et, ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        scanBtn = (Button) findViewById(R.id.scanBtn);
        clearBtn = findViewById(R.id.clearBtn);

        et = findViewById(R.id.edittext);
        ed = findViewById(R.id.edit);

        switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (switch1.getText().equals("Manual")){
                switch1.setText("electronic");
                ed.setEnabled(false);
            }
            else{
                switch1.setText("Manual");
                ed.setEnabled(true);
            }

        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //Toast.makeText(MainActivity.this, "strings match", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //tx.setText("Items "+et.getLineCount());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Toast.makeText(MainActivity.this, "This text has changed", Toast.LENGTH_SHORT).show();
                String str = et.getText().toString();
                String str1 = ed.getText().toString();
                //tx.setText(str+"-"+str1);
                String[] newString = str.split("\n");
                int intRows = newString.length;
                String s;
                s = newString[intRows-1];
                //tx.setText(str1);
                if(s.equals(str1)) {
                    scanBtn.setBackgroundColor(Color.GREEN);
                    scanBtn.setText("RFID found");
                    et.setText("");
                    et.clearFocus();
                    et.setHint("Tag FOUND");

                    et.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                        et.setInputType(InputType.TYPE_NULL);
                    }
                    clearBtn.setEnabled(true);


                }
            }
        });

    }

    public void onClear(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        finish();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            overridePendingTransition(0,0);
        }
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            overridePendingTransition(0,0);
        }

    }
}