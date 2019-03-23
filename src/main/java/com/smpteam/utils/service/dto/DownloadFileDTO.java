package com.smpteam.utils.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DownloadFile entity.
 */
public class DownloadFileDTO implements Serializable {

    private String id;

    private String name;

    private String downloadTime;

    private Instant count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(String downloadTime) {
        this.downloadTime = downloadTime;
    }

    public Instant getCount() {
        return count;
    }

    public void setCount(Instant count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DownloadFileDTO downloadFileDTO = (DownloadFileDTO) o;
        if (downloadFileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), downloadFileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DownloadFileDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", downloadTime='" + getDownloadTime() + "'" +
            ", count='" + getCount() + "'" +
            "}";
    }
}
