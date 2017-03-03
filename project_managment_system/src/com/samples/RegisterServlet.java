package com.samples;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Marius on 2017-03-03.
 */
@WebServlet(name = "/register")
public class RegisterServlet extends HttpServlet {
    String file = "C:\\Users\\Marius\\IdeaProjects\\employee-management-system\\project_managment_system\\src\\test.txt";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        FileWriter filewriter = new FileWriter(file, true);
        filewriter.write(name + " " + pass + " " + email + "\r\n");
        filewriter.close();
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}
