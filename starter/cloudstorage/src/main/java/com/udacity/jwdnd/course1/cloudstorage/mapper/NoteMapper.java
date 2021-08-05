package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    Note[] getNotesByUserId(Integer userid);

    @Select("SELECT * FROM NOTES WHERE noteid= #{noteId}")
    Note getNoteById(Integer noteID);

    @Insert("INSERT INTO NOTES(notetitle,notedescription,userid) VALUES(#{notetitle},#{noteDescription},#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    Integer insertNote(Note note );

    @Update("UPDATE NOTES SET notetitle=#{noteTitle} , notedescription = #{noteDescription} WHERE noteid =#{noteid} ")
    void  updateNote(Integer noteid , String noteTitle , String noteDescription);

    @Delete("DELETE FROM NOTES WHERE noteid =#{noteID}")
    void deleteNote(Integer noteID);
}
