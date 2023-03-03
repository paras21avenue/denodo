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
	int id=0;
	
	@Test public void createProperties() throws IOException, InterruptedException 
    { 
		
		FileInputStream file = new FileInputStream( 
        		"C:\\Users\\denodo\\Documents\\properties.xlsx"); 
        XSSFWorkbook wb = new XSSFWorkbook(file); 
        XSSFSheet sh = wb.getSheet("Sheet1"); 
  
        HashMap<String, String> map 
            = new HashMap<String, String>(); 
  
        for (int r = 0; r <= sh.getLastRowNum(); r++)
        { 
            String key = sh.getRow(r) 
                          .getCell(0) 
                          .getStringCellValue();
            String value = sh.getRow(r) 
                               .getCell(1) 
                               .getStringCellValue(); 
            map.put(key, value); 
        } 
  
        // Using HashMap to create json request:
        Iterator<Entry<String, String> > new_Iterator 
            = map.entrySet().iterator(); 
        JSONArray jsonArray = new JSONArray();
        
     
        
        while (new_Iterator.hasNext()) 
        { 
        	JSONObject jo = new JSONObject();
            Map.Entry<String, String> new_Map 
                = (Map.Entry<String, String>) 
                      new_Iterator.next(); 
            
            
            
            JSONObject requestParams = new JSONObject(); 
			requestParams.put("name", new_Map.getKey()); 
			requestParams.put("value", new_Map.getValue());
	       // jo.put(new_Map.getKey(),new_Map.getValue());
	         
	        jsonArray.put(requestParams);
        } 
     
       
       // System.out.println(jsonArray);
        
        
        RestAssured.baseURI = "http://52.149.136.229:10090/environments/28";
		
		Response response = given()
				.header("Authorization","Basic YWRtaW46V2VsY29tZS4x")
                .header("Content-type", "application/json")
                .and()
                .body(jsonArray.toString())
                .when()
                .post("/vdpProperties")
                .then()
                .extract().response();
		
	
          
        wb.close(); 
        file.close(); 
        Thread.sleep(5000);
	    } 
	
//==============================================================================================================//
	
	@Test (dependsOnMethods={"createProperties"})
	void test001() throws IOException, InterruptedException
	{
		File file = new File("C:\\Users\\denodo\\.jenkins\\workspace\\base64\\Base64.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        JSONObject requestParams = new JSONObject();
        while((st=br.readLine()) != null){
          
             
    		requestParams.put("name", "Denodo_revision"); 
    		requestParams.put("description", "Revision for deployment_001"); 
    		requestParams.put("content", st.substring(4)); 
    		 
    		RestAssured.baseURI = "http://52.149.136.229:10090/revisions";
    		
    		
    		Response response = given()
    				.header("Authorization","Basic YWRtaW46V2VsY29tZS4x")
                    .header("Content-type", "application/json")
                    .and()
                    .body(requestParams.toJSONString())
                    .when()
                    .post("/loadFromVQL")
                    .then()
                    .extract().response();
    		
    		int user_id = response.jsonPath().getInt("id");
    		
    		System.out.println(requestParams.put("content", st));
    		id=user_id;
    		System.out.println(id);
    		Thread.sleep(5000);
    	
    	
        
      
	}
	
	}
	
//=================================================================================================================//	
	@Test(dependsOnMethods={"test001"})
	void test002() throws IOException, InterruptedException
	{
		

		ArrayList<Integer> arrylist = new ArrayList<Integer>();
		arrylist.add(id);
		System.out.println("Revision id used for deployment is:"+arrylist);
		Thread.sleep(2000);
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("revisionIds", arrylist); 
		requestParams.put("environmentId", 28); 
		requestParams.put("description", "Deployment to environment"); 
		
		RestAssured.baseURI = "http://52.149.136.229:10090";
		Response response = given().
			header("Authorization","Basic YWRtaW46V2VsY29tZS4x").
			header("Content-Type", "application/json")
			.and()
            .body(requestParams.toJSONString())
            .when()
            .post("/deployments")
            .then()
            .extract().response();
	
		String id = response.jsonPath().getString("deploymentId");
		System.out.println(id);
	
	
	
	}
	
	
	
	
	

}
