package apiChaining;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Runnerclass extends CRUDOperations{
	
	Response response;
	@Test
	public void RunnerMethod() throws IOException {
		
		//GetAllEmployees
		response=GETAllEmployees();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Add New Employee
		response=PostMethod();
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath jpath=response.jsonPath();
		int empId=jpath.get("id");
		System.out.println("New Id : "+empId);
		
		//Update the employee details
		response=PutMethod(empId, "Ajit","Mane", 25000, "AjitMane@gmail.com");
		Assert.assertEquals(response.getStatusCode(), 200);
		jpath=response.jsonPath();
		Assert.assertEquals(jpath.get("firstName"), "Ajit");
		Assert.assertEquals(jpath.get("lastName"), "Mane");
		
		//Delete Employee
		response=DeleteMethod(empId);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().asString(), "");

		//Verify Deleted Employee
		response=GetDeletedEmployee(empId);
		Assert.assertEquals(response.getStatusCode(), 400);
		jpath=response.jsonPath();
		Assert.assertEquals(jpath.get("message"), "Entity Not Found");
		
	}
	
}
