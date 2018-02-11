package test;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class myServlet
 */
public class myServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactList contactList = new ContactList();
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phone = request.getParameter("phone");
		contactList.addContact(new contact(firstname,lastname,phone));
		
		out.println("The contact to add: " + firstname + ' ' + lastname + ' ' + phone);
		out.println("<!DOCTYPE html>");
		out.println("<html><head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		out.println("<title>The contact to add: " + firstname + ' ' + lastname + ' ' + phone + "</title></head>");
		out.println("<body>");
		out.println("<br>Contacts:<br>");
		contactList.printContacts(out);
		out.println("Click <A HREF=\"http://sgroenjesaws.ddns.net:8080/TechExercise\">");
		out.println(" here</A> to add contact.");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
