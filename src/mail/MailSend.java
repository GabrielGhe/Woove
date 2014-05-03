package mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import beans.AlbumBean;
import beans.Cart;
import beans.ClientBean;
import beans.InvoiceBean;
import beans.TrackBean;


public class MailSend {

	private MailConfiguration mailConfig;
	private boolean mailSent;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public MailSend(MailConfiguration config)
	{
		this.mailConfig = config;
		mailSent=true;
	}
	
	public boolean sendMail(Cart cart, InvoiceBean ib, ClientBean client) {

		if (mailConfig.isGmailAcc()) {
			mailSent = gmailSend(cart, ib, client);
		} else {
			mailSent = smtpSend(cart, ib, client);
		}
		return mailSent;
	}
	
	private boolean smtpSend(Cart cart, InvoiceBean ib, ClientBean client) {
		Session session = null;
		try {
			// Create a properties object
			Properties smtpProps = new Properties();

			// Add mail configuration to the properties
			smtpProps.put("mail.transport.protocol", "smtp");

			smtpProps.put("mail.smtp.host", mailConfig.getUrlSMTPserver());
			smtpProps.put("mail.smtp.port", mailConfig.getSmtpPortNumber());
			
			if (mailConfig.getIsSmtpAuth()) {
				smtpProps.put("mail.smtp.auth", "true");
				Authenticator auth = new SMTPAuthenticator();
				session = Session.getInstance(smtpProps, auth);
			} else
				session = Session.getDefaultInstance(smtpProps);
			
			// Create a new message
			MimeMessage msg = getMimeMessage(session, client, cart, ib);

			//Send the message
			if (mailConfig.getIsSmtpAuth()) {
				Transport transport = session.getTransport();
				
				transport.connect();
				transport.sendMessage(msg, msg
						.getRecipients(Message.RecipientType.TO));
				transport.close();
			} else{
				Transport.send(msg);
			}
						
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"There is no server at the SMTP address.",
					"SMTP-NoSuchProviderException", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No Such Provider.", e);
			System.exit(1);
			mailSent = false;
		} catch (AddressException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"There is an error in a recipient's address.",
					"SMTP-AddressException", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "Address Error.", e);
			System.exit(1);
			mailSent = false;
		} catch (MessagingException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"There is a problem with the message.",
					"SMTP-MessagingException", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "Error sending message.", e);
			System.exit(1);
			mailSent = false;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"There has been an unknown error.",
					"SMTP-UnknownException", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "Unexpected Error.", e);
			System.exit(1);
			mailSent = false;
		}
	   return mailSent;
	}
	
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			String username = mailConfig.getLoginPOP3Name();
			String password = mailConfig.getPasswordPOP3();

			return new PasswordAuthentication(username, password);
		}
	}
	
	private boolean gmailSend(Cart cart, InvoiceBean ib, ClientBean client) {
		mailSent = true;
		Transport transport = null;
		
		try {
			// Create a properties object
			Properties smtpProps = new Properties();

			// Add mail configuration to the properties
			smtpProps.put("mail.transport.protocol", "smtps");
			smtpProps.put("mail.smtps.host", mailConfig.getUrlSMTPserver());
			smtpProps.put("mail.smtps.auth", "false");
			smtpProps.put("mail.smtps.quitwait", "false");
			
			// Create a mail session
			Session mailSession = Session.getDefaultInstance(smtpProps);
			
			// Instantiate the transport object
			transport = mailSession.getTransport();

			// Create a new message
			MimeMessage msg = getMimeMessage(mailSession, client, cart, ib);

			// Connect and authenticate to the server
			transport.connect(mailConfig.getUrlSMTPserver(), mailConfig
					.getSmtpPortNumber(), mailConfig.getLoginPOP3Name(),
					mailConfig.getPasswordPOP3());

			// Send the message
			transport.sendMessage(msg, msg.getAllRecipients());

			
			// Close the connection
			transport.close();
						
		}catch (NoSuchProviderException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"There is no server at the SMTP address.",
					"Gmail-NoSuchProviderException", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No Such Provider.", e);
			System.exit(1);
			mailSent = false;
		} catch (AddressException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"There is an error in a recipient's address.",
					"Gmail-AddressException", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "Address Error.", e);
			System.exit(1);
			mailSent = false;
		} catch (MessagingException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"There is a problem with the message.",
					"Gmail-MessagingException", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "Messaging Error.", e);
			System.exit(1);
			mailSent = false;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"There has been an unknown error.",
					"Gmail-UnknownException", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "Unexpected Error.", e);
			System.exit(1);
			mailSent = false;
		}
		
		return mailSent;
	}
	
	/**
	 * Create a MimeMessage based on the session and mail message
	 * 
	 * @param mmd   The email data
	 * @param session  The mail session
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	private MimeMessage getMimeMessage(Session session, ClientBean client, Cart cart, InvoiceBean ib) throws AddressException, MessagingException
	{
		MimeMessage msg = new MimeMessage(session);
		
		msg.setFrom(new InternetAddress("Woove@woovemusic.com"));
		
		// Set the To, CC, and BCC from their ArrayLists
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					client.getEmail(), false));

		// Set the subject line
		msg.setSubject("Woove Transaction: " + ib.getDate());
	
		// Set the message body
		String receipt = "Thank you for making a purchase from Woove" + "\n" + 
						 "The following is the receipt of your purchase" + "\n";
		
		for(TrackBean t: cart.getTracks())
			receipt += "\n" + "Item Name: " + t.getTitle() + "      Price: " + 
					(t.getSale_price()!=0?t.getSale_price():t.getList_price());
					
		
		for(AlbumBean a: cart.getAlbum())
			receipt += "\n" + "Item Name: " + a.getTitle() + "      Price: " + 
					(a.getSalePrice()!=0?a.getSalePrice():a.getListPrice());
					
			
		receipt += "\n" + "\n" + "Subtotal: " + ib.getTotal_net_value() + 
				"\n" + "Tax: " + (ib.getGst() + ib.getPst()) + 
				"\n" + "Total: " + ib.getTotal_gross_value() + "\n" + 
				"\n" + "Thank You for shopping at Woove!";
		
		msg.setText(receipt);

		// Set some other header information
		msg.setHeader("X-Mailer", "Comp Sci Tech Mailer");
		msg.setSentDate(new Date());
		
		return msg;
	}
}
