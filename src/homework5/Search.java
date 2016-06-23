package homework5;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.glass.ui.CommonDialogs.Type;

@WebServlet("/MVC/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String URL = "jdbc:mysql://cs3.calstatela.edu:3306/cs320stu26";
	public static final String USER = "cs320stu26";
	public static final String PASSWORD = "theman123";

	public void init(ServletConfig config) throws ServletException {

		super.init(config);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
		System.out.println("Get request");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
		System.out.println("Post request");
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException{
				
		String zip = request.getParameter("zip");
		String city = request.getParameter("city");
		String type = request.getParameter("type");
		String latitude = request.getParameter("lat");
		String longitude = request.getParameter("lon");
		String radius = request.getParameter("radius");
		
		if (type != null && type.equals("zip") && zip != null && !zip.isEmpty()) {
			request.getSession().setAttribute("places", getPlacesByZip(zip));
			request.getSession().setAttribute("latitude", "");
			request.getSession().setAttribute("longitude", "");
			request.getSession().setAttribute("radius", "");
			request.getSession().setAttribute("city", "");
			request.getSession().setAttribute("zip", zip);	
		}

		if (type != null && type.equals("city") && city != null && !city.isEmpty()) {
			request.getSession().setAttribute("places", getPlacesByCity(city));
			request.getSession().setAttribute("latitude", "");
			request.getSession().setAttribute("longitude", "");
			request.getSession().setAttribute("radius", "");
			request.getSession().setAttribute("zip", "");
			request.getSession().setAttribute("city", city);		
		}

		if (type != null && type.equals("location") && radius != null && !radius.isEmpty() && longitude != null && !longitude.isEmpty()
				&& latitude != null && !latitude.isEmpty()) {
			request.getSession().setAttribute("places", getPlacesByLocation(latitude, longitude, radius));
			request.getSession().setAttribute("city", "");
			request.getSession().setAttribute("zip", "");
			request.getSession().setAttribute("latitude", latitude);
			request.getSession().setAttribute("longitude", longitude);
			request.getSession().setAttribute("radius", radius);		
		}
		
		request.getRequestDispatcher("/WEB-INF/hw5/Search.jsp").forward(request, response);
	}

	protected List<Place> getPlacesByZip(String query) {
		List<Place> places = new ArrayList<Place>();

		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT name, street_address, city, state, zip, latitude, longitude" + " FROM starbucks"
					+ " WHERE zip LIKE ?";

			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				places.add(new Place(rs.getString("name"), rs.getString("street_address"), rs.getString("city"),
						rs.getString("state"), rs.getString("zip"), rs.getDouble("latitude"),
						rs.getDouble("longitude")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	protected List<Place> getPlacesByCity(String query) {
		List<Place> places = new ArrayList<Place>();

		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT name, street_address, city, state, zip, latitude, longitude" + " FROM starbucks"
					+ " WHERE SOUNDEX( ? ) = SOUNDEX( city ) || city LIKE ?";
			
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, query);
			pstmt.setString(2, "%" + query + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("name"));
				places.add(new Place(rs.getString("name"), rs.getString("street_address"), rs.getString("city"),
						rs.getString("state"), rs.getString("zip"), rs.getDouble("latitude"),
						rs.getDouble("longitude")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}
	
	protected List<Place> getPlacesByLocation(String latitude, String longitude, String radius) {
		List<Place> places = new ArrayList<Place>();

		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT *, ( 3959 * acos( cos( radians( ? ) ) * cos( radians( latitude ) ) * "
					+ "cos( radians(longitude) - radians( ? )) + sin(radians( ? )) * sin( radians"
					+ "(latitude)))) AS distance FROM starbucks HAVING distance <= ? ORDER BY distance";

			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, latitude);
			pstmt.setString(2, longitude);
			pstmt.setString(3, latitude);
			pstmt.setString(4, radius);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				places.add(new Place(rs.getString("name"), rs.getString("street_address"), rs.getString("city"),
						rs.getString("state"), rs.getString("zip"), rs.getDouble("latitude"),
						rs.getDouble("longitude")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}
}
