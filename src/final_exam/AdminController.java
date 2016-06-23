package final_exam;

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

@WebServlet("/Final/Admin")
public class AdminController extends HttpServlet {
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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		
		if (action != null && action.equals("delete") && id != null) {
			deleteCandidate(Integer.parseInt(id));
		}
		request.setAttribute("candidates", getCandidates());
		request.getRequestDispatcher("/WEB-INF/final/AdminView.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");

		if (name != null && name.trim().length() != 0) {
			addCandidate(name);
			request.setAttribute("candidates", getCandidates());
			request.getRequestDispatcher("/WEB-INF/final/AdminView.jsp").forward(request, response);
		}
	}
	
	protected List<Candidate> getCandidates() {
		List<Candidate> candidates = new ArrayList<>();
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "SELECT * FROM candidates";

			PreparedStatement pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Candidate candidate = new Candidate(rs.getInt("id"), rs.getString("name"), rs.getInt("yes"), rs.getInt("no"));
				candidates.add(candidate);
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

		return candidates;
	}

	protected void addCandidate(String name) {
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "INSERT INTO candidates (name) values ( ? )";

			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();

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
	}
	
	protected void deleteCandidate(int id) {
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "DELETE FROM candidates where id = ?";

			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();

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
	}
	
}
