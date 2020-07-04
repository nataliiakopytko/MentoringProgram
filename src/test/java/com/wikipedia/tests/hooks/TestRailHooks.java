package com.wikipedia.tests.hooks;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.*;
import com.hometask.hometask2.PropertiesLoader;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestRailHooks {
    private final static String endPoint = PropertiesLoader.getProperty(PropertiesLoader.pathToTestrailPropertyFile, "api.url");
    private final static String username = PropertiesLoader.getProperty(PropertiesLoader.pathToTestrailPropertyFile, "api.user");
    private final static String password = PropertiesLoader.getProperty(PropertiesLoader.pathToTestrailPropertyFile, "api.password");
    private final static String newRun = PropertiesLoader.getProperty(PropertiesLoader.pathToTestrailPropertyFile, "is.new.run.allowed");

    private static final int projectId = 6;
    private static final int suiteId = 7;

    private static TestRail testRail;
    private static Project project;
    private static Suite suite;
    private static Case testCase;
    private static List<CaseField> caseFields;
    private static List<Case> casesInTestRail;
    private static Run run;

    private static final boolean hasTestRailCreationAllowed = Boolean.parseBoolean(newRun);

//    static {
//        if (hasTestRailCreationAllowed) {
//            testRail = TestRail.builder(endPoint, username, password).applicationName("Github_Project").build();
//            project = testRail.projects().get(projectId).execute();
//            suite = testRail.suites().get(suiteId).execute();
//            caseFields = testRail.caseFields().list().execute();
//            casesInTestRail = testRail.cases().list(projectId, suiteId, caseFields).execute();
//            run = testRail.runs().add(projectId, new Run().setSuiteId(suiteId).setName("Test Run " + getDate())).execute();
//        }
//    }
//
//    @After(order = 3)
//    public void connectScenarioFromCucumberWithTestRail(Scenario scenario) {
//        if (hasTestRailCreationAllowed) {
//            testCase = casesInTestRail.stream()
//                    .filter(trc -> trc.getTitle().equalsIgnoreCase(scenario.getName()))
//                    .findFirst()
//                    .orElseThrow(() -> new IllegalStateException(scenario.getName() + "is not found in Test Rail"));
//        }
//    }
//
//    @After(order = 2)
//    public void setStatusToCase(Scenario scenario) {
//        if (hasTestRailCreationAllowed) {
//            List<ResultField> customResultFields = testRail.resultFields().list().execute();
//            testRail.tests().list(run.getId()).execute()
//                    .stream()
//                    .filter(test -> test.getCaseId() == testCase.getId())
//                    .findFirst()
//                    .ifPresent(test -> testRail.results()
//                            .addForCase(run.getId(), test.getCaseId(), new Result().setStatusId(getStatusMap().get(scenario.getStatus())),
//                                    customResultFields).execute());
//        }
//    }

    private Map<cucumber.api.Result.Type, Integer> getStatusMap() {
        Map<cucumber.api.Result.Type, Integer> map = new HashMap<>();
        map.put(cucumber.api.Result.Type.PASSED, 1);
        map.put(cucumber.api.Result.Type.UNDEFINED, 2);
        map.put(cucumber.api.Result.Type.SKIPPED, 3);
        map.put(cucumber.api.Result.Type.PENDING, 4);
        map.put(cucumber.api.Result.Type.FAILED, 5);
        return map;
    }

    private static String getDate() {
        String dateFormatPattern = "dd-MM-yyyy HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        date = cal.getTime();
        return dateFormat.format(date);
    }
}