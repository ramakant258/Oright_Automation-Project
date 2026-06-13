package Testcases;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Baseclass;

@Test(singleThreaded=true)
public class Testing_Dashboard_CC extends Baseclass {
	@Test
	 public void login() throws IOException, InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
        WebElement getStartedButton = driver.findElement(By.xpath(Loc.getProperty("getStarted")));
        getStartedButton.click();
        WebElement emailInput = driver.findElement(By.xpath(Loc.getProperty("Email_id")));
        emailInput.sendKeys("jite201547");
        WebElement nextButton = driver.findElement(By.xpath(Loc.getProperty("Next_button")));
        nextButton.click();
        WebElement passwordInput = driver.findElement(By.xpath(Loc.getProperty("Password_inputbox_placeholder")));
        passwordInput.sendKeys("jite201547");
        WebElement customerCodeInput = driver.findElement(By.xpath(Loc.getProperty("customer_code_input_box")));
        customerCodeInput.sendKeys("or19");
        WebElement loginButton = driver.findElement(By.xpath(Loc.getProperty("Login")));
        loginButton.click();
       
//        try {
//            WebElement successMessage = driver.findElement(By.xpath(Loc.getProperty("Login_successful")));
//            if (successMessage.isDisplayed()) {
//                System.out.println("Login successful.");
//            }
//        } catch (Exception e) {
//            System.out.println("Login unsuccessful.");
//        }
        WebElement dashboardButton = driver.findElement(By.xpath(Loc.getProperty("dashboardtext")));
        dashboardButton.click();
        Thread.sleep(5000);
		//WebElement edpuElement = driver.findElement(By.xpath(Loc.getProperty("EDPUFooter")));
	    //edpuElement.click();
       // WebElement menu =driver.findElement(By.xpath(EDPU.getProperty("Menu")));
        //System.out.println("Menu element is visible and clickable.");
        //menu.click();
        //WebElement edpu=driver.findElement(By.xpath(EDPU.getProperty("edpu")));
	     //edpu.click();
	     //WebElement edpu_testing=driver.findElement(By.xpath(EDPU.getProperty("edpu_testing")));
	     //edpu_testing.click();
		}
	//verify that User so able to Select Menu 
	@Test
	public void verify_that_User_so_able_to_Select_Menu() throws IOException, InterruptedException {
		login();
//		WebElement menu =driver.findElement(By.xpath(EDPU.getProperty("Menu")));
//        System.out.println("Menu element is visible and clickable.");
//        menu.click();
//        WebElement edpu=driver.findElement(By.xpath(EDPU.getProperty("edpu")));
//	     edpu.click();
//	     WebElement edpu_testing=driver.findElement(By.xpath(EDPU.getProperty("edpu_testing")));
//	     edpu_testing.click();
		Thread.sleep(10000);
	     try {
	    	 Thread.sleep(15000);
	    	 WebElement menu =driver.findElement(By.xpath(EDPU.getProperty("Menu")));
	         System.out.println("Menu element is visible and clickable.");
	         menu.click();
	         System.out.println("Clicked on the Menu element successfully.");
	     } catch (Exception e) {
	         System.err.println("Failed to click on the Menu element: " + e.getMessage());
	         throw e; // Rethrow the exception to fail the test
	     }
	     WebElement edpu=driver.findElement(By.xpath(EDPU.getProperty("edpu")));
	     edpu.click();
	     WebElement edpu_testing=driver.findElement(By.xpath(EDPU.getProperty("edpu_testing")));
	     edpu_testing.click();
	}
	
	@Test
	public void verifyTestingDashboardHeader() throws InterruptedException, IOException {
	  //  login();
		verify_that_User_so_able_to_Select_Menu();
	    Thread.sleep(5000); // Wait for the dashboard to load completely
	    String expectedHeader = "Testing Dashboard";
	    String actualHeader = driver.findElement(By.xpath(Loc4.getProperty("Header"))).getText();

	    if (expectedHeader.equals(actualHeader)) {
	        System.out.println("Testing Dashboard header is displayed correctly.");
	    } else {
	        System.out.println("Header verification failed. Expected: " 
	                           + expectedHeader + " but found: " + actualHeader);
	        Assert.fail("Header is Not correct ,Header verification failed.");
	    }
	}
	
	@Test
	public void verifyLastSyncedDisplayed() throws IOException, InterruptedException {
		//login();
		verify_that_User_so_able_to_Select_Menu();
		Thread.sleep(5000); // Wait for the dashboard to load completely
	    String lastSynced = driver.findElement(By.xpath(Loc4.getProperty("Last_Sync"))).getText();
        System.out.println("Last Synced timestamp displayed correctly: " + lastSynced);
	    
	}
	@Test
	public void verifyRefreshButtonWorking() throws IOException, InterruptedException {
		  //login();
		verify_that_User_so_able_to_Select_Menu();
		  Thread.sleep(15000); // Wait for the dashboard to load completely
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    String oldTimestamp = driver.findElement(By.xpath(Loc4.getProperty("Last_Sync"))).getText();
	    WebElement refresh = driver.findElement(By.xpath(Loc4.getProperty("Sync_Button")));
	    refresh.click();
	    wait.until(ExpectedConditions.not(
	            ExpectedConditions.textToBe(By.xpath(Loc4.getProperty("Last_Sync")), oldTimestamp)));

	    String newTimestamp = driver.findElement(By.xpath(Loc4.getProperty("Last_Sync"))).getText();

	    if (!oldTimestamp.equals(newTimestamp)) {
	        System.out.println("Refresh button working correctly. Timestamp updated.");
	    } else {
	        System.out.println("Refresh button not working.");
	        Assert.fail("Refresh Button Not Working ");
	    }
	}
	@Test
	public void verifyThreeDotOptionsFunctionality() 
	        throws InterruptedException, IOException {
	    //login();
		verify_that_User_so_able_to_Select_Menu();
	    Thread.sleep(15000);
	    driver.findElement(By.xpath(Loc4.getProperty("Three_Dot"))).click();
	    Thread.sleep(2000);
	    WebElement ors = driver.findElement(
	            By.xpath(Loc4.getProperty("ORD_Sync")));
	    System.out.println("Clicking: " + ors.getText());
	    ors.click();
	    Thread.sleep(13000);
	    String orsExpected = "ORS DPU Sync";
	    WebElement orsHeader = driver.findElement(
	            By.xpath(Loc4.getProperty("Header")));
	    if (orsExpected.equals(orsHeader.getText())) {
	        System.out.println("ORS DPU Sync page opened successfully.");
	    } else {
	        System.out.println("Failed to open ORS DPU Sync page.");
	    }
	    driver.findElement(By.xpath(Loc4.getProperty("Back_Nav"))).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath(Loc4.getProperty("Three_Dot"))).click();
	    Thread.sleep(2000);
	    WebElement dpmcu = driver.findElement(
	            By.xpath(Loc4.getProperty("DPMCU_Sync")));
	    System.out.println("Clicking: " + dpmcu.getText());
	    dpmcu.click();
	    Thread.sleep(3000);
	    String dpmcuExpected = "DPMCU Sync";
	    WebElement dpmcuHeader = driver.findElement(
	            By.xpath(Loc4.getProperty("Header")));
	    if (dpmcuExpected.equals(dpmcuHeader.getText())) {
	        System.out.println("DPMCU Sync page opened successfully.");
	    } else {
	        System.out.println("Failed to open DPMCU Sync page.");
	    }
	    driver.findElement(By.xpath(Loc4.getProperty("Back_Nav"))).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath(Loc4.getProperty("Three_Dot"))).click();
	    Thread.sleep(2000);
	    WebElement unreg1 = driver.findElement(
	            By.xpath(Loc4.getProperty("Unregistered_farmer1")));
	    System.out.println("Clicking: " + unreg1.getText());
	    unreg1.click();
	    Thread.sleep(3000);
	    String unregExpected = "Unregistered Farmer List";
	    WebElement unregHeader = driver.findElement(
	            By.xpath(Loc4.getProperty("Header")));
	    if (unregExpected.equals(unregHeader.getText())) {
	        System.out.println("Unregistered Farmer List opened successfully.");
	    } else {
	        System.out.println("Failed to open Unregistered Farmer List.");
	    }
	    driver.findElement(By.xpath(Loc4.getProperty("Back_Nav"))).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath(Loc4.getProperty("Three_Dot"))).click();
	    Thread.sleep(2000);
	    WebElement unreg2 = driver.findElement(
	    By.xpath(Loc4.getProperty("Unregistered_farmer2")));
	    System.out.println("Clicking: " + unreg2.getText());
	    unreg2.click();
	    Thread.sleep(3000);
	    WebElement unregHeader2 = driver.findElement(
	            By.xpath(Loc4.getProperty("Header")));
	    if (unregExpected.equals(unregHeader2.getText())) {
	        System.out.println("Unregistered Farmer List opened successfully.");
	    } else {
	        System.out.println("Failed to open Unregistered Farmer List.");
	    }
	    driver.findElement(By.xpath(Loc4.getProperty("Back_Nav"))).click();
	    Thread.sleep(3000);
	    System.out.println("All three dot options verified successfully.");
	}
	@Test
	public void verifyCurrentDateAndShiftDisplayed() throws InterruptedException, IOException {
		//login();
		verify_that_User_so_able_to_Select_Menu();
	    Thread.sleep(5000); // Wait for the dashboard to load completely
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	    String currentDate = LocalDate.now().format(formatter);
	    String displayedDate = driver.findElement(By.xpath(Loc4.getProperty("Current_Date"))).getText();
	    String displayedShift = driver.findElement(By.xpath(Loc4.getProperty("Default_Shift"))).getText();
	    if (currentDate.equals(displayedDate)) {
	        System.out.println("Current Date displayed correctly: " + displayedDate);
	    } else {
	        System.out.println("Date mismatch. Expected: " + currentDate + " but found: " + displayedDate);
	    }
	    if (displayedShift.equalsIgnoreCase("Morning") || displayedShift.equalsIgnoreCase("Evening")) {
	        System.out.println("Shift displayed correctly: " + displayedShift);
	    } else {
	        System.out.println("Shift not displayed correctly.");
	    }
	}
	   @Test
	    public void verifyCalendarIsClickable() throws InterruptedException, IOException {
	        //login();
		   verify_that_User_so_able_to_Select_Menu();
	        Thread.sleep(3000);
	        WebElement calendarIcon = driver.findElement(
	                By.xpath(Loc4.getProperty("Calnder_icon")));
	        if (calendarIcon.isDisplayed() && calendarIcon.isEnabled()) {
	            calendarIcon.click();
	            System.out.println("Calendar is clickable.");
	        } else {
	            System.out.println("Calendar is NOT clickable.");
	        }
	    }
	   @Test
	   public void verifyRandomDateSelectionOnDshboard() throws InterruptedException, IOException {
		   //login();
		   verify_that_User_so_able_to_Select_Menu();
	   Thread.sleep(3000);
	String beforeDate = driver.findElement(
	        By.xpath(Loc4.getProperty("Current_Date")))
	        .getText();
	System.out.println("Dashboard Date Before: " + beforeDate);
	driver.findElement(By.xpath(Loc4.getProperty("Calnder_icon")))
	        .click();
	Thread.sleep(3000);
	List<WebElement> dateList = driver.findElements(
	        By.xpath(Loc4.getProperty("Date_Selection")));
	if (dateList.isEmpty()) {
	    System.out.println("No dates available.");
	    return;
	}
	Random random = new Random();
	int randomIndex = random.nextInt(dateList.size());
	WebElement randomDate = dateList.get(randomIndex);
	String selectedDay = randomDate.getText();  
	System.out.println("Random Date Selected (Day): " + selectedDay);
	randomDate.click();
	Thread.sleep(4000);
	String dashboardDate = driver.findElement(
	        By.xpath(Loc4.getProperty("Current_Date")))
	        .getText();
	System.out.println("Dashboard Date After: " + dashboardDate);
	int day = Integer.parseInt(selectedDay);
	String formattedDay = String.format("%02d", day); 
	String monthYear = dashboardDate.substring(3);
	String expectedDate = formattedDay + "-" + monthYear;
	System.out.println("Expected Date: " + expectedDate);
	if (dashboardDate.equals(expectedDate)) {
	    System.out.println("Random date reflected successfully on dashboard.");
	} else {
	    System.out.println("Selected date NOT reflected on dashboard.");
	}
}
	   @Test
	   public void verifyShiftFilterIsClickable() throws InterruptedException, IOException {
		    //login();
		   verify_that_User_so_able_to_Select_Menu();
		        Thread.sleep(3000);
		        WebElement shiftFilter = driver.findElement(
		                By.xpath(Loc4.getProperty("Shift_Dropdown")));
		        if (shiftFilter.isDisplayed() && shiftFilter.isEnabled()) {
		            shiftFilter.click();
		            System.out.println("Shift filter clicked successfully.");
		            Thread.sleep(3000);
		            boolean morningPresent = driver.findElements(
		                    By.xpath(Loc4.getProperty("Shift_Mor"))).size() > 0;
		            boolean eveningPresent = driver.findElements(
		                    By.xpath(Loc4.getProperty("Shift_Eve"))).size() > 0;
		            boolean cancelPresent = driver.findElements(
		                    By.xpath(Loc4.getProperty("Cancel"))).size() > 0;
		            boolean okPresent = driver.findElements(
		                    By.xpath(Loc4.getProperty("Ok"))).size() > 0;
		            if (morningPresent && eveningPresent && cancelPresent && okPresent) {
		                System.out.println("Shift modal opened successfully with all elements.");
		            } else {
		                System.out.println("Shift modal elements missing.");
		            }

		        } else {
		            System.out.println("Shift filter not clickable.");
		        }
		    }
	   @Test
	   public void verifyMorningShiftSelection() 
		        throws InterruptedException, IOException {
		    //login();
		   verify_that_User_so_able_to_Select_Menu();
		    Thread.sleep(10000);
		    driver.findElement(By.xpath(Loc4.getProperty("Shift_Dropdown")))
		            .click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath(Loc4.getProperty("Shift_Mor")))
		            .click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath(Loc4.getProperty("Ok")))
		            .click();
		    Thread.sleep(4000);
		    String selectedShift = driver.findElement(
		            By.xpath(Loc4.getProperty("Current_Shift")))
		            .getText();
		    if (selectedShift.contains("Morning")) {
		        System.out.println("Morning shift applied successfully.");
		    } else {
		        System.out.println("Morning shift not applied.");
		    }
		} 
	   @Test
	   public void verifyEveningShiftSelection() 
		        throws InterruptedException, IOException {
		   // login();
		   verify_that_User_so_able_to_Select_Menu();
		    Thread.sleep(3000);
		    driver.findElement(By.xpath(Loc4.getProperty("Shift_Dropdown")))
		            .click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath(Loc4.getProperty("Shift_Eve")))
		            .click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath(Loc4.getProperty("Ok")))
		            .click();
		    Thread.sleep(4000);
		    String selectedShift = driver.findElement(
		            By.xpath(Loc4.getProperty("Current_Shift")))
		            .getText();
		    if (selectedShift.contains("Evening")) {
		        System.out.println("Evening shift applied successfully.");
		    } else {
		        System.out.println("Evening shift not applied.");
		        Assert.fail("Evening Shift not applied ");
		    }
		} 
	   @Test
	   public void verifyShiftCancelFunctionality() throws InterruptedException, IOException {
		    //login();
		   verify_that_User_so_able_to_Select_Menu();
		    Thread.sleep(3000);
		    String beforeShift = driver.findElement(
		            By.xpath(Loc4.getProperty("Current_Shift")))
		            .getText();
		    driver.findElement(By.xpath(Loc4.getProperty("Shift_Filter"))).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath(Loc4.getProperty("Shift_Eve")))
            .click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath(Loc4.getProperty("Cancel")))
            .click();
		    Thread.sleep(3000);
		    String afterShift = driver.findElement(
		            By.xpath(Loc4.getProperty("Current_Shift")))
		            .getText();
		    if (beforeShift.equals(afterShift)) {
		        System.out.println("Cancel worked correctly. Shift not changed.");
		    } else {
		        System.out.println("Cancel failed. Shift changed unexpectedly.");
		    }
		}
	   	@Test
	public void VerifyDefaultValuesForTestedMilkAmountRecordsAreZeroDisplayedOnDashboard() throws InterruptedException, IOException {
     //login();
	   verify_that_User_so_able_to_Select_Menu();
	   Thread.sleep(5000); // Wait for the dashboard to load completely
	    String testedMilk = driver.findElement(By.xpath(Loc4.getProperty("Tested_Milk_Value"))).getText();
	    String amount = driver.findElement(By.xpath(Loc4.getProperty("Amount_Value"))).getText();
	    String totalRecords = driver.findElement(By.xpath(Loc4.getProperty("Tested_Records"))).getText();
	    boolean isTestedMilkZero = testedMilk.equals("0") || testedMilk.equals("0.00");
	    boolean isAmountZero = amount.equals("0") || amount.equals("0.00");
	    boolean isTotalRecordsZero = totalRecords.equals("0");
	    if (isTestedMilkZero && isAmountZero && isTotalRecordsZero) {
	        System.out.println("Default values are correctly displayed as zero.");
	    } else {
	        System.out.println("Default values are incorrect.");
	        System.out.println("Tested Milk: " + testedMilk);
	        System.out.println("Amount: " + amount);
	        System.out.println("Total Records: " + totalRecords);
	    }
	}
	@Test
	public void verifyTestedRecordsCount() throws InterruptedException, IOException {
	   // login();
		verify_that_User_so_able_to_Select_Menu();
	    Thread.sleep(5000);
	    int dashboardRecords = Integer.parseInt(
	            driver.findElement(By.xpath(
	                    Loc4.getProperty("Tested_Records"))).getText());
	    System.out.println("Dashboard Tested Records: " + dashboardRecords);
	     List<WebElement> farmerCards = driver.findElements(
	            By.xpath(Loc4.getProperty("Farmer_Card")));
	    int totalFarmers = farmerCards.size();
	    System.out.println("Total Farmers in List: " + totalFarmers);
	    if (dashboardRecords == totalFarmers) {
	        System.out.println("Tested Records verified successfully.");
	    } else {
	        System.out.println("Mismatch in Tested Records.");
	        Assert.fail("Mismatch in Tested Records");
	        System.out.println("Expected: " + totalFarmers 
	                   + " but Found: " + dashboardRecords);
	    }
	}
@Test
	public void verifyAllFieldsAvailableInCard() throws InterruptedException, IOException {
	//login();
	verify_that_User_so_able_to_Select_Menu();
	    Thread.sleep(4000);
	    List<WebElement> farmerCards = driver.findElements(By.xpath(Loc4.getProperty("Farmer_Card")));
	       	    System.out.println("Total cards found: " + farmerCards.size());
	    for (WebElement farmer : farmerCards) {
	        String farmerName = farmer.getText();
	        System.out.println("Verifying card: " + farmerName);
	        WebElement cardContainer = farmer.findElement(By.xpath("ancestor::*[1]"));
	        List<WebElement> cardTexts = cardContainer.findElements(
	                By.className("android.widget.TextView"));
	        boolean hasType = false;
	        boolean hasFat = false;
	        boolean hasSNF = false;
	        boolean hasQty = false;
	        boolean hasPrice = false;
	        boolean hasAmount = false;
	        for (WebElement text : cardTexts) {
	            String value = text.getText();
	            if (value.equals("Type")) hasType = true;
	            if (value.equals("Fat (%)")) hasFat = true;
	            if (value.equals("SNF (%)")) hasSNF = true;
	            if (value.equals("Qty(L)")) hasQty = true;
	            if (value.contains("Price (₹)")) hasPrice = true;
	            if (value.contains("Amount (₹)")) hasAmount = true;
	        }
	        if (hasType && hasFat && hasSNF && hasQty && hasPrice && hasAmount) {
	            System.out.println("All required fields present in: " + farmerName);
	        } else {
	            System.out.println("Missing fields in: " + farmerName);
	        }
	    }
	}
@Test
    public void verifyNavigationToEDPUPageOnClickingEdpuIcon() throws InterruptedException, IOException {
	verify_that_User_so_able_to_Select_Menu();
        Thread.sleep(10000);
        WebElement icon =  driver.findElements(By.xpath(
        Loc4.getProperty("Edpu_Retest_Icon"))).get(0);
icon.click();
Thread.sleep(3000);
boolean navigated = driver.findElements(
        By.xpath( Loc4.getProperty("Milk_Testing_Page")))
        .size() > 0;
if (navigated) {
    System.out.println("Navigation successful.");
} else {
    System.out.println("Navigation failed.");
}
}
@Test
public void verifyPrintWithBluetoothPermission() throws InterruptedException, IOException {
//login();
verify_that_User_so_able_to_Select_Menu();
    Thread.sleep(3000);

    // Click Print Button
    driver.findElement(By.xpath(Loc4.getProperty("Print_Icon")))
            .click();
    Thread.sleep(4000);
    driver.findElement(By.xpath(Loc4.getProperty("BT_Allow "))).click();
        System.out.println("Bluetooth permission popup displayed.");
        Thread.sleep(4000);
        boolean scanningStarted = driver.findElement(By.xpath(Loc4.getProperty("Loader"))).isDisplayed();       	

        if (scanningStarted) {
            System.out.println("Bluetooth scanning started successfully.");
            } else {
        System.out.println("Permission popup not displayed.");
    }
}
@Test
public void verifyPrintWithBluetoothDenied() 
        throws InterruptedException, IOException {
    //login();
    verify_that_User_so_able_to_Select_Menu();
    Thread.sleep(3000);
    driver.findElement(By.xpath(Loc4.getProperty("Print_Icon")))
            .click();
    Thread.sleep(4000);
    driver.findElement(By.xpath(Loc4.getProperty("Deny")))
            .click();
    Thread.sleep(3000);
    boolean toastDisplayed = driver.findElements(
            By.xpath(Loc4.getProperty("Toast_message")))
            .size() > 0;
    if (toastDisplayed) {
        System.out.println("Toast message displayed correctly: Please turn Bluetooth on");
    } else {
        System.out.println("Toast message NOT displayed.");
    }
}
	}
	