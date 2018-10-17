package test.test;

import static org.testng.Assert.fail;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import appsObject.CalculatorPage;

public class SimpleAndroidCalcTest {

private static WebDriver driver;
 
private AppiumDriverLocalService service;
private AppiumServiceBuilder builder;

public static ExtentReports extent;
public static ExtentTest test;
	
// Created object of DesiredCapabilities class.
private DesiredCapabilities capabilities = new DesiredCapabilities();

private static String adbPath = "C:\\Users\\RTTS\\android-sdks\\platform-tools\\adb.exe";
private static String emulatorPath = "C:\\Users\\RTTS\\android-sdks\\tools\\emulator.exe";

@BeforeClass(alwaysRun = true)
public void startServer() {
	
		//Launches AVD
		launchEmulator("androidemu12");
	
		extent = new ExtentReports("C:\\Users\\RTTS\\Desktop\\AppiumFramework-master\\MyExtentReport.html", true);
		extent.loadConfig(new File("C:\\Users\\RTTS\\Desktop\\AppiumFramework-master\\extent-config.xml"));
		
		//Set Capabilities
		capabilities.setCapability("noReset", "false");
		
		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withCapabilities(capabilities);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		
		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		System.out.println("Appium Server Started\n");
}

 @BeforeMethod(alwaysRun = true)
 public void setUp(Method method) throws MalformedURLException {
	 
	 test = extent.startTest(this.getClass().getSimpleName() + " :: " + method.getName(), method.getName());
	 test.assignAuthor("Jack");
	 test.assignCategory("Calculator App");
	// test.log(LogStatus.PASS, "App Launched Sucessfully");
	 
	//System.out.println("Setting Capabilities");
	 
  // Set android deviceName desired capability. Set your device name.
  capabilities.setCapability("deviceName", "emulator-5554");
  
  // Set BROWSER_NAME desired capability. It's Android in our case here.
  //  capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

  // Set android VERSION desired capability. Set your mobile device's OS version.
  capabilities.setCapability(CapabilityType.VERSION, "7.0");

  // Set android platformName desired capability. It's Android in our case here.
  capabilities.setCapability("platformName", "Android");

  // Set android appPackage desired capability. It is
  // com.android.calculator2 for calculator application.
  // Set your application's appPackage if you are using any other app.
  capabilities.setCapability("appPackage", "com.android.calculator2");
  // Set android appActivity desired capability. It is
  // com.android.calculator2.Calculator for calculator application.
  // Set your application's appPackage if you are using any other app.
  capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

  // Created object of RemoteWebDriver will all set capabilities.
  // Set appium server address and port number in URL string.
  // It will launch calculator app in android device.
  driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 }

 @Test(enabled = true, priority=1)
 public void Sum() throws InterruptedException {
	 
	 System.out.println("Running Addition Calculator Test");
	 
  // Click on DELETE/CLR button to clear result text box before running test.
//  driver.findElements(By.xpath("//android.widget.GridLayout")).get(1).click();

//	 WebDriverWait wait = new WebDriverWait(driver,600);
//	 WebElement elmt;
//	 elmt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Gmail']")));
	 
	  // Click on number 3 button.
		CalculatorPage.numberBtn(driver,"3").click();
		Thread.sleep(2000);
	  
	  // Click on + button.
	  	CalculatorPage.additionBtn(driver).click();
	  	Thread.sleep(2000);
	  
	  // Click on number 5 button.
	  	CalculatorPage.numberBtn(driver,"5").click(); 
	  	Thread.sleep(2000);
	  
	  // Click on = button.
	  	CalculatorPage.equalsBtn(driver).click();
	  	Thread.sleep(3000);

	  // Get result from result text box.
	    String result = CalculatorPage.resultField(driver).getText();
	    System.out.println("result:" + result);
//	    System.out.println("Number subtraction result is : " + result);
	    

	 	
	 	try {
	 		Assert.assertEquals("8",result);
	 		System.out.println("Test Passed | Actual Result : " + result);
	 		test.log(LogStatus.PASS, "Test Passed | Actual Result : " + result);
	 	}
	 		catch (AssertionError e) {
	 			System.out.println("Test Failed | Actual Result : " + result + " :: Expected Result : " + "8");
		 		test.log(LogStatus.FAIL, "Test Failed | Actual Result : " + result + " :: Expected Result : " + "8");
		 		Assert.assertEquals("8",result);	
	 		}
	 	
	 	System.out.println("Addition Test Completed");
  
}
 
 @Test(enabled = true, priority=2)
 public void Subtract() throws InterruptedException {
  // Click on DELETE/CLR button to clear result text box before running test.
  //driver.findElements(By.xpath("//android.widget.GridLayout")).get(1).click();
	 
	 System.out.println("Running Subtraction Calculator Test");
	 
  // Click on number 5 button.
	CalculatorPage.numberBtn(driver, "5").click();
	Thread.sleep(2000);
  
  // Click on − button.
  	CalculatorPage.subtractionBtn(driver).click();
  	Thread.sleep(2000);
  
  // Click on number 3 button.
  	CalculatorPage.numberBtn(driver,"3").click(); 
  	Thread.sleep(2000);
  
  // Click on = button.
  CalculatorPage.equalsBtn(driver).click();
  Thread.sleep(3000);
  
  // Get result from result text box.
  String result = CalculatorPage.resultField(driver).getText();
  //System.out.println("Number subtraction result is : " + result);
  
  System.out.println("result:" + result);
  
	try {
 		Assert.assertEquals("2",result);
 		System.out.println("Test Passed | Actual Result : " + result);
 		test.log(LogStatus.PASS, "Test Passed | Actual Result : " + result);
 	}
 		catch (AssertionError e) {		
 			
 			System.out.println("Test Failed | Actual Result : " + result + " :: Expected Result : " + "2");
	 		test.log(LogStatus.FAIL, "Test Failed | Actual Result : " + result + " :: Expected Result : " + "2");
	 		Assert.assertEquals("2",result);
 		}
	
	  System.out.println("Subtraction Test Completed");
  
 }
 
 public static void launchEmulator(String nameOfAVD){	 
	 System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
	 String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
	 try {
	  Process process = new ProcessBuilder(aCommand).start();
	 process.waitFor(40, TimeUnit.SECONDS);
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	}
 
 public static void closeEmulator() {
	 System.out.println("Closing Emulator...\n");	 
	 String[] aCommand = new String[] { adbPath, "emu", "kill" };
	 try {
	  Process process = new ProcessBuilder(aCommand).start();
	  process.waitFor(1, TimeUnit.SECONDS);
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
 }

 @AfterMethod(alwaysRun = true)
	public void tearDown() {
    driver.quit(); 
  //  test.log(LogStatus.PASS, "App closed sucessfully");
    extent.endTest(test);
}
 
 @AfterClass(alwaysRun = true)
	public void stopServer() {	 
	 	System.out.println("\nFinished running all tests");
		service.stop();
		System.out.println("Appium Server Stopped");
		closeEmulator();
		extent.flush();
		extent.close();
	}	 
}