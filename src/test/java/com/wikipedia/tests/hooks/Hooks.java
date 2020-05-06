package com.wikipedia.tests.hooks;

import com.wikipedia.core.browser.Browser;
import com.wikipedia.core.context.Context;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    @Before
    public void setUp() {
        Browser.setBrowserPosition();
        Browser.setBrowserSize();
        Context.initContext();
    }

    @After(order = 5)
    public void tearDown() {
        Context.destroyContext();
        Browser.closeDriver();
    }
}
