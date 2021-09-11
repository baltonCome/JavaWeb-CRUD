<%-- 
    Document   : contacts
    Created on : 9 Sep 2021, 14:51:04
    Author     : Mr. Savagery
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Bean"%>
<%@page import="java.util.ArrayList"%>

<%
    ArrayList<Bean> list = (ArrayList<Bean>) request.getAttribute("contacts");
%>


<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>Contacts</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    </head>
    <body>
        
        <div class="container text-center">
            
            <div class="text-center container">
                
                <h1 class="display-4">MY AGENDA</h1>
                <p class="lead">Contacts</p>
                
                <table class="table table-borderless">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Number</th>
                            <th scope="col">E-mail</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (list.isEmpty()){
                                out.println("No Available contacts to show");
                            }else{
                                for(int i =0; i< list.size(); i++){
                        %>
                                    <tr>
                                        <th scope="row"><%=list.get(i).getId()%></th>
                                        <td><%=list.get(i).getName()%></td>
                                        <td><%=list.get(i).getNumber()%></td>
                                        <td><%=list.get(i).getEmail()%></td>
                                        <td><a href="edit?id=<%=list.get(i).getId()%>" class="btn btn-warning"> Edit </a>
                                        <a href="javascript:del(<%=list.get(i).getId()%>)" class="btn btn-danger"> Remove </a></td>
                                    </tr>
                              <%}
                            }%>
                    </tbody>
                  </table>           
                <a href="add.html" class="btn btn-success"> Add Contact</a>
            </div>            
        </div>
        
        <script src="js/script.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
