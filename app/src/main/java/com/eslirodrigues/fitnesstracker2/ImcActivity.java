package com.eslirodrigues.fitnesstracker2;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
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

            int weight = Integer.parseInt(editWeight.getText().toString());
            int height = Integer.parseInt(editHeight.getText().toString());

            double imcResult = calculateImc(weight, height);
            int imcResponse = responseImc(imcResult);

            AlertDialog dialog = new AlertDialog.Builder(ImcActivity.this)
                    .setTitle(getString(R.string.imc_response, imcResult))
                    .setMessage(imcResponse)
                    .setPositiveButton(android.R.string.ok, (dialog1, which) -> {})
                    .setNegativeButton(R.string.save, (dialog1, which) -> {
                        new Thread(() -> {
                            long calcId = SqlHelper.getINSTANCE(ImcActivity.this).addItem("imc", imcResult);
                            runOnUiThread(() -> {
                                if (calcId > 0) {
                                    Toast.makeText(ImcActivity.this, R.string.calc_saved, Toast.LENGTH_SHORT).show();
                                    openListCalcActivity();
                                }
                            });
                        }).start();
                    })
                    .create();

            dialog.show();

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editWeight.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(editHeight.getWindowToken(), 0);
        });
    }

    public void openListCalcActivity() {
        Intent intent = new Intent(this, ListCalcActivity.class);
        intent.putExtra("type", "imc");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                openListCalcActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @StringRes
    private int responseImc(double imc) {
        if (imc < 15) {
            return R.string.imc_severely_low_weight;
        } else if (imc < 16) {
            return R.string.imc_very_low_weight;
        } else if (imc < 18.5) {
            return R.string.imc_low_weight;
        } else if (imc < 25) {
            return R.string.normal;
        } else if (imc < 30) {
            return R.string.imc_high_weight;
        } else if (imc < 35) {
            return R.string.imc_so_high_weight;
        } else if (imc < 40) {
            return R.string.imc_severely_high_weight;
        } else {
            return R.string.imc_extreme_weight;
        }
    }

    private double calculateImc(int weight, int height) {
        return weight / (((double) height / 100) * ((double) height / 100));
    }

    private boolean validate() {
        return (!editHeight.getText().toString().isEmpty()
            && !editHeight.getText().toString().startsWith("0")
            && !editWeight.getText().toString().isEmpty()
            && !editWeight.getText().toString().startsWith("0"));
    }
}