package Testcase;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseTest;

public class UserMaster extends BaseTest{
	private static final boolean WebElement = false;
	// user is able to login successfully valid user name password and customer code 
	@Test
	public void checkLogin()throws InterruptedException{
    driver.manage().window().maximize();
    WebElement getStarted = driver.findElement(By.xpath(Loc2.getProperty("getstarted")));
    getStarted.click();
    Thread.sleep(2000);
    WebElement Email = driver.findElement(By.xpath(Loc2.getProperty("email")));
    Email.sendKeys("apdf1");
    Thread.sleep(1000);	
    WebElement Next = driver.findElement(By.xpath(Loc2.getProperty("next")));
    Next.click();
    Thread.sleep(2000);	
   WebElement Password = driver.findElement(By.xpath(Loc2.getProperty("password")));
   Password.sendKeys("apdf1");
    Thread.sleep(1000);
 	WebElement customerCode = driver.findElement(By.xpath(Loc2.getProperty("customercode")));
 	customerCode.sendKeys("test");
 	Thread.sleep(1000);	 
 	WebElement logIn = driver.findElement(By.xpath(Loc2.getProperty("login")));
 	logIn.click();
 	Thread.sleep(4000); 	
	}
	// simple login 
	@Test
	public  void SimpleLogin()throws InterruptedException{
	    driver.manage().window().maximize();
	    WebElement getStarted = driver.findElement(By.xpath(Loc2.getProperty("getstarted")));
	    getStarted.click();
	    Thread.sleep(2000);
	    WebElement Email = driver.findElement(By.xpath(Loc2.getProperty("email")));
	    Email.sendKeys("apdf1");
	    Thread.sleep(1000);	
	    WebElement Next = driver.findElement(By.xpath(Loc2.getProperty("next")));
	    Next.click();
	    Thread.sleep(1000);	
	   WebElement Password = driver.findElement(By.xpath(Loc2.getProperty("password")));
	   Password.sendKeys("apdf1");
	    Thread.sleep(1000);
	 	WebElement customerCode = driver.findElement(By.xpath(Loc2.getProperty("customercode")));
	 	customerCode.sendKeys("test");
	 	Thread.sleep(1000);	 
	 	WebElement logIn = driver.findElement(By.xpath(Loc2.getProperty("login")));
	 	logIn.click();
	 	Thread.sleep(4000); 
	 	WebElement clickMenu = driver.findElement(By.xpath(Loc2.getProperty("menu")));
		clickMenu.click();
		Thread.sleep(2000);
		WebElement masterDisplayed = driver.findElement(By.xpath(Loc2.getProperty("master")));
		masterDisplayed.click();
		Thread.sleep(2000);
		WebElement userMaster = driver.findElement(By.xpath(Loc2.getProperty("menuUserMaster")));
		userMaster.click();
		}
	// click menu button from dairy dashboard 
	@Test
	public  void clickMenu() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.checkLogin();
		WebElement clickMenu = driver.findElement(By.xpath(Loc2.getProperty("menu")));
		clickMenu.click();
		if(clickMenu.isDisplayed()) {
			System.out.println("Menu button is available & clicked succesfully");
		}else{
			System.out.println("Menu Button is not available");
		}
}
	// check master is available in menu and after click on master list expend and user master is available in list
	@Test
	public  void masterDisplayedInMenu() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.clickMenu();
		WebElement masterDisplayed = driver.findElement(By.xpath(Loc2.getProperty("master")));
		if(masterDisplayed.isDisplayed()) {
			System.out.println("Master Displayed in Menu");
		} else {
			System.out.println("Master Not Displayed in Menu");
		}
		masterDisplayed.click();
		System.out.println("click performed on Master");
		WebElement userMaster = driver.findElement(By.xpath(Loc2.getProperty("menuUserMaster")));
		String userMasterText=userMaster.getText();
		        System.out.println(userMasterText + " is available in Master List.");
		        Thread.sleep(2000);
		        userMaster.click();
		        System.out.println("After click on User Master from menu its Navigted to User Master main Page");
		    }
	// check that header is displayed on top center 
	@Test
		public  void userMasterHeaderIsAvailable() throws InterruptedException{
			UserMaster cc = new UserMaster();
			cc.masterDisplayedInMenu();
			WebElement Header = driver.findElement(By.xpath(Loc2.getProperty("userMasterHeader")));
			Thread.sleep(2000);
			String headerText = Header.getText();
			if(Header.isDisplayed()) {
				System.out.println(headerText+ "' is Displayed");
			} else {
				System.out.println(headerText+ "' is not Displayed");
			}
	}
	// check back button is available and clickable  
	@Test
	 public  void backButtonIsAvailableAndClickable() throws InterruptedException{
		UserMaster cc = new UserMaster();
	    	cc.userMasterHeaderIsAvailable();
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
	    	  System.out.println("After click on back button user navigated to back page succesfully");
	      }else {
	    	  System.out.println("back button is not clickable");  
	      }
	    }
	// check the search bar is available or not in user master page 
	@Test
	public  void searchInputIsAvailable() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.SimpleLogin();
		Thread.sleep(2000);
		WebElement searchInput = driver.findElement(By.xpath(Loc2.getProperty("inputSearch")));
		if(searchInput.isDisplayed()) {
			System.out.println("searchInput is Displayed");
		} else {
			System.out.println("searchInput is Not Displayed");	
		}
	}
	// check searchInput is clickable or not 
	@Test
	public  void issearchInputClickable() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.SimpleLogin();
		Thread.sleep(2000);
		WebElement searchInput = driver.findElement(By.xpath(Loc2.getProperty("searchBar")));
		searchInput.click();
		try {
			System.out.println("searchBar is Clickable");
		} catch(ElementClickInterceptedException e) {
			System.out.println("searchBar is not Clickable ");	
		}
	}
	// check search data is filter out from table 
	@Test
	public  void searchDataInsearchInput() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.SimpleLogin();
		Thread.sleep(2000);
		 WebElement searchInput = driver.findElement(By.xpath(Loc2.getProperty("inputSearch")));
	        Thread.sleep(1000);
	        String[] searchData = {"appuW1", "AparnaDFLogin", "Aparna Lab", "dffegvewgewhe"};
	        String[] actual = {"appuW1", "AparnaDFLogin", "Aparna Lab"};
	        List<String> actualData = Arrays.asList(actual);
	        for (String data : searchData) {
	            searchInput.sendKeys(data);
	            Thread.sleep(1000); 
	            if (actualData.contains(data)) { 
	                System.out.println(data + " is available in the table");
	            } else {
	                System.out.println(data + " - no data found");
	            }
	            Thread.sleep(2000);
	           searchInput.clear(); 
	        }
	       
	}
	// check Add Userbutton is enable and displayed
	@Test
	public  void addUserButtonDisplayedAndEnabled() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.SimpleLogin();
		WebElement addUser = driver.findElement(By.xpath(Loc2.getProperty("AddUser")));
		String addUserText = addUser.getText();
		if(addUser.isDisplayed() & addUser.isEnabled()) {
			System.out.println(addUserText+ " ' is Displayed and Enabled");
		} else {
			System.out.println(addUserText+ " ' is not Displayed and Enabled");
		}		
}
	// check that after click on add user button its navigated to add user form 
	@Test
	public  void clickAddUserButton() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.SimpleLogin();
		Thread.sleep(2000);
		WebElement addUser = driver.findElement(By.xpath(Loc2.getProperty("AddUser")));
		addUser.click();
	     System.out.println("AddUser is clickable and clicked successfully!");
	     Thread.sleep(2000);
		WebElement userCreationForm = driver.findElement(By.xpath(Loc2.getProperty("userCreationForm")));
		String userCreationFormText = userCreationForm.getText();
	   if(userCreationForm.isDisplayed()) {
		   System.out.println("After Click on Add User its Navigated to '" +userCreationFormText);
	   } else {
		   System.out.println("Click not perfrom , Somthing Went Wrong");
	   }
	}
	// check back button is displayed and enabled in header 
	@Test
	public  void backButtonIsEnableAndDisplayed() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.clickAddUserButton() ;
		WebElement backButton = driver.findElement(By.xpath(Loc2.getProperty("backButtonForm")));
		if (backButton.isDisplayed() & backButton.isEnabled()) {
			System.out.println("Back button is Displayed and Enabled");
		} else {
			System.out.println("Back Button is Not Displayed and Enabled");
			
		}
	}
		// check mandatory field label in form on top right
	@Test
		public  void getMandatoyFieldText() throws InterruptedException{
			UserMaster cc = new UserMaster();
			cc.clickAddUserButton() ;
			WebElement mandatoryField = driver.findElement(By.xpath(Loc2.getProperty("mandatoryField")));
           String mandatoryFieldText = mandatoryField.getText();
           if(mandatoryField.isDisplayed()){
           System.out.println(mandatoryFieldText + " ' text displayed");
		} else {
		      System.out.println(mandatoryFieldText + " ' text not displayed");
		}
			}
	// check Active Status Text is DIsplayed
	@Test
	public  void activeStatusDisplayed() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.clickAddUserButton() ;
		Thread.sleep(3000);
		WebElement activeStatus = driver.findElement(By.xpath(Loc2.getProperty("activeStatus")));
       String mandatoryFieldText = activeStatus.getText();
       if(activeStatus.isDisplayed()){
       System.out.println(mandatoryFieldText + " ' text displayed");
	} else {
	      System.out.println(mandatoryFieldText + " ' text not displayed");
	}
		}
	// check toggle button is Active or Inactive bydefault 
	@Test
	public  void bydefaultStatusActivityOfToggleButton() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.activeStatusDisplayed() ;
		WebElement toggle = driver.findElement(By.xpath(Loc2.getProperty("ionToggle")));
       String mandatoryFieldText = toggle.getAttribute("value");
        System.out.println("Bydefault Toggle button is '" +mandatoryFieldText);
      }
	// check fields name in user creation form 
	@Test
	public  void fieldName() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.clickAddUserButton();
		List<WebElement> fieldName = driver.findElements(By.xpath(Loc2.getProperty("formFields")));
		for(WebElement FieldName:fieldName  ) {
			String formFields = FieldName.getText();
			System.out.println("Following Fields available in the form");
			System.out.println(formFields);	
		}
	}
	// check dairyfarm is input type or ion selection type in form 
	@Test
	public  void dairyFarm() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.clickAddUserButton();
		Thread.sleep(3000);
		WebElement dairyFarm = driver.findElement(By.xpath(Loc2.getProperty("dairyFarm")));
		String TagName = dairyFarm.getTagName();
		if(TagName.equals("ion-select")) {
		    System.out.println("DairyFarm is Selection Type");
		} else if(TagName.equals("input")) {
		    System.out.println("DairyFarm is Input Type");
		} else {
		    System.out.println("DairyFarm is Another Type");
		}
	}
	// check name field is input type or not 
	@Test
	public  void enterName() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.clickAddUserButton();
		WebElement enterName = driver.findElement(By.xpath(Loc2.getProperty("name")));
		String TagName = enterName.getTagName();
		if(TagName.equals("ion-select")) {
		    System.out.println("Enter Name is Selection Type");
		} else if(TagName.equals("input")) {
		    System.out.println("Enter Name is Input Type");
		} else {
		    System.out.println("Enter Name is Another Type");
		}
  }
	// check loginid field is clickable or not 
	@Test
	public  void loginID() throws InterruptedException{
		UserMaster cc = new UserMaster();
	cc.clickAddUserButton();
		WebElement loginID = driver.findElement(By.xpath(Loc2.getProperty("LoginID")));
		if(!loginID.isEnabled()){
			System.out.println("loginID is clickable");
	} else {
			System.out.println("loginID is not clickable");
		}
}
// check password1 field is clickable or not 
	@Test
	public  void generatePassword() throws InterruptedException{
		UserMaster cc = new UserMaster();
	cc.clickAddUserButton();
		WebElement pass = driver.findElement(By.xpath(Loc2.getProperty("password1")));
		if(!pass.isEnabled()){
			System.out.println("Password is clickable");
	} else {
			System.out.println("Password is not clickable");
		}
}
	// check gender are input type or selection type 
	@Test
	public  void gender() throws InterruptedException{
		UserMaster cc = new UserMaster();
		cc.clickAddUserButton();
		Thread.sleep(3000);
		WebElement gender = driver.findElement(By.xpath(Loc2.getProperty("gender")));
		String TagName = gender.getTagName();
		if(TagName.equals("ion-select")) {
		    System.out.println("Gender is Selection Type");
		} else if(TagName.equals("input")) {
		    System.out.println("Gender is Input Type");
		} else {
		    System.out.println("Gender is Another Type");
		}
	}
	// check roles are input type or selection type 
		@Test
		public  void role() throws InterruptedException{
			UserMaster cc = new UserMaster();
			cc.clickAddUserButton();
			Thread.sleep(3000);
			WebElement role = driver.findElement(By.xpath(Loc2.getProperty("Roles")));
			String TagName = role.getTagName();
			if(TagName.equals("ion-select")) {
			    System.out.println("Roles is Selection Type");
			} else if(TagName.equals("input")) {
			    System.out.println("Roles is Input Type");
			} else {
			    System.out.println("Roles is Another Type");
			}
		}
// check phone number is input type or selection type 
		@Test
		public  void phone() throws InterruptedException{
			UserMaster cc = new UserMaster();
			cc.clickAddUserButton();
			Thread.sleep(3000);
			WebElement phone = driver.findElement(By.xpath(Loc2.getProperty("phone1")));
			String TagName = phone.getTagName();
			if(TagName.equals("ion-select")) {
			    System.out.println("Phone No. is Selection Type");
			} else if(TagName.equals("input")) {
			    System.out.println("Phone No. is Input Type");
			} else {
			    System.out.println("Phone No. is Another Type");
			}
		}
		// check email is input type or selection type 
		@Test
		public  void email()throws InterruptedException{
			UserMaster cc = new UserMaster();
			cc.clickAddUserButton();
			Thread.sleep(3000);
			WebElement email = driver.findElement(By.xpath(Loc2.getProperty("email1")));
			String TagName = email.getTagName();
			if(TagName.equals("ion-select")) {
			    System.out.println("EMail is Selection Type");
			} else if(TagName.equals("input")) {
			    System.out.println("EMail is Input Type");
			} else {
			    System.out.println("EMail is Another Type");
			}
		}
		// check that after click on dairyfarm dropdown button user type list open in model page 
		@Test
		public  void dairyFarm_click() throws InterruptedException{
			UserMaster cc = new UserMaster();
			cc.clickAddUserButton();
			Thread.sleep(3000);
			WebElement dairyFarm = driver.findElement(By.xpath(Loc2.getProperty("dairyFarm")));
			dairyFarm.click();
			Thread.sleep(2000);
			System.out.println("Click perfromed on DairyFarm");
			WebElement modelpage= driver.findElement(By.xpath(Loc2.getProperty("alertModel")));
			if (modelpage.isDisplayed()) {
				System.out.println("Model Page Displayed ");
			}else {
				System.out.println("Model Page not Displayed ");
			}
		}
			// check that the model page contains usertype with radio button
			@Test
			public  void usertype_List() throws InterruptedException{
			UserMaster cc = new UserMaster();
			cc.dairyFarm_click();
			Thread.sleep(3000);
			List<WebElement> usertype_List = driver.findElements(By.xpath(Loc2.getProperty("usertype_List")));
			for(WebElement usertype_list : usertype_List) {
			    String usertype_text = usertype_list.getText();
				System.out.println("UserType Displayed on model page");
				System.out.println(usertype_text);
				}		
           }
			// check radio button is selected or not on usertype model page 
			@Test
			public  void usertype_Radio() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.usertype_List();
				Thread.sleep(3000);
				List<WebElement> radioButtons = driver.findElements(By.xpath(Loc2.getProperty("usertyperadio")));
				for (WebElement radioButton : radioButtons) {
				    String radiobutton = radioButton.getAttribute("aria-checked");

				    if (radiobutton.equals("true")) {
				        System.out.println("Radio button is selected: " + radioButton.getAttribute("aria-checked"));
				    } else {
				        System.out.println("Radio button is not selected: " + radioButton.getAttribute("aria-checked"));
				    }
				}

				// Check and click the second radio button if it is not selected
				if (radioButtons.size() >= 2) {
				    WebElement secondButton = radioButtons.get(1);
				    String radiobutton1 = secondButton.getAttribute("aria-checked");

				    if (radiobutton1.equals("true")) {
				        System.out.println("Second radio button is already selected: " + radiobutton1);
				    } else {
				        secondButton.click();
				        System.out.println("Second radio button was not selected. Now it is selected.");
				    }
				}
				// Check and click the third radio button if it is not selected
				if (radioButtons.size() >= 3) {
				    WebElement secondButton = radioButtons.get(2);
				    String radiobutton1 = secondButton.getAttribute("aria-checked");

				    if (radiobutton1.equals("true")) {
				        System.out.println("Third radio button is already selected: " + radiobutton1);
				    } else {
				        secondButton.click();
				        System.out.println("Third radio button was not selected. Now it is selected.");
			                    }
                    }
			             }
			// check that cancel and ok button is displayed and enable
			@Test
			public  void cancelAndOkButton() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.dairyFarm_click();
				Thread.sleep(3000);
				List<WebElement> buttons = driver.findElements(By.xpath(Loc2.getProperty("cancelOk")));
				for (WebElement button : buttons) {
				    String buttonText = button.getText();
				    if (button.isDisplayed() && button.isEnabled()) {
				        System.out.println(buttonText + " is Enabled and Displayed");
				    } else if (button.isDisplayed() && !button.isEnabled()) {
				        System.out.println(buttonText + " is Displayed but not Enabled");
				    } else if (!button.isDisplayed()) {
				        System.out.println(buttonText + " is Not Displayed");
				    }
				}

                 }
			// check that after click on cancel button model page getting hide
			@Test
			public  void cancelClick() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.dairyFarm_click();
				Thread.sleep(3000);
				WebElement cancel = driver.findElement(By.xpath(Loc2.getProperty("Cancel")));
				cancel.click();
				Thread.sleep(2000);
				WebElement dairyFarm = driver.findElement(By.xpath(Loc2.getProperty("dairyFarm")));
				if(! dairyFarm.isDisplayed()) {
					System.out.println("Cancel button is not Clickable");
             } else {
            	 System.out.println("Model Getting Hide after clicking Cancel button ");
             }
			}	
			//  check after clicking ok button selected user type DairyFarm displayed in form
			@Test
			public void  okClick()throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.dairyFarm_click();
				WebElement ok = driver.findElement(By.xpath(Loc2.getProperty("Ok")));
				Thread.sleep(2000);
				ok.click();
				Thread.sleep(2000);
				WebElement dairyFarm=driver.findElement(By.xpath(Loc2.getProperty("dairyFarmText")));
				String dairyFarmText = dairyFarm.getText();
			   String Expected = "Dairy Farm" ;
			   if(dairyFarmText.equals(Expected)) {
				   System.out.println("After click on Ok selected UserType' " + dairyFarmText + " 'Displayed on Form");
			   } else {
				   System.out.println("After click on Ok selected UserType' " + dairyFarmText + " is not Displayed on Form");
			   }
				
			}
		//  check after clicking OK button selected user type Collection Center displayed in form	
			@Test
			public void  okClick1()throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.dairyFarm_click();
				WebElement CollectionCenter = driver.findElement(By.xpath(Loc2.getProperty("collectionCenterOnModelPage")));
				CollectionCenter.click();
				Thread.sleep(2000);
				WebElement ok = driver.findElement(By.xpath(Loc2.getProperty("Ok")));
				Thread.sleep(2000);
				ok.click();
				Thread.sleep(2000);
				WebElement collectionCenter = driver.findElement(By.xpath(Loc2.getProperty("collectionCenterOnForm")));
				String CollectionCenterText= collectionCenter.getText();
				String Expected = "Collection Centre";
				if(CollectionCenterText.equals(Expected)) {
					 System.out.println("After click on Ok selected UserType' " + CollectionCenterText + " 'Displayed on Form");
				   } else {
					   System.out.println("After click on Ok selected UserType' " + CollectionCenterText + " is not Displayed on Form");
				   } 
					
				}
			//  check after clicking OK button selected user type Chilling Center displayed in form	
			@Test
			public void  okClick2()throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.dairyFarm_click();
				WebElement chillingCenter = driver.findElement(By.xpath(Loc2.getProperty("chillingCenterOnModelPage")));
				chillingCenter.click();
				Thread.sleep(2000);
				WebElement ok = driver.findElement(By.xpath(Loc2.getProperty("Ok")));
				Thread.sleep(2000);
				ok.click();
				Thread.sleep(2000);
				WebElement chillingCenter1 = driver.findElement(By.xpath(Loc2.getProperty("chillingcenterOnForm")));
				String chillingcenterText = chillingCenter1.getText();
				String expected = "Chilling Centre";
				if(chillingcenterText.equals(expected)) {
					System.out.println("After click on Ok UserType' "+chillingcenterText + " ' Displayed on Form" );
				} else {
					System.out.println("After click on Ok UserType' "+chillingcenterText + " ' Not Displayed on Form" );
				}
			}
			/*
			// check that select mcc lable is displayed in the from
			@Test
			public void  selectMccLabel()throws InterruptedException{
				UserMaster cc = new UserMaster();
                cc.clickAddUserButton();
                WebElement selectMCC = driver.findElement(By.xpath(Loc2.getProperty("selectMCC")));
                String selectMCCLabel = selectMCC.getAttribute("aria-label");
                if(selectMCC.isDisplayed()) {
                	System.out.println(selectMCCLabel + " ' label Displayed in the Form");
                }else {
                	System.out.println(selectMCCLabel + " ' label Not Displayed in the Form");
                }
                */
         // check the gender bydefault Female selected in to the Form 
			@Test
			public void  selectMccLabel()throws InterruptedException{
				UserMaster cc = new UserMaster();
                cc.clickAddUserButton();
                WebElement gender = driver.findElement(By.xpath(Loc2.getProperty("gender")));
                String genderText = gender.getText();
            	String Expected = "Male";
                if(gender.isDisplayed()) {
                	System.out.println("Bydefault ' "+ genderText + "'Gender is Selected in the Form ");
                } else if(!gender.isDisplayed()){
                	System.out.println("Gender Field is Blank");
          }else if(genderText.equals(Expected)) {
        	  System.out.println("Bydefault the selected gender is Male");
          } else {
        	  System.out.println("Bydefault the selected gender is Other"); 
          }
           }
			// click on the gender field to check the model is opend with gender list 
			@Test
			public  void genderClick() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.clickAddUserButton();
				Thread.sleep(3000);
				WebElement gender= driver.findElement(By.xpath(Loc2.getProperty("gender")));
				gender.click();
				Thread.sleep(2000);
				System.out.println("Click perfromed on Gender");
				WebElement modelpage= driver.findElement(By.xpath(Loc2.getProperty("alertModel")));
				if (modelpage.isDisplayed()) {
					System.out.println("Model Page Displayed ");
				}else {
					System.out.println("Model Page not Displayed ");
				}
			}
			// check the three gender selection option are available on model page 
			@Test
			public  void getGenderListFromModelPage() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.genderClick();
				List<WebElement> genders = driver.findElements(By.xpath(Loc2.getProperty("genderList")));
				for(WebElement genderList :genders) {
					String genderText = genderList.getText();
					if(genderList.isDisplayed()) {
						System.out.println("Gender' "+ genderText + " ' Displayed on Model Page");	
					} else {
						System.out.println("Gender List Not Displayed");
						}
					}
				}
			// check that bydefault female gender is selected and other is unselected
			@Test
			public  void checkThatBydefaultFemaleGenderIsSelected() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.genderClick();
				List<WebElement> genderRadio= driver.findElements(By.xpath(Loc2.getProperty("checkRadioOnGender")));
				for(WebElement genderRadioCheck : genderRadio) {
					String genderText = genderRadioCheck.getText();
					String  genderRadioChecked = genderRadioCheck.getAttribute("aria-checked");
					String Expected= "true";
					if(genderRadioChecked.equals(Expected)) {
						System.out.println(genderText + "' Selected '" +genderRadioChecked);
					} else{
						System.out.println(genderText + "' Not-Selected '" +genderRadioChecked);
					}
				}
			 }
			// check that after click on male and Other Gender then check radio is selected or not 
			@Test
			public void clickOnFelmaleOrOtherToCheckRadioIsSelected() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.genderClick();
				WebElement clickMale = driver.findElement(By.xpath(Loc2.getProperty("maleRadioClick")));
				String checkRadioSelected = clickMale.getAttribute("aria-checked");
				String genderText = clickMale.getText();
				String Expected = "true";
				if(checkRadioSelected.equals(Expected)) {
					System.out.println(genderText + "' is Selected before click :'" +checkRadioSelected );
				} else {
					System.out.println(genderText + "' is Not-Selected before click :'" +checkRadioSelected );
				} 
				clickMale.click();
				Thread.sleep(2000);
				WebElement clickMale1 = driver.findElement(By.xpath(Loc2.getProperty("maleRadioClick")));
				String checkRadioSelected1 = clickMale.getAttribute("aria-checked");
				String genderText1 = clickMale1.getText();
				String Expected1 = "true";
				if(checkRadioSelected1.equals(Expected1)) {
					System.out.println(genderText1 + "' is Selected After click :'" +checkRadioSelected1 );
				} else {
					System.out.println(genderText1 + "' is Not-Selected After click :'" +checkRadioSelected1 );
				}///////
				WebElement clickOther = driver.findElement(By.xpath(Loc2.getProperty("otherRadioClick")));
				String checkRadioSelected2 = clickOther.getAttribute("aria-checked");
				String genderText2 = clickOther.getText();
				String Expected2 = "true";
				if(checkRadioSelected2.equals(Expected2)) {
					System.out.println(genderText2 + "' is Selected before click :'" +checkRadioSelected2 );
				} else {
					System.out.println(genderText2 + "' is Not-Selected before click :'" +checkRadioSelected2 );
				} 
				clickOther.click();
				Thread.sleep(2000);
				WebElement clickOther1 = driver.findElement(By.xpath(Loc2.getProperty("otherRadioClick")));
				String checkRadioSelected3 = clickOther1.getAttribute("aria-checked");
				String genderText3 = clickOther1.getText();
				String Expected3 = "true";
				if(checkRadioSelected1.equals(Expected3)) {
					System.out.println(genderText3 + "' is Selected After click :'" +checkRadioSelected3 );
				} else {
					System.out.println(genderText3 + "' is Not-Selected After click :'" +checkRadioSelected3 );
				}
			}
			//check that canel and ok button is displayed on Gender Model page
			@Test
			public  void checkThatCancelAndOkButtonIsDisplayedOnGenderModelPage() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.genderClick();
			List<WebElement> buttons1 = driver.findElements(By.xpath(Loc2.getProperty("cancelOk")));
			for (WebElement button : buttons1) {
			    String buttonText1 = button.getText();
			    if (button.isDisplayed() && button.isEnabled()) {
			        System.out.println(buttonText1 + " is Enabled and Displayed");
			    } else if (button.isDisplayed() && !button.isEnabled()) {
			        System.out.println(buttonText1 + " is Displayed but not Enabled");
			    } else if (!button.isDisplayed()) {
			        System.out.println(buttonText1 + " is Not Displayed");
			    }
			     }
	     	  }
			//  check that after click on cancel button the model page getting hide 
			@Test
			public  void clickCancelButtonFromGenderModelPage() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.genderClick();
				Thread.sleep(3000);
				WebElement cancel1 = driver.findElement(By.xpath(Loc2.getProperty("Cancel")));
				cancel1.click();
				Thread.sleep(2000);
				try {
				WebElement genderModel = driver.findElement(By.xpath(Loc2.getProperty("genderModel")));
				if(genderModel.isDisplayed()) {
					System.out.println("Cancel button is not Clickable");
             }
				} catch(Exception e ){
            	 System.out.println("Model Getting Hide after clicking Cancel button ");
             }
			}
			// check that after click on ok button model page getting hide 
			@Test
			public void clickOkButtonFromGenderModePage() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.genderClick();
				Thread.sleep(2000);
				WebElement Ok1 = driver.findElement(By.xpath(Loc2.getProperty("Ok")));
				Ok1.click();
				Thread.sleep(2000);
				try {
					WebElement genderModel = driver.findElement(By.xpath(Loc2.getProperty("genderModel")));
					if(genderModel.isDisplayed()) {
						System.out.println("Ok button is not Clickable");
	             }
					} catch(Exception e ){
	            	 System.out.println("Model Getting Hide after clicking Ok button ");
	             }
			}
			// check that after selecting gender female and click ok from model page selected gender female getting displayed on user creation form 
			@Test
			public void selectGenderFemaleAndClickOkFromModelPageThenSelectedGenderDisplayedOnForm() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.genderClick();
				WebElement Female = driver.findElement(By.xpath(Loc2.getProperty("Female")));
				String femaleText = Female.getText();
				WebElement ok = driver.findElement(By.xpath(Loc2.getProperty("Ok")));
				ok.click();
				Thread.sleep(2000);
				WebElement genderInForm = driver.findElement(By.xpath(Loc2.getProperty("genderInForm")));
				String genderTextInForm = genderInForm.getText();
				if(femaleText.equals(genderTextInForm))	{
					System.out.println("Selected Gender " +femaleText + "' Displayed on Form ");
				} else if(!femaleText.equals(genderTextInForm)) {
					System.out.println("Selected Gender Not-Displayed on Form");
					} 
				else {
				System.out.println("Gender Field Blank on Form");
				}
			  }
			// check that after selecting gender male and click ok from model page selected gender male getting displayed on user creation form 
			@Test
			public void selectGendermaleAndClickOkFromModelPageThenSelectedGenderDisplayedOnForm() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.genderClick();
				Thread.sleep(2000);
				WebElement male = driver.findElement(By.xpath(Loc2.getProperty("maleClick")));
				String maleText = male.getText();
				male.click();
				WebElement ok = driver.findElement(By.xpath(Loc2.getProperty("Ok")));
				ok.click();
				Thread.sleep(2000);
				WebElement genderInForm = driver.findElement(By.xpath(Loc2.getProperty("genderInForm")));
				String genderTextInForm = genderInForm.getText();
				if(maleText.equals(genderTextInForm))	{
					System.out.println("Selected Gender " +maleText + "' Displayed on Form ");
				} else if(!maleText.equals(genderTextInForm)) {
					System.out.println("Selected Gender Not-Displayed on Form");
					} 
				else {
				System.out.println("Gender Field Blank on Form");
				}
			  }
			// check that after selecting gender other and click ok from model page selected gender other getting displayed on user creation form 
			@Test
			public void selectGenderOtherAndClickOkFromModelPageThenSelectedGenderDisplayedOnForm() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.genderClick();
				Thread.sleep(2000);
				WebElement other = driver.findElement(By.xpath(Loc2.getProperty("otherClick")));
				String otherText = other.getText();
				other.click();
				WebElement ok = driver.findElement(By.xpath(Loc2.getProperty("Ok")));
				ok.click();
				Thread.sleep(2000);
				WebElement genderInForm = driver.findElement(By.xpath(Loc2.getProperty("genderInForm")));
				String genderTextInForm = genderInForm.getText();
				if(otherText.equals(genderTextInForm))	{
					System.out.println("Selected Gender " +otherText + "' Displayed on Form ");
				} else if(!otherText.equals(genderTextInForm)) {
					System.out.println("Selected Gender Not-Displayed on Form");
					} 
				else {
				System.out.println("Gender Field Blank on Form");
				}
			  }
			// verify that the select role is clickable and after click on select role the model page is open
			@Test
			public void clickSelectRoleAndCheckRoleListIsDisplayedOnModelPage() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.clickAddUserButton();
				Thread.sleep(2000);
				WebElement selectRoleClick= driver.findElement(By.xpath(Loc2.getProperty("clickSelectRole")));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  selectRoleClick);
				selectRoleClick.click();
				Thread.sleep(2000);
				WebElement selectRoleModelPage = driver.findElement(By.xpath(Loc2.getProperty("selectRoleModel")));
				if(selectRoleModelPage.isDisplayed()) {
					System.out.println("Click Perform on Select Role and Model is Displayed");
				} else {
					System.out.println("Click not Performed on Select Role");
				}
			}
			// check that after click on select role the role list is shown on model page
			@Test
			public void roleListDisplayedOnModelPage() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.clickAddUserButton();
				Thread.sleep(2000);
				WebElement selectRoleClick= driver.findElement(By.xpath(Loc2.getProperty("clickSelectRole")));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  selectRoleClick);
				selectRoleClick.click();
				Thread.sleep(2000);
				List<WebElement> roleList_Dairy = driver.findElements(By.xpath(Loc2.getProperty("roleList(DairyFarm)")));
					for( WebElement rolelist : roleList_Dairy) {
						String rolelist1 =rolelist.getText();
					if(rolelist.isDisplayed()) {
						System.out.println("Role List Contains:'"+rolelist1 );
					} else {
						System.out.println("Role List Not Displayed on Model Page");
					        }	
					    }	
                     }
			// check that cancel and ok button shown in model page
			@Test
			public void checkCancelAndOkButtonOnRoleListMode() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.clickSelectRoleAndCheckRoleListIsDisplayedOnModelPage();
				WebElement cancelOkButton = driver.findElement(By.xpath(Loc2.getProperty("cancelOk")));
				String cancelOkText = cancelOkButton.getText();
				if (cancelOkButton.isDisplayed() && cancelOkButton.isEnabled()) {
					System.out.println("button ' " +cancelOkText + "'Displayed and Enabled" );
				}else if(cancelOkButton.isDisplayed()&& !cancelOkButton.isEnabled()) {
					System.out.println(cancelOkText + "' Displayed but not Enabled");
				}else {
					System.out.println(cancelOkText + "' not Displayed");
				}
             }   
			// check that after click on cancel button role list model getting hide
			@Test
			public void roleListGettingHideAfterClickingCancelFromModelPage() throws InterruptedException{
				UserMaster cc = new UserMaster();
				cc.clickSelectRoleAndCheckRoleListIsDisplayedOnModelPage();
				Thread.sleep(2000);
				WebElement Cancel = driver.findElement(By.xpath(Loc2.getProperty("Cancel")));
				Cancel.click();
				Thread.sleep(2000);
				try {
					WebElement Cancel1 = driver.findElement(By.xpath(Loc2.getProperty("selectRoleModel")));
					if(Cancel1.isDisplayed()) {
						System.out.println("Cancel button is not clcickable");
					}	
					} catch(Exception e) {
				   System.out.println("Model Page Getting Hide After Click on Cancel Button");
					}
                 }
			// Check whether a checkbox is checked after clicking on it, considering that it is not checked by default
			@Test
			public void byDefaultCheckBoxIsUncheckedInRoleList() throws InterruptedException {
				UserMaster cc = new UserMaster();
				cc.clickSelectRoleAndCheckRoleListIsDisplayedOnModelPage();
				Thread.sleep(2000);
				List<WebElement> uncheckCheckbox = driver.findElements(By.xpath(Loc2.getProperty("uncheckedCheckbox")));
		      for(WebElement uncheckbox1 : uncheckCheckbox) {
				String uncheckCheckBoxText= uncheckbox1.getText();
				String uncheckCheckboxAttri = uncheckbox1.getAttribute("aria-checked");
				String Expected = "false" ;
				if(uncheckCheckboxAttri.equals(Expected)) {
					System.out.println("is "+ uncheckCheckBoxText + " Checked : " +uncheckCheckboxAttri);
				} else {
					System.out.println("is "+ uncheckCheckBoxText + " Not-Checked : " +uncheckCheckboxAttri);
				}
			  }
			}
		      // Check after click on paticular checkbox the roles is checked 
			@Test
			       public void afterClickOnParticularRoleCheckBoxIsChecked() throws InterruptedException {
					UserMaster cc = new UserMaster();
					cc.clickSelectRoleAndCheckRoleListIsDisplayedOnModelPage();
					Thread.sleep(2000);
					List<WebElement> roleList = driver.findElements(By.xpath(Loc2.getProperty("rolelists"))) ;
					for(WebElement roles :roleList) {
						roles.click();
						Thread.sleep(2000);
						String roleClickText = roles.getText();
						String  roleClickAttri = roles.getAttribute("aria-checked");
						String Expected4  = "true";
						if(roleClickAttri.equals(Expected4)) {
							System.out.println("is " + roleClickText + "is Checked " + roleClickAttri);
						} else {
							System.out.println("is " + roleClickText + "is Not-Checked " + roleClickAttri);
						}
					}
					Thread.sleep(2000);
                }
                //check after selecting checkbox(all) click on cancel then no role should be shown on form 
					@Test
					 public void checkAfterSelectAllRoleThenClickOnCancelFromModelNoRoleDisplayedOnForm() throws InterruptedException {
							UserMaster cc = new UserMaster();
							cc.afterClickOnParticularRoleCheckBoxIsChecked();
					WebElement cancelClick = driver.findElement(By.xpath(Loc2.getProperty("Cancel")));
					cancelClick.click();
				    Thread.sleep(2000);
				    WebElement roleOnForm = driver.findElement(By.xpath(Loc2.getProperty("selectRoleLabel")));
				    String roleOnFormAttri = roleOnForm.getAttribute("aria-label");
				    if(roleOnFormAttri.equals("Select Roles")) {
				    	System.out.println("After Checked Role From Model Page Click On Cancel Then No Role Should Display");
					 } else {
						 System.out.println("Checked Role Should be Displayed");
					 }
					 }  
}