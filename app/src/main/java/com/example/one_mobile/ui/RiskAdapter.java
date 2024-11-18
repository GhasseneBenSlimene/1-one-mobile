package com.example.one_mobile.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.one_mobile.R;
import com.example.one_mobile.data.local.RiskEvaluation;

import java.util.ArrayList;
import java.util.List;

public class RiskAdapter extends RecyclerView.Adapter<RiskAdapter.RiskViewHolder> {

    private List<RiskEvaluation> risks = new ArrayList<>();

    public void setRisks(List<RiskEvaluation> risks) {
        this.risks = risks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RiskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_risk, parent, false);
        return new RiskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RiskViewHolder holder, int position) {
        RiskEvaluation risk = risks.get(position);
        holder.titleTextView.setText(risk.getTitle());
        holder.descriptionTextView.setText(risk.getDescription());
    }

    @Override
    public int getItemCount() {
        return risks.size();
    }

    static class RiskViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView descriptionTextView;

        public RiskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_title);
            descriptionTextView = itemView.findViewById(R.id.text_description);
        }
    }
}
