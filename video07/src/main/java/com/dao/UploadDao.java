package com.dao;

import com.domain.Upload;
import com.domain.Video;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UploadDao {

    @Insert("insert into upload(videoname,uuid,imagename,introduction,zonename,user_id)values(#{videoname},#{uuid},#{imagename},#{introduction},#{zonename},#{user_id})")
    void addVideo(@Param("videoname")String videoname, @Param("uuid")String uuid, @Param("imagename")String imagename, @Param("introduction")String introduction, @Param("zonename")String zonename,@Param("user_id") int user_id);

    @Select("select upload.videoname,upload.time,upload.id from upload order by time asc limit #{begin},#{rows}")
    List<Upload> queryPageUpload(@Param("begin")int begin, @Param("rows")int rows);

    @Select("SELECT COUNT(*) FROM upload")
    int countUpload();

    @Delete("delete from upload WHERE id=#{id}")
    void deleteuploadvideo(@Param("id")int id);

    @Select("select * from upload where id=#{id}")
    Upload queryUploadById(@Param("id")int id);

    @Select("select * from upload where user_id=#{user_id}")
    List<Upload> queryUploadByUserid(@Param("user_id") int user_id);

    @Delete("delete from upload WHERE user_id=#{user_id}")
    void deleteuploadByUserid(@Param("user_id")int user_id);
}
