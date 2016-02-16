package homework3;

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
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@WebServlet("/Homework3/CoffeeShopLocator")
public class CoffeeShopLocator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		List<Location> locations = new ArrayList<Location>();
		ServletContext context = this.getServletContext();

		String csvFile = context.getRealPath("/WEB-INF/starbucks.csv");
		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				Location location = new Location(fields);
				locations.add(location);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (context.getAttribute("locations") == null) {
			context.setAttribute("locations", locations);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String querySticky = (String) session.getAttribute("query");
		String typeSticky = (String) session.getAttribute("type");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("	<!doctype html>");
		out.println("	<html>");
		out.println("	<head>");
		out.println("	<title>CS320 - Homework 3</title>");
		out.println(
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
		out.println("	</head>");
		out.println("	<body>");
		out.println("	<form action=\"CoffeeShopLocator\" method=\"post\">");
		if (querySticky != null) {
			out.println("	<input type=\"text\" name=\"query\" value=" + "\"" + querySticky + "\"" + "/> ");
		} else {
			out.println("	<input type=\"text\" name=\"query\" /> ");
		}
		if (typeSticky != null) {
			out.println("	<select name=\"type\">");

			if (typeSticky.equals("location")) {
				out.println("	<option value=\"location\" selected>Location</option>");
			} else {
				out.println("	<option value=\"location\">Location</option>");
			}

			if (typeSticky.equals("city")) {
				out.println("	<option value=\"city\" selected>City</option>");
			} else {
				out.println("	<option value=\"city\">City</option>");
			}

			if (typeSticky.equals("zip")) {
				out.println("	<option value=\"zip\" selected>Zip</option>");
			} else {
				out.println("	<option value=\"zip\">Zip</option>");
			}
			out.println("	</select> <input type=\"submit\" value=\"submit\" />");
		} else {
			out.println("	<select name=\"type\">");
			out.println("	<option value=\"location\">Location</option>");
			out.println("	<option value=\"city\">City</option>");
			out.println("	<option value=\"zip\">Zip</option>");
			out.println("	</select> <input type=\"submit\" value=\"submit\" />");
		}
		out.println("	</form>");
		out.println("	</body>");
		out.println("	</html>");
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext context = this.getServletContext();
		List<Location> locations = (List<Location>) context.getAttribute("locations");

		String query = request.getParameter("query");
		String type = request.getParameter("type");

		if (query == null || type == null) {
			doGet(request, response);
		}

		HttpSession session = request.getSession();
		session.setAttribute("results", new ArrayList<Location>());
		session.setAttribute("query", query);
		session.setAttribute("type", type);

		List<Location> results = (List<Location>) request.getSession().getAttribute("results");

		if (type.equals("location") && query != "") {
			String[] locationPair = query.split(",");
			double latitude1 = Math.toRadians(Double.valueOf(locationPair[0]));
			double longitude1 = Math.toRadians(Double.valueOf(locationPair[1]));

			for (Location location : locations) {
				double latitude2 = Math.toRadians(location.getLatitude());
				double longitude2 = Math.toRadians(location.getLongitude());
				double longitudeDelta = Math.abs(longitude2 - longitude1);
				double centralAngle = Math.acos(Math.sin(latitude1) * Math.sin(latitude2)
						+ Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitudeDelta));

				// in miles
				double distance = centralAngle * 3961;
				location.setMiles((double)Math.round(distance * 100d) / 100d);
				session.setAttribute("miles", distance);
				if (distance <= 10) {
					results.add(location);
				}
			}
		}
		
		if (type.equals("city") && query != "") {
			session.setAttribute("miles", null);
			for (Location location : locations) {
				if (location.getCity() != null) {
					String city = location.getCity().toLowerCase();
					if (city.contains(query)) {
						results.add(location);
					}
				}
			}
		}

		if (type.equals("zip")) {
			session.setAttribute("miles", null);
			for (Location location : locations) {
				String zip = location.getZip();
				if (zip != null) {
					if (zip.equals(query)) {
						results.add(location);
					}
				}
			}
		}

		String querySticky = (String) session.getAttribute("query");
		String typeSticky = (String) session.getAttribute("type");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("	<!doctype html>");
		out.println("	<html>");
		out.println("	<head>");
		out.println("	<title>CS320 - Homework 3</title>");
		out.println(
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
		out.println("	</head>");
		out.println("	<body>");
		out.println("	<form action=\"CoffeeShopLocator\" method=\"post\">");

		if (querySticky != null) {
			out.println("	<input type=\"text\" name=\"query\" value=" + "\"" + querySticky + "\"" + "/> ");
		} else {
			out.println("	<input type=\"text\" name=\"query\" /> ");
		}

		if (typeSticky != null) {
			out.println("	<select name=\"type\">");

			if (typeSticky.equals("location")) {
				out.println("	<option value=\"location\" selected>Location</option>");
			} else {
				out.println("	<option value=\"location\">Location</option>");
			}

			if (typeSticky.equals("city")) {
				out.println("	<option value=\"city\" selected>City</option>");
			} else {
				out.println("	<option value=\"city\">City</option>");
			}

			if (typeSticky.equals("zip")) {
				out.println("	<option value=\"zip\" selected>Zip</option>");
			} else {
				out.println("	<option value=\"zip\">Zip</option>");
			}
			out.println("	</select> <input type=\"submit\" value=\"submit\" />");
		} else {
			out.println("	<select name=\"type\">");
			out.println("	<option value=\"location\">Location</option>");
			out.println("	<option value=\"city\">City</option>");
			out.println("	<option value=\"zip\">Zip</option>");
			out.println("	</select> <input type=\"submit\" value=\"submit\" />");
		}
		out.println("	</form>");

		if (results.size() >= 1) {
			if (session.getAttribute("miles") == null) {
				out.println("	<table id=\"results\" class=\"table table-bordered table-striped table-hover\">");
				out.println("<tr><th>Latitude</th><th>Longitude</th><th>Name</th><th>Address</th></tr>");
				for (Location location : results) {
					out.println("	<tr>");
					out.println("	<td>" + location.getLatitude() + "</td>");
					out.println("	<td>" + location.getLongitude() + "</td>");
					out.println("	<td>" + location.getName() + "</td>");
					out.println("	<td>" + location.getAddress() + "</td>");
					out.println("	</tr>");
				}
				out.println("	</table>");
			} else {
				out.println("	<table id=\"results\" class=\"table table-bordered table-striped table-hover\">");
				out.println("<tr><th>Distance (mi.)</th><th>Latitude</th><th>Longitude</th><th>Name</th><th>Address</th></tr>");
				for (Location location : results) {
					out.println("	<tr>");
					out.println("	<td>" + location.getMiles() + "</td>");
					out.println("	<td>" + location.getLatitude() + "</td>");
					out.println("	<td>" + location.getLongitude() + "</td>");
					out.println("	<td>" + location.getName() + "</td>");
					out.println("	<td>" + location.getAddress() + "</td>");
					out.println("	</tr>");
				}
				out.println("	</table>");
			}
		}
		out.println("	</body>");
		out.println("	</html>");
	}
}
