package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestSummaryListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static final Map<String, ExtentTest> testMap = new LinkedHashMap<>();
    private static final List<String> passedTests  = new ArrayList<>();
    private static final List<String> failedTests  = new ArrayList<>();
    private static final List<String> skippedTests = new ArrayList<>();
    private static long suiteStartTime;
    private static String reportDir;

    /* ───────────────────────── SUITE EVENTS ───────────────────────── */

    @Override
    public void onStart(ISuite suite) {
        suiteStartTime = System.currentTimeMillis();
        reportDir = System.getProperty("user.dir") + "/FinalReports";
        new File(reportDir).mkdirs();

        String ts = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String htmlPath = reportDir + "/FINAL_TEST_REPORT_" + ts + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(htmlPath);
        spark.config().setDocumentTitle("Collection Center - Final Test Report");
        spark.config().setReportName("Complete Test Suite Execution Report");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setEncoding("utf-8");
        spark.config().setTimelineEnabled(true);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Application",  "Collection Center");
        extent.setSystemInfo("Platform",     "Android");
        extent.setSystemInfo("Framework",    "Selenium + Appium + TestNG");
        extent.setSystemInfo("Tester",       "QA Automation Team");
        extent.setSystemInfo("Environment",  "QA");
        extent.setSystemInfo("Execution",    "Serial (One at a time)");
        extent.setSystemInfo("Report Time",  ts);

        System.out.println("\n" + "=".repeat(70));
        System.out.println("  COLLECTION CENTER - AUTOMATION SUITE STARTED");
        System.out.println("=".repeat(70));
    }

    @Override
    public void onFinish(ISuite suite) {
        generateFinalSummary(suite);
        extent.flush();
        printConsoleSummary();
    }

    /* ───────────────────────── TEST EVENTS ───────────────────────── */

    @Override
    public void onTestStart(ITestResult result) {
        String name = getFullTestName(result);
        ExtentTest test = extent.createTest(name);
        testMap.put(name + Thread.currentThread().getId(), test);
        System.out.println("\n▶ RUNNING: " + name);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String name = getFullTestName(result);
        ExtentTest test = testMap.get(name + Thread.currentThread().getId());
        if (test != null) test.pass("✅ TEST PASSED");
        passedTests.add(name);
        System.out.println("  ✅ PASSED:  " + name);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String name = getFullTestName(result);
        ExtentTest test = testMap.get(name + Thread.currentThread().getId());
        if (test != null) {
            test.fail("❌ TEST FAILED");
            if (result.getThrowable() != null)
                test.fail(result.getThrowable());
        }
        failedTests.add(name);
        System.out.println("  ❌ FAILED:  " + name + " → " +
            (result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error"));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String name = getFullTestName(result);
        ExtentTest test = testMap.get(name + Thread.currentThread().getId());
        if (test != null) test.skip("⚠️ TEST SKIPPED");
        skippedTests.add(name);
        System.out.println("  ⚠️ SKIPPED: " + name);
    }

    /* ───────────────────────── HELPERS ───────────────────────── */

    private String getFullTestName(ITestResult result) {
        return result.getTestClass().getRealClass().getSimpleName()
               + " → " + result.getName();
    }

    private void generateFinalSummary(ISuite suite) {
        long elapsed   = System.currentTimeMillis() - suiteStartTime;
        int  total     = passedTests.size() + failedTests.size() + skippedTests.size();
        double passRate = total > 0 ? (passedTests.size() * 100.0 / total) : 0;

        ExtentTest summary = extent.createTest("📊 FINAL EXECUTION SUMMARY");
        summary.info("<b>Total Tests :</b> "   + total);
        summary.info("<b>Passed      :</b> "   + passedTests.size());
        summary.info("<b>Failed      :</b> "   + failedTests.size());
        summary.info("<b>Skipped     :</b> "   + skippedTests.size());
        summary.info("<b>Pass Rate   :</b> "   + String.format("%.1f", passRate) + "%");
        summary.info("<b>Total Time  :</b> "   + formatTime(elapsed));

        if (!passedTests.isEmpty()) {
            StringBuilder sb = new StringBuilder("<b>✅ PASSED TESTS:</b><br>");
            for (int i = 0; i < passedTests.size(); i++)
                sb.append((i + 1) + ". " + passedTests.get(i) + "<br>");
            summary.pass(sb.toString());
        }
        if (!failedTests.isEmpty()) {
            StringBuilder sb = new StringBuilder("<b>❌ FAILED TESTS:</b><br>");
            for (int i = 0; i < failedTests.size(); i++)
                sb.append((i + 1) + ". " + failedTests.get(i) + "<br>");
            summary.fail(sb.toString());
        }
        if (!skippedTests.isEmpty()) {
            StringBuilder sb = new StringBuilder("<b>⚠️ SKIPPED TESTS:</b><br>");
            for (int i = 0; i < skippedTests.size(); i++)
                sb.append((i + 1) + ". " + skippedTests.get(i) + "<br>");
            summary.skip(sb.toString());
        }

        // Also write a plain text summary
        writeTextSummary(total, elapsed, passRate);
    }

    private void writeTextSummary(int total, long elapsed, double passRate) {
        String ts   = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String path = reportDir + "/SUMMARY_" + ts + ".txt";
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("=".repeat(60) + "\n");
            fw.write("  COLLECTION CENTER - TEST EXECUTION SUMMARY\n");
            fw.write("=".repeat(60) + "\n\n");
            fw.write("Date/Time   : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n");
            fw.write("Total Time  : " + formatTime(elapsed) + "\n\n");
            fw.write("Total Tests : " + total + "\n");
            fw.write("✅ Passed   : " + passedTests.size() + "\n");
            fw.write("❌ Failed   : " + failedTests.size() + "\n");
            fw.write("⚠️ Skipped  : " + skippedTests.size() + "\n");
            fw.write("Pass Rate   : " + String.format("%.1f", passRate) + "%\n\n");

            fw.write("--- PASSED TESTS ---\n");
            for (int i = 0; i < passedTests.size(); i++)
                fw.write((i+1) + ". " + passedTests.get(i) + "\n");

            fw.write("\n--- FAILED TESTS ---\n");
            for (int i = 0; i < failedTests.size(); i++)
                fw.write((i+1) + ". " + failedTests.get(i) + "\n");

            fw.write("\n--- SKIPPED TESTS ---\n");
            for (int i = 0; i < skippedTests.size(); i++)
                fw.write((i+1) + ". " + skippedTests.get(i) + "\n");

            fw.write("\n" + "=".repeat(60) + "\n");
            System.out.println("📄 Text summary saved: " + path);
        } catch (IOException e) {
            System.err.println("Failed to write summary: " + e.getMessage());
        }
    }

    private void printConsoleSummary() {
        int total = passedTests.size() + failedTests.size() + skippedTests.size();
        double passRate = total > 0 ? (passedTests.size() * 100.0 / total) : 0;

        System.out.println("\n" + "=".repeat(70));
        System.out.println("  COLLECTION CENTER - FINAL TEST RESULTS");
        System.out.println("=".repeat(70));
        System.out.printf("  %-20s %d%n",  "Total Tests:",   total);
        System.out.printf("  ✅ %-18s %d (%.1f%%)%n", "Passed:",  passedTests.size(),  passRate);
        System.out.printf("  ❌ %-18s %d (%.1f%%)%n", "Failed:",  failedTests.size(),  total > 0 ? failedTests.size()*100.0/total : 0);
        System.out.printf("  ⚠️  %-17s %d (%.1f%%)%n", "Skipped:", skippedTests.size(), total > 0 ? skippedTests.size()*100.0/total : 0);
        System.out.println("-".repeat(70));

        if (!failedTests.isEmpty()) {
            System.out.println("\n  ❌ FAILED TEST CASES:");
            for (int i = 0; i < failedTests.size(); i++)
                System.out.println("     " + (i+1) + ". " + failedTests.get(i));
        }
        if (!skippedTests.isEmpty()) {
            System.out.println("\n  ⚠️  SKIPPED TEST CASES:");
            for (int i = 0; i < skippedTests.size(); i++)
                System.out.println("     " + (i+1) + ". " + skippedTests.get(i));
        }
        System.out.println("\n  📊 Reports saved in: " + reportDir);
        System.out.println("=".repeat(70) + "\n");
    }

    private String formatTime(long ms) {
        long secs = ms / 1000;
        return String.format("%d min %d sec", secs / 60, secs % 60);
    }
}
