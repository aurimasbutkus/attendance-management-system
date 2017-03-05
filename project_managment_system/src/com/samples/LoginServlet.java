package com.samples;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet(name = "/login")
public class LoginServlet extends HttpServlet {
    private static String file = "C:\\Users\\Marius\\IdeaProjects\\employee-management-system\\project_managment_system\\src\\test.txt";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("uname");
        String pass = request.getParameter("upass");
        if(verify(name, pass))
        {
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
        }
        else
        {
            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
            rs.include(request, response);
        }
    }
    private static boolean verify(String name, String pass) throws IOException {
        /*BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line = br.readLine();
            while (line != null) {
                String[] info = line.split(" ");
                if(info.length >=2) {
                    if (((name.compareToIgnoreCase(info[0]) == 0) || (name.compareToIgnoreCase(info[2]) == 0))
                            && (pass.compareToIgnoreCase(info[1]) == 0)) {
                        return true;
                    }
                    line = br.readLine();
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return false;*/
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/employee_management_system";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,"root","");


            String query = "SELECT account.username, account.password FROM account";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
               if(rs.getString("username").compareTo(name)==0 && rs.getString("password").compareTo(pass)==0)
                   return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}