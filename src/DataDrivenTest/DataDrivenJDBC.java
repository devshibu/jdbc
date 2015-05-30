package DataDrivenTest;

import java.sql.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



/**
 * Created by Shibu on 5/30/2015.
 */
public class DataDrivenJDBC {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception{
        driver = new FirefoxDriver();
        driver.get("http://newtours.demoaut.com/mercurywelcome.php");
        driver.manage().window().maximize();
    }

    @Test
    public void CreateDB() throws Exception{
        String url ="jdbc:mysql:///peoplentech";
        String dbClass = "com.mysql.jdbc.Driver";
        Class.forName(dbClass).newInstance();
        Connection con = DriverManager.getConnection(url,"root", "sddev");
        Statement stmt = (Statement) con.createStatement();
        ResultSet result = (ResultSet) stmt.executeQuery("SELECT * FROM students");
        while(result.next()){

            String id = result.getString(1);
            String firstname = result.getString(2);
            String lastname = result.getString(3);
            String phone = result.getString(4);
            String email = result.getString(5);
            String username = result.getString(6);
            String password = result.getString(7);

            driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a")).click();
            driver.findElement(By.name("firstName")).sendKeys(firstname);
            driver.findElement(By.name("lastName")).sendKeys(lastname);
            driver.findElement(By.name("phone")).sendKeys(phone);
            driver.findElement(By.name("userName")).sendKeys(email);

            new Select(driver.findElement(By.name("country"))).selectByVisibleText("UNITED STATES");
            driver.findElement(By.name("email")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.name("confirmPassword")).sendKeys(password);
            driver.findElement(By.name("register")).click();

            Thread.sleep(5);
            driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[2]/font/a[1]")).click();

            driver.findElement(By.name("userName")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.name("login")).click();

            Thread.sleep(5);
            driver.findElement(By.name("findFlights")).click();
            driver.findElement(By.name("reserveFlights")).click();
            driver.findElement(By.name("passFirst0")).sendKeys(firstname);
            driver.findElement(By.name("passLast0")).sendKeys(lastname);
            driver.findElement(By.name("creditnumber")).sendKeys("1234567890987654");
            driver.findElement(By.name("buyFlights")).click();
            driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[7]/td/table/tbody/tr/td[2]/a/img")).click();

            System.out.println("Test Passed for: " + firstname);
        }
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }


}

