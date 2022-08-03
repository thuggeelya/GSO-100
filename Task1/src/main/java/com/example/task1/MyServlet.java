package com.example.task1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class MyServlet extends HttpServlet {

    private String name;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        name = request.getParameter("name");
        request.setAttribute("name", name);
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html><body>");
        out.println();

        if ((name == null) || (name.trim().isEmpty())) {
            out.write("<h2>Error: the name is null</h2>");
            out.println();
        } else {
            out.write("<h2>Hello, " + name + "</h2>");
            out.println();
        }

        out.write("</body></html>");
        out.flush();
    }
}