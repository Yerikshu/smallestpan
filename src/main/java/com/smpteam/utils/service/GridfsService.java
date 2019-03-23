package com.smpteam.utils.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class GridfsService {

    @Autowired
    private MongoDbFactory mongodbfactory;


    /**
     * 插入文件
     * @param file
     * @return
     */
    public GridFSInputFile save(MultipartFile file){

        GridFS gridFS = new GridFS(mongodbfactory.getLegacyDb());

        try{

            InputStream in = file.getInputStream();

            String name = file.getOriginalFilename();

            GridFSInputFile gridFSInputFile = gridFS.createFile(in);

            gridFSInputFile.setFilename(name);

            gridFSInputFile.setContentType(file.getContentType());

            gridFSInputFile.save();
            return gridFSInputFile;
        }
        catch (Exception e){}

        return null;

    }

    /**
     * 据id返回文件
     */
    public GridFSDBFile getById(ObjectId id){
        DBObject query  = new BasicDBObject("_id", id);
        GridFS gridFS = new GridFS(mongodbfactory.getLegacyDb());
        GridFSDBFile gridFSDBFile = gridFS.findOne(query);
        return gridFSDBFile;

    }


    /**
     * 删除
     * @param id
     */
    public void remove(String id) {
        GridFS gridFS = new GridFS(mongodbfactory.getLegacyDb());
        gridFS.remove(new ObjectId(id));
    }

    public void setMongodbfactory(MongoDbFactory mongodbfactory) {
        this.mongodbfactory = mongodbfactory;
    }
}
