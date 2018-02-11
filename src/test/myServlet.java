package test;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class myServlet
 */
public class myServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String url = "jdbc:mysql://sgroenjesaws.ddns.net:3306/myDB";
	static String user = "sgroenjes";
	static String password = "Groen6OE";
	static Connection connection = null;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e){
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		connection = null;
		try{
			connection = (Connection) DriverManager.getConnection(url, user, password);
		} catch(SQLException e){
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		if(connection == null){
			System.out.println("Failed to make connection.");
		}	
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phone = request.getParameter("phone");
		String sql = "INSERT INTO myTable VALUE(DEFAULT,'"+firstname+"','"+lastname+"','"+phone+"');";
		
		//Add contact to DB
		try{
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		out.println("The contact to add: " + firstname + ' ' + lastname + ' ' + phone);
		out.println("<!DOCTYPE html>");
		out.println("<html><head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		out.println("<title>The contact to add: " + firstname + ' ' + lastname + ' ' + phone + "</title></head>");
		out.println("<body>");
		out.println("Click <A HREF=\"http://sgroenjesaws.ddns.net:8080/TechExercise/addContact.html\">");
		out.println(" here</A> to add contact.");
		
		} catch(SQLException e){
			e.printStackTrace();
			return;
		}
		
		//Write contacts to screen
		try{
			String selectSQL = "SELECT * FROM myTable;";
			out.println("<br>");
			out.println("-------------------------------------------------------------<br>");
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			out.println("<br>Contacts:<br>");
			while(rs.next()){
				firstname = rs.getString("FIRSTNAME");
				lastname = rs.getString("LASTNAME");
				phone = rs.getString("PHONE");
				out.append(firstname+ ' ' + lastname + " "+ phone + "<br>");
			}
			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		out.close();
	}

}
