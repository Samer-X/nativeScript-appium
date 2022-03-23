package TestCases;


import CommonHelper.CommonHelper;
import DriverSetup.DriverSetup;
import Login.SignInHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.io.IOException;

public class Login_TC extends DriverSetup {


/*    @BeforeClass
    public void setup() throws Exception {
        DriverSetup.driverSetup();
    }*/


   // @Test(priority = 1)
    public void SignIn() throws IOException, InterruptedException {
        SignInHelper signinHelper = new SignInHelper();
        signinHelper.clickGetStartedButton();
        signinHelper.clickUserProfile();
        Thread.sleep(1000);
        //signinHelper.logInSuccessfully("test@test.com", "Password");
        CommonHelper.compareImages("LoginPage");
        // CommonHelper.openFirstMessage();
        // CommonHelper.openNotifications();
        //CommonHelper.openCamera();
    }

    @Test(priority = 1)
    public void testMDIApp() throws IOException, InterruptedException {
        Thread.sleep(3000);
        CommonHelper.compareImages("HomePage");



    }

}
