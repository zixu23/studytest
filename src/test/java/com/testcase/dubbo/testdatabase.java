package com.testcase.dubbo;

import com.newretail.logisticscenter.addressbasecenter.client.service.AddressbaseService;
import jxl.read.biff.BiffException;
import net.sf.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tools.excelread.GetTestCaseExcel;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author  sunmm
 * Created Time  2018/2/9 14:32
 */
public class testdatabase {
    private AddressbaseService ClassConsumer;
    private String FILE_PATH = "/src/test/TestCaseExcelData/dubbo/";  //文件路径src/testExampleForJmeterData/GetTestCaseExcel/dubbo
    private GetTestCaseExcel excelData;
    private String fileName = "com.newretail.logisticscenter.addressbasecenter.client.service.AddressbaseService"; //dubbo接口的包名作为文件名，不包含文件后缀.xls
    private String caseName = "queryFirstLevelAddress"; //dubbo接口名作为sheet名

    @BeforeClass
    private void setUp() {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "dubbo-config.xml");
            context.start();
            ClassConsumer=(AddressbaseService)context.getBean("AddressbaseService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //不需要修改：dubbo接口访问Provider
    @Test(dataProvider = "Numbers")
    public void test(HashMap<String, String> data) {
        String county = data.get("county");
        String status = data.get("code");
        String message = data.get("message");

        //requestParam=county;
        Object responseData = ClassConsumer.queryFirstLevelAddress(county);
        JSONObject resJson = JSONObject.fromObject(responseData);

        if (responseData!=null) {
            String responseStatus = resJson.getString("code");
            String responseMessage =resJson.getString("message");
            String Data=resJson.getString("data");
            System.out.println(Data);
            if (responseStatus.equals(status) && responseMessage.equals(message)) {
                Reporter.log("期望的Status：" + status + "，期望的Message：" + message + "\n" + "实际的Status：" + responseStatus + "，实际的Message：" + responseMessage);
                System.out.println("期望的Status：" + status + "，期望的Message：" + message + "\n" + "实际的Status：" + responseStatus + "，实际的Message：" + responseMessage);
                Assert.assertTrue(true);

            } else {
                Reporter.log("期望的Status：" + status + "，期望的Message：" + message + "\n" + "实际的Status：" + responseStatus + "，实际的Message：" + responseMessage);
                System.out.println("期望的Status：" + status + "，期望的Message：" + message + "\n" + "实际的Status：" + responseStatus + "，实际的Message：" + responseMessage);
                Assert.assertTrue(false);

            }
        } else {
            Reporter.log("responseData为空");
            Assert.assertTrue(false);
        }
    }

    @DataProvider
    public Object[][] Numbers() throws BiffException, IOException {
        excelData = new GetTestCaseExcel(FILE_PATH, fileName, caseName);
        return excelData.getExcelData();
    }
}
