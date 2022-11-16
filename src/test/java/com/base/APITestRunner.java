package com.base;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.apidata.RequestData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class APITestRunner {
	RequestData rd = new RequestData();
	
	@BeforeClass
	 public void setupAPIUrl() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		}
    
	//Happy Flow providing all valid data
    @Test()
    public void test1()
    {
    	String requestBody = rd.getRequestasString("prateek","saxena",100,true,"2022-11-11","2022-11-12","Need Mineral Water");
    	
    	Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .extract().response();
    	
    	Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    	Assert.assertEquals(200, response.statusCode());
    	Assert.assertEquals(response.jsonPath().getString("booking.firstname"),"prateek");
    	Assert.assertEquals( response.jsonPath().getString("booking.lastname"),"saxena");
    	Assert.assertEquals( response.jsonPath().getInt("booking.totalprice"),100);
    	Assert.assertEquals( response.jsonPath().getBoolean("booking.depositpaid"),true);
    	Assert.assertEquals( response.jsonPath().getString("booking.bookingdates.checkin"),"2022-11-11");
    	Assert.assertEquals( response.jsonPath().getString("booking.bookingdates.checkout"),"2022-11-12");
    	Assert.assertEquals( response.jsonPath().getString("booking.additionalneeds"),"Need Mineral Water");
    }
    
    
    //providing wrong date format in input
    @Test()
    public void test2()
    {
    	String requestBody = rd.getRequestasString("prateek","saxena",100,true,"2022/11/11","2022/11/12","Need Mineral Water");
    	
    	Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .extract().response();
    	
    	Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    	Assert.assertEquals(200, response.statusCode());
    	Assert.assertEquals(response.jsonPath().getString("booking.firstname"),"prateek");
    	Assert.assertEquals( response.jsonPath().getString("booking.lastname"),"saxena");
    	Assert.assertEquals( response.jsonPath().getInt("booking.totalprice"),100);
    	Assert.assertEquals( response.jsonPath().getBoolean("booking.depositpaid"),true);
    	Assert.assertEquals( response.jsonPath().getString("booking.bookingdates.checkin"),"2022-11-11");
    	Assert.assertEquals( response.jsonPath().getString("booking.bookingdates.checkout"),"2022-11-12");
    	Assert.assertEquals( response.jsonPath().getString("booking.additionalneeds"),"Need Mineral Water");
    }
    
    //providing wrong data as input but still api creating a booking id
    @Test
    public void test3()
    {
    	String requestBody = rd.getRequestasString("","",0,false,"","","");
    	
    	Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .extract().response();
    	
    	System.out.println(response.getStatusLine());
    	System.out.println(response.asString());
    	
    	Assert.assertEquals( response.statusCode(),400);
    	Assert.assertEquals(response.jsonPath().getString("booking.firstname"),"");
    	Assert.assertEquals( response.jsonPath().getString("booking.lastname"),"");
    	Assert.assertEquals( response.jsonPath().getInt("booking.totalprice"),0);
    	Assert.assertEquals( response.jsonPath().getBoolean("booking.depositpaid"),false);
    	Assert.assertEquals( response.jsonPath().getString("booking.bookingdates.checkin"),"");
    	Assert.assertEquals( response.jsonPath().getString("booking.bookingdates.checkout"),"");
    	Assert.assertEquals( response.jsonPath().getString("booking.additionalneeds"),"");
    }
    
    //API is not ready for testing......

}
