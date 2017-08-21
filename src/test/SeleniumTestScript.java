/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package seleniumtestscript;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 *
 * @author user
 */
public class SeleniumTestScript {

    public WebDriver driver;
            
            
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        // TODO code application logic here
        
        SeleniumTestScript obj = new SeleniumTestScript();
        obj.runTest();
       
    }
    
    public void runTest() throws InterruptedException{
        boolean flag = false;
        assert flag == true;
        
        System.setProperty("webdriver.chrome.driver", "E:\\Jars\\chromedriver_win32\\chromedriver.exe");
        
        driver = new ChromeDriver();

        driver.navigate().to("http://localhost:8080/VehicleDummyWebpage/");

        String appTitle = driver.getTitle();

        System.out.println("Application title is : "+appTitle);
        
        Map<String, List<String>> objTable = readTable();
        System.out.println("HashMap 1 containing data of Vehicle Dummy Webpage:" + objTable);
       
        if((flag = compareSourceData(objTable))== true){
            System.out.println("Validating Vehicle Dummy Webpage Data from CSV File:" + flag);
        }
        else{
            System.out.println("Validating Vehicle Dummy Webpage Data from CSV File:" + flag + "  No match Found");
        }
        
        
        Thread.sleep(200);

        driver.quit();
    }
    
    public Map<String, List<String>> readTable(){

        Map<String, List<String>> objTable = new HashMap<>();

        List<WebElement> objRows = driver.findElements(By.cssSelector("tr#data"));
        for(int iCount=0; iCount<objRows.size(); iCount++){
            List<WebElement> objCol = objRows.get(iCount).findElements(By.cssSelector("td.tableTxt"));
            List<String> columns = new ArrayList<>();
            for(int col=0; col<objCol.size(); col++){
                columns.add(objCol.get(col).getText());
            }
            objTable.put(String.valueOf(iCount), columns);
        }
        return objTable;
    }
    
    public boolean compareSourceData(Map<String, List<String>> objTable){
        String csvFile = "file.csv";
        String line = "";
        String cvsSplitBy = ",";
        
        Map<String, List<String>> objTable1 = new HashMap<>();
        List<VehicleDTO> data = new ArrayList<>();
        System.out.println("Reading From CSV File:");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            line = br.readLine();
           
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] str = line.split(cvsSplitBy);
                System.out.println(str[0] + "," + str[1] + "," + str[2]);
                VehicleDTO obj = new VehicleDTO();
                obj.setId(str[0]);
                obj.setVehicleName(str[1]);
                obj.setVehicleColor(str[2]);
                data.add(obj);
            }
            for(int iCount=0;iCount<data.size();iCount++){
                List<String> arr = new ArrayList<>();
                arr.add(data.get(iCount).getId());
                arr.add(data.get(iCount).getVehicleName());
                arr.add(data.get(iCount).getVehicleColor());
                objTable1.put(String.valueOf(iCount), arr);
            }
            
            System.out.println("HashMap 2 containing data of CSV file:" + objTable1);
            boolean flag = objTable.equals(objTable1);
            if(flag){
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
