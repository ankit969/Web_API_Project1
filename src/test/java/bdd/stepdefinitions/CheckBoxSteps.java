package bdd.stepdefinitions;

import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckBoxPage;

public class CheckBoxSteps {
	
	private CheckBoxPage page = new CheckBoxPage();
	private List<String> results;
	
	@Given("user is on CheckBox page")
	public void user_is_on_check_box_page() {
		page.open();
		page.expandAll();
	}
	
	@When("user selects Home checkbox")
	public void user_selects_home_checkbox() {
		page.selectHome();
		results = page.getSelectedResults();
	}
	
	@When("user selects Desktop checkbox")
	public void user_selects_desktop_checkbox() {
		page.selectDesktop();
		results = page.getSelectedResults();
	}
	
	@When("user selects Documents checkbox")
	public void user_selects_documents_checkbox() {
		page.selectDocuments();
		results = page.getSelectedResults();
	}
	
	@When("user selects Desktop and Documents checkboxes")
	public void user_selects_multiple_checkboxes() {
		page.selectDesktop();
		page.selectDocuments();
		results = page.getSelectedResults();
	}
	
	@When("user collapses and expands all")
	public void user_collapses_and_expands_all() {
		page.collapseAll();
		page.expandAll();
		results = page.getSelectedResults();
	}
	
	@Then("selected result should contain {string}")
	public void selected_result_should_contain(String value) {
		Assert.assertTrue(results.contains(value), "Expected result not found: "+value);
	}
	

}
