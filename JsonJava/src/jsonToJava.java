import java.sql.*;


public class jsonToJava {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn=null;
conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business",username,password);

//object of the statement class will help us to execute queries
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("select * from CustomerInfo where location = 'Asia' and purchasedDate=curdate() LIMIT 1;");
while(rs.next()) //setting pointer to a particular row
{
	CustomerDetails customers = new CustomerDetails();
	customers.setCourseName(rs.getString(1));
	customers.setPurchasedDate(rs.getString(2));
	customers.setAmount(rs.getInt(3));
	customers.setLocation(rs.getString(4));

	System.out.println(customers.getCourseName());
	System.out.println(customers.getPurchasedDate());
}
conn.close();


	}

}
