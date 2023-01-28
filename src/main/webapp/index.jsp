<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
    <h2>Hello World!</h2>
    <a href="a/b/c.html">a/b/c.html</a><br/>
    <a href="http://localhost:8080/hello-servlet/ForwardStaticHtml">forward 到 a/b/c.html</a><br/>
    <a href="/">路径为斜杠, 在浏览器中代表 ip:port (例如 http://localhost:8080/) </a>
    <br/><br/>
   <%--request.getContextPath()--%>
    <form action="http://localhost:8080/hello-servlet/UploadServlet" method="post" enctype="multipart/form-data">
        <label>选择一个文件：</label>
        <input type="file" name="fileUpload"><br>
        <input type="submit" value="上传"><br>
    </form>
</body>
</html>
