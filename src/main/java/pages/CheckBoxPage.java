package pages;

import java.util.List;

import org.openqa.selenium.By;
import config.ConfigReader;
public class CheckBoxPage extends BasePage{
	
	 // Locators
	private By expandAllBtn = By.cssSelector("button[title='Expand all']");
    private By collapseAllBtn = By.cssSelector("button[title='Collapse all']");
    private By homeCheckbox = By.cssSelector("span[class='rct-checkbox']");
    private By desktopCheckbox = By.cssSelector("label[for='tree-node-desktop'] span.rct-checkbox");
    private By documentsCheckbox = By.cssSelector("label[for='tree-node-documents'] span.rct-checkbox");
    private By resultItems = By.cssSelector("#result span.text-success");
    
    public void open() {
        driver.get(ConfigReader.getBaseUrl() + "/checkbox");
        wait.waitForPageLoadComplete();
    }

    public void expandAll() {
        actions.safeClick(expandAllBtn);
    }

    public void collapseAll() {
        actions.scrollAndClick(collapseAllBtn);
    }

    public void selectHome() {
        actions.scrollAndClick(homeCheckbox);
    }
    
    public void selectDesktop() {
        actions.scrollAndClick(desktopCheckbox);
    }
    
    public void selectDocuments() {
        actions.scrollAndClick(documentsCheckbox);
    }
    
    public List<String> getSelectedResults() {
    	actions.scrollIntoView(resultItems);
        return actions.getTexts(resultItems);
    }

}
