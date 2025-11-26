package Utils;

import org.joda.time.DateTime;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Common.BaseTest;

public class ExtentManager extends BaseTest{
public static final ExtentReports extentReports = new ExtentReports();
	
	public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter;
        DateTime dt = new DateTime();
		try {
		reporter = new ExtentSparkReporter(System.getProperty("user.dir")  + "/extent-reports/ExecutionReport_"+getTodayDateInYYYYMMDD()+"_"+dt.getHourOfDay()+"_"+dt.getMinuteOfHour()+"_"+dt.getSecondOfMinute()+".html");
        reporter.config().setReportName("Execution Report");
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Software Name", "Salamtek");
        extentReports.setSystemInfo("Author", "Geethu");
        reporter.config().setTheme(Theme.DARK);
    }catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		  return extentReports;
	}
}
