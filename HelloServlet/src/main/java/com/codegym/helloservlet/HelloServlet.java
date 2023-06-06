package com.codegym.helloservlet;

import java.io.*;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message = "Hello";

    @Override
    public void init() throws ServletException {
        System.out.println("Khoi tao servlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("Phuc vu Request gui toi");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("Chay vao ham doGet");
        /**
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


        **/

    }

    public void destroy() {

        System.out.println("Huy servlet");
    }
}