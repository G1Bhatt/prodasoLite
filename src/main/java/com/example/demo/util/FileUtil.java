package com.example.demo.util;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FileUtil {

    public static File save(String filename, InputStream input) throws IOException {

        File targetFile = new File(filename);

        FileUtils.copyInputStreamToFile(input, targetFile);
        return targetFile;
    }

    public static void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.delete()) {
            }
        } catch (Exception e) {
        }
    }

    public static File convertToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public static String convertToLowerCaseAndTrim(String string) {
        return string.trim().toLowerCase();
    }

}
