package com.smpteam.utils.service.mapper;

import com.smpteam.utils.domain.DownloadFile;
import com.smpteam.utils.service.dto.DownloadFileDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-03-05T15:26:34+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class DownloadFileMapperImpl implements DownloadFileMapper {

    @Override
    public DownloadFile toEntity(DownloadFileDTO dto) {
        if ( dto == null ) {
            return null;
        }

        DownloadFile downloadFile = new DownloadFile();

        downloadFile.setId( dto.getId() );
        downloadFile.setName( dto.getName() );
        downloadFile.setDownloadTime( dto.getDownloadTime() );
        downloadFile.setCount( dto.getCount() );

        return downloadFile;
    }

    @Override
    public DownloadFileDTO toDto(DownloadFile entity) {
        if ( entity == null ) {
            return null;
        }

        DownloadFileDTO downloadFileDTO = new DownloadFileDTO();

        downloadFileDTO.setId( entity.getId() );
        downloadFileDTO.setName( entity.getName() );
        downloadFileDTO.setDownloadTime( entity.getDownloadTime() );
        downloadFileDTO.setCount( entity.getCount() );

        return downloadFileDTO;
    }

    @Override
    public List<DownloadFile> toEntity(List<DownloadFileDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DownloadFile> list = new ArrayList<DownloadFile>( dtoList.size() );
        for ( DownloadFileDTO downloadFileDTO : dtoList ) {
            list.add( toEntity( downloadFileDTO ) );
        }

        return list;
    }

    @Override
    public List<DownloadFileDTO> toDto(List<DownloadFile> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DownloadFileDTO> list = new ArrayList<DownloadFileDTO>( entityList.size() );
        for ( DownloadFile downloadFile : entityList ) {
            list.add( toDto( downloadFile ) );
        }

        return list;
    }
}
