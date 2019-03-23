package com.smpteam.utils.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.smpteam.utils.service.FileService;
import com.smpteam.utils.service.GridfsService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class FileResource {

    private final Logger log = LoggerFactory.getLogger(FileResource.class);

    private FileService fileService;

    private GridfsService gridfsService;

    public FileResource(FileService fileService, GridfsService gridfsService) {
        this.fileService = fileService;
        this.gridfsService = gridfsService;
    }

    @GetMapping("/download/:id")
    @Timed
    public ResponseEntity<Object> downloadFile(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/upload")
    @Timed
    public ResponseEntity<Object> uploadFile(@RequestBody Object object) {
        return null;
    }

    @RequestMapping(value = "/file/upload")
    public Object uploadData(@RequestParam(value = "file") MultipartFile file) {

        GridFSInputFile inputFile = gridfsService.save(file);

        if (inputFile == null) {
            return "upload fail";
        }
        String id = inputFile.getId().toString();
        String md5 = inputFile.getMD5();
        String name = inputFile.getFilename();
        long length = inputFile.getLength();

        Map<String,Object> dt = new HashMap<String,Object>();
        dt.put("id",id);
        dt.put("md5",md5);
        dt.put("name",name);
        dt.put("length",length);

        return dt;
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/file/delete", method = RequestMethod.POST)
    public Object deleteFile(@RequestParam(value = "id") String id) {

//        删除文件
        gridfsService.remove(id);
        return "delete ok";
    }


    /**
     * 下载文件
     * @param id
     * @param response
     */
    @RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
    public void getFile(@PathVariable String id, HttpServletResponse response) {
        GridFSDBFile file = gridfsService.getById(new ObjectId(id));

        if (file == null) {
            responseFail("404 not found",response);
            return;
        }

        OutputStream os = null;

        try {
            os = response.getOutputStream();
            response.addHeader("Content-Disposition", "attachment;filename=" + file.getFilename());
            response.addHeader("Content-Length", "" + file.getLength());
            response.setContentType("application/octet-stream");
            file.writeTo(os);
            os.flush();
            os.close();

        } catch (Exception e) {
            try{
                if (os != null) {
                    os.close();
                }
            }catch (Exception e2){}
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/file/view/{id}", method = RequestMethod.GET)
    public void viewFile(@PathVariable String id, HttpServletResponse response) {

        GridFSDBFile file = gridfsService.getById(new ObjectId(id));

        if (file == null) {
            responseFail("404 not found",response);
            return;
        }

        OutputStream os = null;

        try {
            os = response.getOutputStream();
            response.addHeader("Content-Disposition", "attachment;filename=" + file.getFilename());
            response.addHeader("Content-Length", "" + file.getLength());
            response.setContentType(file.getContentType());
            file.writeTo(os);
            os.flush();
            os.close();

        } catch (Exception e) {
            try{
                if (os != null) {
                    os.close();
                }
            }catch (Exception e2){}
            e.printStackTrace();
        }

    }

    private void responseFail(String msg,HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        ObjectMapper mapper = new ObjectMapper();
        try{
            String res = mapper.writeValueAsString(msg);
            out = response.getWriter();
            out.append(res);
        } catch (Exception e){
            try {
                if (out != null) {
                    out.close();
                }
            }catch (Exception e2) {}
        }
    }
}
