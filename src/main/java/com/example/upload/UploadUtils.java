package com.example.upload;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class UploadUtils {
    /**
     * Utility method to get file name from HTTP header content-disposition
     */
    public static String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

    /**
     * 把 multi-part/form-data 读进内存中并打印到控制台
     */
    public static void viewFormData(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        byte [] buffer = new byte[1024000];
        int len = inputStream.read(buffer);
        String str = new String(buffer, 0, len);
        System.out.println(str);
    }
}
