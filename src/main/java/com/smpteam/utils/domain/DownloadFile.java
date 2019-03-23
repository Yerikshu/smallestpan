package com.smpteam.utils.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DownloadFile.
 */
@Document(collection = "download_file")
public class DownloadFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("download_time")
    private String downloadTime;

    @Field("count")
    private Instant count;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DownloadFile name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownloadTime() {
        return downloadTime;
    }

    public DownloadFile downloadTime(String downloadTime) {
        this.downloadTime = downloadTime;
        return this;
    }

    public void setDownloadTime(String downloadTime) {
        this.downloadTime = downloadTime;
    }

    public Instant getCount() {
        return count;
    }

    public DownloadFile count(Instant count) {
        this.count = count;
        return this;
    }

    public void setCount(Instant count) {
        this.count = count;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DownloadFile downloadFile = (DownloadFile) o;
        if (downloadFile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), downloadFile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DownloadFile{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", downloadTime='" + getDownloadTime() + "'" +
            ", count='" + getCount() + "'" +
            "}";
    }
}
