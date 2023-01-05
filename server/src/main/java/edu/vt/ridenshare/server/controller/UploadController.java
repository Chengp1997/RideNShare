package edu.vt.ridenshare.server.controller;

import edu.vt.ridenshare.server.common.log.LogAnnotation;
import edu.vt.ridenshare.server.util.AWSS3Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private AWSS3Utils awss3Utils;
    @PostMapping
    @LogAnnotation(module="/upload", operation="upload image")
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile file){

        String originalFilename= file.getOriginalFilename();

        String filename = UUID.randomUUID().toString()+"."+ StringUtils.substringAfterLast(originalFilename,".");

        boolean upload = awss3Utils.upload(filename,file);
        if(upload){
            return ResponseEntity.ok(awss3Utils.url+filename);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed!");
    }

}
