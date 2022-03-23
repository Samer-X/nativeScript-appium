package StepDefinition;

import Login.SignInHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import mobile.SignIn;
import mobile.SignInAndroid;
import mobile.SignInIOS;

import java.io.IOException;

public class StepDefinition {

    SignInHelper signInHelper;
    SignIn x;
    String env = "IOS";

    public StepDefinition() {
        signInHelper = new SignInHelper();
        if (env == "Android") {
            x = new SignInAndroid();
        } else if (env == "IOS") {
            x = new SignInIOS();
        }
    }

    @Given("As a User I Will Enter user profile")
    public void enterUserProfileMode() {
        x.clickAll();
        signInHelper.clickGetStartedButton();
        signInHelper.clickUserProfile();
    }

    @Given("I Enter Valid email {string} and Valid Password {string}")
    public void Login(String email, String password) {
        signInHelper.logInSuccessfully(email, password);
    }

    @Then("I Should Be Redirected To Home Page")
    public void verifyHomePageIsOpened() {
    }

    @Then("Get Color")
    public void getColor() throws IOException {
        signInHelper.getColor(340, 1977);
    }
}

