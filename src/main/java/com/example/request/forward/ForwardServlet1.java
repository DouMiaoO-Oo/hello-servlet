package com.example.request.forward;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 请求转发 (request forward) 的特点：
 *     1. 浏览器地址栏没有变化，仅仅是一次请求.
 *            因为浏览器地址没变，此时前端使用相对路径可能会出错：
 *              直接访问 http://localhost:8080/hello-servlet/a/b/c.html
 *              与通过访问 http://localhost:8080/hello-servlet/ForwardStaticHtml 时,
 *                  ../../index.jsp 进行跳转时效果不一致
*               需要在前端页面中通过 base 标签设置页面相对路径工作时参照的地址
 *     2. 可以转发到 WEB-INF 目录下
 *     3. 不可以访问工程以外的资源
 */

@WebServlet(urlPatterns = "/ForwardServlet1")
public class ForwardServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求的参数（办事的材料）查看
        String username = req.getParameter("username");
        System.out.println("在 ForwardServlet1 （柜台1）中查看参数（材料）：" + username);

        // 给材料 盖一个章，并传递到Servlet2（柜台 2）去查看
        req.setAttribute("key1","柜台1的章");

        // 问路：Servlet2（柜台 2）怎么走
        /**
         * 请求转发必须要以斜杠打头，/ 斜杠表示地址为：http://ip:port/工程名/ , 映射到IDEA代码的web目录
         *
         */
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ForwardServlet2");
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("http://www.baidu.com");

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        String content = String.format("<h1>Hello, %s! 还没转发请求</h1>", this.getClass().getName());
        pw.write(content);
//        pw.flush();  // 这里 flush 的话就不会执行 forward

        // 走向 Sevlet2（柜台 2）
        requestDispatcher.forward(req,resp);

        // 转发之后的一些操作
        System.out.println("Servlet1 转发请求完成 ");
        content = String.format("<h1>Hello, %s! 转发完请求</h1>", this.getClass().getName());
        pw.write(content);
        pw.flush();
    }
}
