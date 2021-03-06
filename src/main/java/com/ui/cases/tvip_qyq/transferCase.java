package com.ui.cases.tvip_qyq;

import com.ui.base.Base;
import com.ui.util.AssertUtil;
import com.ui.util.ZTestReport;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({ZTestReport.class})
public class transferCase extends Base
{
    private Logger logger = Logger.getLogger(transferCase.class);
    public static String url = "https://tvip.nucarf.cn";

    @BeforeClass(enabled = false)
    public void Login() throws InterruptedException {
        driver.navigate().to(url);
        sendKeys(getElement("登陆页", "手机号"), "18311447530");
        sendKeys(getElement("登陆页", "密码"), "an1234");
        click(getElement("登陆页", "登陆按钮"));
        Thread.sleep(2000);
    }

    @Test(description = "正确企业转账",priority = 0)
    public void transfer() throws InterruptedException {
        String chargeMoney = "4";
        click(getElement("公用", "首页"));
        Thread.sleep(1000);
        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double kyMoney = doubleNum(getText(getElement("公用", "可用余额")));

        Thread.sleep(1000);
        click(getElement("企业转账","企业结算管理"));
        click(getElement("企业转账","企业账户转账"));
        click(getElement("企业转账","新加转账企业"));
        sendKeys(getElement("企业转账","企业名称"),"王文静测试企业");
        sendKeys(getElement("企业转账","企业万金油账号"),"3532460016");
        sendKeys(getElement("企业转账","转账金额"),chargeMoney);
        click(getElement("企业转账","转账"));
        click(getElement("企业转账","确定"));
        Thread.sleep(1000);
        double altransferMoney = doubleNum(getText(getElement("企业转账", "实际转账金额")));
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);

        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double alkyMoney = doubleNum(getText(getElement("公用", "可用余额")));

        double extotalMoney =totalMoney-doubleNum(chargeMoney);
        double exkyMoney= kyMoney-doubleNum(chargeMoney);

        AssertUtil.assertNumEquals(altransferMoney,doubleNum(chargeMoney));
        AssertUtil.assertNumEquals(alkyMoney,exkyMoney);
        AssertUtil.assertNumEquals(altotalMoney,extotalMoney);

    }
    @Test(description = "超额企业转账",priority = 1)
    public void overtransfer() throws InterruptedException {
        click(getElement("公用", "首页"));
        Thread.sleep(2000);
        double totalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double kyMoney = doubleNum(getText(getElement("公用", "可用余额")));

        Thread.sleep(1000);
        click(getElement("企业转账","企业结算管理"));
        click(getElement("企业转账","企业账户转账"));
        click(getElement("企业转账","新加转账企业"));
        sendKeys(getElement("企业转账","企业名称"),"李垚测试企业");
        sendKeys(getElement("企业转账","企业万金油账号"),"3365506444");
        sendKeys(getElement("企业转账","转账金额"),String.valueOf(totalMoney+1));
        click(getElement("企业转账","转账"));
        click(getElement("公用", "首页"));
        click(getElement("公用", "运营数据"));
        driver.navigate().refresh();
        Thread.sleep(2000);

        double altotalMoney = doubleNum(getText(getElement("公用", "万金油系统余额")));
        double alkyMoney = doubleNum(getText(getElement("公用", "可用余额")));

        double extotalMoney =totalMoney;
        double exkyMoney= kyMoney;
        AssertUtil.assertNumEquals(alkyMoney,exkyMoney);
        AssertUtil.assertNumEquals(altotalMoney,extotalMoney);
        LoginOut();
    }



}
