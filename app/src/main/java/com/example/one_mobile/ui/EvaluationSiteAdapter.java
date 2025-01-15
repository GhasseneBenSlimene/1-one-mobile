package com.example.one_mobile.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.one_mobile.R;
import com.example.one_mobile.data.model.EvaluationSiteWithDetails;

import java.util.List;

public class EvaluationSiteAdapter extends RecyclerView.Adapter<EvaluationSiteAdapter.ViewHolder> {

    private List<EvaluationSiteWithDetails> evaluationSites;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(EvaluationSiteWithDetails evaluationSite);
    }

    public EvaluationSiteAdapter(List<EvaluationSiteWithDetails> evaluationSites) {
        this.evaluationSites = evaluationSites;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evaluation_site, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EvaluationSiteWithDetails evaluationSite = evaluationSites.get(position);

        // Remplir les informations principales
        holder.siteTextView.setText("Site: " + evaluationSite.getSite().getLib());
//        holder.risqueTextView.setText("Risque: " + evaluationSite.getEvaluation().getRisque().getLib());
        holder.descriptionTextView.setText("Description: " + getShortDescription(evaluationSite.getEvaluation().getDesc()));

        // Listener pour les clics
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(evaluationSite);
            }
        });
    }

    @Override
    public int getItemCount() {
        return evaluationSites.size();
    }

    private String getShortDescription(String desc) {
        if (desc == null) {
            return "";
        }
        return desc.length() > 30 ? desc.substring(0, 30) + "..." : desc;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView siteTextView, risqueTextView, descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            siteTextView = itemView.findViewById(R.id.text_site);
            risqueTextView = itemView.findViewById(R.id.text_risque);
            descriptionTextView = itemView.findViewById(R.id.text_description);
        }
    }
}
