package com.example.one_mobile.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.one_mobile.R;
import com.example.one_mobile.data.model.EvaluationSite;

public class EvaluationDetailsDialog extends DialogFragment {

    private EvaluationSite evaluationSite;
    private OnActionListener listener;

    public interface OnActionListener {
        void onModify(EvaluationSite evaluationSite);
        void onDelete(EvaluationSite evaluationSite);
    }

    public EvaluationDetailsDialog(EvaluationSite evaluationSite, OnActionListener listener) {
        this.evaluationSite = evaluationSite;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_evaluation_details, container, false);

        TextView siteTextView = view.findViewById(R.id.text_site_details);
        TextView risqueTextView = view.findViewById(R.id.text_risque_details);
        TextView descTextView = view.findViewById(R.id.text_description_details);
        TextView dateTextView = view.findViewById(R.id.text_date_details);
        TextView validityTextView = view.findViewById(R.id.text_validity_details);

        // Remplir les données
        siteTextView.setText("Site: " + evaluationSite.getSite().getLib());
        risqueTextView.setText("Risque: " + evaluationSite.getEvaluation().getRisque().getLib());
        descTextView.setText(evaluationSite.getEvaluation().getDesc());
        //dateTextView.setText("Date: " + evaluationSite.getDate());
       // validityTextView.setText("Validité: " + evaluationSite.getValidite());

        // Scrolling pour la description
        ScrollView descScroll = view.findViewById(R.id.scroll_description);

        // Boutons
        Button modifyButton = view.findViewById(R.id.button_modify);
        Button deleteButton = view.findViewById(R.id.button_delete);
        Button cancelButton = view.findViewById(R.id.button_cancel);

        modifyButton.setOnClickListener(v -> {
            if (listener != null) listener.onModify(evaluationSite);
            dismiss();
        });

        deleteButton.setOnClickListener(v -> {
            if (listener != null) listener.onDelete(evaluationSite);
            dismiss();
        });

        cancelButton.setOnClickListener(v -> dismiss());

        return view;
    }
}

