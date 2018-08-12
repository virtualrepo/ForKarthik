package com.hex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.ui.ModelMap;

@Controller
public class HelloController {

	@RequestMapping("/viewData")
	public ModelAndView viewemp() throws SQLException {
		List<Emp> list = new ArrayList<Emp>();
		ResultSet rs = getFullData();
		while (rs.next()) {
			Emp e = new Emp();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setSalary(rs.getInt(3));
			e.setDesignation(rs.getString(4));
			list.add(e);
		}
	
		return new ModelAndView("index", "list", list);
	}
	
	public static ResultSet getFullData() throws SQLException {
		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM EMPLOYEE_DETAILS");
		ResultSet rs = stmt.executeQuery();
		return rs;

	}
	
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "Yogesh");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}