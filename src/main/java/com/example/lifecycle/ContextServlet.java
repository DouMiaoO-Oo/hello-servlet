package com.example.lifecycle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 这个类演示了 ServletContext 对象的简单使用.
 * ServletContext 在工程启动的时候创建, 工程停止的时候销毁. ServletContext 对象是一个域对象 ( 可以像 Map 一样存取数据)
 * ServletContext 可以用于:
 *    1. 获取web.xml 中配置的上下文参数context-param
 *    2. 获取当前的工程路径，格式: /工程路径
 *    3. 获取工程部署后在服务器硬盘上的绝对路径
 *    4. 作为域对象像 Map 一样存取数据, 可以在同一个工程下的多个 Servlet 中共享
 */

@WebServlet(
        urlPatterns = "/HelloContextServlet"
)
public class ContextServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取web.xml中配置的上下文参数context-param
        // (1) 获取 ServletConfig 对象
        ServletConfig config = getServletConfig();
        // (2) 通过 ServletConfig 对象获取 ServletContext 对象
        ServletContext context = config.getServletContext();
//        ServletContext context = getServletContext();  // 也可以通过 getServletContext 直接获取, 查看源码可知还是通过 config 获取 context

        System.out.println("context-param参数`context-param1`的值是: " + context.getInitParameter("context-param1"));
        System.out.println("context-param参数`context-param2`的值是: " + context.getInitParameter("context-param2"));

//        2、获取当前的工程路径，格式: /工程路径
//                                或者根据 pom.xml 中定义的<finalName>标签来生成路径
        System.out.println( "当前工程路径:" + context.getContextPath() );  // "/hello-servlet" 或者 "/hello-servlet-finalName"
//        3、获取工程部署后在服务器硬盘上的绝对路径
        /**
         *  / 斜杠被服务器解析地址为:http://ip:port/工程名/  映射到IDEA代码的webapp目录
         */
        System.out.println("工程部署的路径是:" + context.getRealPath("/"));  // "*/hello-servlet\src\main\webapp\"

//        4. 作为域对象像 Map 一样存取数据, 可以在同一个工程下的多个 Servlet 中共享
        System.out.println("servletContext为:"+context+". 保存之前: Context 获取 ContextKey 的值是:"+ context.getAttribute("ContextKey"));
        context.setAttribute("ContextKey", "contextValue");
        System.out.println("servletContext为:"+context+". 保存之后: Context 中获取域数据 ContextKey 的值是:"+ context.getAttribute("ContextKey"));
    }
}
