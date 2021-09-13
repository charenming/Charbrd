package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	


	Connection  conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public UserDAO() {
		try {
			String driver = "oracle.jdbc.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "Board";
			String password = "0808";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

public int login(String userID, String userPassword) {
	String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 성공
				}
				else 
					return 0; // 불일치
			}
			return -1; //아이디없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //데이터베이스 오류
	}
}
	
