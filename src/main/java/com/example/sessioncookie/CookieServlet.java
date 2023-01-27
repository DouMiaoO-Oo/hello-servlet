package com.example.sessioncookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/CookieServlet")
public class CookieServlet extends BaseServlet {
    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 创建Cookie对象
        Cookie cookie = new Cookie("CookieServlet_key1", "value1");
        //2 通知客户端保存Cookie
        resp.addCookie(cookie);

        //1 创建Cookie对象
        Cookie cookie1 = new Cookie("CookieServlet_key2", "value2");
        //2 通知客户端保存Cookie
        resp.addCookie(cookie1);
        resp.getWriter().write("<h1>Cookie创建成功!</h1>");
        resp.getWriter().write("<ol><li>CookieServlet_key1</li><li>CookieServlet_key2</li></ol>");
        resp.getWriter().write("<b>请通过F12查看</b>");
        resp.getWriter().flush();
    }

    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(null == cookies){
            resp.getWriter().write("没有 Cookie <br/>");
        } else{
            for (Cookie cookie : cookies) {
                // getName方法返回Cookie的key（名）
                // getValue方法返回Cookie的value值
                resp.getWriter().write("Cookie[" + cookie.getName() + "=" + cookie.getValue() + "] <br/>");
            }
        }

    }

    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        方案一：
//         1、先创建一个要修改的同名的Cookie对象
//         2、在构造器，同时赋于新的Cookie值。
//        Cookie cookie = new Cookie("CookieServlet_key1","newValue1");
//        3、调用response.addCookie( Cookie ); 通知 客户端 保存修改
//        resp.addCookie(cookie);

//        方案二：
//        1、先查找到需要修改的Cookie对象
        Cookie cookie = CookieUtils.findCookie("CookieServlet_key1", req.getCookies());
        if (cookie != null) {
//            2、调用setValue()方法赋于新的Cookie值。
            cookie.setValue("newValue1");
//        3、调用response.addCookie()通知客户端保存修改
            resp.addCookie(cookie);
        }
        resp.getWriter().write("CookieServlet_key1 的Cookie已经修改好");
    }

    /**
     * 默认的会话级别的Cookie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defalutLife","defaultLife");
        cookie.setMaxAge(-1);  // 浏览器关闭时删除该 Cookie（默认值即为-1）
        resp.addCookie(cookie);
        resp.getWriter().write("已经创建了一个Session级别的Cookie <br />浏览器关闭时删除该 Cookie");
    }

    /**
     * 设置存活1个小时的Cooie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void life3600(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("life3600", "life3600");
        cookie.setMaxAge(60 * 60); // 设置Cookie一小时之后被删除。无效
        resp.addCookie(cookie);
        resp.getWriter().write("已经创建了一个存活一小时的Cookie");
    }

    /**
     * 马上删除一个Cookie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("keyName");
        // 先找到你要删除的Cookie对象
        Cookie cookie = CookieUtils.findCookie(name, req.getCookies());
        if (cookie != null) {
            cookie.setMaxAge(0); // 表示马上删除，都不需要等待浏览器关闭
            resp.addCookie(cookie);
            resp.getWriter().write(name + "的Cookie已经被删除");
        } else {
            resp.getWriter().write("未找到 Cookie " + name);
        }
    }

    protected void testPath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("pathCookie", "pathValue");
        // getContextPath() ===>>>>  得到工程路径
        cookie.setPath( req.getContextPath() + "/abc" ); // ===>>>>  /工程路径/abc
        resp.addCookie(cookie);
        resp.getWriter().write("创建了一个带有Path路径的Cookie");
    }
}
