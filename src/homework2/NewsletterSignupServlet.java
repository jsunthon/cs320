package homework2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Homework2/NewsletterSignup")
public class NewsletterSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext context = this.getServletContext();
		if (context.getAttribute("MailingList") == null){
			context.setAttribute("MailingList", new ArrayList<Homework2User>());
		}
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ServletContext context = this.getServletContext();
		List<Homework2User> entries = (List<Homework2User>) context.getAttribute("users");
		
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		String email = request.getParameter("email") == null ? "" : request.getParameter("email");
		
		response.setContentType("text/html");
		PrintWriter output = response.getWriter();
		
		output.println("<html><head><meta charset=\"utf-8\"><title>CS320 Newsletter</title>" +
		"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">" +
		"<link rel=\"stylesheet\" href=\"http://albertcervantes.com/cs320/cdn/homework2/styles/animate.css\">" +
		"<link rel=\"stylesheet\" href=\"http://albertcervantes.com/cs320/cdn/homework2/styles/main.css\" />" +
		"</head>" + 
	    	"<body>" + 
			"<div class=\"container\">" + 
			"<div class=\"row\">" + 
				"<div class=\"col-sm-offset-3 col-sm-6 newsletter-form animated bounceInDown\">" + 
					"<div class=\"well text-center\">" +
						"<h1>CS320 Newsletter</h1>" +
						"<hr />");
		if  (context.getAttribute("registered") == "success") {
			context.setAttribute("registered", null);
		output.println("<p class=\"lead\">You're all set, <strong>" + request.getAttribute("firstName") + "</strong>!<br />We'll e-mail you at <strong>" + email + "</strong> with regular updates.</p>" +
						"<h1><span class=\"glyphicon glyphicon-ok text-success\"></span></h1>" +
						"<a href=\"NewsletterAdmin\">View All E-mails</a>");
		}
		else {
						output.println("<p class=\"lead\">Subscribe to our weekly Newsletter, and stay up-to-date with all things related to the course!</p>" +
						"<form action=\"NewsletterSignup\" method=\"post\">");
		
						
						if (request.getAttribute("nameError") != null) {
								output.println("<div class=\"form-group has-error\">" +
								"<div class=\"input-group\">" +
									"<div class=\"input-group-addon\">" +
										"<span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span>" +
									"</div>" + 
									"<input class=\"form-control\" type=\"text\" name=\"name\" placeholder=\"Enter your first and last name\" />"
										+ "</div><p class=\"text-danger\">" + request.getAttribute("nameError") + "</p></div>");
						} else {
						output.println("<div class=\"form-group\">" +
								"<div class=\"input-group\">" +
									"<div class=\"input-group-addon\">" +
										"<span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span>" +
									"</div>" + 
									"<input class=\"form-control\" type=\"text\" value=\"" + name + "\" name=\"name\" placeholder=\"Enter your first and last name\" /></div></div>");
						}
						
						if (request.getAttribute("emailError") != null) {
							output.println("<div class=\"form-group has-error\">" +
							"<div class=\"input-group\">" +
								"<div class=\"input-group-addon\">" +
									"<span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span>" +
								"</div>" + 
								"<input class=\"form-control\" type=\"text\" name=\"email\" placeholder=\"Enter your e-mail address\" />"
									+ "</div><p class=\"text-danger\">" + request.getAttribute("emailError") + "</p></div>");
						} else {
							output.println("<div class=\"form-group\">" +
							"<div class=\"input-group\">" +
								"<div class=\"input-group-addon\">" +
									"<span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span>" +
								"</div>" + 
								"<input class=\"form-control\" type=\"text\" value=\"" + email + "\" name=\"email\" placeholder=\"Enter your e-mail address\" /></div></div>");
					}
							
							output.println("<input type=\"submit\" class=\"btn btn-info btn-block btn-lg\" value=\"Subscribe Now!\" /></form>");
		}							
						
					output.println("</div>" +
				"</div>" +
			"</div>" +
		"</div>" +
	"</body>" +
"</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		boolean hasError = false;
		
		/*
		 * Name validation and parsing
		 */
		String[] nameArray = name.split("\\s+");
			
		if (name == null || name.trim().length() == 0 || nameArray.length < 2){
			// If we get here, there is an issue with the name
			request.setAttribute("nameError", "Please enter your first and last names.");
			hasError = true;
		} else {
			for (String name_part: nameArray) {
				if (name_part.length() < 2) {
					request.setAttribute("nameError", "Please enter your first and last names.");
					hasError = true;
				}
			}
		}
		
		String[] lastNameArray = new String[nameArray.length - 1];
		
		for (int i = 1; i < nameArray.length; i++) {
			lastNameArray[i - 1] = nameArray[i];
		}
		
		String firstName = nameArray[0];
	//	String lastName = String.join(" ", lastNameArray);
		
		String lastName = "";
		
		for (int i = 0; i < lastNameArray.length; i++) {
			lastName += lastNameArray[i];
			
			if (i != lastNameArray.length - 1) {
				lastName += " ";
			}
		}
		
		/*
		 * Email validation
		 */		
		if (email == null || email.trim().length() == 0) {
			request.setAttribute("emailError", "Please enter a valid e-mail address.");
			hasError = true;
		} 
		else if (!(Pattern.matches("[A-Za-z0-9._%]+@[A-Za-z0-9._%]+\\.[A-Za-z]{2,5}", email))) {
			// If we get here, there is an issue with the message
			request.setAttribute("emailError", "Please enter a valid e-mail address.");
			hasError = true;
		}
		else {
			request.setAttribute("emailError", null);
		}
		
		/*
		 * if error, request same page using doGet
		*/
		if (hasError){
			doGet(request, response);
		}
		
		
		else{
			
			ServletContext context = this.getServletContext();
			List<Homework2User> MailingList = (ArrayList<Homework2User>) context.getAttribute("MailingList");
			context.setAttribute("registered", "success");
			request.setAttribute("firstName", firstName);
			MailingList.add(new Homework2User(firstName, lastName, email));
			
			doGet(request, response);
		}
	}	

}
