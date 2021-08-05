package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {
    private Integer noteid;
    private String notetitle;
    private String noteDescription;
    private Integer userid;

    public Note(Integer noteid,String notetitle, String noteDescription, Integer userid) {
        this.noteid = noteid;
        this.notetitle = notetitle;
        this.noteDescription = noteDescription;
        this.userid = userid;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public void setNoteDescription(String notedescription) {
        this.noteDescription = notedescription;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
