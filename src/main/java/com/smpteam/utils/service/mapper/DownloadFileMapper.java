package com.smpteam.utils.service.mapper;

import com.smpteam.utils.domain.*;
import com.smpteam.utils.service.dto.DownloadFileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DownloadFile and its DTO DownloadFileDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DownloadFileMapper extends EntityMapper<DownloadFileDTO, DownloadFile> {



    default DownloadFile fromId(String id) {
        if (id == null) {
            return null;
        }
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setId(id);
        return downloadFile;
    }
}
