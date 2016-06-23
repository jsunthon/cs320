package final_exam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Final/EditQuotationController")
public class EditQuotationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String URL = "jdbc:mysql://cs3.calstatela.edu:3306/cs320stu26";
	public static final String USER = "cs320stu26";
	public static final String PASSWORD = "theman123";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String id = request.getParameter("id");
//		String quotation = request.getParameter("quotation");
//		String author = request.getParameter("author");
//		request.setAttribute("id", id);
//		request.setAttribute("quotation", quotation);
//		request.setAttribute("author", author);
		request.getRequestDispatcher("/WEB-INF/final/EditQuotationView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String quotation = request.getParameter("quotation");
		String author = request.getParameter("author");
		if (id != null && quotation != null && author != null) {
			try {
				if (!invalidParameters(id)) {
					editQuotation(Integer.parseInt(id), quotation, author);
					request.setAttribute("quotation", quotation);
					request.setAttribute("author", author);
					doGet(request, response);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	protected void editQuotation(int id, String quotation, String author) {
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "UPDATE quotations set quotation = ?, author = ? where id = ?";

			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, quotation);
			pstmt.setString(2, author);
			pstmt.setInt(3, id);

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

	private boolean invalidParameters(String parameter) {
		try {
			Integer.parseInt(parameter);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}

}
