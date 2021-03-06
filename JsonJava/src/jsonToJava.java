import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class jsonToJava {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=null;
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business",[user], [password]);


		ArrayList<CustomerDetails> dbArray = new ArrayList<CustomerDetails>();

		//object of the statement class will help us to execute queries
		Statement st=conn.createStatement();
		ResultSet resultSet=st.executeQuery("select * from CustomerInfo where location = 'Asia' and purchasedDate=curdate();");
		while(resultSet.next()) //setting pointer to a particular row
		{
			CustomerDetails customers = new CustomerDetails();
			customers.setCourseName(resultSet.getString(1));
			customers.setPurchasedDate(resultSet.getString(2));
			customers.setAmount(resultSet.getInt(3));
			customers.setLocation(resultSet.getString(4));

			dbArray.add(customers);

		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", dbArray);

		new ObjectMapper().writeValue(new File("C:\\[path]\\eclipse-workspace\\JsonJava\\customerInfo.json"), jsonObject);

//		for(int i=0;i<dbArray.size();i++)
//		{
//			ObjectMapper objmap = new ObjectMapper();
//			objmap.writeValue(new File("C:\\[path]\\eclipse-workspace\\JsonJava\\customerInfo"+i+".json"), dbArray.get(i));
//		}

		conn.close();
	}
}