package weblayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Validation
 */
@WebServlet("/Validation")
public class Validation extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String jdbcURL = "jdbc:mysql://localhost:3306/shop";
        String username = "root";
        String password = "1234";
        
        try(PrintWriter object=	response.getWriter())
		{
        	response.setContentType("text/html");
        	String username1=request.getParameter("uname");
    		String password1=request.getParameter("pwd");
    		
    		if(username1.equals("admin")&&password1.equals("admin123"))
    		{
    			object.println("Welcome admin");
    			 
    			RequestDispatcher rd = 	request.getRequestDispatcher("AdminHome.html");
    			rd.forward(request, response);
    		}
    		else
    		{
    			Class.forName("com.mysql.cj.jdbc.Driver"); // correct for MySQL 8+
    			Connection con=DriverManager.getConnection(jdbcURL,username,password);   
    			Statement stmt =con.createStatement();  
    		ResultSet rs=	stmt.executeQuery("select * from register");
    		int flag=0;
    		   while(rs.next())
    		   {
    			   String dbuser= rs.getString("username");
    			   String dbpassword=rs.getString("password");
    			   if(username1.equals(dbuser)&&password1.equals(dbpassword))
    			   {
    				   object.println("Login Sucessfully Welcome User");
    				   flag=1;
    				   RequestDispatcher rd = 	request.getRequestDispatcher("UserHome.html");
    				   rd.forward(request, response);
    				   break;
    			   }
    			   
    		   }
    		   if(flag==0)
			   {
				   object.println("Sorry data not found");
				   RequestDispatcher rd = 	request.getRequestDispatcher("index.html");
//				   request.getRequestDispatcher("index.html");
				   rd.include(request, response);
			   }
    		}
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
