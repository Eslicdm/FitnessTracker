package com.eslirodrigues.fitnesstracker2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListCalcActivity extends AppCompatActivity {

    private RecyclerView recyclerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_calc);

        Bundle extras = getIntent().getExtras();

        recyclerResult = findViewById(R.id.recycler_list_calc);

        if (extras != null) {
            String type = extras.getString("type");
            List<Register> registers = SqlHelper.getINSTANCE(this).getRegisterBy(type);

            ListCalcAdapter adapter = new ListCalcAdapter(registers);
            recyclerResult.setLayoutManager(new LinearLayoutManager(this));
            recyclerResult.setAdapter(adapter);
        }
    }

    public class ListCalcHolder extends RecyclerView.ViewHolder {

        public ListCalcHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Register register) {
            String formatted = "";
            try {
                Date sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("pt", "BR")).parse(register.createdDate);
                formatted = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", new Locale("pt", "BR")).format(sdf);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ((TextView) itemView).setText(getString(R.string.list_response, register.response, formatted));
        }
    }

    public class ListCalcAdapter extends RecyclerView.Adapter<ListCalcHolder> {

        List<Register> registers;

        public ListCalcAdapter(List<Register> registers) {
            this.registers = registers;
        }

        @NonNull
        @Override
        public ListCalcHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ListCalcHolder(getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ListCalcHolder holder, int position) {
            Register register = registers.get(position);
            holder.bind(register);

        }

        @Override
        public int getItemCount() {
            return registers.size();
        }
    }
}