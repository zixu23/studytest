package com.testcase;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author sunmm
 * Created Time 2018/2/23 16:28
 */
public class MailAuthenticator extends Authenticator {
    public static String USERNAME = "";
    public static String PASSWORD = "";

    public MailAuthenticator() {
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(USERNAME, PASSWORD);
    }
}
