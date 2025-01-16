package com.example.one_mobile.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.one_mobile.R;
import com.example.one_mobile.data.model.EvaluationSiteWithDetails;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModel;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModelFactory;

import java.util.List;

public class EvaluationSiteAdapter extends RecyclerView.Adapter<EvaluationSiteAdapter.ViewHolder> {

    private List<EvaluationSiteWithDetails> evaluationSites;
    private OnItemClickListener listener;
    private LifecycleOwner lifecycleOwner;

    public interface OnItemClickListener {
        void onItemClick(EvaluationSiteWithDetails evaluationSite);
    }

    public EvaluationSiteAdapter(List<EvaluationSiteWithDetails> evaluationSites, LifecycleOwner lifecycleOwner) {
        this.evaluationSites = evaluationSites;
        this.lifecycleOwner = lifecycleOwner;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evaluation_site, parent, false);

        EvaluationSiteViewModelFactory factory = new EvaluationSiteViewModelFactory(parent.getContext());
        EvaluationSiteViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner, factory).get(EvaluationSiteViewModel.class);
        return new ViewHolder(view, viewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EvaluationSiteWithDetails evaluationSite = evaluationSites.get(position);

        // Fill in the static data
        holder.siteTextView.setText("Site: " + evaluationSite.getSite().getLib());
        holder.descriptionTextView.setText("Description: " + getShortDescription(evaluationSite.getEvaluation().getDesc()));

        // Observe the LiveData and update the UI when data is available
        holder.viewModel.getEvaluationDTOByEvaluation(evaluationSite.getEvaluation()).observe(lifecycleOwner, evaluationDTO -> {
            if (evaluationDTO != null) {
                holder.risqueTextView.setText("Risque: " + evaluationDTO.getRisque().getLib());
                holder.origineTextView.setText("Origine: " + evaluationDTO.getOrigine().getLib());
            }
        });

        // Listener for clicks
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
        TextView siteTextView, origineTextView, risqueTextView, descriptionTextView;
        EvaluationSiteViewModel viewModel;

        public ViewHolder(@NonNull View itemView, EvaluationSiteViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
            siteTextView = itemView.findViewById(R.id.text_site);
            origineTextView = itemView.findViewById(R.id.text_origine);
            risqueTextView = itemView.findViewById(R.id.text_risque);
            descriptionTextView = itemView.findViewById(R.id.text_description);
        }
    }
}