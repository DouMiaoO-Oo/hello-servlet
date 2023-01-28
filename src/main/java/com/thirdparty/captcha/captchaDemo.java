package com.thirdparty.captcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;


import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


@WebServlet(urlPatterns = "/captchaDemo")
public class captchaDemo extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        PrintWriter writer = resp.getWriter();

        String code = req.getParameter("captcha");
        String attribute = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        if(null != code && code.equalsIgnoreCase(attribute)){
            System.out.println("验证码正确!");
            writer.write("success");
        } else {
            System.out.println("验证码输入错误");
            writer.write("fail");
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
