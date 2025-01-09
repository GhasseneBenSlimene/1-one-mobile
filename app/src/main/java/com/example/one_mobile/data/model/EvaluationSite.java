package com.example.one_mobile.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "evaluation_sites",
        foreignKeys = {
                @ForeignKey(entity = Site.class, parentColumns = "id", childColumns = "site_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Evaluation.class, parentColumns = "id", childColumns = "evaluation_id", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("site_id"), @Index("evaluation_id")}
)
public class EvaluationSite {
    @PrimaryKey
    private long id;

    @ColumnInfo(name = "site_id")
    private long siteId;

    @ColumnInfo(name = "evaluation_id")
    private long evaluationId;

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(long evaluationId) {
        this.evaluationId = evaluationId;
    }
    @Override
    public String toString() {
        return "EvaluationSite{" +
                "id=" + id +
                ", siteId=" + siteId +
                ", evaluationId=" + evaluationId +
                '}';
    }
}
