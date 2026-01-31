package tdd;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CheckBoxPage;
import pages.TextBoxPage;
import utils.RetryAnalyzer;

public class CheckBoxTest extends BaseTest{
	
	private CheckBoxPage checkBoxPage;
	
	@BeforeMethod
	public void setupTest() {
		checkBoxPage = new CheckBoxPage();
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void tc01_selectHome() {		
		checkBoxPage.open();
		checkBoxPage.expandAll();
		checkBoxPage.selectHome();
		Assert.assertTrue(checkBoxPage.getSelectedResults().contains("home"));
	}
	
	@Test
	public void tc02_selectDesktop() {		
		checkBoxPage.open();
		checkBoxPage.expandAll();
		checkBoxPage.selectDesktop();
		Assert.assertTrue(checkBoxPage.getSelectedResults().contains("desktop"));
	}
	
	@Test
	public void tc03_selectDocuments() {		
		checkBoxPage.open();
		checkBoxPage.expandAll();
		checkBoxPage.selectDocuments();
		Assert.assertTrue(checkBoxPage.getSelectedResults().contains("documents"));
	}
	
	@Test
	public void tc04_selectMultiple() {		
		checkBoxPage.open();
		checkBoxPage.expandAll();
		checkBoxPage.selectDesktop();
		checkBoxPage.selectDocuments();
		
		List<String> results = checkBoxPage.getSelectedResults();
		Assert.assertTrue(results.contains("desktop"));
		Assert.assertTrue(results.contains("documents"));
	}
	
	@Test
	public void tc05_collapseAndExpandPersistence() {		
		checkBoxPage.open();
		checkBoxPage.expandAll();
		checkBoxPage.selectHome();
		checkBoxPage.collapseAll();
		checkBoxPage.expandAll();
		
		Assert.assertTrue(checkBoxPage.getSelectedResults().contains("home"), "Selection should persist after collapse and expand");
	}

}
