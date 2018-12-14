package com.hanpeng.service;

import com.hanpeng.bean.FaceRecognition;

import java.io.IOException;
import java.util.List;

public interface FaceRecognitionService {
    public List<FaceRecognition> getBaseList();

    public boolean getResult(String image1, String image2) throws IOException;

    public boolean getFaceResult(String image) throws IOException;

    public void insertFaceInfo(FaceRecognition faceRecognition);

    public boolean queryCountByFaceInfo(String username, int n);
}
