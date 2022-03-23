package CommonHelper;

import DriverSetup.DriverSetup;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CommonHelper {

    public static MobileElement mobileElement;
    static String filename = null;
    public static AndroidDriver<MobileElement> driver = DriverSetup.getDriver();


    public static void clickWhenReady(WebElement Po) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(Po));
        Po.click();
    }

    public static void longTap(WebElement Po) {

        new Actions(driver).clickAndHold(Po).release().perform();
    }

    public static void longTapAt(Integer x, Integer y) {

        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(x, y)).release().perform();


    }

    public static boolean isDisplayed(MobileElement element) {
        boolean check = false;
        try {
            check = element.isDisplayed();
            return check;
        } catch (Exception e) {
            return false;
        }
    }

    public static MobileElement getWhenVisible(MobileElement Po, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(Po));
        return Po;
    }

    public static void WaitTillVisible(MobileElement Po, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(Po));
    }

    public static void NavigateToPage(MobileElement page) {
        page.click();
    }

    public static void clickByText(AndroidDriver driver, String text) {

        mobileElement = (MobileElement) driver.findElementByAndroidUIAutomator(text);
        mobileElement.click();
    }

    public static void clickByXpath(AndroidDriver driver, String text) {

        mobileElement = (MobileElement) driver.findElement(By.xpath(text));
        mobileElement.click();
    }

    public static void sendByText(AndroidDriver driver, String text, String input) {

        mobileElement = (MobileElement) driver.findElementByAndroidUIAutomator(text);
        mobileElement.click();
        driver.getKeyboard().sendKeys(input);

    }

    public static void sendByXpath(AndroidDriver driver, String text, String input) {

        mobileElement = (MobileElement) driver.findElement(By.xpath(text));
        mobileElement.click();
        driver.getKeyboard().sendKeys(input);

    }

    public static MobileElement returnSelected(AndroidDriver driver, String text) {

        mobileElement = (MobileElement) driver.findElementByAndroidUIAutomator(text);
        return mobileElement;
    }

    public static void sendText(MobileElement Po, String Text) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(Po));
        CommonHelper.clickWhenReady(Po);
        driver.getKeyboard().sendKeys(Text);
        driver.hideKeyboard();
    }

    public static void hideKeyBoard() {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
    }

    public static String GetText(MobileElement Po) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(Po));
        return Po.getText();
    }

    public static void screenshot() throws IOException {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        filename = UUID.randomUUID().toString();
        File targetFile = new File("resources/screenshots/actualResult/" + filename + ".jpg");
        FileUtils.copyFile(srcFile, targetFile);
    }

    public static String actualScreenshot() throws IOException {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        filename = UUID.randomUUID().toString();
        File targetFile = new File("resources/screenshots/actualResult/" + filename + ".jpg");
        FileUtils.copyFile(srcFile, targetFile);
        return String.valueOf(targetFile);
    }

    public static void compareImages(String imageName) throws IOException {
        //load images to be compared:
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(CommonHelper.actualScreenshot());
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources("resources/screenshots/expectedResult/" + imageName + ".jpg");
        // where to save the result
        File resultDestination = new File("resources/screenshots/comparison/" + imageName + "-comparison.jpg");
        //Create ImageComparison object and compare the images.
        ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage, resultDestination).compareImages();
        //Check the result
        Assert.assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
    }

    public static void clearText(WebElement element) {
        element.clear();
    }


    public static int getDropDownSize(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        List dropDownList = driver.findElements(By.className("android.widget.CheckedTextView"));
        element.click();
        return dropDownList.size() + 1;
    }


    public static String getDropDownSelectedOption(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public static int getDropDownSelectedIndex(WebElement element) {
        int count = 0;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        List options = select.getOptions();
        WebElement selected = select.getFirstSelectedOption();
        for (count = 0; count < options.size(); count++) {
            if (selected == options.get(count)) {
                break;
            }
        }
        return count;
    }

    public static void openFirstMessage() throws InterruptedException {
        String appPackage = driver.getCurrentPackage();
        String appActivity = driver.currentActivity();
        driver.startActivity(new Activity("com.google.android.apps.messaging", "com.google.android.apps.messaging.home.HomeActivity"));
        //  driver.findElement(By.xpath("(//*[@text='Vodafone365'])[1]")).click();
        Thread.sleep(5000);
        driver.navigate().back();
        //   driver.startActivity(new Activity(appPackage, appActivity));
        // get text and use start activity to return to the app with otp
    }

    public static void openNotifications() {
        driver.openNotifications();
        //click on first notification and go to messages to get otp
    }

    public static void openCamera() {
        driver.startActivity(new Activity("com.huawei.camera", "com.huawei.camera.controller.CameraActivity"));
/*        driver.press(AndroidKeyCode.SPACE, AndroidKeyMetastate.META_SHIFT_ON);
        driver.pressKeyCode(AndroidKeyCode.BACK);*/

    }

    public static void switchContextView() throws InterruptedException {

        // if the app is hybrid this method is to switch between its view native view and webview

        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }
        driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1

        //do some web testing
        Thread.sleep(3000);
        // driver2.findElement(By.id("com.atos.mdi.development:id/landingStatusCAButton")).click();

        driver.context("NATIVE_APP");
    }
}
