package com.example.upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 参考:
 *  https://www.jianshu.com/p/1968019b6927
 *  https://www.digitalocean.com/community/tutorials/servlet-3-file-upload-multipartconfig-part
 *  https://dotnettutorials.net/lesson/uploading-and-downloading-files-in-servlet/
 */

@WebServlet("/UploadServlet")
@MultipartConfig(
        fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100   	// 100 MB
)
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "upload";
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 查看 form-data
        //        UploadUtils.viewFormData(req.getInputStream());resp.getWriter().print("FINISH!");resp.getWriter().flush();

        // 获取文件上传组件
        Part part = req.getPart("fileUpload");

        // 获取文件名
        // content-disposition: form-data; name="fileUpload"; filename="native-fireshot.log"
//        String contentDisp = part.getHeader("content-disposition");
//        String name = contentDisp.substring(contentDisp.indexOf("filename=") + 10, contentDisp.length() - 1);
        String name = UploadUtils.getFileName(part);

        // 获取文件的路径
        String relativePath = String.format("/%s",UPLOAD_DIR);
        String realPath = getServletContext().getRealPath(relativePath);

        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 对拷流
        InputStream inputStream = part.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(new File(dir, name));
        int len = -1;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }

        // 关闭资源
        outputStream.close();
        inputStream.close();

        // 删除临时文件
        part.delete();

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().print("文件" + name + "上传成功！");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
