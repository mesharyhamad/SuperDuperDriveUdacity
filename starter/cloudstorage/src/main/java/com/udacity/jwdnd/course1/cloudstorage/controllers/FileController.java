package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/file/upload")
    public String UploadFile(@ModelAttribute("newFile") FileForm newFile, Authentication authentication , Model model) throws IOException {
        String  name = authentication.getName();
        Integer userId = userService.getUserIdByName(name);
        MultipartFile file = newFile.getFile();
        //To check if a user has upload same file
        Boolean isFileExistByUser = this.fileService.isFileExistByUser(newFile.getFile().getOriginalFilename(),userId);

        if(!isFileExistByUser){
        this.fileService.insertFile(file,userId);
        model.addAttribute("status","true");
        model.addAttribute("message","file has been uploaded successfully");
        }else {
            model.addAttribute("status","false");
            model.addAttribute("message","the file  already exist");

        }

        return "result";
    }

    @GetMapping("/file/delete/{fileID}")
    public String deleteFile(@PathVariable Integer fileID,Model model){
        this.fileService.deleteFileById(fileID);
        model.addAttribute("status","true");
        model.addAttribute("message","File has been deleted successfully");
      return "result";
    }
    @GetMapping( value = "/file/get-file/{fileID}" , produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    byte[] getFileById(@PathVariable Integer fileID){

        return this.fileService.getFileByID(fileID).getFiledata();
    }
}
