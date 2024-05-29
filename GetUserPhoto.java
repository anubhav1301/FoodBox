package com.foodbox.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.foodbox.Modal.DAO;


/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/GetUserPhoto")
@MultipartConfig
public class GetUserPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String email=request.getParameter("uemail");
			DAO db=new DAO();
			byte[] photo=db.getUserPhoto(email);
			db.closeConnection();
			if(photo==null) {
				ServletContext ctx=getServletContext();
				photo=ctx.getResourceAsStream("/resources/user.png").readAllBytes();
			}
			response.getOutputStream().write(photo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}
