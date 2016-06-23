package final_exam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Final/QuotationController")
public class QuotationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String URL = "jdbc:mysql://cs3.calstatela.edu:3306/cs320stu26";
	public static final String USER = "cs320stu26";
	public static final String PASSWORD = "theman123";

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.setAttribute("quotations", getQuotations(""));
		request.getRequestDispatcher("/WEB-INF/final/QuotationView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String query = request.getParameter("query");

		// if user entered a valid query, then search for it
		if (query != null && query.trim().length() != 0) {
			request.setAttribute("quotations", getQuotations(query));
			request.getRequestDispatcher("/WEB-INF/final/QuotationView.jsp").forward(request, response);
		} else {
			doGet(request, response);
		}
	}

	/**
	 * Return a list of quotations
	 * 
	 * @param query
	 * @return List<Quotation> quotations
	 */
	protected List<Quotation> getQuotations(String query) {
		List<Quotation> quotations = new ArrayList<>();
		System.out.println(query);
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "SELECT * from quotations" + " WHERE quotation LIKE ? OR author LIKE ?";

			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			pstmt.setString(2, "%" + query + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				quotations.add(new Quotation(rs.getInt("id"), rs.getString("quotation"), rs.getString("author")));

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

		return quotations;
	}

}
