package midterm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Midterm/Signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String time = request.getParameter("time");
		String day = request.getParameter("day");
		request.getSession().setAttribute("time", time);
		request.getSession().setAttribute("day", day);
		
		String sessionTime = (String) request.getSession().getAttribute("time");
		String sessionDay = (String) request.getSession().getAttribute("day");
		System.out.println(sessionTime);
		System.out.println(sessionDay);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title>SignupServlet</title>");
		out.println(
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
		out.println(
				"<link rel=\"stylesheet\" href=\"http://albertcervantes.com/cs320/cdn/homework2/styles/animate.css\">");

		out.println("	</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("<h1>Presentation Scheduler</h1>");
		out.println("<div class=\"row\">");
		out.println("Requested Date & Time");
		out.println("&nbsp&nbsp;" + sessionTime + " at " + sessionDay);
		out.println("</div>");
		out.println("<div class=\"row\">");
		out.println("<form action=\"Signup\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"time\" value=\"" + sessionTime + "\"" + "/>");
		out.println("<input type=\"hidden\" name=\"day\" value=\"" + sessionDay + "\"" + "/>");
		out.println("Student's Name <input type=\"text\" name=\"name\" placeholder=\"Enter your full name\" />");
		if (request.getAttribute("nameError") != null) {
			out.println("Error: please enter a name<br/>");
		}
		out.println("<input class=\"btn btn-info\" type=\"submit\" value=\"Sign-Up\" />");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String time = request.getParameter("time");
		String day = request.getParameter("day");
		String name = request.getParameter("name");

		boolean hasError = false;

		if (name == null || name.trim().length() == 0) {
			request.setAttribute("nameError", "Please enter a name");
			hasError = true;
		}

		if (hasError) {
			doGet(request, response);
		} 
		
		else {
			HttpSession session = request.getSession();
			
			request.setAttribute("nameError", null);
			ServletContext context = this.getServletContext();
			@SuppressWarnings("unchecked")
			List<Slot> slots = (List<Slot>) context.getAttribute("slots");

			for (Slot slot : slots) {
				if (slot.getTime().equals(time) && slot.getName() == null) {
					slot.setDay(day);
					slot.setName(name);
				}
			}
			session.setAttribute("currentStudentName", name);
			response.sendRedirect("Scheduler");
		}
	}
}
