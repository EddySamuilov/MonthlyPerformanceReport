package com.example.monthlyperformancereport.utils;

import java.io.IOException;

public interface FileUtil {

    String readFileContent(String path) throws IOException;

    void write(String content, String filePath) throws IOException;
}
