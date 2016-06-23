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

@WebServlet("/Final/Vote")
public class VoteController extends HttpServlet {
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

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Candidate> candidates = (ArrayList<Candidate>) request.getSession().getAttribute("candidatesRand");
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		
		//no candidates yet and did not vote for any of them.
		if (candidates == null && action == null) {
			candidates = new ArrayList<>();
			Candidate candidate = getRandomCandidate();
			request.getSession().setAttribute("candidate", candidate);
			System.out.println("Candidates is null. Creating Candidates...");
			candidates.add(candidate);
			request.getSession().setAttribute("candidatesRand", candidates);
		} else if (action != null && id != null) {
			boolean candidateFound = false;
			while (!candidateFound) {
				Candidate candidate = getRandomCandidate();
				int candId = Integer.parseInt(id);
				if (action.equals("yes")) {
					increaseYesVote(candId);			
					if (candidates.contains(candidate)) {
						System.out.println("candidate not added...adding now");
						request.getSession().setAttribute("candidate", candidate);
						candidates.add(candidate);
						candidateFound = true;
					}
				} 
				else if (action.equals("no")) {
					increaseNoVote(candId);
					if (candidates.contains(candidate)) {
						System.out.println("candidate not added...adding now");
						request.getSession().setAttribute("candidate", candidate);
						candidates.add(candidate);
						candidateFound = true;
					}
				}				
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/final/VoteView.jsp").forward(request, response);
	}

	protected Candidate getRandomCandidate() {

		Connection c = null;
		Candidate candidate = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "SELECT * from candidates order by RAND() LIMIT 1";

			PreparedStatement pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				candidate = new Candidate(rs.getInt("id"), rs.getString("name"), rs.getInt("yes"), rs.getInt("no"));
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

		return candidate;
	}

	protected void increaseYesVote(int id) {
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "UPDATE candidates SET yes = yes + 1 WHERE id = ?";

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

	protected void increaseNoVote(int id) {
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "UPDATE candidates SET no = no + 1 WHERE id = ?";

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