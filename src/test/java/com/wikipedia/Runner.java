package com.wikipedia;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/wikipediafeatures",
        glue = {"com.wikipedia.tests"},
        tags = {"not @Ignore"},
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        }
)

public class Runner extends AbstractTestNGCucumberTests {
}
