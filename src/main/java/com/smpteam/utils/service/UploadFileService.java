package com.smpteam.utils.service;

import com.smpteam.utils.service.dto.UploadFileDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UploadFile.
 */
public interface UploadFileService {

    /**
     * Save a uploadFile.
     *
     * @param uploadFileDTO the entity to save
     * @return the persisted entity
     */
    UploadFileDTO save(UploadFileDTO uploadFileDTO);

    /**
     * Get all the uploadFiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UploadFileDTO> findAll(Pageable pageable);


    /**
     * Get the "id" uploadFile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UploadFileDTO> findOne(String id);

    /**
     * Delete the "id" uploadFile.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
