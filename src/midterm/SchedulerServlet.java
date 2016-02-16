package midterm;

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

import homework2.Homework2User;

@WebServlet("/Midterm/Scheduler")
public class SchedulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		List<Slot> slots = new ArrayList<Slot>();
		// populate the slots with times
		Slot slot1 = new Slot(null, days, "10:00 AM");
		slots.add(slot1);
		Slot slot2 = new Slot(null, days, "10:30 AM");
		slots.add(slot2);
		Slot slot3 = new Slot(null, days, "11:00 AM");
		slots.add(slot3);
		Slot slot4 = new Slot(null, days, "11:30 AM");
		slots.add(slot4);
		Slot slot5 = new Slot(null, days, "12:00 PM");
		slots.add(slot5);
		Slot slot6 = new Slot(null, days, "12:30 PM");
		slots.add(slot6);
		Slot slot7 = new Slot(null, days, "1:00 PM");
		slots.add(slot7);
		Slot slot8 = new Slot(null, days, "1:30 PM");
		slots.add(slot8);
		Slot slot9 = new Slot(null, days, "2:00 PM");
		slots.add(slot9);
		Slot slot10 = new Slot(null, days, "2:30 PM");
		slots.add(slot10);
		Slot slot11 = new Slot(null, days, "3:00 PM");
		slots.add(slot11);
		Slot slot12 = new Slot(null, days, "3:30 PM");
		slots.add(slot12);
		Slot slot13 = new Slot(null, days, "4:00 PM");
		slots.add(slot13);
		Slot slot14 = new Slot(null, days, "4:30 PM");
		slots.add(slot14);
		Slot slot15 = new Slot(null, days, "5:00 PM");
		slots.add(slot15);
		Slot slot16 = new Slot(null, days, "5:30 PM");
		slots.add(slot16);

		ServletContext context = this.getServletContext();
		if (context.getAttribute("slots") == null) {
			context.setAttribute("slots", slots);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext context = this.getServletContext();

		List<Slot> slots = (List<Slot>) context.getAttribute("slots");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title>SchedulerServlet</title>");
		out.println(
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
		out.println(
				"<link rel=\"stylesheet\" href=\"http://albertcervantes.com/cs320/cdn/homework2/styles/animate.css\">");

		out.println("	</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-sm-offset-2 col-sm-8 newsletter-form animated fadeInUp\">");
		out.println("<div class=\"well\">");

		out.println("<table class=\"table table-bordered table-striped table-hover\">");
		out.println("<tr>");
		out.println("<th>Time</th>");
		out.println("<th>Monday</th>");
		out.println("<th>Tuesday</th>");
		out.println("<th>Wednesday</th>");
		out.println("<th>Thursday</th>");
		out.println("<th>Friday</th>");
		out.println("</tr>");

		String currentStudent = (String) request.getSession().getAttribute("currentStudentName");

		for (Slot slot : slots) {
			out.println("	<tr>");
			out.println("<td>" + slot.getTime() + "</td>");

			String nameDelete = request.getParameter("nameDelete");
			String dayDelete = request.getParameter("dayDelete");
			String timeDelete = request.getParameter("timeDelete");
			if (nameDelete != null && slot.getName() != null && dayDelete != null && slot.getDay() != null
					&& timeDelete != null && slot.getTime() != null) {
				if (slot.getName().equals(nameDelete) && slot.getTime().equals(timeDelete)
						&& slot.getDay().equals(dayDelete)) {
					slot.setName(null);
				}
			}

			String name = slot.getName();

			for (Slot slot1 : slot.getSlots()) {

				if (name != null && slot.getDay().equals("Monday")) {
					if (name.equals(currentStudent)) {
						out.println("<td>" + slot.getName() + "<a href=\"Scheduler?nameDelete=" + currentStudent
								+ "&dayDelete=" + slot.getDay() + "&timeDelete=" + slot.getTime() + "\">[x]</a></td>");
					} else {
						out.println("<td>" + slot.getName() + "</td>");
					}
				} else {
					out.println("<td><a class=\"btn btn-info\" href=\"Signup?time=" + slot.getTime()
							+ "&day=Monday \">Sign-Up</a></td>");
				}

				if (name != null && slot.getDay().equals("Tuesday")) {
					if (name.equals(currentStudent)) {
						out.println("<td>" + slot.getName() + "<a href=\"Scheduler?nameDelete=" + currentStudent
								+ "&dayDelete=" + slot.getDay() + "&timeDelete=" + slot.getTime() + "\">[x]</a></td>");
					} else {
						out.println("<td>" + slot.getName() + "</td>");
					}
				} else {
					out.println("<td><a class=\"btn btn-info\" href=\"Signup?time=" + slot.getTime()
							+ "&day=Tuesday \">Sign-Up</a></td>");
				}

				if (name != null && slot.getDay().equals("Wednesday")) {
					if (name.equals(currentStudent)) {
						out.println("<td>" + slot.getName() + "<a href=\"Scheduler?nameDelete=" + currentStudent
								+ "&dayDelete=" + slot.getDay() + "&timeDelete=" + slot.getTime() + "\">[x]</a></td>");
					} else {
						out.println("<td>" + slot.getName() + "</td>");
					}
				} else {
					out.println("<td><a class=\"btn btn-info\" href=\"Signup?time=" + slot.getTime()
							+ "&day=Wednesday \">Sign-Up</a></td>");
				}

				if (name != null && slot.getDay().equals("Thursday")) {
					if (name.equals(currentStudent)) {
						out.println("<td>" + slot.getName() + "<a href=\"Scheduler?nameDelete=" + currentStudent
								+ "&dayDelete=" + slot.getDay() + "&timeDelete=" + slot.getTime() + "\">[x]</a></td>");
					} else {
						out.println("<td>" + slot.getName() + "</td>");
					}
				} else {
					out.println("<td><a class=\"btn btn-info\" href=\"Signup?time=" + slot.getTime()
							+ "&day=Thursday  \">Sign-Up</a></td>");
				}

				if (name != null && slot.getDay().equals("Friday")) {
					if (name.equals(currentStudent)) {
						out.println("<td>" + slot.getName() + "<a href=\"Scheduler?nameDelete=" + currentStudent
								+ "&dayDelete=" + slot.getDay() + "&timeDelete=" + slot.getTime() + "\">[x]</a></td>");
					} else {
						out.println("<td>" + slot.getName() + "</td>");
					}
				} else {
					out.println("<td><a class=\"btn btn-info\" href=\"Signup?time=" + slot.getTime()
							+ "&day=Friday \">Sign-Up</a></td>");
				}
			}
			out.println("</tr>");
		}

		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
