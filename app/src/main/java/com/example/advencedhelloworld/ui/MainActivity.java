package com.example.advencedhelloworld.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.advencedhelloworld.R;
import com.example.advencedhelloworld.viewmodel.RiskViewModel;

public class MainActivity extends AppCompatActivity {
    private RiskViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final RiskAdapter adapter = new RiskAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(RiskViewModel.class);

        // Observer les données du ViewModel pour les mettre à jour dans l'UI
        viewModel.getRisks().observe(this, adapter::setRisks);
    }
}
