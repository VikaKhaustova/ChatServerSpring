package com.example.chatserverspring.model;

import lombok.Data;

import java.util.List;

@Data
public class FileDTO {

    private Long id;

    private String fileName;

    private String fileData;

    private byte[] fileDataByte;

    private List<byte[]> fileDataList;

    private List<String> fileNames;


}