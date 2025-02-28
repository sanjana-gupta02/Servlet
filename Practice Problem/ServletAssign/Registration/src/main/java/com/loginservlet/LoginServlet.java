package com.loginservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import com.registrationservlet.RegisterServlet; // Import RegisterServlet

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final HashMap<String, String> users = RegisterServlet.getUsers(); // Access stored users

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve login details
        String username = request.getParameter("user");
        String password = request.getParameter("pwd");

        // Validate user credentials
        if (users.containsKey(username) && users.get(username).equals(password)) {
            request.setAttribute("user", username);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            out.println("<h3 style='color:red;'>Invalid Username or Password.</h3>");
            request.getRequestDispatcher("login.html").include(request, response);
        }
    }
}
