package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFileByName(String filename);

    @Select("SELECT * FROM FILES WHERE fileId= #{fileId}")
    File getFileById(Integer fileId);

    @Select("SELECT * FROM FILES WHERE userid= #{userid}")
    List<File> getFilesByUserID(Integer userid);

    @Select("SELECT COUNT(*) FROM FILES WHERE userid= #{userid} AND filename = #{filename}")
    Integer countFilesByNameAndUserID(String filename,Integer userid);

    @Select("SELECT * FROM  FILES")
    List<File> getFiles();

    @Insert("INSERT INTO FILES(filename,contenttype,filesize,userid,filedata) VALUES(#{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true,keyProperty = "fileId")
    Integer createFile(File file);

    @Delete("DELETE FROM FILES where fileId =#{fileId}")
    void deleteFile(Integer fileId);
}
