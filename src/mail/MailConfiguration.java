package mail;

public class MailConfiguration {

	  private String userName; 
	  private String urlSMTPserver;
	  private String loginPOP3Name;
	  private String passwordPOP3;
	  private int pop3PortNumber;
	  private int smtpPortNumber;
	  private boolean gmailAcc;
	  private boolean isSmtpAuth;
	
	public MailConfiguration(){
		  userName = "Woove Music Store" ; 
		  urlSMTPserver = "waldo2.dawsoncollege.qc.ca" ;
		  loginPOP3Name = "g02w13" ;
		  passwordPOP3 = "neptune7cat" ;
		  pop3PortNumber = 110;
		  smtpPortNumber = 25;
		  gmailAcc = false;
		  isSmtpAuth = true;
	}

	public MailConfiguration(String userName, String userEmail,
			String urlPOP3server, String urlSMTPserver, String userServerName,
			String userServerPassword, int pop3PortNumber,
			int smtpPortNumber, boolean gmailAcc, boolean isSmtpAuth) {

		this.userName = userName;
		this.urlSMTPserver = urlSMTPserver;
		this.loginPOP3Name = userServerName;
		this.passwordPOP3 = userServerPassword;
		this.pop3PortNumber = pop3PortNumber;
		this.smtpPortNumber = smtpPortNumber;
		this.gmailAcc = gmailAcc;
		this.isSmtpAuth = isSmtpAuth;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrlSMTPserver() {
		return urlSMTPserver;
	}

	public void setUrlSMTPserver(String urlSMTPserver) {
		this.urlSMTPserver = urlSMTPserver;
	}

	public String getLoginPOP3Name() {
		return loginPOP3Name;
	}

	public void setLoginPOP3Name(String userServerName) {
		this.loginPOP3Name = userServerName;
	}

	public String getPasswordPOP3() {
		return passwordPOP3;
	}

	public void setPasswordPOP3(String userServerPassword) {
		this.passwordPOP3 = userServerPassword;
	}

	public int getPop3PortNumber() {
		return pop3PortNumber;
	}

	public void setPop3PortNumber(int pop3PortNumber) {
		this.pop3PortNumber = pop3PortNumber;
	}

	public int getSmtpPortNumber() {
		return smtpPortNumber;
	}

	public void setSmtpPortNumber(int smtpPortNumber) {
		this.smtpPortNumber = smtpPortNumber;
	}

	public boolean isGmailAcc() {
		return gmailAcc;
	}

	public void setGmailAcc(boolean gmailAcc) {
		this.gmailAcc = gmailAcc;
	}
	
	public Boolean getIsSmtpAuth() {
		return isSmtpAuth;
	}

	public void setIsSmtpAuth(Boolean isSmtpAuth) {
		this.isSmtpAuth = isSmtpAuth;
	}

	@Override
	public String toString()
	{
		return "User Name: " + userName + "\n" +
				"Login Name: " + loginPOP3Name + "\n" +
				"Login password: " + passwordPOP3 + "\n"+
				"SMTP Server: " + urlSMTPserver;
	}
	
	

}
