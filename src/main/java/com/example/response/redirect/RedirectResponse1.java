package com.example.response.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求重定向的特点：
 *     1. 浏览器地址会发生变化
 *     2. 会发生2次请求
 *     3. 2次请求Request数据独立, 不能共享
 *     4. 可以跳转到工程以外的地址
 */
@WebServlet(urlPatterns = "/RedirectResponse1")
public class RedirectResponse1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("曾到此一游 RedirectResponse1 ");

        req.setAttribute("key1", "value1");

        // 设置响应状态码302 ，表示暂时重定向，(Temporarily Moved, 已搬迁）
//        resp.setStatus(302);
        // 设置响应头，说明 新的地址在哪里
//        resp.setHeader("Location", "http://localhost:8080/hello-servlet/RedirectResponse2");

        // 直接使用 sendRedirect 方法即可（推荐）
        resp.sendRedirect("http://localhost:8080/hello-servlet/RedirectResponse2");
//        resp.sendRedirect("https://www.baidu.com/");  // 可以跳转到工程外的地址
    }
}
