package com.example.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HttpServletResponse 类和HttpServletRequest 类一样。每次请求进来，Tomcat 服务器都会创建一个Response 对象传
 * 递给Servlet 程序去使用。HttpServletRequest 表示请求过来的信息，HttpServletResponse 表示所有响应的信息
 * 字节流 resp.getOutputStream() 常用于下载（传递二进制数据）
 * 字符流 resp.getWriter() 常用于回传字符串（常用）
 */

@WebServlet(urlPatterns = "/ResponseIOServlet")
public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println( resp.getCharacterEncoding() );  // 默认 ISO-8859-1

//        // 设置服务器字符集为UTF-8
//        resp.setCharacterEncoding("UTF-8");
//        // 通过响应头，设置浏览器也使用UTF-8字符集
//        resp.setHeader("Content-Type", "text/html; charset=UTF-8");

        // 它会同时设置服务器和客户端都使用UTF-8字符集，还设置了响应头（ response header)
        // 此方法一定要在获取流对象之前调用才有效
        // 更推荐使用
        resp.setContentType("text/html; charset=UTF-8");

        System.out.println( resp.getCharacterEncoding() );  // UTF-8

//        要求 ： 往客户端回传 字符串 数据。
        PrintWriter writer = resp.getWriter();
        writer.write("中文response");
    }
}
