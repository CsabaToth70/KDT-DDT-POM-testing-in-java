package KDT.excelfilehandler;

import KDT.operation.ReadObject;
import KDT.operation.UIOperation;
import junit.framework.AssertionFailedError;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class ReadExcel {
    ReadObject object = new ReadObject();
    Properties allObjects = object.getObjectRepository();
    WebDriver webdriver = new ChromeDriver();
    String path = System.getProperty("user.dir");

    public ReadExcel() throws IOException {
    }

    @Test
    public void readExcelAndExecute() throws Exception {

        //From excel file
        String excelFilePath = path + "/externals/testCases.xlsx";
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        int testCasesCount = workbook.getNumberOfSheets() - 1;

        System.out.println("Number test cases: " + testCasesCount);

        for (int testCase = 0; testCase < testCasesCount; testCase++) {
            WebDriver webdriver = new ChromeDriver();
            UIOperation operation = new UIOperation(webdriver);
            setup(webdriver, operation);

            XSSFSheet worksheet = workbook.getSheetAt(testCase);

            System.out.println("Worksheet number " + testCase + ":" + worksheet.getSheetName());

            int row = worksheet.getLastRowNum();
            int column = worksheet.getRow(0).getLastCellNum();

            for (int i = 1; i <= row; i++) {

                LinkedList<String> testExecution = new LinkedList<>();

                System.out.println("Row value: " + i + "It has first cell value as: " + worksheet.getRow(i).getCell(0));

                for (int j = 0; j < column; j++) {
                    Cell criteria = worksheet.getRow(i).getCell(j);

                    String criteriaText;
                    if (criteria == null) {
                        criteriaText = null;
                    } else {
                        criteriaText = criteria.getStringCellValue();
                    }
                    testExecution.add(criteriaText);

                }

                // or rather from here by used the testExecution list
                System.out.println("List: " + testExecution);
                String testSteps = testExecution.get(0);
                String objectName = testExecution.get(1);
                String locatorType = testExecution.get(2);
                String value = testExecution.get(3);
                operation.perform(allObjects, testSteps, objectName, locatorType, value);
                System.out.println("Row" + i + " is read and action performed");
            }
            // AfterEach
            webdriver.close();
            System.out.println("************************** TEST CASE " + worksheet.getSheetName() + " was executed *****************************");
        }
    }

    private void setup(WebDriver webdriver, UIOperation operation) throws Exception {
        webdriver.manage().window().maximize();
        operation.perform(allObjects, "goToUrl", null, null, "baseUrl");
            operation.perform(allObjects, "wait", "startingPopUp", "id", null);
            operation.perform(allObjects, "cancel", "startingPopUp", "id", null);
    }
}
