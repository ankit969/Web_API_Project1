package tdd;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.RadioButtonPage;
import utils.RetryAnalyzer;

public class RadioButtonTest extends BaseTest{
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void verifyYesRadioButton() {
		RadioButtonPage radioPage = new RadioButtonPage();
		
		radioPage.open();
		radioPage.selectYes();
		Assert.assertEquals(radioPage.getSelectedValue(), "Yes", "Yes radio button was not selected");
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void verifyImpressiveRadioButton() {
		RadioButtonPage radioPage = new RadioButtonPage();
		
		radioPage.open();
		radioPage.selectImpressive();
		Assert.assertEquals(radioPage.getSelectedValue(), "Impressive", "Impressive radio button was not selected");
	}

}
