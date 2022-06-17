package apiChaining;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CRUDOperations {


	String BaseURI="http://54.87.237.166:8088/employees";

	public Response GETAllEmployees() {
		
		RestAssured.baseURI=BaseURI;
		RequestSpecification request=RestAssured.given();
		Response response=request.get();
		return response;
	}
	
	public Response PostMethod() throws IOException {
		
		RestAssured.baseURI=BaseURI;
		RequestSpecification request=RestAssured.given();
		byte[] databytes=Files.readAllBytes(Paths.get("TestData.json"));
		
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .body(databytes)
								 .post();
		return response;
	}
	
	public Response PutMethod(int empId, String fName, String lName, int Salary, String email) {
		
		RestAssured.baseURI=BaseURI;
		RequestSpecification request=RestAssured.given();
		Map<String, Object> mapObject=new HashMap<String, Object>();
		mapObject.put("firstName", fName);
		mapObject.put("lastName", lName);
		mapObject.put("salary", Salary);
		mapObject.put("email", email);
		
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .body(mapObject)
								 .put("/"+empId);
		
		return response;
	}
	
	public Response DeleteMethod(int empId) {
		
		RestAssured.baseURI=BaseURI;
		RequestSpecification request=RestAssured.given();
		Response response=request.delete("/"+empId);
		return response;
	}
	
    public Response GetDeletedEmployee(int empId) {
		
		RestAssured.baseURI=BaseURI;
		RequestSpecification request=RestAssured.given();
		Response response=request.get("/"+empId);
		return response;
	}
}
