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
	    
	    RestAssured.baseURI = "https://demoqa.com/Account/v1/User/";
		RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("test");

        // Get the status code of the request. 
        //If request is successful, status code will be 200
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 401 /*expected value*/, 
                "Correct status code returned");
	}

}
