<%@page import="com.lab.smart.core.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    System.out.print("click");
    String click = request.getParameter("click");
    System.out.println("click="+click);
    out.write("click="+click);
    int btnID = Integer.parseInt(click);
    switch(btnID) {
    	case 0:  Client.leftMouse(); break;
    	case 1:  Client.rightMouse(); break;
    }
   
    %>
