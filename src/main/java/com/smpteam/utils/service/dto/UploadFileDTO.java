package com.smpteam.utils.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UploadFile entity.
 */
public class UploadFileDTO implements Serializable {

    private String id;

    private String uploadTime;

    private String nameId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String downloadFileId) {
        this.nameId = downloadFileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UploadFileDTO uploadFileDTO = (UploadFileDTO) o;
        if (uploadFileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uploadFileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UploadFileDTO{" +
            "id=" + getId() +
            ", uploadTime='" + getUploadTime() + "'" +
            ", name=" + getNameId() +
            "}";
    }
}
