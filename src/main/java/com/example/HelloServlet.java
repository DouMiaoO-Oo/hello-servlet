package com.example;
/* 参考自廖雪峰Web开发-Servlet入门
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// WebServlet注解表示这是一个Servlet，并映射到地址/:
//@WebServlet(urlPatterns = "/helloServletAnnotated")  // 3.0以上高版本 Servlet 才能使用, 低版本 Servlet 需要webapp/WEB-INF/web.xml 配置文件
public class HelloServlet extends HttpServlet {  // Servlet总是继承自HttpServlet，然后覆写doGet()或doPost()方法
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 设置响应类型:
        resp.setContentType("text/html");
        // 获取输出流:
        PrintWriter pw = resp.getWriter();
        // 写入响应:
        pw.write("<h1>Hello, Servlet!</h1>");
        // 最后不要忘记flush强制输出:
        pw.flush();
    }
}
