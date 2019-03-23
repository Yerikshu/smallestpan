package com.smpteam.utils.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UploadFile.
 */
@Document(collection = "upload_file")
public class UploadFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("upload_time")
    private String uploadTime;

    @DBRef
    @Field("name")
    private DownloadFile name;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public UploadFile uploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
        return this;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public DownloadFile getName() {
        return name;
    }

    public UploadFile name(DownloadFile downloadFile) {
        this.name = downloadFile;
        return this;
    }

    public void setName(DownloadFile downloadFile) {
        this.name = downloadFile;
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
        UploadFile uploadFile = (UploadFile) o;
        if (uploadFile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uploadFile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UploadFile{" +
            "id=" + getId() +
            ", uploadTime='" + getUploadTime() + "'" +
            "}";
    }
}
