package Utils;

import io.appium.java_client.remote.MobileCapabilityType;

public interface MobileCapabilityTypeEx extends MobileCapabilityType{
	
	String AUTOMATION_NAME = "automationName";
	
	String PLATFORM_NAME = "platformName";
	
	String PLATFORM_VERSION = "platformVersion";
	
	String DEVICE_NAME = "deviceName";
	
	String UDID = "udid";
	
	String NEW_COMMAND_TIMEOUT = "newCommandTimeout";
	
	String APP_PACKAGE = "appPackage";
	
	String APP_ACTIVITY = "appActivity";
	
	String NO_RESET = "noReset";
	
	String FULL_RESET = "fullReset";
	
	String APP_WAIT_PACKAGE = "appWaitPackage";
	
	String APP_WAIT_ACTIVITY = "appWaitActivity";
	
	String ADB_EXEC_TIMEOUT = "appium:adbExecTimeout";
	
	String CHROME_EXEUTABLE = "chromedriverExecutable";
	
}
