package com.hanpeng.dao;

import com.hanpeng.bean.FaceRecognition;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FaceRecognitionDao {

    List<FaceRecognition> getBaseList();

    void insertFaceInfo(FaceRecognition faceRecognition);

    int queryCountByFaceInfo(@Param("username") String username);
}
