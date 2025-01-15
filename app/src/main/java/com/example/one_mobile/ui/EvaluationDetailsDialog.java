package com.example.one_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.one_mobile.R;
import com.example.one_mobile.data.model.EvaluationSiteWithDetails;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModel;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModelFactory;

import java.text.SimpleDateFormat;

public class EvaluationDetailsDialog extends DialogFragment {

    private EvaluationSiteWithDetails evaluationSite;
    private OnActionListener listener;
    private EvaluationSiteViewModel viewModel;

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
        EvaluationSiteViewModelFactory factory = new EvaluationSiteViewModelFactory(requireContext());
        viewModel = new ViewModelProvider(this, factory).get(EvaluationSiteViewModel.class);


        TextView siteTextView = view.findViewById(R.id.text_site_details);
        TextView risqueTextView = view.findViewById(R.id.text_risque_details);
        TextView descTextView = view.findViewById(R.id.text_description_details);
        TextView dateTextView = view.findViewById(R.id.text_date_details);
        TextView validityTextView = view.findViewById(R.id.text_validity_details);

        // Remplir les données
        siteTextView.setText("Site: " + evaluationSite.getSite().getLib());
//        risqueTextView.setText("Risque: " + evaluationSite.getEvaluation().getRisque().getLib());
        descTextView.setText(evaluationSite.getEvaluation().getDesc());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateTextView.setText("Date: " + dateFormat.format(evaluationSite.getEvaluation().getDate()));
       // validityTextView.setText("Validité: " + evaluationSite.getValidite());

        // Scrolling pour la description
        ScrollView descScroll = view.findViewById(R.id.scroll_description);

        // Boutons
        Button modifyButton = view.findViewById(R.id.button_modify);
        Button deleteButton = view.findViewById(R.id.button_delete);
        Button cancelButton = view.findViewById(R.id.button_cancel);

        modifyButton.setOnClickListener(v -> {
            if (listener != null) {
                Intent intent = new Intent(getContext(), EvaluationSiteUpdateForm.class);
                intent.putExtra("evaluationSiteId", evaluationSite.getEvaluationSite().getId());
                startActivity(intent);
            }
            dismiss();
        });

        deleteButton.setOnClickListener(v -> {
            deleteEvaluationSite();
        });

        cancelButton.setOnClickListener(v -> dismiss());

        return view;
    }



    private void deleteEvaluationSite() {
        viewModel.deleteEvaluationSite(evaluationSite.getEvaluationSite().getId()).observe(this, isDeleted -> {
            if (isDeleted) {
                Toast.makeText(getContext(), "EvaluationSite deleted successfully!", Toast.LENGTH_SHORT).show();
                if (listener != null) listener.onDelete(evaluationSite);
                dismiss();
            } else {
                Toast.makeText(getContext(), "Failed to delete EvaluationSite", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

