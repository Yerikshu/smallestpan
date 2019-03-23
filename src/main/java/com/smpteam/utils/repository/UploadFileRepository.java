package com.smpteam.utils.repository;

import com.smpteam.utils.domain.UploadFile;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the UploadFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UploadFileRepository extends MongoRepository<UploadFile, String> {

}
