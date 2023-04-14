package denodoproject;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
