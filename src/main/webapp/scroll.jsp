<%@page import="com.lab.smart.core.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String xa = request.getParameter("spanXA");
	String xb = request.getParameter("spanXB");
	System.out.println(xa + ":scroll:" + xb);
	out.write(xa + ":" + xb);
	Client.scroll((int)(Double.parseDouble(xa)), (int)(Double.parseDouble(xb)));
%>