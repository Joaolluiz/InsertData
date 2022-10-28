package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement statement = null;
		
		String query = "INSERT INTO seller "
				+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		
		try {
			conn = DB.getConnection();
			
			statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, "Joao");
			statement.setString(2, "joao.fdk8@gmail.com");
			statement.setDate(3, new java.sql.Date(sdf.parse("19/05/2002").getTime()));
			statement.setDouble(4, 900.00);
			statement.setInt(5, 2);
			
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = statement.getGeneratedKeys(); //retorna um objeto do tipo ResultSet
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}
			else {
				System.out.println("No rows affected!");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}  
		catch (ParseException e) {
			  e.printStackTrace();
		}
		finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}
}