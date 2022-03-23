package Login;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;


public class SignInPo extends SignInConstants {

/*	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@text='GET STARTED']"),
			@FindBy(how = How.XPATH, using = "Enter username")
	})
	static WebElement Android_Get_Started;*/


    @FindBy(xpath = "//*[@text='GET STARTED']")
    static MobileElement Get_Started;

    @FindBy(xpath = "//*[@text='User profile']")
    static MobileElement userProfile;

    @FindBy(xpath = "//*[@text='Username']/following-sibling::*")
    static MobileElement userName;

    @FindBy(xpath = "//*[@text='Email']/following-sibling::*")
    static MobileElement emailField;

    @FindBy(xpath = "//*[@text='Password']/following-sibling::*")
    static MobileElement passwordField;

    @FindBy(xpath = "//*[@text='UPDATE']")
    static MobileElement updateButton;

    @FindBy(xpath = "//*[@text='Reservations']")
    static MobileElement reservations;

    @FindBy(xpath = "(//android.widget.ImageView[@class='android.widget.ImageView'])[2]")
    static MobileElement addReservation;

    @FindBy(xpath = "(//android.widget.Spinner)[3]")
    static MobileElement dropDown;

}
