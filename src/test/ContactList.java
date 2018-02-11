package test;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

class contact{
	public contact(String firstname2, String lastname2, String phone2) {
		firstname = firstname2;
		lastname = lastname2;
		phone = phone2;
	}
	String firstname;
	String lastname;
	String phone;
	public void PrintContact(PrintWriter writer){
		writer.println(firstname+ ' '+lastname+' '+phone);
	}
}

class ContactList{
	ArrayList<contact> contactList;
	public ContactList(){
		if(contactList == null)
			contactList = new ArrayList<contact>();
	}
	
	public void addContact(contact cont){
		contactList.add(cont);
	}
	
	public void printContacts(PrintWriter writer){
		for(Iterator<contact> i = contactList.iterator(); i.hasNext();){
			contact item= i.next();
			item.PrintContact(writer);
			writer.println("<br>");
		}
	}
	
	
}