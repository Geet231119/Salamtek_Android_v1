package Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import Utils.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class BaseTest {

	static String path = System.getProperty("user.dir");
	protected static AppiumDriver<MobileElement> driver = null;
	Properties prop = new Properties();
	InputStream deviceInput = null;

	/*
	 * @BeforeSuite public void startAppiumServer() { Runtime runtime =
	 * Runtime.getRuntime(); try { runtime.
	 * exec("cmd.exe /c start cmd.exe /k \"appium --allow-cors --use-plugins=element-wait\""
	 * ); Thread.sleep(1000); } catch (IOException | InterruptedException e) {
	 * e.printStackTrace(); } }
	 */
	/**
	 * 
	 */
	@BeforeSuite
	public void setup() {
		try {
			//WebDriverManager.chromedriver().setup();
			
			deviceInput = new FileInputStream(path + "\\src\\main\\resources\\device.properties");

			// Load properties file
			prop.load(deviceInput);

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, prop.getProperty("AutomationName"));
			caps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, prop.getProperty("PlatformName"));
			
			//For Emulator
//			caps.setCapability(MobileCapabilityTypeEx.PLATFORM_VERSION, prop.getProperty("e_PlatformVersion"));
//			caps.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, prop.getProperty("e_deviceName"));
//			caps.setCapability(MobileCapabilityTypeEx.UDID, prop.getProperty("e_UDID"));
			
			//For Real Device
			caps.setCapability(MobileCapabilityTypeEx.PLATFORM_VERSION, prop.getProperty("r_PlatformVersion"));
			caps.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, prop.getProperty("r_deviceName"));
			caps.setCapability(MobileCapabilityTypeEx.UDID, prop.getProperty("r_UDID"));
			
			caps.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT,Integer.parseInt(prop.getProperty("NewCommandTimeout")));
			caps.setCapability(MobileCapabilityTypeEx.APP, path + "\\src\\test\\resources\\Apps\\Salamtek_Latest.apk");
			caps.setCapability(MobileCapabilityTypeEx.NO_RESET, false);
			caps.setCapability(MobileCapabilityTypeEx.FULL_RESET, false);
			caps.setCapability(MobileCapabilityTypeEx.APP_WAIT_PACKAGE, "com.google.android.permissioncontroller");
			caps.setCapability(MobileCapabilityTypeEx.APP_WAIT_ACTIVITY, "com.android.permissioncontroller.permission.ui.GrantPermissionsActivity");
			//caps.setCapability(MobileCapabilityTypeEx.ADB_EXEC_TIMEOUT, 20000);
			//caps.setCapability(MobileCapabilityTypeEx.CHROME_EXEUTABLE, path + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
			
			if (deviceInput != null) {
				try {
					deviceInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			URL url = new URL("http://127.0.0.1:4723/");

			driver = new AppiumDriver<MobileElement>(url, caps);
			
			By permissionControllerDialog = By.id("com.android.permissioncontroller:id/grant_dialog");
			By permissionController_Allow = By.id("com.android.permissioncontroller:id/permission_allow_button");
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			click_IfDisplayed(permissionControllerDialog, permissionController_Allow);

		} catch (Exception e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Message is: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public AppiumDriver<MobileElement> getDriver() {
		return driver;
	}

	public String getExcelSheetName() {
		return path + "\\src\\test\\resources\\TestData\\TestDataAndroid.xlsx";
	}

	// To get today's date in yyyy-MM-dd format
	public static String getTodayDateInYYYYMMDD() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(today);
	}

	// To swipe down the screen
	public static void swipeDown(int start_x, int start_y, int end_x, int end_y) {
		final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		var start = new Point(start_x, start_y);
		var end = new Point(end_x, end_y);
		var swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(),
				start.getY()));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.getX(),
				end.getY()));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
	}
	
	public static void tapAnElement(int x, int y) {
		final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		var tapPoint = new Point(x, y);
		var tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
		    PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(new Pause(finger, Duration.ofMillis(50)));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(tap));
	}

	// To wait for sometime
	public void waitSometime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// To wait for sometime
	public void waitForSpecificTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Check and click the element if the element is displayed
	public void click_IfDisplayed(By mainEle, By actionEle) {
		List<MobileElement> targetElement = driver.findElements(mainEle);
		try {
			System.out.println("Size of elements: " + targetElement.size());
			if (targetElement.size() >= 1) {
				for (int i = 0; i < targetElement.size(); i++) {
					if (targetElement.get(i).isDisplayed()) {
						System.out.println("++++++caught popup++++++");
						driver.findElement(actionEle).click();
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Exception in finding the element:" + e.getMessage());
		}
	}
	
	//return true if size is 1 or return false if size is 0
	public Boolean displayedOrNot(By ele) {
		boolean status = false;
		try {
			List<MobileElement> targetElement = driver.findElements(ele);
			if(targetElement.size() == 1)
				status = true;
			else
				status = false;
		} catch (NoSuchElementException e) {
			System.out.println("Exception in finding the element:" + e.getMessage());
		}
		return status;
	}
	
	// Explicitly waits a locator to be visible for a specific time
	public MobileElement explicitWait(AppiumDriver<?> driver, MobileElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	    return element;
	}
	
	
	// Explicitly waits a locator to be visible for a specific time
		public WebElement explicitWaitForWeb(AppiumDriver<?> driver, WebElement element) {
		    WebDriverWait wait = new WebDriverWait(driver, 20);
		    wait.until(ExpectedConditions.elementToBeClickable(element));
		    return element;
		}
	// To take screenshot and stores in Screenshots folder
	public static void takeScreenShot(WebDriver driver, String screenShotName, String tcID, String sheetname) {
		String fileSeparator = "\\";
		try {
			String filePath = path + fileSeparator + "src" + fileSeparator + "test" + fileSeparator + "resources"
					+ fileSeparator + "Screenshots" + fileSeparator + "Results" + fileSeparator + sheetname
					+ fileSeparator + tcID;
			File file = new File(filePath);
			System.out.println("File Name:   " + filePath);
			if (!file.exists()) {
				System.out.println("Create File");
				file.mkdir();
			}
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File(path + fileSeparator + "src" + fileSeparator + "test" + fileSeparator
					+ "resources" + fileSeparator + "Screenshots" + fileSeparator + "Results" + fileSeparator
					+ sheetname + fileSeparator + tcID + fileSeparator + screenShotName + ".jpg");
			FileUtils.copyFile(screenshotFile, targetFile);
		} catch (Exception e) {
			System.out.println("An exception occured while taking screenshot " + e.getCause());
		}
	}
	
	public void clickBack() {
		By backBtn = By.id("com.app.salamtek:id/ivBackToolbar");
		new Actions(driver).click(driver.findElement(backBtn)).perform();
	}

	@AfterSuite
	public void teardown() {
		driver.quit();
	}

	/*
	 * @AfterSuite public void stopAppiumServer() { Runtime runtime =
	 * Runtime.getRuntime(); try { runtime.exec("taskkill /F /IM node.exe");
	 * runtime.exec("taskkill /F /IM cmd.exe"); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 */
}
