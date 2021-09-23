package user;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbID = "emcha";
			String dbPassword = "0808";
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			   conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
			 }

	
	public int login(String userID, String userPassword) {
		   String SQL = "SELECT userPassword FROM EMCHA WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) 
					return 1; //로그인성공
				else
					return 0; //비밀번호 불일치
			}
			return -1; //아이디 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}
	
	public int join(User emcha) {
		String SQL = "INSERT INTO emcha VALUES (?, ?, ?, ?, ?)";
		try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, emcha.getUserID());
            pstmt.setString(2, emcha.getUserPassword());
            pstmt.setString(3, emcha.getUserName());
            pstmt.setString(4, emcha.getUserGender());
            pstmt.setString(5, emcha.getUserEmail());
            return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {if(conn != null) {conn.close();}} catch (Exception ex) {ex.printStackTrace();}
			try {if(rs != null)   {rs.close();}}   catch (Exception ex) {ex.printStackTrace();}
			try {if(pstmt != null){pstmt.close();}}catch (Exception ex) {ex.printStackTrace();}
		}
		return -1; // 데이터베이스 오류
	}
}
	
