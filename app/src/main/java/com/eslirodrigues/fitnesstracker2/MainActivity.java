package com.eslirodrigues.fitnesstracker2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.recycler_main);

        List<MainItems> mainItems = new ArrayList<>();
        mainItems.add(new MainItems(1, R.string.label_imc, R.drawable.ic_baseline_wb_sunny_24, Color.GREEN));
        mainItems.add(new MainItems(2, R.string.label_tmb, R.drawable.ic_baseline_visibility_24, Color.YELLOW));

        rvMain.setLayoutManager(new GridLayoutManager(this, 2));
        MainAdapter adapter = new MainAdapter(mainItems);
        adapter.setListener(id -> {
            switch (id) {
                case 1:
                    startActivity(new Intent(MainActivity.this, ImcActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, TmbActivity.class));
                    break;
            }
        });
        rvMain.setAdapter(adapter);
    }

    public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

        List<MainItems> mainItems;
        OnItemClickListener listener;

        public MainAdapter(List<MainItems> mainItems) {
            this.mainItems = mainItems;
        }

        public void setListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainHolder(getLayoutInflater().inflate(R.layout.main_items, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainHolder holder, int position) {
            MainItems mainItem = mainItems.get(position);
            holder.bind(mainItem);
        }

        @Override
        public int getItemCount() {
            return mainItems.size();
        }

        public class MainHolder extends RecyclerView.ViewHolder {

            public MainHolder(@NonNull View itemView) {
                super(itemView);
            }

            public void bind(MainItems mainItem) {
                TextView txtLabel = itemView.findViewById(R.id.item_txt);
                ImageView imgLabel = itemView.findViewById(R.id.item_img);
                LinearLayout btnChoice = itemView.findViewById(R.id.item_layout);

                btnChoice.setOnClickListener(view -> {
                    listener.onClick(mainItem.getIdLabel());
                });

                txtLabel.setText(mainItem.getTextLabel());
                imgLabel.setImageResource(mainItem.getImageLabel());
                btnChoice.setBackgroundColor(mainItem.getColorLabel());
            }
        }
    }
}