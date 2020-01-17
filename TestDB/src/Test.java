
public class Test{
	public static void main(String[] args) {
		TestUserDAO dao = new TestUserDAO();
//どのフィールドか分かっている時
		dao.select("taro","123");
//フィールドに値が入っているところまで
		dao.selectAll();
//user_nameをキーに、指定された値を表示する。
		dao.selectByName("taro");
//passwordをキーに、指定された値を表示する。
		dao.selectByPassword("123");
//第一引数にupdate前の値を、第二引数にupdateしたい値を渡し、DBにUPDATEする。
		dao.updateUserNameByUserName("taro","saburo");
//レコードをDBに追加する。
		dao.insert(4,"shiro","012");
//user_nameをキーにレコードを削除する。
		dao.delete("jiro");
	}
}
