package com.samples;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

/**
 * Created by Marius on 2017-03-03.
 */
@WebServlet(name = "/register")
public class RegisterServlet extends HttpServlet {

    String file = "C:\\Users\\Marius\\IdeaProjects\\employee-management-system\\project_managment_system\\src\\test.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            response.setContentType("text/html;charset=UTF-8");
            String enteredUsername = request.getParameter("name");
            String enteredEmail = request.getParameter("email");
            String enteredPassword = request.getParameter("pass");
            //FileWriter filewriter = new FileWriter(file, true);
            //filewriter.write(name + " " + pass + " " + email + "\r\n");
            //filewriter.close();


            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/employee_management_system";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,"root","");
            Statement st = conn.createStatement();
            st.executeUpdate("SET FOREIGN_KEY_CHECKS=0");

            String query = "INSERT INTO `account` (`user_id`, `username`, `password`, `email_address`, `user_role` , `fk_Employee`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setNull(1, java.sql.Types.INTEGER);
            statement.setString(2,enteredUsername);
            statement.setString(3, enteredPassword);
            statement.setString(4,enteredEmail);
            statement.setInt(5, 3);
            statement.setNull(6, java.sql.Types.INTEGER);
            statement.execute();

            st.executeUpdate("SET FOREIGN_KEY_CHECKS=1");
            conn.close();
        }catch(Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}
