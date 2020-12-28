import java.sql.*;


public class jsonToJava {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn=null;
conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business",username,password);

//object of the statement class will help us to execute queries
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("select * from CustomerInfo");
while(rs.next()) //setting pointer to a particular row
{
	System.out.println(rs.getString(1));
	System.out.println(rs.getString(2));
	System.out.println(rs.getInt(3));
	System.out.println(rs.getString(4));
}
conn.close();


	}

}
