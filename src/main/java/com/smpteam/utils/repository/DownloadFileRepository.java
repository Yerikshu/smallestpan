package com.smpteam.utils.repository;

import com.smpteam.utils.domain.DownloadFile;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the DownloadFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DownloadFileRepository extends MongoRepository<DownloadFile, String> {

}
