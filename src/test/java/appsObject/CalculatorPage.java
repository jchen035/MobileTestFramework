package appsObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalculatorPage {
	
	private static WebElement elmt = null;
	
	//+ button
	public static WebElement additionBtn(WebDriver driver) {
		elmt = driver.findElement(By.xpath("//android.widget.Button[@text='+']"));
		return elmt;
	}
	
	//− button
	public static WebElement subtractionBtn(WebDriver driver) {
		elmt = driver.findElement(By.xpath("//android.widget.Button[@text='−']"));
		return elmt;
	}
	
	//= button
	public static WebElement equalsBtn(WebDriver driver) {
		elmt = driver.findElement(By.xpath("//android.widget.Button[@text='=']"));
		return elmt;
	}
	
	//Number button
	public static WebElement numberBtn(WebDriver driver, String numBtn) {
		elmt = driver.findElement(By.xpath("//android.widget.Button[@text='"+numBtn+"']"));
		return elmt;
	}
	
	// Result Text Field
	public static WebElement resultField(WebDriver driver) {
		elmt = driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'formula')]"));
		return elmt;
	}
	
	
	
	
}
