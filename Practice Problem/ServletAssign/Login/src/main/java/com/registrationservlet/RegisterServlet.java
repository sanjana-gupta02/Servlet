package com.registrationservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final HashMap<String, String> users = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String username = request.getParameter("user");
        String password = request.getParameter("pwd");

        // Check if username already exists
        if (users.containsKey(username)) {
            out.println("<h3 style='color:red;'>Username already exists! Try another one.</h3>");
            request.getRequestDispatcher("registration.html").include(request, response);
        } else {
            // Store user data
            users.put(username, password);
            out.println("<h3 style='color:green;'>Registration Successful! You can now login.</h3>");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }

    // Add this method to allow LoginServlet to access registered users
    public static HashMap<String, String> getUsers() {
        return users;
    }
}
