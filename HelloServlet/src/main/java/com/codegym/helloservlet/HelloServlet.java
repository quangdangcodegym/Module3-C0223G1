package com.codegym.helloservlet;

import java.io.*;
import java.util.Enumeration;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Enumeration<String> enumeration = request.getHeaderNames();

        response.setContentType("text/html");
        while (enumeration.hasMoreElements()) {
            String header = enumeration.nextElement();
            System.out.println(header);
            System.out.println("Value: " + request.getHeader(header));
            if (header.equalsIgnoreCase("Sec-Ch-Ua")) {
                if (request.getHeader(header).contains("Google Chrome")) {
                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<h1>" + "CAM CHROME TRUY CAP" + "</h1>");
                    out.println("</body></html>");
                    return;
                }
            }
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");


        // Hello

    }

    public void destroy() {
    }
}