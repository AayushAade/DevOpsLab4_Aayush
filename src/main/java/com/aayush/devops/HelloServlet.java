package com.aayush.devops;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Aayush DevOps Application</title>
            </head>
            <body>
                <h1>Aayush Aade</h1>
                <h2>Jenkins CI/CD Pipeline</h2>
                <p>Roll No: 34101</p>
                <p>Build, Test, Package and Deploy using Maven and Jenkins.</p>
            </body>
            </html>
            """);
    }
}
