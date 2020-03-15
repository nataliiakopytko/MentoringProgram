package com.wikipedia.tests.stepsdef;

import com.wikipedia.pages.BasePage;
import com.wikipedia.pages.PageFactory;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class CommonPagesStepsDef {
    @Then("^I check Wikipedia \"?([^\"]*)\"? page is \"?(opened|closed)\"?$")
    public void iCheckWikipediaPageIs(String identifier, String state) {
        BasePage page = PageFactory.getPageByIdentifier(identifier);
        boolean isOpened = "opened".equals(state);
        Assert.assertEquals(page.verify(), isOpened, String.format("Page '%s' is not '%s'", identifier, state));
    }
}
