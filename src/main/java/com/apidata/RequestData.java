package com.apidata;

import org.json.JSONObject;

public class RequestData {
	
	//method to set request as a JSONObject
	  	public JSONObject getRequestJsonObject(String firstname,String lastname,int totalprice,boolean depositpaid,String checkindate,String checkoutdate,String additionalneeds)
	  	 {
		 JSONObject data = new JSONObject();
		 
		  data.put("firstname", firstname);
		  data.put("lastname", lastname);
		  data.put("totalprice", totalprice);
		  data.put("depositpaid", depositpaid);
		  
		  JSONObject bookingdates = new JSONObject();
		  bookingdates.put("checkin", checkindate);
		  bookingdates.put("checkout", checkoutdate);
		  
		  data.put("bookingdates",bookingdates);
		  data.put("additionalneeds",additionalneeds);
		  
		  return data;
	  	 }
	  	 
	  //method to set expected response as a JSONObject
	  	public JSONObject getResponseJsonObject(String bookingid,String firstname,String lastname,int totalprice,boolean depositpaid,String checkindate,String checkoutdate,String additionalneeds)
	  	 {
	  		JSONObject data =new JSONObject();
	  		data.put("bookingid", bookingid);
	  		
		 JSONObject booking = new JSONObject();
		 
		 booking.put("firstname", firstname);
		 booking.put("lastname", lastname);
		 booking.put("totalprice", totalprice);
		 booking.put("depositpaid", depositpaid);
		  
		  JSONObject bookingdates = new JSONObject();
		  bookingdates.put("checkin", checkindate);
		  bookingdates.put("checkout", checkoutdate);
		  
		  booking.put("bookingdates",bookingdates);
		  booking.put("additionalneeds",additionalneeds);
		  
		  data.put("booking", booking);
		  
		  return data;
	  	 }

	  	
	  //method to set request as a JSON String
	  	public String getRequestasString(String firstname,String lastname,int totalprice,boolean depositpaid,String checkindate,String checkoutdate,String additionalneeds)
	  	{
	  	String requestBody = "{\r\n"
				+ "    \"firstname\" : \""+firstname+"\",\r\n"
				+ "    \"lastname\" : \""+lastname+"\",\r\n"
				+ "    \"totalprice\" : "+totalprice+",\r\n"
				+ "    \"depositpaid\" : "+depositpaid+",\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \""+checkindate+"\",\r\n"
				+ "        \"checkout\" : \""+checkoutdate+"\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \""+additionalneeds+"\"\r\n"
				+ "}";
	  	
		return requestBody;
	  	}
	  	
	  //method to get expected response as a JSON String
	  	public String getExpectedResponseasString(int bookingid,String firstname,String lastname,int totalprice,boolean depositpaid,String checkindate,String checkoutdate,String additionalneeds)
	  	{
	  	String requestBody = "{\"bookingid\":"+bookingid+",\"booking\":{\r\n"
				+ "    \"firstname\" : \""+firstname+"\",\r\n"
				+ "    \"lastname\" : \""+lastname+"\",\r\n"
				+ "    \"totalprice\" : "+totalprice+",\r\n"
				+ "    \"depositpaid\" : "+depositpaid+",\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \""+checkindate+"\",\r\n"
				+ "        \"checkout\" : \""+checkoutdate+"\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \""+additionalneeds+"\"\r\n"
				+ "}}";
	  	
		return requestBody;
	  	}
	  	
}


