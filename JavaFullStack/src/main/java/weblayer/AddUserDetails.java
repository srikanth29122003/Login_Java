package weblayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUserDetails
 */
@WebServlet("/AddUserDetails")
public class AddUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jdbcURL = "jdbc:mysql://localhost:3306/shop";
        String username = "root";
        String password = "1234";
		try(PrintWriter object=	response.getWriter())
		{
			String u=request.getParameter("n");
			String p=request.getParameter("p");
			String cp=request.getParameter("cp");
			
			
			Class.forName("com.mysql.cj.jdbc.Driver"); // correct for MySQL 8+
  
			Connection con=DriverManager.getConnection(jdbcURL,username,password);   
			Statement stmt =con.createStatement();  
			int value= stmt.executeUpdate("insert into register (username,password,cpassword) values('"+u+"','"+p+"','"+cp+"')");
			if(value==1)
				object.println("record inserted");
			
		} 
		
		catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		catch(SQLException e)
		{
				System.out.println(e);
		}
		
		
		  
		
		
		
	}

}
