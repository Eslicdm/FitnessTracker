package com.eslirodrigues.fitnesstracker2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMain = findViewById(R.id.recycler_main);

        rvMain.setLayoutManager(new GridLayoutManager(this, 2));
        MainAdapter adapter = new MainAdapter();
        rvMain.setAdapter(adapter);
    }

    public class MainHolder extends RecyclerView.ViewHolder {

        public MainHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class MainAdapter extends RecyclerView.Adapter<MainHolder> {


        @NonNull
        @Override
        public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainHolder(getLayoutInflater().inflate(R.layout.main_items, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }
}