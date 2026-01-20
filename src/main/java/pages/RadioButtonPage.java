package pages;

import org.openqa.selenium.By;
import config.ConfigReader;

public class RadioButtonPage extends BasePage{
	
	// Locators
    private final By yesRadio = By.cssSelector("label[for='yesRadio']");
    private final By impressiveRadio = By.cssSelector("label[for='impressiveRadio']");
    private final By outputText = By.cssSelector(".text-success");
    
    public void open() {
        driver.get(ConfigReader.getBaseUrl() + "/radio-button");
        wait.waitForPageLoadComplete();
    }

    public void selectYes() {
        actions.safeClick(yesRadio);
    }

    public void selectImpressive() {
        actions.safeClick(impressiveRadio);
    }

    public String getSelectedValue() {
        return actions.getText(outputText);
    }

}
