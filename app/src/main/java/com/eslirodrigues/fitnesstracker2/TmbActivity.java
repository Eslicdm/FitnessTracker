package com.eslirodrigues.fitnesstracker2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TmbActivity extends AppCompatActivity {

    private EditText editTmbHeight;
    private EditText editTmbWeight;
    private EditText editTmbAge;
    private Button btnSendTmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmb);

        editTmbWeight = findViewById(R.id.edit_weight_tmb);
        editTmbHeight = findViewById(R.id.edit_height_tmb);
        editTmbAge = findViewById(R.id.edit_age_tmb);
        btnSendTmb = findViewById(R.id.btn_send_tmb);

        btnSendTmb.setOnClickListener(view -> {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_messages, Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    private boolean validate() {
        return (!editTmbHeight.getText().toString().isEmpty()
                && !editTmbHeight.getText().toString().startsWith("0")
                && !editTmbWeight.getText().toString().isEmpty()
                && !editTmbWeight.getText().toString().startsWith("0")
                && !editTmbAge.getText().toString().isEmpty()
                && !editTmbAge.getText().toString().startsWith("0"));
    }
}