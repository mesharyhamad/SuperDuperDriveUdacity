package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Note[] getNotesByUserId(Integer userID){
        return  this.noteMapper.getNotesByUserId(userID);
    }

    public Note getNoteByID(Integer noteId){
        return this.noteMapper.getNoteById(noteId);
    }


    public void insertNote(String title, String description, Integer userID) {
        Note note = new Note(0, title, description, userID);
        noteMapper.insertNote(note);
    }
    public void updateNote(Integer noteId,String title, String description){
        this.noteMapper.updateNote(noteId,title,description);
    }
    public void deleteNote(Integer noteID){
        this.noteMapper.deleteNote(noteID);
    }
}
