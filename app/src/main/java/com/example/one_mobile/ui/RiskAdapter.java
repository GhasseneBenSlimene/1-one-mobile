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

    @NonNull
    @Override
    public RiskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.risk_item, parent, false);
        return new RiskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RiskViewHolder holder, int position) {
        RiskEvaluation currentRisk = risks.get(position);
        holder.titleTextView.setText(currentRisk.getTitle());
        holder.descriptionTextView.setText(currentRisk.getDescription());
    }

    @Override
    public int getItemCount() {
        return risks.size();
    }

    public void setRisks(List<RiskEvaluation> risks) {
        this.risks = risks;
        notifyDataSetChanged();
    }

    static class RiskViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView descriptionTextView;

        public RiskViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.risk_title);
            descriptionTextView = itemView.findViewById(R.id.risk_description);
        }
    }
}
