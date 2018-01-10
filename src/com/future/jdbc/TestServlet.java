package com.future.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*
	 * Datasource is a name given to the connection set up to a database from a server. The name is commonly used 
	 * when creating a query to the database. The data source name (DSN) need not be the same as the filename for
	 *  the database. For example, a database file named friends.mdb could be set up with a DSN of school. Then DSN school 
	 *  would be used to refer to the database when performing a query.
	 * 
	 */
   @Resource(name="jdbc/web_student_tracker")
   private DataSource dataSource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Step 1: set up the printwriter
		PrintWriter out = response.getWriter();
		// Step 2: Get a connection to the database
		response.setContentType("text/plain");
		// Step3: Create a SQL statement
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = (Connection) dataSource.getConnection();
			String sql = "select * from student";
			myStmt = (Statement) myConn.createStatement();
			// Step 4: Execute SQL query
			myRs = myStmt.executeQuery(sql);
			// Step5: Process the result set
			while(myRs.next()){
				String email = myRs.getString("email");
				out.println(email);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
