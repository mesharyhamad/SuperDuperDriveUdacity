package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService   {

    FileMapper fileMapper;
    UserMapper userMapper;
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

   public File getFileByID(Integer fileId){
        return this.fileMapper.getFileById(fileId);
    }

    public List<File> getFilesByUserId(Integer userid){
        return this.fileMapper.getFilesByUserID(userid);
    }
    // this function bellow will check if a user has upload a duplicate file.
    public Boolean isFileExistByUser(String filename, Integer userid){
        Integer countFileUser = this.fileMapper.countFilesByNameAndUserID(filename,userid);
        if(countFileUser >0)
           return true;

        return false;
    }

    List<File> getFiles(){
        return this.fileMapper.getFiles();
    }

    public Integer insertFile(MultipartFile file,Integer userId) throws IOException {

        return this.fileMapper.createFile(new File(file.getOriginalFilename(),file.getContentType(),
                String.valueOf(file.getSize()),userId,file.getBytes())) ;
    }

    public void deleteFileById(Integer fileId){
        this.fileMapper.deleteFile(fileId);
    }
}
