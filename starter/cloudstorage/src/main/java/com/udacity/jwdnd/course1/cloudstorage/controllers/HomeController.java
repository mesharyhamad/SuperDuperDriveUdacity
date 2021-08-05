package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private FileService fileService;
    private UserService userService;
    private NoteService noteService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public HomeController(FileService fileService, UserService userService,NoteService noteService,CredentialService credentialService,EncryptionService encryptionService) {
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }
//ModelAttribute
    @GetMapping(value = {"/home","/"})
    String home(Authentication authentication, @ModelAttribute("newFile") FileForm newFile, @ModelAttribute("newNote") NoteForm note, @ModelAttribute("credentialForm")CredentialForm credentialForm, Model model ){
        String  name = authentication.getName();
        Integer userId = userService.getUserIdByName(name);
        model.addAttribute("files",this.fileService.getFilesByUserId(userId));
        model.addAttribute("notes",this.noteService.getNotesByUserId(userId));
        model.addAttribute("credentialList",this.credentialService.getCredentials(userId));
        model.addAttribute("encryptionService",this.encryptionService);
        return "home";
    }


}
