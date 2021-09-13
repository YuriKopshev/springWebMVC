package ru.netology;

import com.google.gson.Gson;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.config.WebConfig;
import ru.netology.controller.PostController;
import ru.netology.initializer.ApplicationInitializer;
import ru.netology.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
//    public static final String GET = "GET";
//    public static final String POST = "POST";
//    public static final String DELETE = "DELETE";
//    public static final String PATH = "/api/posts";
//    private static PostController controller;

    public static void startServer() throws LifecycleException, IOException {
        final var tomcat = new Tomcat();
        final var baseDir = Files.createTempDirectory("tomcat");
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final var connector = new Connector();
        connector.setPort(9999);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        tomcat.start();
        tomcat.getServer().await();
    }

//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            final var path = req.getRequestURI();
//            final var method = req.getMethod();
//            if (method.equals(GET) && path.equals(PATH)) {
//                controller.all();
//            } else if (method.equals(GET) && path.matches(PATH + "/\\d+")) {
//                // easy way
//                final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
//                controller.getById(id);
//            } else {
//                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            final var path = req.getRequestURI();
//            final var method = req.getMethod();
//            if (method.equals(POST) && path.equals(PATH)) {
//                Gson gson = new Gson();
//                final var post = gson.fromJson(req.getReader(), Post.class);
//                controller.save(post);
//            } else {
//                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            final var path = req.getRequestURI();
//            final var method = req.getMethod();
//            if (method.equals(DELETE) && path.matches(PATH + "/\\d+")) {
//                final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
//                controller.removeById(id);
//            } else {
//                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }

    public static void main(String[] args) throws LifecycleException, IOException {
        Main.startServer();
    }
}
