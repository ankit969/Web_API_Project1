package bdd.stepdefinitions;

import org.testng.Assert;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.TextBoxPage;

public class TextBoxSteps extends BaseTest{
	
	private TextBoxPage textBoxPage;
	
	@Given("user is on TextBox page")
	public void user_is_on_text_box_page() {
		textBoxPage = new TextBoxPage();
	    textBoxPage.open();
	}
	
	@When("user submits form with name {string}, email {string}, address {string}, country {string}")
	public void user_submits_form(String name, String email, String cAddress, String pAddress) {
		if(cAddress.equals("<longAddress>")) {
			cAddress = "This is a very long address".repeat(20);
		}
		
		textBoxPage.fillForm(name, email, cAddress, pAddress);
		textBoxPage.submit();
	}
	
	@When("user submits empty form")
	public void user_submits_empty_form() {
		textBoxPage.submit();
	}
	
	
	@Then("output name should be {string}")
	public void output_name_should_be(String expectedName) {
		Assert.assertTrue(textBoxPage.getOutputName().equals(expectedName));
	}
	
	@Then("output should not be displayed")
	public void output_should_not_be_displayed() {
		Assert.assertFalse(textBoxPage.isOutputDisplayed());
	}
	
	@Then("email output should not be displayed")
	public void email_output_should_not_be_displayed() {
		Assert.assertFalse(textBoxPage.isEmailOutputDisplayed());
	}
	
	@Then("long output address should be displayed")
	public void long_output_address_should_be_displayed() {
		Assert.assertTrue(textBoxPage.getOutputAddress().contains("This is a very long address"));
	}
	

}
