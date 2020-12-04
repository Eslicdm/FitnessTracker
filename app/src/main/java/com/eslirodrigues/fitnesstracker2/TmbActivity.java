package com.eslirodrigues.fitnesstracker2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static androidx.core.os.LocaleListCompat.create;

public class TmbActivity extends AppCompatActivity {

    private EditText editTmbHeight;
    private EditText editTmbWeight;
    private EditText editTmbAge;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmb);

        editTmbWeight = findViewById(R.id.edit_weight_tmb);
        editTmbHeight = findViewById(R.id.edit_height_tmb);
        editTmbAge = findViewById(R.id.edit_age_tmb);
        Button btnSendTmb = findViewById(R.id.btn_send_tmb);
        spinner = findViewById(R.id.spinner_tmb);

        btnSendTmb.setOnClickListener(view -> {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_messages, Toast.LENGTH_SHORT).show();
                return;
            }

            int height = Integer.parseInt(editTmbHeight.getText().toString());
            int weight = Integer.parseInt(editTmbWeight.getText().toString());
            int age = Integer.parseInt(editTmbAge.getText().toString());

            double tmbResult = calculateTmb(height, weight, age);

            double tmbTotal = tmbResponse(tmbResult);

            AlertDialog dialog = new AlertDialog.Builder(TmbActivity.this)
                    .setTitle(getString(R.string.tmb_response, tmbTotal))
                    .setPositiveButton(android.R.string.ok, (dialog1, which) -> {})
                    .setNegativeButton(R.string.save, (dialog1, which) -> {
                        new Thread(() -> {
                            long calcId = SqlHelper.getINSTANCE(TmbActivity.this).addItem("tmb", tmbTotal);
                            runOnUiThread(() -> {
                                if (calcId > 0) {
                                    Toast.makeText(TmbActivity.this, R.string.calc_saved, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }).start();
                    })
                    .create();

            dialog.show();

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editTmbWeight.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(editTmbHeight.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(editTmbAge.getWindowToken(), 0);
        });


    }

    private double calculateTmb(int height, int weight, int age) {
        return 66 + (weight * 13.8) + (5 * height) - (6.8 * age);
    }

    private double tmbResponse(double tmb) {
        int index = spinner.getSelectedItemPosition();
        switch (index) {
            case 0:
                return tmb * 1.2;
            case 1:
                return tmb * 1.375;
            case 2:
                return tmb * 1.55;
            case 3:
                return tmb * 1.725;
            case 4:
                return tmb * 1.9;
            default:
                return 0;
        }
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