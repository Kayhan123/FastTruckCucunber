package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;
import utility.ConfigurationReader;
import utility.Driver;

public class LoginTest {

    Login login = new Login();
    @Given("user go to the login page")
    public void user_go_to_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("login_url"));

    }
    @When("user enters correct credentials")
    public void user_enters_correct_credentials() {
        String username = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");

        login.userNameInput.sendKeys(username);
        login.passwordInput.sendKeys(password);
        WebDriverWait wait= new WebDriverWait(Driver.getDriver(),3);
        login.loginBtn.click();
    }
    @Then("user should be in {string}")
    public void user_should_be_in(String string) {

        String actualTitle = login.title.getText();
        Assert.assertEquals(string,actualTitle);

    }

    @When("user enters wrong credentials")
    public void user_enters_wrong_credentials() {
        String username = ConfigurationReader.getProperty("username")+ "1";
        String password = ConfigurationReader.getProperty("password")+ "1";

        login.userNameInput.sendKeys(username);
        WebDriverWait wait= new WebDriverWait(Driver.getDriver(),3);
        login.passwordInput.sendKeys(password);
        new WebDriverWait(Driver.getDriver(),3);
        login.loginBtn.click();
    }
    @Then("user should be at {string}")
    public void user_should_be_at(String string) {
        WebDriverWait wait= new WebDriverWait(Driver.getDriver(),3);

        String actualTitle = login.title.getText();
        Assert.assertEquals(string,actualTitle);
    }
}
