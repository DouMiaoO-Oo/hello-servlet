package com.example.lifecycle;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * 这个类演示了 Servlet 的生命周期, 以及 ServletConfig 对象的简单使用.
 * Servlet 对象和 ServletConfig 对象都是由 Tomcat 负责创建，我们负责使用
 * 在每个 Servlet 程序被创建时，会对应的创建一个 ServletConfig 对象, 可以用于:
 *    1. 获取 Servlet 程序的信息 例如别名 servlet-name
 *    2. 获取初始化参数 init-param
 *    3. 获取 ServletContext 对象
 */

// @WebServlet 注解跟 web.xml 中的配置只有一个能生效
/*@WebServlet(
        urlPatterns = "/HelloServletInterface",
        initParams={
                @WebInitParam(name="username", value="DM"),
                @WebInitParam(name="email", value="Not provided~")
        }
)*/
public class HelloServletInterface implements Servlet {
    private static final String className = HelloServletInterface.class.getName();
    public HelloServletInterface() {
        System.out.println("1 构造器方法 servlet被访问时才会初始化");
    }

    /**
     * @param servletConfig 在每个 Servlet 程序被创建时，会对应的创建一个 ServletConfig 对象用于
     * @throws ServletException
     * */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2 init初始化方法");

//        1、可以获取Servlet程序的别名servlet-name的值
        System.out.printf("%s程序的别名是: %s\n", className, servletConfig.getServletName());
//        2、获取初始化参数init-param
        System.out.println("初始化参数username的值是;" + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数url的值是;" + servletConfig.getInitParameter("email"));
//        3、获取ServletContext对象
        ServletContext context = servletConfig.getServletContext();
        System.out.printf("%s中访问的servletContext为:%s, 获取域数据 ContextKey 的值是: %s\n",
                className,
                context,
                context.getAttribute("ContextKey"));
    }  // ContextKey 在 ContextServlet 中进行赋值

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * service方法是专门用来处理请求和响应的
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.printf("3 service === %s 被访问了\n", className);
        // 类型转换（因为它有getMethod()方法）
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 获取请求的方式
        String method = httpServletRequest.getMethod();

        if ("GET".equals(method)) {
            doGet();
        } else if ("POST".equals(method)) {
           doPost();
        }
    }

    /**
     * 做get请求的操作
     */
    public void doGet(){
        System.out.println("get请求");
    }
    /**
     * 做post请求的操作
     */
    public void doPost(){
        System.out.println("post请求");
    }


    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4 . destroy销毁方法");
    }
}
