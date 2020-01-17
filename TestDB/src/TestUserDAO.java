//DAOとは、DBConnectorからインスタンスして、getConnectionを呼び出し、mysqlにログインする。
//SQL文でPreparedStatementの中に値を入れてクエリを実行し、データをDBに登録する。そのあとは、必ずDBとの接続を切る。
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUserDAO {

	String name = "";
	String password = "";

	public void select(String name, String password) {
//DBの接続の準備。SQLにログインしている。
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

//user_nameとpasswordに入る二つの条件を満たしたデータを変数sqlに格納するということ。
//=?はプレースホルダ。その都度違うデータを格納したい際に記述する。その値だけになるので、制限がかけられる。
		String sql = "select * from test_table where user_name=? and password=?";
		try {
//PreparedStatementはDBまで値を運んでくれる箱。
			PreparedStatement ps = con.prepareStatement(sql);
//第一引数の1や2は、プレースホルダーを指している。
			ps.setString(1,name);
			ps.setString(2,password);
//executeQuery()は実行メソッドで実行すると必ずResultSetが返ってくる。
			ResultSet rs =ps.executeQuery();
//next()はカラムに対して一行下にずれて、値が存在していれば(true)、{}内の処理をするということ。
//存在していなければfalseで返す。
			if(rs.next()) {
				System.out.println(rs.getString("user_name"));
				System.out.println(rs.getString("password"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
//DBとの接続を切る。メモリに負担がかかるので、必ず記述を忘れない。
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void selectAll() {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "select * from test_table";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
//while文。一行ずつ値を見にいく。カラムの中に値がなくなるまで、実行する。なくなったら終了。
			while(rs.next()) {
				System.out.println(rs.getString("user_name"));
				System.out.println(rs.getString("password"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void selectByName(String name) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "select * from test_table where user_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("user_name"));
				System.out.println(rs.getString("password"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void selectByPassword(String password){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "select * from test_table where password=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("user_name"));
				System.out.println(rs.getString("password"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUserNameByUserName(String oldName,String newName) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "update test_table set user_name=? where user_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,newName);
			ps.setString(2,oldName);
//INSERT,DELETE,UPDATEの実行メソッドはexecuteUpdate()で戻り値はint (intの変数に実行式を入れるイメージ)
//SELECTの実行メソッドはexecuteQuery()で戻り値はResultSet(データの件数を表しているのでint型になる。)
			int i = ps.executeUpdate();
			if(i > 0) {
				System.out.println(i + "件更新されました");
			}else {
				System.out.println("該当するデータはありませんでした");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(int user_id,String name,String password) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "insert into test_table values(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,user_id);
			ps.setString(2,name);
			ps.setString(3	,password);
			int i = ps.executeUpdate();
			if(i > 0) {
				System.out.println(i + "件登録されました");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String name) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "delete from test_table where user_name=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			int i = ps.executeUpdate();
			if(i > 0) {
				System.out.println(i + "件削除されました");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
