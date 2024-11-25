package com.example.one_mobile.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class DangersActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangers);

        // Set the title dynamically
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Dangers");
        }

        tableLayout = findViewById(R.id.table_dangers);
        Button buttonAjouter = findViewById(R.id.button_ajouter);

        // Handle "Ajouter" button click
        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialog();
            }
        });
    }

    private void openAddDialog() {
        // Inflate the custom layout for the dialog
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_danger, null);

        EditText editTextCode = dialogView.findViewById(R.id.editText_code);
        EditText editTextLibelle = dialogView.findViewById(R.id.editText_libelle);
        EditText editTextDescription = dialogView.findViewById(R.id.editText_description);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle("Ajouter un danger");

        builder.setPositiveButton("Save", (dialog, which) -> {
            String code = editTextCode.getText().toString().trim();
            String libelle = editTextLibelle.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();

            if (!code.isEmpty() && !libelle.isEmpty() && !description.isEmpty()) {
                addEntryToTable(code, libelle, description);
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    private void addEntryToTable(String code, String libelle, String description) {
        TableRow tableRow = new TableRow(this);

        TextView textCode = new TextView(this);
        textCode.setText(code);
        tableRow.addView(textCode);

        TextView textLibelle = new TextView(this);
        textLibelle.setText(libelle);
        tableRow.addView(textLibelle);

        TextView textDescription = new TextView(this);
        textDescription.setText(description);
        tableRow.addView(textDescription);

        // Add action buttons
        Button buttonDelete = new Button(this);
        buttonDelete.setText("Delete");
        buttonDelete.setOnClickListener(v -> tableLayout.removeView(tableRow));
        tableRow.addView(buttonDelete);

        tableLayout.addView(tableRow);
    }
}
