package basicAPI;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonMockResponseValidations {

	@Test
	public void validations() {
		JsonPath js = new JsonPath(payload.CourseDetails());
//Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println("Number of courses Is: " +count);
//Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount is: " +totalAmount);
//Print Title of the first course
		String titleFirstCourse = js.getString("courses[0].title");
		System.out.println("Title of first course is: " +titleFirstCourse);
//Print All course titles and their respective Prices
		for(int i=0; i<count; i++) {
		//String courseTitle = js.get("courses["+i+"].title");
		//int coursePrice = js.getInt("courses["+i+"].price");
		//System.out.println("Title of course is: " +courseTitle);
		//System.out.println("Price of course is:" +coursePrice);
//code optimisation
		System.out.println("Title of course is: " +js.getString("courses["+i+"].title"));
		System.out.println("Price of course is: " +js.getInt("courses["+i+"].price"));
		}
//Print no of copies sold by RPA Course
		for(int i=0;  i<count; i++) {
			String expTitle = js.getString("courses["+i+"].title");
			if(expTitle.equals("RPA")) {
				System.out.println("Price of RPA course is: " +js.getInt("courses["+i+"].price")); 
//code optimisation, dont iterate further once you find the title
				break;
			}
		}
//Verify if Sum of all Course prices matches with Purchase Amount
		int sumPrice = 0;
		for(int i=0; i<count; i++) {
			int coursePrice = js.getInt("courses["+i+"].price");
			int courseCopies = js.getInt("courses["+i+"].copies");
			//sumPrice = sumPrice + coursePrice*courseCopies;
			sumPrice += coursePrice*courseCopies;
		}
		System.out.println("Sum of prices of all courses is: " +sumPrice);
		Assert.assertEquals(totalAmount, sumPrice);
	}

}
