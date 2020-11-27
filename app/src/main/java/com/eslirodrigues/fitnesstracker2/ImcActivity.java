package com.eslirodrigues.fitnesstracker2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity {

    private EditText editHeight;
    private EditText editWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

    }

    private boolean validate() {
        return (!editHeight.getText().toString().isEmpty()
            && !editHeight.getText().toString().startsWith("0")
            && !editWeight.getText().toString().isEmpty()
            && !editWeight.getText().toString().startsWith("0"));
    }
}