package cn.catguild.system.infrastructure.message.impl;

import cn.catguild.system.SystemAppTest;
import cn.catguild.system.infrastructure.message.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

public class EmailServiceImplTest extends SystemAppTest {

    @Autowired
    private EmailService emailService;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    void sendHtmlMail() {
        Context context = new Context();
        //HashMap dataMap = JSONUtil.toBean(JSONUtil.parseObj(warningDocument), HashMap.class);
        //context.setVariables(dataMap);
        context.setLocale(Locale.SIMPLIFIED_CHINESE);
        String htmlContent = templateEngine.process("email/tenantRegisterTemplate.html", context);
        emailService.sendHtmlMail("2659712343@qq.com","猫公会租户注册",htmlContent);
    }

}