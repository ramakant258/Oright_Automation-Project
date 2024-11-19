package Testcase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.concurrent.TimeUnit;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;
// check login with diffrence credentials
public class ColectionCenterMaster1  extends BaseTest{ 
	@Test(dataProvider = "testdata")
	public  void LoginTest(String username, String Password,String Customercode ) throws InterruptedException {
		   driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
		   driver.manage().window().maximize();
		   driver.findElement(By.xpath(Loc.getProperty("getstarted"))).click();
		   Thread.sleep(2000);
	        driver.findElement(By.xpath(Loc.getProperty("email"))).sendKeys(username);
	        Thread.sleep(2000);	
	        driver.findElement(By.xpath(Loc.getProperty("next"))).click();
	        Thread.sleep(2000);	
	        driver.findElement(By.xpath(Loc.getProperty("password"))).sendKeys(Password);
	        Thread.sleep(2000);
	     	driver.findElement(By.xpath(Loc.getProperty("customercode"))).sendKeys(Customercode);
	     	Thread.sleep(2000);	 
	     	driver.findElement(By.xpath(Loc.getProperty("login"))).click();
	     	Thread.sleep(4000);
	     	String expectedUrl = "https://www.oright.in/kamdhenu/dairy-farm-dashboard";
	     	String currentUrl = driver.getCurrentUrl();
	     	System.out.println("Current URL: " + currentUrl);
	       	Thread.sleep(2000);
	     	if (currentUrl.equals(expectedUrl)) {
	     	    System.out.println("Login successful!");
	     	} else {
	     	    System.out.println("Login failed. Expected URL: " + expectedUrl + ", Actual URL: " + currentUrl);
	     	}


	     	
	}
	@DataProvider (name = "testdata")
	public Object[][] tdata()
	{
		return new Object[][] {
			{"apdf1","apdf1","test"},
			{"apdf","apdf1","test"},
			{"apdf1","apdf","test"},
			{"apdf1","apdf1","test5"},
			{"apdf","apdf","test3"}
	};
			
	} 
	// check menu button button available and click performed succesfully 
	@Test
	public  void menu() throws InterruptedException { 
		ColectionCenterMaster1 cc=new ColectionCenterMaster1();
		cc.LoginTest("apdf1","apdf1","test");
		Thread.sleep(3000);
		WebElement  Menuelement = driver.findElement(By.xpath(Loc.getProperty("menu")));
		Menuelement.click();
		if(Menuelement.isDisplayed()) {
			System.out.println("Menu button is available & clicked succesfully");
		}else{
			System.out.println("some error ocured");
		}
		
	}
	 // check master availability is available in menu 
	@Test
	public  void CCMaster()throws InterruptedException{   
		ColectionCenterMaster1 cc= new ColectionCenterMaster1();
		cc.menu();
		Thread.sleep(3000);
		WebElement master1 = driver.findElement(By.xpath(Loc.getProperty("master")));
		master1.click();
		String elementtext=master1.getText();
		if(master1.isDisplayed()) {
			System.out.println("Master Clicked Succesfully and Text is: "+ elementtext);
		}else{
			System.out.println("Not Clicked");
		}
	}
	 // check collection center master availability under master 
	@Test
		public  void Master()throws InterruptedException{ 
			ColectionCenterMaster1 cc= new ColectionCenterMaster1();
			cc.CCMaster();
			Thread.sleep(3000);
			WebElement ccmaster1 = driver.findElement(By.xpath(Loc.getProperty("ccmaster")));
			String ccmaster = ccmaster1.getText();
			if(ccmaster1.isDisplayed()) {
				System.out.println(ccmaster+ "' Available under Master With Text: ");
			}else {
				System.out.println("Collection Center Master Not Available");
			}
			ccmaster1.click();
		}

	 // check nav(back) button is available on cc master and after click on back button user navigatd to back page 	
    @Test
    public  void backbutton() throws InterruptedException{
    	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
    	cc.Master();
    	Thread.sleep(2000);
    	WebElement backbtn = driver.findElement(By.xpath(Loc.getProperty("navbutton")));
    	if(backbtn.isDisplayed()) {
    		System.out.println("Back button is available");
    	}else {
    		System.out.println("Back button is not available");
    	}
     WebElement btn =  driver.findElement(By.xpath(Loc.getProperty("navbutton")));
      btn.click();
      Thread.sleep(3000);
      WebElement checkback = driver.findElement(By.xpath(Loc.getProperty("Dashboard")));
      if(checkback.isDisplayed())
      {
    	  System.out.println("user navigated to back page succesfully");
      }else {
    	  System.out.println("back button is not clickable");  
      }
    }
 // check collection center header is displayed and correctly aligned 
    @Test
    public void ccheader() throws InterruptedException {  
    	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
    	cc.Master();
    	Thread.sleep(3000);
    	WebElement headers = driver.findElement(By.xpath(Loc.getProperty("header")));
    	Thread.sleep(2000);
    	String headerText = headers.getText();
    	System.out.println("Header is available with Text :" +headerText);	
    	String alignment = headers.getCssValue("text-align");
    	System.out.println("Header correctly Aligned with :"+ alignment);
    }
 // check search bar is displayed or not in collection center master page 
    @Test
    public void seachbar() throws InterruptedException {  
    	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
    	cc.ccheader();
    	Thread.sleep(3000);
    	WebElement search = driver.findElement(By.xpath(Loc.getProperty("searchbar_displayed")));
    	if(search.isDisplayed()) {
    		System.out.println("Searchbar is Displayed");
    	}else{
    		System.out.println("Searchbar is not Displayed");
    	}
    }
    // check searched data from searchbar is available in table 
    @Test
    public void searchdata() throws InterruptedException { 
       ColectionCenterMaster1 cc= new ColectionCenterMaster1();
     	cc.seachbar();
     	 WebElement searchinput = driver.findElement(By.xpath(Loc.getProperty("searchinput")));
     	String[] searchdata = {"0001","0054","Sohit C4","fbdfbdd"};
     	String[] Actual_data = {"0001","0054","Sohit C4"};
     	 List<String> actualDataList = Arrays.asList(Actual_data);
     	for (String data :searchdata) {
     		searchinput.sendKeys(data);
     	if(actualDataList.contains(data)) {
		System.out.println(data + "' available in table");
		}else {
		System.out.println(data +"' no data found");
		}
     	Thread.sleep(2000);
     	searchinput.clear();
     	}
   	WebElement search_closebutton= driver.findElement(By.xpath(Loc.getProperty("search_closebutton1")));
   	search_closebutton.click();
   	System.out.println("close button clicked succesfully !");
   	    }
 // check close button in search is clickable or not if clickable then check input data clear from searchbar 
    @Test
    public  void searchclose() throws InterruptedException {  
    	ColectionCenterMaster1 cc= new ColectionCenterMaster1(); 
        cc.seachbar();
        WebElement senddata_searchbar = driver.findElement(By.xpath(Loc.getProperty("searchinput")));     
        senddata_searchbar.sendKeys("877");
        Thread.sleep(2000);
        WebElement close = driver.findElement(By.xpath(Loc.getProperty("searchclosebutton")));
       close.click();
        System.out.println("close button clicked succesfully and search data get clear");
	}
    // check buttons in collection center masters are enabled or not 
    @Test
    public  void checkbutton() throws InterruptedException { 
    	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        cc.Master();
       Thread.sleep(2000);
        List<WebElement> inputs = driver.findElements(By.xpath(Loc.getProperty("buttons")));
        for (WebElement input : inputs) {
        String inputText = input.getText();
        boolean isEnabled = input.isEnabled();
        System.out.println("button '" + inputText + "' is " + (isEnabled ? "enabled" : "disabled"));
    }
    }
    // check chc filter is displayed and selected bydefault 
    @Test
    public  void chcfilter() throws InterruptedException { 
    	ColectionCenterMaster1 cc= new ColectionCenterMaster1(); 
        cc.tablecheck();
        Thread.sleep(2000);
        WebElement CHC_Filter = driver.findElement(By.xpath(Loc.getProperty("chcfilter")));
        String filter= CHC_Filter.getText();
        if(CHC_Filter.isDisplayed()) {
        	 System.out.println("By default, first Chilling Center is Selected and Displayed : " + filter);
        } else {
            System.out.println("Please Select Chilling Center ");
             }
        Thread.sleep(2000);
        WebElement clickfilter = driver.findElement(By.xpath(Loc.getProperty("clickchcfilter")));
        clickfilter.click();
        System.out.println("After Click on Chilling Center Filter List Opend");
        }
    // check chc list header is displayed 
    @Test
    public  void listheader() throws InterruptedException {  
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1(); 
	 cc.chcfilter();
     Thread.sleep(2000);
     WebElement listheader = driver.findElement(By.xpath(Loc.getProperty("CHClistheader")));
     String LHeader=listheader.getText();
     if(listheader.isDisplayed()){
   	  System.out.println("List Header is :" +LHeader);
     } else {
   	System.out.println("list header is not available");  
     } 
} 
    // check bydefault table is displayed if data available 
    @Test
    public  void tablecheck()throws InterruptedException {  
    	ColectionCenterMaster1 cc= new ColectionCenterMaster1(); 
        cc.seachbar();
        Thread.sleep(2000);  
  WebElement table = driver.findElement(By.xpath(Loc.getProperty("td")));
  String tabledata = table.getText();
  if (table.isDisplayed()) {
      System.out.println("By default, table is displayed and data is: " + tabledata);
  } else {
      System.out.println("Table not available.");
  }
    }
 // check add collection center is clickable or not 
  @Test
      public  void Addcc() throws InterruptedException {   
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1(); 
	  cc.Master();
	  Thread.sleep(2000);
	  WebElement addcc_click= driver.findElement(By.xpath(Loc.getProperty("addcollectioncenter")));
	    addcc_click.click();
	  if(addcc_click.isDisplayed()) {
		  System.out.println("Add CollectionCenter Clicked Succesfully ") ;
	  }else {
		  System.out.println("not clicked");
	  }
      }
  // check back button on add collection center form is displayed or not 
  @Test
  public  void backbutton1() throws InterruptedException {  
  ColectionCenterMaster1 cc= new ColectionCenterMaster1(); 
  cc.Addcc() ;
  Thread.sleep(2000);
  WebElement backButton = driver.findElement(By.xpath(Loc.getProperty("backbuttononform")));
  if (backButton.isDisplayed()) {
      System.out.println("Back button is Displayed");
  } else {
      System.out.println("Back button is not Displayed");
  }
  }
//check back button is clckable or not in collection center creation form 
@Test
public  void backbutton1click() throws InterruptedException { 
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1(); 
	  cc.backbutton1() ;
	  Thread.sleep(2000);
	  WebElement backButton = driver.findElement(By.xpath(Loc.getProperty("backbuttononform")));
	  backButton.click();
	  Thread.sleep(1000);	
	  WebElement ccmaster =   driver.findElement(By.xpath(Loc.getProperty("header"))); 
	  String ccmaster1 = ccmaster.getText();
	  if(ccmaster.isDisplayed()){
		  System.out.println("after click on back button user succesfully navigted to" +ccmaster1) ;
		  }else {
			  System.out.println("back button is not clickable");		  }
			  } 
//check header of create collection center form is displayd or not   
         @Test 
       public  void createccform() throws InterruptedException {  
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1(); 
	  cc.backbutton1();
	  Thread.sleep(2000);
	  WebElement ccform =driver.findElement(By.xpath(Loc.getProperty("ccform")));
	  String ccform1 = ccform.getText();
	  if(ccform.isDisplayed()){
		  System.out.println("Header name is " +ccform1);
	  } else {
		  System.out.println("header is not displayed"); 
	          }
	  }
      // find field on create cc form is displayed or not 
         @Test
         public void formfield() throws InterruptedException{
        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1(); 
        	 cc.createccform();
        	 List<WebElement> formf = driver.findElements(By.xpath(Loc.getProperty("fieldinccform"))); 
        	for(WebElement forms1 :formf)
        	{
        		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", forms1);
        		Thread.sleep(1000);   
        		String fields= forms1.getText();   
        		System.out.println("Field in the form is :" + fields + " ' Displayed" ); 
        	 }
        	 }
         // check fields in add cc form is input type or not 
         @Test
         public void check_Fieldstype() throws InterruptedException{  
        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	 cc.formfield();
        	 List<WebElement> inputfields = driver.findElements(By.xpath(Loc.getProperty("inputype"))); 
          	 List<WebElement>  inputcheck = driver.findElements(By.xpath(Loc.getProperty("fieldinccform")));
          	 for (int i = 0; i < inputfields.size(); i++) {
                 WebElement input = inputfields.get(i);
                 WebElement inputField = inputcheck.get(i);

                 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", input);
                 Thread.sleep(1000);

                 String inputfieldname = inputField.getText();
                 if (inputfieldname == null || inputfieldname.isEmpty()) {
                     inputfieldname = input.getAttribute("name");
                 }

                 if ("input".equals(input.getTagName())) {
                     System.out.println("Element is input type: " + inputfieldname);
                 } else {
                     System.out.println("Element is not input type: " + inputfieldname);
                 }
             }
         }
         // check bydefault chc is selected or not in add cc form
         @Test
         public void check_Fields() throws InterruptedException{  
        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	 cc.check_Fieldstype();
        	 WebElement selectedchc=driver.findElement(By.xpath(Loc.getProperty("selectedchconform")));
        	 
        	String expectedselectedchc = " 0001-Apchilling ";
        	String selectedchc1 = selectedchc.getAccessibleName();
        	System.out.println("expected "+expectedselectedchc);
        	System.out.println("actual "+selectedchc1 );
        	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedchc);
        	Thread.sleep(3000);
        	if(expectedselectedchc.equals(selectedchc1)) {
        				System.out.println("bydefault '" +selectedchc1+ "'is selected" );
        			} else{ 
        				System.out.println(" Not Selected ! Please select Chilling center ");
        			}  
             }
         // check cancel button displaying or not in add cc form 
         @Test
         public void cancel_button() throws InterruptedException{  
        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	 cc.createccform();
        	WebElement button= driver.findElement(By.xpath(Loc.getProperty("cancel")));
            	String btn =button.getText();
            	if (button.isDisplayed())
            	{
            	System.out.println( btn+"'button is Displayed");
            	}else {
            		System.out.println(btn +"'button is not displayed");           	
            	}
        	 }
         //check the cancel button is clickable or not in add cc form 
         @Test
        public void cancel_click() throws InterruptedException {
        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	 cc.cancel_button();
        	 WebElement button= driver.findElement(By.xpath(Loc.getProperty("cancel")));
        	 String buttonname=button.getText();
        	 button.click();
        	 Thread.sleep(3000);     
        	 WebElement backpagename =   driver.findElement(By.xpath(Loc.getProperty("header"))); 
        	 String backpage =backpagename.getText() ;
        	 Point backpage1 =backpagename.getLocation();
        	 if(backpagename.isDisplayed()) {
        		 System.out.println("after click on'" + buttonname + "'user succesfully navigated to '" +backpage+"Location:" + backpage1); 
        		 }else{
        			 System.out.println("cancel button is not clickable");
        		 };
         }
       //check the reset button is displaying or not in add cc form 
         @Test
         public void check_reset() throws InterruptedException { 
        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
               cc.cancel_button();
               WebElement reset =driver.findElement(By.xpath(Loc.getProperty("reset")));
               String resetbtton=reset.getText();
               if(reset.isDisplayed())
               {
            	   System.out.println( resetbtton +"'button is displayed");
               }else {
            	   System.out.println( resetbtton+"'button is not displayed");
               }
         }
      // send data in form and click reset button to check all field is cleard or not 
         @Test
         public void senddata_inputfield() throws InterruptedException{  
        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	 cc.check_reset();
        	List<WebElement> sendinput_data = driver.findElements(By.xpath(Loc.getProperty("senddata_input")));
        	String [] data = {"0054","0021","Mamura Milk Ltd.","MamuraSuperviser","8009311258","Delhi","Union Bank","Noida","UNBIO213","234567890987","Ajay","Aadhar Card","238066833876","Ajay021"};
        	for (int i = 0; i < sendinput_data.size(); i++) {
        	    if (i < data.length) {
        	    	sendinput_data.get(i).sendKeys(data[i]);
        	    }
        	}
        	System.out.println("Data entered in all input fields.");
            WebElement reset =driver.findElement(By.xpath(Loc.getProperty("reset")));
            String reset1=reset.getText();
            reset.click();
            System.out.println("All Data get reset after click on'" +reset1);
}
      // check submit button is available or not  in add cc form        
         @Test
         public void submit_button() throws InterruptedException{  
        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	 cc.check_reset();
        	 WebElement submit = driver.findElement(By.xpath(Loc.getProperty("Submit")));
        	 String get_button=submit.getText();
        	 if(submit.isDisplayed())
        	 {
        		 System.out.println(get_button +"' buttn is displayed");
        	 }else {
        		 System.out.println(get_button +"'not displayed");
        	 }	 
         } 
         // send data in field and click create to check cc created succesfuly 
         @Test
         public void click_Reset() throws InterruptedException{
        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	 cc.submit_button();
        	 List<WebElement> sendinput_data = driver.findElements(By.xpath(Loc.getProperty("senddata_input")));
         	String [] data = {"0054","Mamura Milk Ltd.","0012","Noida","MamuraSuperviser","8009311258","Union Bank","Noida","UNBIO213","234567890987","Ajay","Aadhar Card","238066833876","Ajay021"};
         	for (int i = 0; i < sendinput_data.size(); i++) {
         	    if (i < data.length) {
         	    	sendinput_data.get(i).sendKeys(data[i]);
         	    }
         	}
        	 Thread.sleep(2000);
        	 WebElement submit = driver.findElement(By.xpath(Loc.getProperty("Submit")));
        	 submit.click();
        	 WebElement toastmessage = driver.findElement(By.xpath(Loc.getProperty("toastmessage")));
        	 String toastmessage1 = toastmessage.getText();
        	 System.out.println(toastmessage1+ " 'and navigated to collection center master page ");
         
         }
      // check created cc data displayed in table  
         @Test
         public void matchdatatable() throws InterruptedException { 

                ColectionCenterMaster1 cc = new ColectionCenterMaster1();
                cc.click_Reset();
                Thread.sleep(2000);
                WebElement search = driver.findElement(By.xpath(Loc.getProperty("searchinput")));
                search.sendKeys("0054");
                List<WebElement> table_data = driver.findElements(By.xpath(Loc.getProperty("table_updated_data")));
                for (WebElement table :table_data) {
                	String data = table.getText();
                	if (table.isDisplayed()) {
                		System.out.println("added data displayed in table'" +data );
                	} else {
                		System.out.println("added data not diaplayed in table ");
                	}
                		
                }
            }
      // check  error after leaving blank mandatory field in form and click submit 
             @Test
         public void checkerror_aftersenddata() throws InterruptedException{
        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	 cc.submit_button();
        	 List<WebElement> sendinput_data = driver.findElements(By.xpath(Loc.getProperty("senddata_input")));
         	String [] data = {"0054","Mamura Milk Ltd.","0012","Noida","MamuraSuperviser","8009311258","Union Bank","Noida","UNBIO213","234567890987","Ajay","Aadhar Card","238066833876","Ajay021"};
         	int[] skip_fields = {0,1,4,5};
         	for (int i = 0; i < sendinput_data.size(); i++) {
         	    if (shouldskipindex(i,skip_fields)) {
         	    	System.out.println("Skip Mandatory field at index" +i);
         	    	  continue;
         	    }
         	    	sendinput_data.get(i).sendKeys(data[i]);
         	    }
         	   WebElement submit = driver.findElement(By.xpath(Loc.getProperty("Submit")));
          	 submit.click();
         	}		
             
			private boolean shouldskipindex(int i, int[] skip_fields) {
				 for (int index : skip_fields) {
			     
						if (i == index) {
			                return true;
			            }
			        }
			        return false;
			        
			} 
			// get  error after leaving blank mandatory field in form 
			@Test
			 public void geterror() throws InterruptedException{
	        	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
			cc.checkerror_aftersenddata();
			Thread.sleep(2000);
			List<WebElement> error = driver.findElements(By.xpath(Loc.getProperty("errormessage")));
			{
				for(int i=0; i<error.size();i++){
					String errorText = error.get(i).getText();
					System.out.println("error message is:" +errorText);
					
				}
			
			}
       } 
			//check download format is displayed & Enabled 
			@Test
			  public void Download_Format() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		            cc.ccheader();
		            WebElement downloadformat = driver.findElement(By.xpath(Loc.getProperty("DownloadFormat")));
		            String downloadformat_text = downloadformat.getText();
		            if (downloadformat.isDisplayed() && downloadformat.isEnabled() ) {
		            	System.out.println(downloadformat_text+ " is Displayed and Enabled ");
		            } else {
		            	System.out.println(downloadformat_text+ " is not Displayed and Enabled ");
		            }       
           }
			// click on download format to check file download succesfully 
			@Test
			  public void DownloadFormat_click() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		            cc.Download_Format();
		            WebElement downloadformat = driver.findElement(By.xpath(Loc.getProperty("DownloadFormat")));
		            downloadformat.click();
		            WebElement message = driver.findElement(By.xpath(Loc.getProperty("message")));
		            String textmessage = message.getText();
		            	System.out.println("Format "  + textmessage);    	
			  }
			// check the download file format is downloaded and available in directory 
			@Test
			 public void checkfile() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		           cc.DownloadFormat_click();
		           Thread.sleep(5000);
		            String Dir= "C:\\Users\\dell\\Downloads" ;
		            String fileName = "collectioncenterCreationExcelFormat.xlsx";
		            File file = new File(Dir + "\\" + fileName);
		            if(file.exists()) {
		            	System.out.println("collectioncenterCreationExcelFormat downladed succesfully");
		            	} else {
		            		System.out.println("collectioncenterCreationExcelFormat is not downloaded");
		            	}
		          
		            }
			// read downloaded format excel file 
			@Test
			 public void readexcelfile() throws InterruptedException, EncryptedDocumentException, IOException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		           cc.checkfile();
		           String filePath = "C:\\Users\\dell\\Downloads\\collectioncenterCreationExcelFormat (3).xlsx";
		           FileInputStream fis = new FileInputStream(new File(filePath));
		           Workbook workbook = WorkbookFactory.create(fis);
		           Sheet sheet = workbook.getSheetAt(0);
		           for (Row row : sheet) {
		               for (org.apache.poi.ss.usermodel.Cell cell : row) {
		                   System.out.print(cell.toString() + "\t");
		               }
		               System.out.println(); 
		           }
		   	}
			// read downloaded format excel file
			@Test
			 public void readexcelcredetials() throws InterruptedException, EncryptedDocumentException, IOException { 
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		           cc.readexcelfile();
		           String filePath = "C:\\Users\\dell\\Downloads\\collectionCenterCredentials (9).csv";
		           try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		               String line;
		               while ((line = br.readLine()) != null) {
		                   String[] values = line.split(",");
		                   for (String value : values) {
		                       System.out.print(value + "\t");
		                   }
		                   System.out.println(); 
		               }
		           }}
			//check upload excel is displayed & Enabled
			@Test
			  public void upload_excel() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		            cc.checkfile();
		            WebElement uploadexcel = driver.findElement(By.xpath(Loc.getProperty("uploadexcel")));
		            String uploadexcel_text = uploadexcel.getText();
		            if (uploadexcel.isDisplayed() && uploadexcel.isEnabled() ) {
		            	System.out.println(uploadexcel_text + " is Displayed and Enabled ");
		            } else {
		            	System.out.println(uploadexcel_text+ " is not Displayed and Enabled ");
		            }   		
			}	
			// click on upload excel and then check that upload excel file is downloaded 
			@Test
			 public void upload_excelclick() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		            cc.upload_excel();
		            WebElement downloadformat = driver.findElement(By.xpath(Loc.getProperty("uploadexcel")));
		            downloadformat.click();
			}
			// check close button on upload excel page is displayed 
			@Test
			 public void checkclose() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		            cc.upload_excelclick();
		            WebElement closebutton = driver.findElement(By.xpath(Loc.getProperty("excelclose")));
		            if (closebutton.isDisplayed()){
		            	System.out.println("Close Button is DIsplayed");
		            	}else {
		            		System.out.println("close Button is not displayed");
		            	}
		            }
			// check after click on close button navigated to back page from upload excel page
			@Test
			 public void clickclose() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		            cc.checkclose();
		            Thread.sleep(2000);
		            WebElement closebutton = driver.findElement(By.xpath(Loc.getProperty("excelclose")));
		            closebutton.click();
		            Thread.sleep(3000);
		            WebElement header = driver.findElement(By.xpath(Loc.getProperty("header1")));
		           String header1=header.getText();
		           if (header.isDisplayed()) {
		        	   System.out.println("after click on close button user navigated  " + header1);
		           }else {
		        	   System.out.println("close button is not clickable");
		           }
			 }
			// check choosefile is displayed on upload excel page
			@Test
			 public void choosefile() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		            cc.checkclose();
		            WebElement chosefile = driver.findElement(By.xpath(Loc.getProperty("choosefile")));
		            String chosefile2 = chosefile .getAttribute("type");
		            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",chosefile);
		           if(chosefile.isDisplayed()) {
		        	  
		        	  System.out.println("Choose File is Displayed an its type is:" + chosefile2); 
		           }else {
		        	   System.out.println(" not Displayed");
		           }
		           }
			// check after upload file its uploaded succesfully 
			@Test
			 public void uploadfile() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		            cc.choosefile();
		            WebElement chosefile = driver.findElement(By.xpath(Loc.getProperty("choosefile")));
		            String filepath = "C:\\Users\\dell\\Downloads\\collectioncenterCreationExcelFormat.xlsx";
		            chosefile.sendKeys(filepath);
		            WebElement upload = driver.findElement(By.xpath(Loc.getProperty("uploadbutton"))); 
		            upload.click();
		           WebElement sucess = driver.findElement(By.xpath(Loc.getProperty("uploadesuccesfully")));
		           String success =sucess.getText();
		           if(sucess.isDisplayed()) {
		        	   System.out.println("file " +success);   
		           } else {
		        	   System.out.println("somthing error in excel file ");  
		           } 
			}
			// check that data are same in excel log table and excel file 
		           @Test
		           public void table_data() throws InterruptedException { 
		               ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		               cc.uploadfile();
		               WebElement Actual1 = driver.findElement(By.xpath(Loc.getProperty("table_data")));
		               String Actual= Actual1.getText();
		               System.out.println("Uploaded data is :'" + Actual);
		              
		               String[] Expected = {"Upload Successfully","AjayKumar","1","1","Ajay","8009455678"};
		              String Expected_Data = String.join(" ", Expected);
		              System.out.println("Expected text: " + Expected_Data);
		              String normalizedActual = normalizeText(Actual);
		              String normalizedExpected = normalizeText(Expected_Data);
		               if(normalizedActual.equals(normalizedExpected)){
		             	  System.out.println("data are same in excel file and table ");
		               } else {
		             	  System.out.println("data are not same in excel file and table ");
		               }
		               
		           }
		           
		           private String normalizeText(String text) {
		               return text.trim().replaceAll("\\s+", " ");
			 }  
		           // fill  leave balank or incorrect data in excel file then check error is displaying in table or not 
		           @Test
		           public void uploadwrontdata_file() throws InterruptedException {// check after upload file its uploaded succesfully 
			            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
			            cc.choosefile();
			            Thread.sleep(2000);
			            WebElement chosefile = driver.findElement(By.xpath(Loc.getProperty("choosefile")));
			            String filepath = "C:\\Users\\dell\\Downloads\\collectioncenterCreationExcelFormat (2).xlsx";
			            chosefile.sendKeys(filepath);
			            Thread.sleep(2000);
			            WebElement upload = driver.findElement(By.xpath(Loc.getProperty("uploadbutton"))); 
			            upload.click();
			           WebElement mandatory_data = driver.findElement(By.xpath(Loc.getProperty("mandatory_data")));
			           String mandatory_data_text =mandatory_data.getText();
			           if(mandatory_data.isDisplayed()) {
			        	   System.out.println("file " +mandatory_data_text);   
			           } else {
			        	   System.out.println("file not uploaded ");  
			           } 
				}       
		           
		        // check after incorrect excel file upload then check alert message is displayed    
			@Test
			 public void uploadWrongfile() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		            cc.choosefile();
		            WebElement chosefile = driver.findElement(By.xpath(Loc.getProperty("choosefile")));
		            String filepath = "C:\\Users\\dell\\Downloads\\collectioncenterCreationExcelFormat (18).xlsx";
		            chosefile.sendKeys(filepath);
		            WebElement upload = driver.findElement(By.xpath(Loc.getProperty("uploadbutton"))); 
		            upload.click();
		           WebElement sucess = driver.findElement(By.xpath(Loc.getProperty("somthingwenwrong")));
		           String success =sucess.getText();
		           if(sucess.isDisplayed()) {
		        	   System.out.println(success);   
		           } else {
		        	   System.out.println("somthing error in excel file ");  
		           } 
			}
			@Test
			// after click on ok button alert message will be dismiss
			 public void ok_button() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		            cc.choosefile();
		            WebElement chosefile = driver.findElement(By.xpath(Loc.getProperty("choosefile")));
		            String filepath = "C:\\Users\\dell\\Downloads\\collectioncenterCreationExcelFormat (18).xlsx";
		            chosefile.sendKeys(filepath);
		            WebElement upload = driver.findElement(By.xpath(Loc.getProperty("uploadbutton"))); 
		            upload.click();
		           WebElement sucess = driver.findElement(By.xpath(Loc.getProperty("somthingwenwrong")));
		           String success =sucess.getText();
		           if(sucess.isDisplayed()) {
		        	   System.out.println(success);   
		           } else {
		        	   System.out.println("somthing error in excel file ");  
		           } 
		           WebElement ok = driver.findElement(By.xpath(Loc.getProperty("ok2")));
		           String ok_text= ok.getText();
		           ok.click(); 
		           System.out.println("after click on " +ok_text +"message is dismiss");
			 }

			
	           // check that if file is not selected and click on upload then please select file message displayed 
			@Test
		           public void file_notselected() throws InterruptedException { 
			            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
			            cc.choosefile();
			            WebElement upload = driver.findElement(By.xpath(Loc.getProperty("uploadbutton"))); 
			            upload.click();  
			            WebElement alert = driver.findElement(By.xpath(Loc.getProperty("selectfile")));
				           String alert1 =alert.getText();
				           if(alert.isDisplayed()) {
				        	   System.out.println("file not selected please "  + alert1);   
				           } else {
				        	   System.out.println("no alert displayed ");  
				           } 
				           WebElement ok = driver.findElement(By.xpath(Loc.getProperty("ok2")));
				           String ok_text= ok.getText();
				           ok.click(); 
				           System.out.println("after click on " +ok_text +  "   message is dismiss");
					 }
			
			// check header of excel log details page 
			 public void excellog() throws InterruptedException { 
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		           cc.uploadfile();
		           WebElement excellogheader = driver.findElement(By.xpath(Loc.getProperty("excellogdetials")));
		           String excellog_header = excellogheader.getText();
		           if(excellogheader.isDisplayed()) {
		        	   System.out.println("header :" + excellog_header + "' header is displayed");   
	           } else {
	        	   System.out.println( excellog_header+ " not displayed");  
	           }     
		 
		        	   
		           
 }
			// check back button is displayed on excel log details page and after click navigated to upload excels page 
			 @Test
			 public void excellog_backbutton() throws InterruptedException {
		            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
		           cc.excellog();
		           WebElement backbutton = driver.findElement(By.xpath(Loc.getProperty("backbutton")));
		          if (backbutton.isDisplayed())
		          {
		        	   System.out.println("back button is displayed ");   
	           } else {
	        	   System.out.println("back button is not displayed");  
	           }      
		          backbutton.click();
		          Thread.sleep(2000);
		          WebElement header = driver.findElement(By.xpath(Loc.getProperty("uploadexcelheader")));
		          String headerText = header.getText().trim();
		          String expectedHeaderText = "Uploaded Excels";
		          if (headerText.equals(expectedHeaderText)) {
		              System.out.println("after click on back button User successfully navigated to: " + headerText);
		          } else {
		              System.out.println("Back button is not clickable or header text did not match. Current header: " + headerText);
		          }  
          }
          // check that table displayed on uploaded excel page with column names 
       @Test
         public void checktable_column() throws InterruptedException {
        	 ColectionCenterMaster1 cc=new ColectionCenterMaster1(); 
        	 cc.excellog_backbutton();
        	 WebElement column_name = driver.findElement(By.xpath(Loc.getProperty("uploaded_excel_table")));
        	 String column_name_text = column_name.getText();
        	 if (column_name.isDisplayed()) {
        		 System.out.println(column_name_text   +  "'columns displayed in table");
        	 } else {
        		 System.out.println("column names are not displayed in table ");
        	 } 
       }
        
        	 // check the duplicate data in table name (uploaded on , uploaded by, excel name ,response)
  	      @Test
        	 public void checktabledata() throws InterruptedException{
        	 ColectionCenterMaster1 cc=  new ColectionCenterMaster1();
        	 cc.checktable_column();
        	 Set<String> seenRows = new HashSet<>();
        	 List<WebElement> tabledata = driver.findElements(By.xpath(Loc.getProperty("uploaded_excel_tabledata")));
        for (WebElement tabledata1: tabledata) {
        	
        		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  tabledata1);
        		Thread.sleep(1000);   
        		String fields= tabledata1.getText();  
        		  if (seenRows.add(fields)) {
                      System.out.println("Unique data displayed in table: " + fields);
                  } else {
                      System.out.println("Duplicate data found: " + fields);
                  }
        		 }
  	       }
  	      
  	       // check download button is available in particulare row data in uploaded excel table 
  	       @Test
  	       public void download_buttn() throws InterruptedException{
  	       ColectionCenterMaster1 cc = new ColectionCenterMaster1();
  	       cc.checktable_column();
  	       List<WebElement>download = driver.findElements(By.xpath(Loc.getProperty("download_button")));
  	       for ( WebElement download_button :download) {
  	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", download_button );
  	    	Thread.sleep(1000); 
  	    	String fields= download_button.getText();  
  	    	if(download_button.isDisplayed()){
  	    		System.out.println("Yes !"   + fields + " is available in every rows " );
  	    		}else {
  	    			System.out.println("No !"   + fields + " is not available in every rows " );	
  	    		}
  	    	}
  	    	
  	       }
  	       
  	      // check  the download button is enbaled in every rows in uploaded excel table 
  	       @Test
  	     public void download_enabled() throws InterruptedException{
    	       ColectionCenterMaster1 cc = new ColectionCenterMaster1();
    	       cc.download_buttn();
    	       List<WebElement>download_enabled = driver.findElements(By.xpath(Loc.getProperty("download_button")));
    	       for ( WebElement download_button :download_enabled) {
    	    	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", download_button );
    	    	Thread.sleep(1000); 
    	    	String fields= download_button .getText();  
    	    	if(download_button.isEnabled()){
    	    		System.out.println("Yes !"   + fields + " is Enabled in every rows " );
    	    		}else {
    	    			System.out.println("No !"   + fields + " is not Enabled in every rows " );	
    	    		}
    	    	}
    	    	
    	       }
  	       // to check after click on download button logdetails excel  downloaded succesfully 
  	       @Test
  	     public void download_log() throws InterruptedException{
  	       ColectionCenterMaster1 cc = new ColectionCenterMaster1();
  	       cc.checktable_column();
  	       List<WebElement> download_check = driver.findElements(By.xpath(Loc.getProperty("download_button")));
  	     Random random = new Random();
  	   for (int i = 0; i < 3; i++) {
  	   WebElement randomButton = download_check.get(random.nextInt(download_check.size()));
  	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomButton);
     randomButton.click();
     WebElement toastmessage = driver.findElement(By.xpath(Loc.getProperty("toastmessage")));
     String toast_text = toastmessage.getText();
     System.out.println("Clicked on the download button file '" + toast_text);
  	   }
  	       }
  	       
  	       // read downloaded excel log detail excel file 
  	       @Test
  	     public void readexcellogdetials() throws InterruptedException, EncryptedDocumentException, IOException { 
	            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
	           cc. download_log();
	           String filePath = "C:\\Users\\dell\\Downloads\\ccLogDetails (1).csv";
	           try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	               String line;
	               while ((line = br.readLine()) != null) {
	                   String[] values = line.split(",");
	                   for (String value : values) {
	                       System.out.print(value + "\t");
	                   }
	                   System.out.println();
	               }
	           }}
// click on particular row to check the rows is clickable or not in table 
  	       @Test
  	     public void click_rows() throws InterruptedException, EncryptedDocumentException, IOException { 
	            ColectionCenterMaster1 cc = new ColectionCenterMaster1();
	           cc.excellog_backbutton();
	           List<WebElement> click_rows = driver.findElements(By.xpath(Loc.getProperty("uploaded_excel_tabledata")));	
	           Random random = new Random();
	           WebElement randomButton = click_rows.get(random.nextInt(click_rows.size()));
	        	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomButton);
	           randomButton.click(); 
	           Thread.sleep(5000);
  	           String Actual_url = driver.getCurrentUrl();
	          String Expected_url = "https://www.oright.in/kamdhenu/master-organisation-creation-excel-log-details-list" ; 
	          if(Expected_url.equals(Actual_url)) {
	        	  System.out.println("user succesfully navigated to  Excel Log Details Page");
	          } else {
	        	  System.out.println("click not performed or some error occured");
	          }
            }
  	  // check filter is displayed on excel log details page 
            @Test
          public void filter() throws InterruptedException {
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
           cc.excellog();
           WebElement filter = driver.findElement(By.xpath(Loc.getProperty("filter")));
           String filter_text = filter.getAttribute("name");
           if (filter.isDisplayed())
           {
        	   System.out.println(filter_text+ "'is DIsplayed ");   
       } else {
    	   System.out.println(filter_text + "'is not DIsplayed ");  
       }
          }
         // click on filter check filter check response text 
      @Test
       public void response() throws InterruptedException {
           ColectionCenterMaster1 cc = new ColectionCenterMaster1();
            cc.filter();
            WebElement click_filter = driver.findElement(By.xpath(Loc.getProperty("filter_click")));
            click_filter.click();
            WebElement response= driver.findElement(By.xpath(Loc.getProperty("response")));
            String response_text = response.getText();
            if (response.isDisplayed())
            {
         	   System.out.println(response_text+ "'is DIsplayed ");   
        } else {
     	   System.out.println(response_text + "'is not DIsplayed ");  
        }
           }
   // click on filter check showall text is displayed
      @Test
      public void showall() throws InterruptedException {
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
           cc.response();
           WebElement showall= driver.findElement(By.xpath(Loc.getProperty("showall")));
           String showall_text = showall.getText();
           if (showall.isDisplayed())
           {
        	   System.out.println(showall_text+ "'is DIsplayed ");   
       } else {
    	   System.out.println(showall_text+ "'is not DIsplayed ");  
       }  ////// click on show all 
           showall.click();
          }
   // click on filter check showall/uploaded/notuploaded text on model page 
      @Test
      public void showalloption() throws InterruptedException {
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
           cc.showall();
           List<WebElement>showalllist= driver.findElements(By.xpath(Loc.getProperty("showallist")));
           for(WebElement alltext :showalllist) {
        	   System.out.println("Following field availale on model page:"+alltext.getText());
           }
      }
   // check that bydefault showall radio buttn is selected 
      @Test
           public void checkradio_selection() throws InterruptedException {
               ColectionCenterMaster1 cc = new ColectionCenterMaster1();
               cc.showalloption();
               Thread.sleep(2000);
               WebElement showall_radio = driver.findElement(By.xpath(Loc.getProperty("showall_radiocheck")));
              String showall_radio1= showall_radio.getAttribute("aria-checked");
              String showall_text = showall_radio.getText();
			if(showall_radio1 !=null &&(showall_radio1.equals("true"))) {
				System.out.println("Bydefault' "+showall_text+" 'is selected: '"+showall_radio1 );
			} else {
				System.out.println("Bydefault'"+ showall_text +"'is not selected: '"+showall_radio1 );
			}
           }
   // check that upload succesfully radio buttn is selected or not 
      @Test
      public void upload_succesfully() throws InterruptedException {
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
          cc.checkradio_selection();
          WebElement Upload_succesfully = driver.findElement(By.xpath(Loc.getProperty("upload_succesfully")));
          String Upload_succesfully1 = Upload_succesfully.getAttribute("aria-checked");
          String Upload_succesfully_text = Upload_succesfully.getText();
          if (Upload_succesfully1 == null || Upload_succesfully1.equals("true")) {
              System.out.println("By default '"+ Upload_succesfully_text +"' is selected: '"+Upload_succesfully1 );
          } else {
              System.out.println("By default '"+ Upload_succesfully_text +"' is not selected: '"+Upload_succesfully1 );
               } 
          Upload_succesfully.click();
          Thread.sleep(2000); 
          Upload_succesfully1 = Upload_succesfully.getAttribute("aria-checked");
          if (Upload_succesfully1 != null && Upload_succesfully1.equals("true")) {
              System.out.println("After Click '"+ Upload_succesfully_text +"' is selected: '"+Upload_succesfully1 );
          } else {
              System.out.println("After Click '"+ Upload_succesfully_text +"' is not selected: '"+Upload_succesfully1 );
          }
      }
   // check that not-uploaded radio buttn is selected or not 
      @Test
      public void not_uploaded() throws InterruptedException {
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
          cc.upload_succesfully();
          WebElement Not_Uploaded = driver.findElement(By.xpath(Loc.getProperty("not_uploaded")));
          String Not_Uploaded1 = Not_Uploaded.getAttribute("aria-checked");
          String Not_Uploaded_text = Not_Uploaded.getText();
          if (Not_Uploaded1 == null || Not_Uploaded1.equals("true")) {
              System.out.println("By default '"+ Not_Uploaded_text +"' is selected: '"+Not_Uploaded1 );
          } else {
              System.out.println("By default '"+ Not_Uploaded_text +"' is not selected: '"+Not_Uploaded1 );
               } 
          Not_Uploaded.click();
          Thread.sleep(2000); 
          Not_Uploaded1 = Not_Uploaded.getAttribute("aria-checked");
          if (Not_Uploaded1 != null && Not_Uploaded1.equals("true")) {
              System.out.println("After Click '"+ Not_Uploaded_text +"' is selected: '"+Not_Uploaded1 );
          } else {
              System.out.println("After Click '"+ Not_Uploaded_text +"' is not selected: '"+Not_Uploaded1 );
          }
      }
   // check that cancel button is enabled and displayed 
      @Test
      public void cancel_button1() throws InterruptedException {
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
          cc.not_uploaded();
          Thread.sleep(2000);
          WebElement cancel = driver.findElement(By.xpath(Loc.getProperty("cancel")));
          String cancel_button= cancel.getAttribute("type");
          String cancel_text= cancel.getText();
          if(cancel.isDisplayed() && cancel.isEnabled()) { 
        	  System.out.println(cancel_text  + cancel_button + "'is Displayed and Enabled");
          } else {
        	  System.out.println(cancel_text  + cancel_button + "'is not Displayed and not Enabled");
          }
      }  
   // check that ok button is enabled and displayed 
      @Test
      public void ok3() throws InterruptedException {
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
          cc.not_uploaded();
          WebElement ok3 = driver.findElement(By.xpath(Loc.getProperty("ok3")));
          String ok3_button= ok3.getAttribute("type");
          String ok3_text= ok3.getText();
          if(ok3.isDisplayed() && ok3.isEnabled()) { 
        	  System.out.println(ok3_text  +  ok3_button + "'is Displayed and Enabled");
          } else {
        	  System.out.println(ok3_text  +  ok3_button + "'is not Displayed and not Enabled");
          }
      }
   // check table column header on excel log details page 
      @Test
      public void table_header() throws InterruptedException {
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
          cc.uploadfile();
          WebElement table_column = driver.findElement(By.xpath(Loc.getProperty("excellog_table")));
          String table_header = table_column.getText();
          if(table_column.isDisplayed()){
        	  System.out.println("column name of the table is: " +    table_header);
          } else {
        	  System.out.println("table not displayed");
          }
      }
      // check after select uploaded succesfullly from model page uploaded succesfully selected and table data displayed 
      @Test
      public void Uploaded_succesfully_datatable() throws InterruptedException {
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
          cc.upload_succesfully();
          WebElement uploadSuccessfullyButton = driver.findElement(By.xpath(Loc.getProperty("upload_succesfully")));
          uploadSuccessfullyButton.click();
          WebElement okButton = driver.findElement(By.xpath(Loc.getProperty("ok3")));
          okButton.click();
          WebElement table = driver.findElement(By.xpath(Loc.getProperty("excellog_table")));
          String tableText = table.getText().trim();
          boolean isNoDataFoundDisplayed = false;
          try {
              WebElement noDataFoundElement = driver.findElement(By.xpath(Loc.getProperty("nodatafound")));
              isNoDataFoundDisplayed = noDataFoundElement.isDisplayed();
          } catch (Exception e) {
              }
          if (isNoDataFoundDisplayed) {
              System.out.println("After selecting upload successfully, 'No Data Found' message displayed.");
          } else if (tableText != null && !tableText.isEmpty()) {
              System.out.println("After selecting upload successfully, table data is displayed :'" +tableText);
          } else {
              System.out.println("Unexpected state. Neither table data nor 'No Data Found' message is displayed.");
          }
      }
      
      // check after select not uploaded succesfullly from model page not uploaded succesfully selected and table data displayed
      @Test
      public void NotUploaded_succesfully_datatable() throws InterruptedException {
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
          cc.uploadfile() ;
          WebElement filter = driver.findElement(By.xpath(Loc.getProperty("filter_click")));
          filter.click();
          WebElement showall = driver.findElement(By.xpath(Loc.getProperty("showall")));
          showall.click();
          WebElement uploadSuccessfullyButton = driver.findElement(By.xpath(Loc.getProperty("not_uploaded")));
          uploadSuccessfullyButton.click();
          WebElement okButton = driver.findElement(By.xpath(Loc.getProperty("ok3")));
          okButton.click();
          WebElement nodatafound = driver.findElement(By.xpath(Loc.getProperty("nodatafound")));
          String nodatafound_text= nodatafound.getText();
          String expected_data = "No Data Found" ;
         if(nodatafound_text.equals(expected_data)){
        	 System.out.println("After selecting the Not-Uploaded from model page no data found message displayed");
         } else {
        	 System.out.println("After selecting the Not-Uploaded from model page no data found message is not displayed");
         }
         
      }

    // check export credentials button displayed on cc master page 
      @Test
         public void export_credentials() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.ccheader();
      	WebElement Export_credentials = driver.findElement(By.xpath(Loc.getProperty("exportcredential")));
      	String Export_credentials_text = Export_credentials.getText();
      	if(Export_credentials.isDisplayed()){
      		System.out.println(Export_credentials_text + "' is Displayed");
      	} else {
      		System.out.println(Export_credentials_text + "' is not Displayed");
      	}
      
         }  
      // check export credentials button is enabled or disabled 
      @Test
      public void export_credentials_enable_or_disable() throws InterruptedException { 
        	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	cc.export_credentials();
        	WebElement Export_credentials = driver.findElement(By.xpath(Loc.getProperty("exportcredential")));
        	String Export_credentials_text = Export_credentials.getText();
        	if(Export_credentials.isEnabled()){
        		System.out.println(Export_credentials_text + "' is Enable");
        	} else {
        		System.out.println(Export_credentials_text + "' is Disable");
        	}
        
           }  
      // check location size and font type of export credentials
      @Test
      public void export_credentials_fontsize_location() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc. export_credentials_enable_or_disable() ;
      	WebElement Export_credentials = driver.findElement(By.xpath(Loc.getProperty("exportcredential")));
      	Point Export_credentials_text1 = Export_credentials.getLocation();
      	Dimension Export_credentials_text = Export_credentials.getSize();
      		System.out.println("Location of Export Credentials button is :'"+ Export_credentials_text1);
      		System.out.println("Size of Export Credentials button is :'"+ Export_credentials_text);
         } 
      // check that after click on credentials exported and toast message shown for seconds
      @Test
      public void export_credential_toast_message() throws InterruptedException { 
        	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	cc. export_credentials_fontsize_location() ;
        	WebElement Export_credentials = driver.findElement(By.xpath(Loc.getProperty("exportcredential")));
        	Export_credentials.click();
        	 WebElement toastmessage = driver.findElement(By.xpath(Loc.getProperty("toastmessage")));
        	 String toastmessage1 = toastmessage.getText();	
        	 if (toastmessage.isDisplayed()){
        	System.out.println("Export Credentials '" +toastmessage1);
           }  else {
        	   System.out.println("some error occured");
        	   }
        	 
           }
      // check downloaded file exist in system 
      @Test
      public void iffileexistinsystem() throws InterruptedException{
    	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
    	  cc.export_credentials_fontsize_location();
    	  String path = "C:/Users/dell/Downloads";
    		String fileName = "collectionCenterCredentials (1).csv";
    			     File file = new File(path + "\\" + fileName);
    			     if(file.exists()) {
    			    	 System.out.println("File Succesfully Downloaded and available in system");
    			     } else 
    			    	 System.out.println("File not available in system");
      }
      // read exprt credential which is dowloaded in system 
      @Test
      public void readexportcredentials() throws InterruptedException, EncryptedDocumentException, IOException { 
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
         cc.iffileexistinsystem();
         String filePath = "C:\\Users\\dell\\Downloads\\collectionCenterCredentials (1).csv";
         try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
             String line;
             while ((line = br.readLine()) != null) {
                 String[] values = line.split(",");
                 for (String value : values) {
                     System.out.print(value + "\t");
                 }
                 System.out.println(); 
             }
         }
      }
        
      // check duplicate data in downloaded export credentials
      @Test
      public void check_duplicacyinexportcredentials() throws InterruptedException, EncryptedDocumentException, IOException, CsvValidationException { 
          ColectionCenterMaster1 cc = new ColectionCenterMaster1();
         cc.readexportcredentials();
         String filePath = "C:\\Users\\dell\\Downloads\\collectionCenterCredentials (1).csv";
         Set<String> uniqValue = new HashSet<>();
         Set<String> duplicateValue = new HashSet<>();
         try(CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
             String[] nextRecord;
             while ((nextRecord = csvReader.readNext()) != null) {
                 String cellValue = nextRecord[0];
                 if (!uniqValue.add(cellValue)) {
                     duplicateValue.add(cellValue);
                 }
             }
             System.out.println("Duplicate Values:");
             for (String duplicate : duplicateValue) {
                 System.out.println(duplicate);
             }
             }
      } 
      // excel export button is displayed on displayed or not 
      @Test
      public void excel_export() throws InterruptedException { 
        	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
        	cc.ccheader();
        	WebElement excel_export = driver.findElement(By.xpath(Loc.getProperty("excelexport")));
        	String excel_export_text = excel_export.getText();
        	if(excel_export.isDisplayed()){
        		System.out.println(excel_export_text + "' is Displayed");
        	} else {
        		System.out.println(excel_export_text + "' is not Displayed");
        	}
        
           } 
      // check excel export button is enable or disable
      @Test
      public void excelexport_enable_or_disable() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.excel_export();
      	WebElement exce_export = driver.findElement(By.xpath(Loc.getProperty("excelexport")));
      	String Export_credentials_text = exce_export.getText();
      	if(exce_export.isEnabled()){
      		System.out.println(Export_credentials_text + "' is Enable");
      	} else {
      		System.out.println(Export_credentials_text + "' is Disable");
      	}
      
         }  
    // check location size and font type of excel export button
    @Test
    public void export_excel_fontsize_location() throws InterruptedException { 
    	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
    	cc. excelexport_enable_or_disable() ;
    	WebElement excel_export = driver.findElement(By.xpath(Loc.getProperty("excelexport")));
    	Point Export_credentials_text1 = excel_export.getLocation();
    	Dimension Export_credentials_text = excel_export.getSize();
    		System.out.println("Location of Export Credentials button is :'"+ Export_credentials_text1);
    		System.out.println("Size of Export Credentials button is :'"+ Export_credentials_text);
       } 
    // check that after click on excel export and toast message shown for seconds
    @Test
    public void excel_export_toast_message() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.export_excel_fontsize_location() ;
      	WebElement excel_export = driver.findElement(By.xpath(Loc.getProperty("excelexport")));
      	excel_export.click();
      	 WebElement toastmessage = driver.findElement(By.xpath(Loc.getProperty("toastmessage")));
      	 String toastmessage1 = toastmessage.getText();	
      	 if (toastmessage.isDisplayed()){
      	System.out.println("Excel Export '" +toastmessage1);
         }  else {
      	   System.out.println("some error occured");
      	   }
      	 
         }
    // check downloaded excel export file exist in system 
    @Test
    public void ifexcelexportfileexixt_system() throws InterruptedException{
  	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
  	  cc.excel_export_toast_message();
  	  String path = "C:/Users/dell/Downloads";
  		String fileName = "collectioncentersExcelExport.xlsx";
  			     File file = new File(path + "\\" + fileName);
  			     if(file.exists()) {
  			    	 System.out.println("File Succesfully Downloaded and available in system");
  			     } else 
  			    	 System.out.println("File not available in system");
    }
    // read excel export which is dowloaded in system 
    @Test
    public void readexcelexport() throws InterruptedException, EncryptedDocumentException, IOException { 
        ColectionCenterMaster1 cc = new ColectionCenterMaster1();
       cc.ifexcelexportfileexixt_system();
       String filePath = "C:\\Users\\dell\\Downloads\\collectioncentersExcelExport (1).xlsx";
       try (FileInputStream fis = new FileInputStream(filePath);
               Workbook workbook = new XSSFWorkbook(fis)) {
    	   Sheet sheet = workbook.getSheetAt(0); // Get first sheet
           for (Row row : sheet) {
               for (org.apache.poi.ss.usermodel.Cell cell : row) {
                   System.out.print(cell.toString() +  "   " );
               }
               System.out.println();
           }
       }
       } 
    // check duplicate data in export excel file 
    @Test
    public void duplicate_excelexport() throws InterruptedException, EncryptedDocumentException, IOException { 
        ColectionCenterMaster1 cc = new ColectionCenterMaster1();
       cc.ifexcelexportfileexixt_system();
       String filePath = "C:\\Users\\dell\\Downloads\\collectioncentersExcelExport (1).xlsx";
       HashSet<String> dataSet = new HashSet<>();
       HashSet<String> duplicates = new HashSet<>();
       try (FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis)) {
           Sheet sheet = workbook.getSheetAt(0); 
           for (Row row : sheet) {
               for (org.apache.poi.ss.usermodel.Cell cell : row) {
                   String cellValue = cell.toString();
                   if (!dataSet.add(cellValue)) {
                       duplicates.add(cellValue); 
                   }
               }
           }
       if (duplicates.isEmpty()) {
           System.out.println("No duplicates found.");
       } else {
           System.out.println("Duplicate values found:");
           for (String duplicate : duplicates) {
               System.out.println(duplicate);
           }
       }
   }
}
// check log details button are displayed
    @Test
    public void Logs() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.ccheader();
      	WebElement Logs = driver.findElement(By.xpath(Loc.getProperty("log")));
      	String Export_credentials_text = Logs.getText();
      	if(Logs.isDisplayed()){
      		System.out.println(Export_credentials_text + "' is Displayed");
      	} else {
      		System.out.println(Export_credentials_text + "' is not Displayed");
      	}
      
        }  
      // Logs is enable or disable 
    @Test
    public void Log_enable_or_disable() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.Logs();
      	WebElement log = driver.findElement(By.xpath(Loc.getProperty("log")));
      	String Export_credentials_text = log.getText();
      	if(log.isEnabled()){
      		System.out.println(Export_credentials_text + "' is Enable");
      	} else {
      		System.out.println(Export_credentials_text + "' is Disable");
      	}
      
         }
       // check that after click on logs user navigated to uploaded excel page
    @Test
    public void Log_navigated_to_uploadedexcel() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc. Log_enable_or_disable();
      	WebElement log = driver.findElement(By.xpath(Loc.getProperty("log")));
        log.click();
        Thread.sleep(2000);
        String expected= "Uploaded Excels" ;
        WebElement Actual = driver.findElement(By.xpath(Loc.getProperty("uploadedexcels")));
        String Actual_Text = Actual.getText();
        if(expected.equals(Actual_Text)){
        	System.out.println("User Correctly Navigated to '" +  Actual_Text);
        	} else {
        		System.out.println("Logs is not clicked");
        	}
        }
        // check bydefault chilling center selected and displayed 
    @Test
    public void selectchc() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.ccheader();
      	WebElement Selected_chc = driver.findElement(By.xpath(Loc.getProperty("selectchc")));
      	String Selected_chc_text = Selected_chc.getText();
      	if(Selected_chc.isDisplayed()){
      		System.out.println(Selected_chc_text + "' is Selected & Displayed");
      	} else {
      		System.out.println(Selected_chc_text + "' is not Displayed");
      	}
      
         }  
    // check chc list ope after click on selected chc from filter 
    @Test
    public void selectchc_list() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.selectchc();
      	Thread.sleep(2000);
      	WebElement Selected_chc = driver.findElement(By.xpath(Loc.getProperty("selectbutton")));
      	 Selected_chc.click();
      	 System.out.println("after click on chc filter chc list opend");
}
    // check that bydefault selected chc checked in chc list 
    @Test
    public void check_selected_chc_checked() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.selectchc_list();
      	WebElement Actual = driver.findElement(By.xpath(Loc.getProperty("checked")));
      	String isChecked = Actual.getAttribute("aria-label");
      if(isChecked !=null) {
    	  System.out.println("bydefault First chilling center selected");
      } else {
    	  System.out.println(" chilling center is not selected");
      }     	
}
    // check after selecting chilling center from list is selected and displayed on , if data not available on that chillingcneter then No Data Found message displayed.
    @Test
    public void click_other_chc() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.check_selected_chc_checked();
      	Thread.sleep(2000);
      	WebElement click_chc = driver.findElement(By.xpath(Loc.getProperty("click_chc_list")));
      	click_chc.click();
      	String expected = "0003-Appuchilling3";
      	String Actual = click_chc.getText();
      if(expected.equals(Actual)) {
    	  System.out.println("same chilling center displayed on Collection center Master page after selecting from chilling center list  ");
      } else {
    	  System.out.println(" chilling center is not clicked");
      } 
      Thread.sleep(2000);
      try {
          WebElement noDataFound = driver.findElement(By.xpath(Loc.getProperty("nodatafound1")));
          String noDataFoundText = noDataFound.getText().trim();
          if (noDataFound.isDisplayed()) {
              System.out.println("After selecting Chilling Center from list'" + noDataFoundText + "' is displayed.");
          }
      } catch (NoSuchElementException e) {
          System.out.println("No Data Found element is not displayed.");
      }  catch (Exception e) {
        e.printStackTrace();
    }finally {
    }
    }
  // check table data displayed or not after selecting chilling center from the list 
    @Test
    public void check_tabledata() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.check_selected_chc_checked(); 
    WebElement clickChc = driver.findElement(By.xpath(Loc.getProperty("click_chc_list1")));
    clickChc.click();
   Thread.sleep(2000);
    try {
        WebElement table = driver.findElement(By.xpath(Loc.getProperty("tabledata")));

        if (table.isDisplayed()) {
            System.out.println("After Selecting Chilling center from list Table is displayed.");
        }
    } catch (NoSuchElementException e) {
        System.out.println("Table is not displayed.");
    }  catch (Exception e) {
        e.printStackTrace();
    }finally {
    }
    }
    // check that the data in table changed after selecting chilling center from list 
    @Test
    public void check_data_intable() throws InterruptedException{
    	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
    	cc.check_selected_chc_checked();
        WebElement clickChc = driver.findElement(By.xpath(Loc.getProperty("click_chc_list1")));
        clickChc.click();
       Thread.sleep(2000);
       WebElement tabledata = driver.findElement(By.xpath(Loc.getProperty("tabledata_varification_after_chc_Selection")));
       String tabledata_text= tabledata.getText();
       System.out.println(tabledata_text + "' is Displayed as per Chilling center selection");
       
    }
    
    // check header of chc list 
    @Test
    public void list_header() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.selectchc_list();
      	Thread.sleep(2000);
      	WebElement list_header = driver.findElement(By.xpath(Loc.getProperty("listheader")));
      	String list_header_text = list_header.getText();
      	if(list_header.isDisplayed()) {
      	System.out.println("Header of list is '" +list_header_text);
    } else {
    	System.out.println("Header is not Displayed");
    }
    	}
    // check cancel button is available or not 
    @Test
    public void cancel() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.list_header();
      	Thread.sleep(2000);
      	WebElement cancel = driver.findElement(By.xpath(Loc.getProperty("cancel3")));
      	String cancel_text = cancel.getText();
      	if(cancel.isDisplayed()) {
      		System.out.println(cancel_text+"' is displayed");
      	}else {
      		System.out.println(cancel_text +"'not displayed");
      	}
    }
    // click on cancel button from chc list page 
    @Test
    public void click_cancel() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.cancel();
      	Thread.sleep(2000);
      	WebElement cancel = driver.findElement(By.xpath(Loc.getProperty("cancel4")));
      	cancel = driver.findElement(By.xpath(Loc.getProperty("cancel4")));
      	cancel.click();
      	System.out.println("click perfrom succesfully on cancel button ");
    }
    // check searchbar is available on chc list page 
    @Test
    public void searchbar() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.check_selected_chc_checked();
      	WebElement searchbar= driver.findElement(By.xpath(Loc.getProperty("searchbar")));
      	if(searchbar.isDisplayed()) {
      		System.out.println("Yes ! Searchbar is displayed");
      	} else {
      		System.out.println("No ! Searchbar is not Displayed");
      	}  
   }
    // check that list chc filter according to  search data from searchbar
    @Test
    public void seach_chc_fromlist() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.searchbar();
      	WebElement searchbar= driver.findElement(By.xpath(Loc.getProperty("search_input")));
      	String[] searchdata = {"0001","appuchilling4","appuchilling5","fbdfbdd"};
      	String[] Actual_data = {"0001","appuchilling4","appuchilling5"};
      	 List<String> actualDataList = Arrays.asList(Actual_data);
      	for (String data :searchdata) {
      	searchbar.sendKeys(data);
      	if(actualDataList.contains(data)) {
		System.out.println(data + "' available in list");
		}else {
		System.out.println(data +"' no data found");
		}
      	Thread.sleep(2000);
      	searchbar.clear();
      	}
    	WebElement search_closebutton= driver.findElement(By.xpath(Loc.getProperty("close_search")));
    	search_closebutton.click();
    	System.out.println("close button clicked succesfully !");
    	
}
    // check duplicate chilling center in list 
    @Test
       public void duplicate_list() throws InterruptedException { 
      	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
      	cc.searchbar();
      	List<WebElement> ChillingCenter_List = driver.findElements(By.xpath(Loc.getProperty("chillingcenter_list")));
      	 checkForDuplicates(ChillingCenter_List);
    }
public  void checkForDuplicates(List<WebElement> elements) {
    Set<String> unique = new HashSet<>();
    boolean hasDuplicates = false;
    for (WebElement element : elements) {
        String text = element.getText().trim();
        if (!unique.add(text)) {
            System.out.println("Duplicate found: " + text);
            hasDuplicates = true;
        }
    }
    if (!hasDuplicates) {
        System.out.println("No duplicates found in the list.");
    }
} 
// check column name in table 
@Test
public void check_column_name() throws InterruptedException { 
  	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
  	cc.ccheader();
  	List<WebElement> column = driver.findElements(By.xpath(Loc.getProperty("table_columns")));
  	for(WebElement columns :column) {
  	String column_text = ((WebElement) columns).getText();
  	System.out.println("Following Column available in table '" +column_text);
  	}
}
// check fields in table available or not
@Test
public void check_fields_table() throws InterruptedException { 
  	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
  	cc.ccheader();
  	List<WebElement> column = driver.findElements(By.xpath(Loc.getProperty("table_fields")));
  	for(WebElement columns :column) {
  	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", columns );
  	String column_text = ((WebElement) columns).getText();
  	System.out.println("Following fields available in every rows '" +column_text);
  	}
}
// check data duplicacy in table 
@Test
   public void check_duplicatedat_table() throws InterruptedException { 
  	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
  	cc.ccheader();
  	List<WebElement> table = driver.findElements(By.xpath(Loc.getProperty("tabledata")));
  	checkForDuplicate(table);
}
public  void checkForDuplicate(List<WebElement> elements) {
Set<String> unique = new HashSet<>();
boolean hasDuplicate = false;
for (WebElement element : elements) {
    String text = element.getText().trim();
    if (!unique.add(text)) {
        System.out.println("Duplicate found: " + text);
        hasDuplicate = true;
    }
}
if (!hasDuplicate) {
    System.out.println("No duplicates found in the list.");

   }
}
// check toggle button is available in particular row
@Test
public void check_toggle() throws InterruptedException { 
  	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
  	cc.ccheader();
List<WebElement>toggle_button = driver.findElements(By.xpath(Loc.getProperty("Toggle_button")));
  for ( WebElement toggle_buttons :toggle_button) {
   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", toggle_buttons );
   if(toggle_buttons.isDisplayed()) {
	   System.out.println("Toggle button is Displayed in Every row");
   } else {
	   System.out.println("Toggle button is not Displayed in Every row");
   }
}
}
// check that bydefault toggle button is active
@Test
public void toggle_active() throws InterruptedException { 
  	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
  	cc.ccheader();
  	WebElement toggle_active = driver.findElement(By.xpath(Loc.getProperty("Toggle_button")));
  	String toggle_activity = toggle_active.getAttribute("value"); {
  	if(toggle_active.equals(toggle_activity)){
  		System.out.println("bydefault toggle button is :'" +toggle_activity);
  	} else {
  	     System.out.println("Bydefault toggle button is :'" + toggle_activity);
  	}
  	}
}
// check that after click on toggle_button alert message model open 
  @Test
    public void toggle_alert_model() throws InterruptedException { 
  	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
  	cc.toggle_active();
	WebElement toggle_active = driver.findElement(By.xpath(Loc.getProperty("Toggle_button")));
	toggle_active.click();
  	System.out.println("click performed on toggle button");
	Thread.sleep(2000);
  	WebElement toggle_alert_model = driver.findElement(By.xpath(Loc.getProperty("alert_model")));
  	if (toggle_alert_model.isDisplayed()) {
  		System.out.println("Alert Model is Displayed");
  	} else {
  		System.out.println("Alert Model is not Displayed");
  	}
}
  // check that "Are YOu Sure" text contain on model page 
  @Test
  public void toggle_alert_model_text() throws InterruptedException { 
	  	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  	cc.toggle_alert_model();
	  	WebElement alert_model_text= driver.findElement(By.xpath(Loc.getProperty("alert_model_text")));
	  	String alert_model_gettext = alert_model_text.getText();
	  	if(alert_model_text.isDisplayed()) {
	  		System.out.println("text contain on model :'" +alert_model_gettext);
	  	}else {
	  		System.out.println("no text contains");
	  		}
	  	}
  // check that cancel is availalable on model page 
  @Test
  public void cancel_() throws InterruptedException{  
 	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
 	 cc.toggle_alert_model_text();
 	WebElement button= driver.findElement(By.xpath(Loc.getProperty("cancel1")));
     	String btn =button.getText();
     	if (button.isDisplayed())
     	{
     	System.out.println( btn+"'button is Displayed");
     	}else {
     		System.out.println(btn +"'button is not displayed");           	
     	}
 	 }
//check the cancel button is clickable or not 
  @Test
 public void click_cancel1() throws InterruptedException { 
 	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
 	 cc.cancel_();
 	 WebElement button= driver.findElement(By.xpath(Loc.getProperty("cancel1")));
 	 button.click();
 	if(!button.isDisplayed()) {
        System.out.println("Alert modal is still displayed.");
    } else {
        System.out.println("clicked on cancel button Alert modal is hidden ");
    }
 	
  }
  // check that ok is availalable on model page 
  @Test
  public void ok() throws InterruptedException{  
 	 ColectionCenterMaster1 cc= new ColectionCenterMaster1();
 	 cc.toggle_alert_model_text();
 	WebElement button= driver.findElement(By.xpath(Loc.getProperty("yes")));
     	String btn =button.getText();
     	if (button.isDisplayed() && button.isEnabled())
     	{
     	System.out.println( btn+"'button is Displayed & Enabled");
     	}else {
     		System.out.println(btn +"'button is not displayed");           	
     	}
 	 }
//check the ok button is clickable and confirmation message displayed 
  @Test
 public void succes_message() throws InterruptedException {
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
 	 cc.ok();
 	 WebElement button= driver.findElement(By.xpath(Loc.getProperty("yes")));
 	 button.click();
 	 System.out.println("click performed on Yes Button");
 	 if(button.isDisplayed()) {
 	     System.out.println("Alert modal is still displayed.");
 	    } else {
 	        System.out.println("clicked on Yes button Alert modal is hidden ");
 	    }
 	 Thread.sleep(2000);
 	 WebElement succesful_message = driver.findElement(By.xpath(Loc.getProperty("Succesfull_message")));
 	 String succesful_message_text=succesful_message.getText();
 	 if (succesful_message.isDisplayed()) {
 		System.out.println(succesful_message_text);
 	 } else {
 		 System.out.println("Message not displayed or click not performed");
 		 }
 	 WebElement ok = driver.findElement(By.xpath(Loc.getProperty("ok1")));
 	 String ok_text= ok.getText();
 	 if(ok.isDisplayed() && ok.isEnabled()) {
 		 System.out.println( ok_text+ "' is Displayed and Enabled");
 	 } else {
 		 System.out.println(ok_text + "' is not Displayed") ; 
 	 }
 	 Thread.sleep(2000);
 	 ok.click();
 	 System.out.println("After click on ok model dismissed");
  }
  // after click on togggle button check that toggle is enable or disable 
  @Test
  public void toggle_status() throws InterruptedException {
  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
  cc.succes_message();
  WebElement Ststus_toggle1= driver.findElement(By.xpath(Loc.getProperty("toggle_off")));
  String Ststus_toggle = Ststus_toggle1.getAttribute("aria-checked");{
  System.out.println("After click on toggle button Status is: '" +Ststus_toggle );
         }
     }
  // check edit button is available in every rows
  @Test
  public void check_edit_button() throws InterruptedException { 
	  	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  	cc.ccheader();
	  	List<WebElement> edit =driver.findElements(By.xpath(Loc.getProperty("edit")));
	  	for(WebElement edits :edit) {
	  	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",edits );
         if(edits.isDisplayed()) {
        	 System.out.println("Edit button is Displayed in every Rows");
         } else {
        	 System.out.println("edit button is not Displayed");
         }
	  	}
     }
  // check that edit button is clickable in every rows 
  @Test
  public void click_edit_button() throws InterruptedException { 
	  	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  	cc.ccheader();
	  	List<WebElement> edit =driver.findElements(By.xpath(Loc.getProperty("click_edit")));
	  	for(WebElement edits :edit) {
	  	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", edits);
	  	 Thread.sleep(2000);
	  	((WebElement) edits).click();
	  	System.out.println("click perfromed succesfully on edit button");   
    }
  }
  // check headers displayed on edit forms
  @Test
  public void click_edit_header() throws InterruptedException { 
	  	ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  	cc.click_edit_button();
	  	WebElement edit_form_header = driver.findElement(By.xpath(Loc.getProperty("edit_form_header")));
	  	String header_text =edit_form_header.getText();
	  	if(edit_form_header.isDisplayed()) {
	  	System.out.println(header_text+ "' is Displayed on header");
	  	} else {
	  		System.out.println(header_text+ "' is not Displayed on header");
	  	}
     }
   // check back buttton available on edit form
  @Test
  public void back_button() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.click_edit_button();
	  Thread.sleep(2000);
	  WebElement back_button = driver.findElement(By.xpath(Loc.getProperty("beckbutton_edit")));
	  if(back_button.isDisplayed()) {
		  System.out.println("Back button is Displayed");
	  } else {
		  System.out.println("Back button is not Displayed");
	  }
  }
  // check after click on back button user navigated to Collection Center Master page 
  @Test
      public void back_button_click() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.back_button();
	  Thread.sleep(2000);
	  WebElement back_button = driver.findElement(By.xpath(Loc.getProperty("beckbutton_edit")));
	  back_button.click();
	  Thread.sleep(2000);
	  String expected = "https://www.oright.in/kamdhenu/master-organisation-creation";
	  Thread.sleep(1000);
      String Actual = driver.getCurrentUrl();
	  if(expected.equals(Actual)) {
		  System.out.println(" After Click on Back button User Correctly Navigated to back page");
	  } else {
		  System.out.println("click not performed");
	  }
      }	 
  // check status is displayed(active/inactive)  on form 
  @Test
  public void status() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.back_button();
	  WebElement status = driver.findElement(By.xpath(Loc.getProperty("Status")));
	  String status_text = status.getText();
	  if(status.isDisplayed()) {
		  System.out.println(status_text+ "'is Displayed");
	  } else {
		  System.out.println(status_text+ "'is not Displayed");
	  }
  }
  // check status(active) is same on edit form and table 
  @Test
  public void status_active() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.status();
	  WebElement status_active = driver.findElement(By.xpath(Loc.getProperty("activity")));
	  String status_text = status_active.getText();
	  String Expected = "Active" ;
	  if(Expected.equals(status_text)) {
		  System.out.println("Status of selected data is '" + status_text + "' on form edit forms and table ");
	  } else {
		  System.out.println("Status is not same in edit forms and table");
	  } 
  }
  // check status(inactive) is same on edit form and table 
  @Test
  public void status_activity() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.ccheader();
	  WebElement edit2 =driver.findElement(By.xpath(Loc.getProperty("click_edit2")));
	  edit2.click();
	  Thread.sleep(2000);
	  WebElement status_inactive = driver.findElement(By.xpath(Loc.getProperty("activity")));
	  String status_text = status_inactive.getText();
	  String Expected = "Inactive" ;
	  if(Expected.equals(status_text)) {
		  System.out.println("Status of selected data is '" + status_text + "' on form edit forms and table ");
	  } else if(status_text.isEmpty()){
		  System.out.println("Status is blank on edit form");
	  } else {
		  System.out.println("Status of selected data is not same ");
	  }
  }
  /// after click on edit button data are same in table and edit form
  @Test
     public void match_data_table_to_editform() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.click_edit_button() ;
	  Thread.sleep(2000);
	  List<WebElement> formData = driver.findElements(By.xpath(Loc.getProperty("edit_form_data")));
      String[] expectedValues = {
          "9098", "Baba Col", "Gurugram", "Manager", "9878786764",
          "Union Bank of India", "Gurugram", "UBIN0679583", "53658943759389448759378",
          ",Kzdlksz", "Aadhar Card", "59423202970"
      };
      int expectedIndex = 0;
      for (int i = 0; i < formData.size(); i++) {
          if (i == 2 || i == 3 || i == 15) {
              continue; // Skip index 2, 3, and 15
          }
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", formData.get(i));
          String formdata = formData.get(i).getAttribute("value");
          if (formdata.equals(expectedValues[expectedIndex])) {
              System.out.println("Element at index " + i + " is the same in both table and form: " + formdata);
          } else {
              System.out.println("Element at index " + i + " does not match. Form: " + formdata + ", Expected: " + expectedValues[expectedIndex]);
          }
          expectedIndex++;
      }    
  }	
  // check noneditable element in edit form 
  @Test
  public void check_readonly_form() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.click_edit_button() ;
	  Thread.sleep(2000);
  List<WebElement> readonly = driver.findElements(By.xpath(Loc.getProperty("noneditable_element")));{
  for (int i= 0;i<readonly.size();i++) {
	   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", readonly.get(i));
	   String readonly_field = readonly.get(i).getAttribute("readonly");
	   String formdata = readonly.get(i).getAttribute("value");
	   System.out.println(formdata + "'is noneditable'" +readonly_field);
  }
}
}
  // data is cleard and new data updated in edit forms 
  @Test
  public void edit_data() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.click_edit_button() ;
	  Thread.sleep(2000);
	  List<WebElement> edit_data = driver.findElements(By.xpath(Loc.getProperty("edit_data")));
	  String[] expectedValues = {
			  "New_Collection","0010", "Gurugram",  "9878724764",
	          "Punjab Nation Bank", "Noida", "PUNB0248000", "53658942959389448759378",
	          "Ajay_Collection_Wala", "Aadhar Card", "59423890970"
	      };
	  int expectedIndex = 0;
      for (int i = 0; i < edit_data.size(); i++) {
          if (i == 0 ||i==2|| i == 5 || i == 14) {
              continue; 
          }
        Thread.sleep(2000);
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", edit_data.get(i));
          WebElement field = edit_data.get(i);
          if (field.isEnabled()) {
              field.clear(); 
              field.sendKeys(expectedValues[expectedIndex]);
              System.out.println("Updated field at index " + i + " with value: " + expectedValues[expectedIndex]);
          } else {
              System.out.println("Field at index " + i + " is not editable.");
          }
          expectedIndex++;
          WebElement update = driver.findElement(By.xpath(Loc.getProperty("Update_button")));
          update.click();
          Thread.sleep(2000);
      }
  }
      //  check that after click on update button user navigated to main dashboard and data are same in form and table 
  @Test
      public void data_compare_form_and_table() throws InterruptedException{
    	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
    	  cc.check_fields_table();
    	  Thread.sleep(3000);
          WebElement search = driver.findElement(By.xpath(Loc.getProperty("searchinput")));
          search.sendKeys("9098");
          List<WebElement> table_data = driver.findElements(By.xpath(Loc.getProperty("table_updated_data")));
        for (WebElement table :table_data) {
        	String data = table.getText();
        	if (table.isDisplayed()) {
        		System.out.println("Updated data displayed in table'" +data );
        	} else {
        		System.out.println("update data not diaplayed in table ");
        	}
        		
        }
          
  }
  // update button is displayed on edit form 
  @Test
  public void Update_displayed() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.click_edit_button();
	  WebElement update_displayed= driver.findElement(By.xpath(Loc.getProperty("Update_button")));
	  String update_text = update_displayed.getText();
	  if (update_displayed.isDisplayed() && update_displayed.isEnabled() ) {
		  System.out.println(update_text+ "' button is displayed on Edit collection form and Enabled bydefault");
	  }else {
		  System.out.println(update_text+ "'Update button is not displayed on Edit collection form");
	  }
}
  // check that after click on update button toast message shown for few seconds 
  @Test
  public void Update_click() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.Update_displayed();
	WebElement clickUpdate = driver.findElement(By.xpath(Loc.getProperty("Update_button")));
	clickUpdate.click();
	WebElement toastMessage = driver.findElement(By.xpath(Loc.getProperty("update_succesfully_toast")));
	String toastMessageText= toastMessage.getText();
	System.out.println("After Click On Update Button ' "+ toastMessageText);
}
  // cancel button is displayed on edit collection center form 
  @Test
  public void checkCacel() throws InterruptedException{
	  ColectionCenterMaster1 cc= new ColectionCenterMaster1();
	  cc.click_edit_button();
	  WebElement checkCancel = driver.findElement(By.xpath(Loc.getProperty("cancelButton")));
	  String getCancelText = checkCancel.getText();
	  if(checkCancel.isDisplayed()&& checkCancel.isEnabled()) {
		  System.out.println(getCancelText+ "' is displayed and enabled on collection cetner edit form ");
	  } else {
		  System.out.println(getCancelText+ "' is not displayed and enabled on collection cetner edit form ");
	  }
	  
  }
  // check that after click on cancel button user navigated to back page
  @Test
  public void clickCancel() throws InterruptedException{
	  ColectionCenterMaster1 cc = new ColectionCenterMaster1();
	  cc.click_edit_button();
	  WebElement checkCancel = driver.findElement(By.xpath(Loc.getProperty("cancelButton")));
	  checkCancel.click();
	  String expected = "Collection Center Master";
	  WebElement backPage = driver.findElement(By.xpath(Loc.getProperty("header_mainpage")));
	  String Actual= backPage.getText();
	  if(expected.equals(Actual)){
		  System.out.println("User Succesfully Navigated to '" + Actual );
	  } else {
		  System.out.println("Click not Performed");
	  }
	  
	  
  }
  
}
