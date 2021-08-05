package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {
    UserService userService;
    NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/note/insert")
    String insertNote(Authentication authentication, @ModelAttribute("newNote") NoteForm note, Model model){
        Integer userID = userService.getUserIdByName(authentication.getName());
        Boolean noError = true;

        String  noteId = note.getNoteId();
        if(noteId.isEmpty()){
            this.noteService.insertNote(note.getTitle(),note.getDescription(),userID);
        }
        else {
            this.noteService.updateNote(Integer.parseInt(noteId),note.getTitle(),note.getDescription());

        }

        model.addAttribute("status","true");
        model.addAttribute("message","Note has been created successfully");

        return "result";
    }

    @GetMapping("/note/delete/{id}")
    String deleteNote(@PathVariable Integer id,Model model){
        this.noteService.deleteNote(id);
        model.addAttribute("status","true");
        model.addAttribute("message","Note has been created successfully");
        return "result";
    }
//
//    @PostMapping("note/update/{id}")
//    String upateNote()
}
