package com.smpteam.utils.service;

import com.smpteam.utils.service.dto.DownloadFileDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DownloadFile.
 */
public interface DownloadFileService {

    /**
     * Save a downloadFile.
     *
     * @param downloadFileDTO the entity to save
     * @return the persisted entity
     */
    DownloadFileDTO save(DownloadFileDTO downloadFileDTO);

    /**
     * Get all the downloadFiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DownloadFileDTO> findAll(Pageable pageable);


    /**
     * Get the "id" downloadFile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DownloadFileDTO> findOne(String id);

    /**
     * Delete the "id" downloadFile.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
