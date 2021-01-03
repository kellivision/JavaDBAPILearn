import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class jsonToJava {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=null;
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business",[user], [password]);

		CustomerDetails customers = new CustomerDetails();
		ArrayList<CustomerDetails> dbArray = new ArrayList<CustomerDetails>();

		//object of the statement class will help us to execute queries
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("select * from CustomerInfo where location = 'Asia' and purchasedDate=curdate() LIMIT 1;");
		while(rs.next()) //setting pointer to a particular row
		{
			customers.setCourseName(rs.getString(1));
			customers.setPurchasedDate(rs.getString(2));
			customers.setAmount(rs.getInt(3));
			customers.setLocation(rs.getString(4));

			dbArray.add(customers);
		}

		for(int i=0; i<dbArray.size(), i++)
		{
			ObjectMapper objmap = new ObjectMapper();
			objmap.writeValue(new File("C:\\Users\\Kevin\\eclipse-workspace\\JsonJava\\customerInfo"+i+".json"), dbArray.get(i));
		}

		conn.close();
	}
}