package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Admin;
import utility.DBConnection;

public class AdminDAO {

	public boolean adminValidate(Admin admin) throws SQLException {
		// TODO Auto-generated method stub
		boolean res=false;
		
		String query = "select * from admin where adminname = ? and adminpassword =?";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query))
		{
			ps.setString(1,admin.getName());
			ps.setString(2,admin.getPassword());
			res = ps.execute();//use execute to get boolean value
		}
		return res;
	}

}
