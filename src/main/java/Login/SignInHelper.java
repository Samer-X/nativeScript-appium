package Login;

import CommonHelper.CommonHelper;
import DriverSetup.DriverSetup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class SignInHelper extends SignInPo {
    public static AppiumDriver driver = DriverSetup.getDriver();

    public SignInHelper() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickGetStartedButton() {
        CommonHelper.clickWhenReady(Get_Started);
    }

    public void clickUserProfile() {
        CommonHelper.clickWhenReady(userProfile);
    }


    public void longTapUserProfile() {
        CommonHelper.longTap(userProfile);
    }

    public void longTapUserProfile2() {
        CommonHelper.longTapAt(513, 1798);
    }

    public void enterEmail(String email) {
        CommonHelper.clearText(emailField);
        CommonHelper.sendText(emailField, email);
    }

    public void enterPassword(String password) {
        CommonHelper.clearText(passwordField);
        CommonHelper.sendText(passwordField, password);
    }

    public void clickReservation() {
        CommonHelper.clickWhenReady(reservations);
    }

    public void clickAddReservation() {
        CommonHelper.clickWhenReady(addReservation);
    }

    public void clickDropDown() {
        CommonHelper.clickWhenReady(dropDown);
    }

    public int getDropDownSize() {
        return CommonHelper.getDropDownSize(dropDown);
    }

    public String getDropDownSelected() {
        return CommonHelper.getDropDownSelectedOption(dropDown);
    }

    public int getDropDownSelectedIndex() {
        return CommonHelper.getDropDownSelectedIndex(dropDown);
    }

    public void clickUpdateButton() {
        CommonHelper.clickWhenReady(updateButton);
    }

    public void logInSuccessfully(String email, String password) {
        this.enterEmail(email);
        this.enterPassword(password);
        this.clickUpdateButton();
    }

    public File addScreenshot() throws IOException {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        String filename = UUID.randomUUID().toString();
        File DestFile = new File("resources/screenshots/colors/" + filename + ".jpg");
        FileUtils.copyFile(SrcFile, DestFile);
        return DestFile;
    }

    public String getColor(int x, int y) throws IOException {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        String filename = UUID.randomUUID().toString();
        File DestFile = new File("resources/screenshots/colors/" + filename + ".jpg");
        FileUtils.copyFile(SrcFile, DestFile);
        File input = new File(String.valueOf(DestFile));
        BufferedImage image;
        image = ImageIO.read(input);
        Color c = new Color(image.getRGB(x, y));
        String hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
        System.out.println(hex);
        System.out.println(c);
        return hex;
    }

}

