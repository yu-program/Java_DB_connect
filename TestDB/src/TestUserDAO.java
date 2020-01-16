import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUserDAO {

	String name = "";
	String password = "";

	public void select(String name, String password) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "select * from test_table where user_name=? and password=?";
		try {
		PreparedStatement ps = con.PreparedStatement(sql);
		}catch() {

		}
	}
}
