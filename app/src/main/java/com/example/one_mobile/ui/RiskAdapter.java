package com.example.one_mobile.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.one_mobile.R;
import com.example.one_mobile.data.local.RiskEvaluation;

import java.util.List;

public class RiskAdapter extends RecyclerView.Adapter<RiskAdapter.RiskViewHolder> {
    private final Context context;
    private List<RiskEvaluation> riskList;
    private final OnRiskActionListener listener;

    public interface OnRiskActionListener {
        void onEdit(RiskEvaluation risk);
        void onDelete(RiskEvaluation risk);
    }

    public RiskAdapter(Context context, OnRiskActionListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void submitList(List<RiskEvaluation> risks) {
        this.riskList = risks;
        notifyDataSetChanged();  // Notifie le RecyclerView des changements
    }


    @NonNull
    @Override
    public RiskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.risk_item, parent, false);
        return new RiskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RiskViewHolder holder, int position) {
        RiskEvaluation risk = riskList.get(position);
        holder.tvTitle.setText(risk.getTitle());
        holder.tvDescription.setText(risk.getDescription());

        holder.btnEdit.setOnClickListener(v -> {
            listener.onEdit(risk);  // Appelle la méthode onEdit avec l'objet RiskEvaluation
        });

        holder.btnDelete.setOnClickListener(v -> {
            listener.onDelete(risk);  // Appelle la méthode onDelete avec l'objet RiskEvaluation
        });

    }

    @Override
    public int getItemCount() {
        return (riskList != null) ? riskList.size() : 0;
    }

    static class RiskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        Button btnEdit, btnDelete;

        public RiskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvRiskTitle);
            tvDescription = itemView.findViewById(R.id.tvRiskDescription);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
