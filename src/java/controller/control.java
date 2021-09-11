/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.Bean;

/**
 *
 * @author Mr. Savagery
 */
@WebServlet(urlPatterns ={"/control" ,"/main", "/save", "/edit" , "/update", "/delete"})
public class control extends HttpServlet {
    
    DAO dao =new DAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            String action = request.getServletPath();
            switch (action) {
                
                case "/main":
                    contacts(request, response);
                    break;
                    
                case "/save":
                    addContact(request, response);
                    break;
                    
                case "/edit":
                    select(request, response);
                    break;
                    
                case "/update":
                    edit(request, response);
                    break;
                    
                case "/delete":
                    remove(request, response);
                    break;
                    
                default:
                    response.sendRedirect("index.html");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void contacts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        ArrayList <Bean> list = dao.list();
        request.setAttribute("contacts", list);
        RequestDispatcher rd = request.getRequestDispatcher("contacts.jsp");
        rd.forward(request, response);
    }
    
    protected void addContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        Bean contact = new Bean(request.getParameter("_name"), request.getParameter("number"), request.getParameter("email"));
        dao.insert(contact);
        response.sendRedirect("main");
    }
    
    Bean contact = new Bean();
    
    protected void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        contact.setId(id);
        
        dao.setContact(contact);
        
        request.setAttribute("id", contact.getId());
        request.setAttribute("_name", contact.getName());
        request.setAttribute("number", contact.getNumber());
        request.setAttribute("email", contact.getEmail());
        
        RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
        rd.forward(request, response);
    }
    
    
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Bean contact = new Bean(id, request.getParameter("_name"), request.getParameter("number"), request.getParameter("email"));
        dao.update(contact);
        response.sendRedirect("main");
    }
    
    protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        contact.setId(id);
        dao.delete(contact);
        response.sendRedirect("main");
    }
    
}
