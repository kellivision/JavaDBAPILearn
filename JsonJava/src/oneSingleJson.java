import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


public class jsonToJava {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=null;
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business",[user], [password]);


		ArrayList<CustomerDetails> dbArray = new ArrayList<CustomerDetails>();
		JSONArray arrayJson = new JSONArray();

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

		for(int i=0; i<dbArray.size(); i++)
		{
			ObjectMapper objmap = new ObjectMapper();
			objmap.writeValue(new File("C:\\[path]\\eclipse-workspace\\JsonJava\\customerInfo"+i+".json"), dbArray.get(i));

			//Create JSON string from a Java object using Gson
			Gson callGson = new Gson();
			String jsonString = callGson.toJson(dbArray.get(i));
			arrayJson.add(jsonString);
		}

		// using JSON simple
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("data", arrayJson);

		System.out.println(jsonObj.toJSONString());

		String unescapedString = StringEscapeUtils.unescapeJava(jsonObj.toJSONString());
		System.out.println(unescapedString);
		String string1 = unescapedString.replace("\"{", "{");
		String finalString = string1.replace("}\"", "}");
		System.out.println(finalString);

		try(FileWriter file = new FileWriter("C:\\[path]\\eclipse-workspace\\JsonJava\\SingleOne.json")) {
			file.write(finalString);
		}

		conn.close();
	}
}