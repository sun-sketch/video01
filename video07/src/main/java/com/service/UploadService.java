package com.service;

import com.domain.Upload;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UploadService {
    void addVideo(HttpServletRequest request, MultipartFile uploadfile, MultipartFile uploadvideo, String zonename, String introduction, HttpServletResponse response) throws Exception;

    List<Upload> queryPageUpload(int begin,int rows);

    int countUpload();

    void deleteuploadvideo(int id, HttpServletRequest request);

    Upload queryUploadById(int id);

    List<Upload> queryUploadByUserid(int user_id);

}
