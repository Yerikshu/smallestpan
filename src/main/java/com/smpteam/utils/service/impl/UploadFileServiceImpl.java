package com.smpteam.utils.service.impl;

import com.smpteam.utils.service.UploadFileService;
import com.smpteam.utils.domain.UploadFile;
import com.smpteam.utils.repository.UploadFileRepository;
import com.smpteam.utils.service.dto.UploadFileDTO;
import com.smpteam.utils.service.mapper.UploadFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing UploadFile.
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    private final UploadFileRepository uploadFileRepository;

    private final UploadFileMapper uploadFileMapper;

    public UploadFileServiceImpl(UploadFileRepository uploadFileRepository, UploadFileMapper uploadFileMapper) {
        this.uploadFileRepository = uploadFileRepository;
        this.uploadFileMapper = uploadFileMapper;
    }

    /**
     * Save a uploadFile.
     *
     * @param uploadFileDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UploadFileDTO save(UploadFileDTO uploadFileDTO) {
        log.debug("Request to save UploadFile : {}", uploadFileDTO);

        UploadFile uploadFile = uploadFileMapper.toEntity(uploadFileDTO);
        uploadFile = uploadFileRepository.save(uploadFile);
        return uploadFileMapper.toDto(uploadFile);
    }

    /**
     * Get all the uploadFiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<UploadFileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UploadFiles");
        return uploadFileRepository.findAll(pageable)
            .map(uploadFileMapper::toDto);
    }


    /**
     * Get one uploadFile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<UploadFileDTO> findOne(String id) {
        log.debug("Request to get UploadFile : {}", id);
        return uploadFileRepository.findById(id)
            .map(uploadFileMapper::toDto);
    }

    /**
     * Delete the uploadFile by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete UploadFile : {}", id);
        uploadFileRepository.deleteById(id);
    }
}
