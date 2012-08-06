package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private DataSource ds;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			InitialContext cxt = new InitialContext();
			ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/default");
			if (ds == null) {
				throw new ServletException("Data source not found!");
			}
			// testing connection
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user");
			config.getServletContext().log("SQL Connection Test: "+rs.next());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().write("Hello World\n");
	}

}
