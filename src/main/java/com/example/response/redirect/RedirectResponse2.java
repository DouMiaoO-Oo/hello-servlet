package com.example.response.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/RedirectResponse2")
public class RedirectResponse2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getAttribute("key1"));  // null
        //  重定向时, Request 对象是浏览器第二次请求时重新创建的, 不会携带第一次请求时的数据

        resp.getWriter().write("RedirectResponse2's result!");
    }
}
