package com.wikipedia.tests;

import com.wikipedia.core.Browser;
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


    @After
    public void tearDown() {
        Context.destroyContext();
        Browser.closeDriver();
    }
}
