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

        editWeight = findViewById(R.id.edit_weight_imc);
        editHeight = findViewById(R.id.edit_height_imc);

        Button btnSend = findViewById(R.id.btn_send);

        btnSend.setOnClickListener(view -> {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_messages, Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    private boolean validate() {
        return (!editHeight.getText().toString().isEmpty()
            && !editHeight.getText().toString().startsWith("0")
            && !editWeight.getText().toString().isEmpty()
            && !editWeight.getText().toString().startsWith("0"));
    }
}