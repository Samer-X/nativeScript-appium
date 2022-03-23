package DriverSetup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSetup {

    public static AndroidDriver<MobileElement> driver = null;

    @BeforeTest
    public static void driverSetup() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName", "HUAWEI nova 3i");
      //  dc.setCapability("udid", "2JN7N18C28008549");    // real device
      //  dc.setCapability("platformVersion", "9.0.0");    // real device

        dc.setCapability("udid", "emulator-5554");       // emulator
        dc.setCapability("platformVersion", "11.0.0");   // emulator

        dc.setCapability("appPackage", "org.nativescript.examples");
        dc.setCapability("appActivity", "com.tns.NativeScriptActivity");


     //   dc.setCapability("appPackage", "com.atos.mdi.staging");
     //   dc.setCapability("appActivity", "com.atos.mdi.features.subscription.home.HomeActivity");

        dc.setCapability("platformName", "Android");
        dc.setCapability("automationName", "UiAutomator2");
        dc.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.18.0");
        dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);

        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(url, dc);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static AndroidDriver<MobileElement> getDriver() {
        return driver;
    }


}
