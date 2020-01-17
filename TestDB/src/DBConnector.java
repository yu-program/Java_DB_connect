import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	private static String driverName = "com.mysql.jdbc.Driver";

//?以降はオプションなのでなくても良い。
	private static String url = "jdbc:mysql://localhost/testdb?autoReconnect=true&useSSL=false";

	private static String user = "root";
	private static String password = "mysql";

//接続状態にするメソッド。
	public Connection getConnection() {
		Connection con = null;

//tryにはエラーが発生しそうな処理を記述する。
		try {
		Class.forName(driverName);
		con = DriverManager.getConnection(url,user,password);
		}catch(ClassNotFoundException e) {
//エラーに至る履歴を表示=printStackTrace()
		e.printStackTrace();
		}catch(SQLException e){
		e.printStackTrace();
		}
		return con;
	}
}
