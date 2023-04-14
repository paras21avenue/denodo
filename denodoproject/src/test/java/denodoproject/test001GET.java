package denodoproject;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class test001GET {
	
	@Test
	public void validate_response_status_code_403() {
	    
	    Header authorizationHeader = new Header("Authorization", "Bearer invalid-authorization-header");
	    RequestSpecification httpRequest = RestAssured.given();
	    httpRequest.header(authorizationHeader);
	    
	    Response response = httpRequest.get("/tov5c2VC2c1RKXeM80rCtgXVmGN6Kj");
	    Assert.assertEquals(403, response.getStatusCode());
	}

}
