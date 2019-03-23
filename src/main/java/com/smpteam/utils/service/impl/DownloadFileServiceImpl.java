package com.smpteam.utils.service.impl;

import com.smpteam.utils.service.DownloadFileService;
import com.smpteam.utils.domain.DownloadFile;
import com.smpteam.utils.repository.DownloadFileRepository;
import com.smpteam.utils.service.dto.DownloadFileDTO;
import com.smpteam.utils.service.mapper.DownloadFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing DownloadFile.
 */
@Service
public class DownloadFileServiceImpl implements DownloadFileService {

    private final Logger log = LoggerFactory.getLogger(DownloadFileServiceImpl.class);

    private final DownloadFileRepository downloadFileRepository;

    private final DownloadFileMapper downloadFileMapper;

    public DownloadFileServiceImpl(DownloadFileRepository downloadFileRepository, DownloadFileMapper downloadFileMapper) {
        this.downloadFileRepository = downloadFileRepository;
        this.downloadFileMapper = downloadFileMapper;
    }

    /**
     * Save a downloadFile.
     *
     * @param downloadFileDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DownloadFileDTO save(DownloadFileDTO downloadFileDTO) {
        log.debug("Request to save DownloadFile : {}", downloadFileDTO);

        DownloadFile downloadFile = downloadFileMapper.toEntity(downloadFileDTO);
        downloadFile = downloadFileRepository.save(downloadFile);
        return downloadFileMapper.toDto(downloadFile);
    }

    /**
     * Get all the downloadFiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<DownloadFileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DownloadFiles");
        return downloadFileRepository.findAll(pageable)
            .map(downloadFileMapper::toDto);
    }


    /**
     * Get one downloadFile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<DownloadFileDTO> findOne(String id) {
        log.debug("Request to get DownloadFile : {}", id);
        return downloadFileRepository.findById(id)
            .map(downloadFileMapper::toDto);
    }

    /**
     * Delete the downloadFile by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete DownloadFile : {}", id);
        downloadFileRepository.deleteById(id);
    }
}
