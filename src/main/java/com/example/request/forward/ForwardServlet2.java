package com.example.request.forward;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/ForwardServlet2")
public class ForwardServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数（办事的材料）查看
        String username = req.getParameter("username");
        System.out.println("在 ForwardServlet2（柜台2）中查看参数（材料）：" + username);

        // 查看 柜台1 是否有盖章
        Object key1 = req.getAttribute("key1");
        System.out.println("柜台1是否有章：" + key1);

        // 处理自己的业务
        System.out.println("Servlet2 处理自己的业务 ");

        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        String content = String.format("<h1>Hello, %s! Servlet2 已经处理完请求</h1>", this.getClass().getName());
        pw.write(content);
        pw.write("<h1>似乎在ForwardServlet2这里就会flush刷新response返回了</h1>");
    }
}
