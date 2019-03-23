package com.smpteam.utils.service.mapper;

import com.smpteam.utils.domain.DownloadFile;
import com.smpteam.utils.domain.UploadFile;
import com.smpteam.utils.service.dto.UploadFileDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-03-05T15:26:34+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class UploadFileMapperImpl implements UploadFileMapper {

    @Autowired
    private DownloadFileMapper downloadFileMapper;

    @Override
    public List<UploadFile> toEntity(List<UploadFileDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UploadFile> list = new ArrayList<UploadFile>( dtoList.size() );
        for ( UploadFileDTO uploadFileDTO : dtoList ) {
            list.add( toEntity( uploadFileDTO ) );
        }

        return list;
    }

    @Override
    public List<UploadFileDTO> toDto(List<UploadFile> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UploadFileDTO> list = new ArrayList<UploadFileDTO>( entityList.size() );
        for ( UploadFile uploadFile : entityList ) {
            list.add( toDto( uploadFile ) );
        }

        return list;
    }

    @Override
    public UploadFileDTO toDto(UploadFile uploadFile) {
        if ( uploadFile == null ) {
            return null;
        }

        UploadFileDTO uploadFileDTO = new UploadFileDTO();

        String id = uploadFileNameId( uploadFile );
        if ( id != null ) {
            uploadFileDTO.setNameId( id );
        }
        uploadFileDTO.setId( uploadFile.getId() );
        uploadFileDTO.setUploadTime( uploadFile.getUploadTime() );

        return uploadFileDTO;
    }

    @Override
    public UploadFile toEntity(UploadFileDTO uploadFileDTO) {
        if ( uploadFileDTO == null ) {
            return null;
        }

        UploadFile uploadFile = new UploadFile();

        uploadFile.setName( downloadFileMapper.fromId( uploadFileDTO.getNameId() ) );
        uploadFile.setId( uploadFileDTO.getId() );
        uploadFile.setUploadTime( uploadFileDTO.getUploadTime() );

        return uploadFile;
    }

    private String uploadFileNameId(UploadFile uploadFile) {
        if ( uploadFile == null ) {
            return null;
        }
        DownloadFile name = uploadFile.getName();
        if ( name == null ) {
            return null;
        }
        String id = name.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
