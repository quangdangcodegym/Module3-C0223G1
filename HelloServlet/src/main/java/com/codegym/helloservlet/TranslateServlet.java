package com.codegym.helloservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "TranslateServlet", urlPatterns = "/translate")
public class TranslateServlet extends HttpServlet {

    private Map<String,String> tudien;
    public TranslateServlet() {
        tudien = new HashMap<>();
        tudien.put("hello", "Xin chao");
        tudien.put("apple", "qua tao");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         resp.setContentType("text/html");

         PrintWriter out = resp.getWriter();
         out.println("<!DOCTYPE html>\n" +
         "<html lang=\"en\">\n" +
         "<head>\n" +
         "    <meta charset=\"UTF-8\">\n" +
         "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
         "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
         "    <title>Document</title>\n" +
         "</head>\n" +
         "<body>\n" +
         "    <form action=\"/translate\" method=\"post\">\n" +
         "        <input type=\"text\" name=\"txtSearch\" >\n" +
         "        <input type=\"submit\" value=\"Translate\" />\n" +
         "    </form>\n" +
         "</body>\n" +
         "</html>");
         */

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
        String key = req.getParameter("txtSearch");

        String value = tudien.get(key.toLowerCase());
        String valueHtml = String.format("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form action=\"/translate\" method=\"post\">\n" +
                "        <input type=\"text\" name=\"txtSearch\" >\n" +
                "        <input type=\"submit\" value=\"Translate\" />\n" +
                "    </form>\n" +
                "    <h6>Meaning: %s</h6>\n" +
                "</body>\n" +
                "</html>", value);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(valueHtml);

         **/

        String key = req.getParameter("txtSearch");
        String value = tudien.get(key.toLowerCase());       // value: 'Xin chao'

        req.setAttribute("value", value);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/translate.jsp");
        requestDispatcher.forward(req, resp);
    }
}
