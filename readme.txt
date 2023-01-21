项目效果
    本项目打包好 hello-servlet-finalName.war 之后置于 apache-tomcat-8.0.50\webapps 路径下，
    在浏览器中访问 http://localhost:8080/hello-servlet-finalName/helloServlet 即可看到 "Hello, Servlet!"
    备注：
        那能不能直接使用/helloServlet而不是/hello-servlet-finalName/helloServlet？毕竟前者比较简洁
        答案是肯定的。先关闭Tomcat（执行shutdown.sh或shutdown.bat），然后删除Tomcat的webapps目录下的所有文件夹和文件，最后把我们的 hello-servlet-finalName.war 复制过来，改名为ROOT.war，文件名为ROOT的应用程序将作为默认应用，启动后直接访问 http://localhost:8080/helloServlet 即可。

项目代码参考
    参考自廖雪峰Web开发-Servlet入门
    在 servlet3.0 以前的低版本 Servlet 需要填写 webapp/WEB-INF/web.xml 配置文件
    本工程使用的是 servlet3.1, 可以注释掉 web.xml 中的 <servlet>,<servlet-mapping> 标签，在 HelloServlet.java中使用注解 @WebServlet(urlPatterns = "/helloServletAnnotated")

依赖环境
    JDK8(1.8.0_291)
    Bundled(Maven 3) version 3.6.3
    servlet 3.1.0
    apache-tomcat-8.0.50-windows-x64

IDEA Community(社区版)+maven创建Java web项目并配置Tomcat全过程
    https://www.cnblogs.com/Luquan/p/12273595.html
    备注：properties 中 archetypeCatalog=internal 可以不添加，仅仅起到加速的作用，且在新版idea中容易引起各种问题

    创建项目流程（lyw总结版）：
        File-New-Project-在左侧选择“maven”-勾选“Create from archetype”-在下面选择“org.apache.maven.archetypes:maven-archetype-webapp"
        此时根据archetype创建出的工程还在更新各种依赖，完成之后会自动创建出 ./src/main/webapp/*
        我们还需要手动在Project栏手动右键项目根目录"servlet-hello"-New- Directory。在弹出的窗口上，单击第一个 src\main\java，按住 shift 键，再单击最后一个 src\test\resources 以选中所有项，再按回车键，创建对应文件夹。

在 IDEA 中使用 Tomcat 插件
    打包成 war 放入 Tomcat/webapps 目录下流程比较繁琐，如果只是用于调试，可以在 IDEA 中通过插件来启动 Tomcat.
    安装Smart Tomcat插件
    　　File -> Settings -> Plugins，搜索 Smart Tomcat，进行安装，安装之后需要重启Idea
    配置 Tomcat
        单击 IDEA 右上角的 Add Configuration...
        单击左上角的加号（+），选择 Smart Tomcat；
        输入 Name 为 tomcat8（如果你的 Tomcat 版本是 9，则写“tomcat9”）
            如果 Tomcat Server 没有值，则点击“...”按钮，选中你的 Tomcat 安装目录，或者单击“Configuration”进行配置。
        点击 Deployment Directory 右边的文件夹图标，在弹出的窗口中选中项目中的 src\main\webapp 文件夹
        Context Path 会自动改成 “左斜杠+项目名(hello-servlet)”
        配置完毕后点击 OK, 然后点击 IDEA 右上角的绿色三角形（运行），项目开始编译（项目结构里多了个 target 文件夹）并开始运行 Tomcat
        Tomcat 会输出 URL ( 类似 http://localhost:8080/hello-servlet
        在浏览器中输入 http://localhost:8080/hello-servlet 访问项目的默认页面 index.jsp
            输入 http://localhost:8080/hello-servlet/helloServlet 即可得到 "Hello, Servlet!"

其他插件
    JRebel and XRebel 热部署插件
    配合 Smart Tomcat 使用，可以修改 java 文件之后即时生效在浏览器中，不需要 restart Tomcat server.
    但是该插件需要收费, 暂时没有进一步考察
