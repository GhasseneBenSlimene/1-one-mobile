package com.example.one_mobile.ui;

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
import com.example.one_mobile.data.model.EvaluationSiteWithDetails;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class EvaluationDetailsDialog extends DialogFragment {

    private EvaluationSiteWithDetails evaluationSite;
    private OnActionListener listener;

    public interface OnActionListener {
        void onModify(EvaluationSiteWithDetails evaluationSite);
        void onDelete(EvaluationSiteWithDetails evaluationSite);
    }

    public EvaluationDetailsDialog(EvaluationSiteWithDetails evaluationSite, OnActionListener listener) {
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
        TextView matriceTextView = view.findViewById(R.id.text_matrice_details);
        TextView facteurTextView = view.findViewById(R.id.text_facteur_details);
        TextView indiceTextView = view.findViewById(R.id.text_indice_details);

        // Remplir les données
        siteTextView.setText("Site: " + evaluationSite.getSite().getLib());
        //risqueTextView.setText("Risque: " + evaluationSite.getEvaluation().getRisque().getLib());
        descTextView.setText(evaluationSite.getEvaluation().getDesc());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        if (evaluationSite.getEvaluation().getDate() != null) {
            dateTextView.setText("Date: " + dateFormat.format(evaluationSite.getEvaluation().getDate()));
        } else {
            dateTextView.setText("Date: N/A");
        }

        matriceTextView.setText("Matrice: " + evaluationSite.getEvaluation().getMatriceId());



        if (evaluationSite.getEvaluation().getValid() != null) {
            validityTextView.setText("Validité: " + dateFormat.format(evaluationSite.getEvaluation().getValid()));
        } else {
            validityTextView.setText("Validité: N/A");
        }


        facteurTextView.setText("Facteur: " + "Nom du facteur"); // Remplacez par le nom du facteur si nécessaire
        indiceTextView.setText("Indice: " + evaluationSite.getEvaluation().getIndice());

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

