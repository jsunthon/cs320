package final_exam;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/exam")
public class exam extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public exam() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("attribute1", "First Value");
		
		HttpSession session = request.getSession();
		session.setAttribute("attribute2", "Second Value");
		
		ServletContext app = request.getServletContext();
		app.setAttribute("attribute3", new java.util.Date());
		

		app.setAttribute("repeated", "ServletContext");
		session.setAttribute("repeated", "Session");
		request.setAttribute("repeated", "Request");
		
		
		request.getRequestDispatcher("/WEB-INF/exam.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
