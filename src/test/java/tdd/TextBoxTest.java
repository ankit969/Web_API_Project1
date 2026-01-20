package tdd;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.TextBoxPage;
import utils.RetryAnalyzer;


public class TextBoxTest extends BaseTest{
	
	/*
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void verifyTextBoxSubmission() {
		TextBoxPage textBoxPage = new TextBoxPage();
		
		textBoxPage.open();
		textBoxPage.fillForm("John", "john@gmail.com", "Virginia", "US");
		textBoxPage.submit();
		Assert.assertTrue(textBoxPage.getOutputName().contains("John"), "Name is not displayed correctly");
	}
	*/
	
	private TextBoxPage textBoxPage;
	
	@BeforeMethod
	public void setupTest() {
		textBoxPage = new TextBoxPage();
	}
	
	@Test
	public void tc01_validSubmission() {
		//TextBoxPage textBoxPage = new TextBoxPage();
		
		textBoxPage.open();
		textBoxPage.fillForm("John", "john@gmail.com", "Virginia", "USA");
		textBoxPage.submit();
		Assert.assertTrue(textBoxPage.getOutputName().contains("John"));
	}
	
	@Test
	public void tc02_emptyFormSubmission() {
		//TextBoxPage textBoxPage = new TextBoxPage();
		
		textBoxPage.open();
		textBoxPage.submit();
		Assert.assertFalse(textBoxPage.isOutputDisplayed(), "Output should not be displayed for empty form");
	}
	
	@Test
	public void tc03_invalidEmail() {
		//TextBoxPage textBoxPage = new TextBoxPage();
		
		textBoxPage.open();
		textBoxPage.fillForm("John", "john@@gmail", "Virginia", "USA");
		textBoxPage.submit();
		Assert.assertFalse(textBoxPage.isEmailOutputDisplayed(), "Invalid email should not be accepted");
	}
	
	@Test
	public void tc04_specialCharactersInName() {
		//TextBoxPage textBoxPage = new TextBoxPage();
		
		textBoxPage.open();
		textBoxPage.fillForm("John@123", "john@gmail.com", "Virginia", "USA");
		textBoxPage.submit();
		Assert.assertTrue(textBoxPage.getOutputName().contains("John@123"));
	}
	
	@Test
	public void tc05_longAddress() {
		String longAddress = "This is a very long address".repeat(20);
		
		//TextBoxPage textBoxPage = new TextBoxPage();
		
		textBoxPage.open();
		textBoxPage.fillForm("John", "john@gmail.com", longAddress, "USA");
		textBoxPage.submit();
		Assert.assertTrue(textBoxPage.getOutputAddress().contains("This is a very long address"));
	}

}
