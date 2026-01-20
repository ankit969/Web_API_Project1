package tdd;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CheckBoxPage;
import utils.RetryAnalyzer;

public class CheckBoxTest extends BaseTest{
	
	@Test
	public void tc01_selectHome() {
		CheckBoxPage page = new CheckBoxPage();
		
		page.open();
		page.expandAll();
		page.selectHome();
		Assert.assertTrue(page.getSelectedResults().contains("home"));
	}
	
	@Test
	public void tc02_selectDesktop() {
		CheckBoxPage page = new CheckBoxPage();
		
		page.open();
		page.expandAll();
		page.selectDesktop();
		Assert.assertTrue(page.getSelectedResults().contains("desktop"));
	}
	
	@Test
	public void tc03_selectDocuments() {
		CheckBoxPage page = new CheckBoxPage();
		
		page.open();
		page.expandAll();
		page.selectDocuments();
		Assert.assertTrue(page.getSelectedResults().contains("documents"));
	}
	
	@Test
	public void tc04_selectMultiple() {
		CheckBoxPage page = new CheckBoxPage();
		
		page.open();
		page.expandAll();
		page.selectDesktop();
		page.selectDocuments();
		
		List<String> results = page.getSelectedResults();
		Assert.assertTrue(results.contains("desktop"));
		Assert.assertTrue(results.contains("documents"));
	}
	
	@Test
	public void tc05_collapseAndExpandPersistence() {
		CheckBoxPage page = new CheckBoxPage();
		
		page.open();
		page.expandAll();
		page.selectHome();
		page.collapseAll();
		page.expandAll();
		
		Assert.assertTrue(page.getSelectedResults().contains("home"), "Selection should persist after collapse and expand");
	}

}
