package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import genericUtilities.IConstantPath;
//This test verifies if user is able to create category
public class AddCategoryTest extends BaseClass {
	
	@Test
	public void addCategoryTest() {
		SoftAssert soft=new SoftAssert();
		
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertEquals(category.getPageHeader(),"Category");
		
		category.clickNewButton();
		soft.assertEquals(addCategory.getPageHeader(),"Add New Category");
		Map<String, String> map= excel.readFromExcel("Add Category");
		addCategory.setName(map.get("Name"));
		addCategory.clickSave();
		
		soft.assertEquals(category.getSuccessMessage(), "Success!");
		category.deleteCategory(web, map.get("Name"));
		soft.assertEquals(category.getSuccessMessage(), "Success!");
		if(category.getSuccessMessage().equals("Success!"))
			excel.updateTestStatus("Add Category","Pass", IConstantPath.Excel_PATH);
		else
			excel.updateTestStatus("Add Category","Fail", IConstantPath.Excel_PATH);
		
		soft.assertAll();
		
	}
	

}
