package cn.catguild.system.infrastructure.message;

import java.util.Map;

/**
 * @author xiyan
 * @date 2023/8/10 11:21
 */
public interface EmailService {

    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     *
     * @param address 收件人
     * @param subject 主题
     * @param id      模版id
     * @param tpParam 模版参数
     */
    void sendHtmlTpMail(String address, String subject, Long id, Map<String, String> tpParam);

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

}
