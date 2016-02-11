package homework2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewsletterAdminServlet
 */
@WebServlet("/Homework2/NewsletterAdmin")
public class NewsletterAdminServlet extends HttpServlet {
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
		
		List<Homework2User> MailingList = (List<Homework2User>) context.getAttribute("MailingList");
		response.setContentType("text/html");
		
		PrintWriter output = response.getWriter();
		
		output.println("<html>");
		output.println("<head><meta charset=\"utf-8\">");
		output.println(	"<title>CS320 Newsletter</title>");
		output.println(	"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">" +
			"<link rel=\"stylesheet\" href=\"http://albertcervantes.com/cs320/cdn/homework2/styles/animate.css\">" +
			"<link rel=\"stylesheet\" href=\"http://albertcervantes.com/cs320/cdn/homework2/styles/main2.css\" />");
		output.println("</head>");
		output.println("<body>" +
			"<div class=\"container\">" +
				"<div class=\"row\">" +
					"<div class=\"col-sm-offset-2 col-sm-8 newsletter-form animated fadeInUp\">" +
						"<div class=\"well\">" +
							"<h1>E-mail List <small>CS320 Newsletter</small></h1>" +
							"<hr />" +						
							"<table class=\"table table-bordered table-striped table-hover\">" +
								"<tr><th>First</th><th>Last</th><th>E-mail</th><th>Date</th></tr>");
		for (Homework2User user: MailingList) {
			output.println("<tr>");
			output.println("	<td>" + user.getFirstName() + "</td>");
			output.println("	<td>" + user.getLastName() + "</td>");
			output.println("	<td>" + user.getEmail() + "</td>");
			output.println("	<td>" + user.getdateCreated()+ "</td>");
			output.println("</tr>");
		}
		output.println("</table>");
		output.println("<p class=\"text-center\">");
		//output.println("	<a href=\"NewsletterSignup?name=\"\"&email=\"\">" + "Back to Sign-Up</a>");
		output.println("	<a href=\"NewsletterSignup\">Back to Sign-Up</a>");
		output.println("</p>");
		output.println("	</div>");
		output.println("</div>");
		output.println("</div></div></body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
