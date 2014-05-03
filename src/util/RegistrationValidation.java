package util;

import beans.ClientBean;
/**
 * Validates the clients submitted registration information
 *  
 *  @author Kim
 */
public class RegistrationValidation {

	public String clientValidation(ClientBean client, String passwordTwo) {
		String errorString = "";

		errorString = validateClientInfo(client);

		//verify if there was no error while validating the client info
		if (!errorString.equals(""))
			return errorString;
		//verify if there was no error while validating the appropriate lengths
		errorString = checkLength(client);
		if (!errorString.equals(""))
			return errorString;
		//verify the e-mail address
		if (!emailValidation(client.getEmail())) {
			errorString = "E-mail is invalid";
			return errorString;
		}
		//verify that the postal code is in the correct format 
		if (!postalCodeValidation(client.getPostalCode())) {
			errorString = "Postal Code is invalid";
			return errorString;
		}
		//verify that the telephone number is in the correct format 
		if (!telephoneNumberValidation(client.getHomePhone())) {
			errorString = "Home Telephone Number is invalid";
			return errorString;
		}
		//verify that the cellphone number is in the correct format 
		if(client.getCellPhone() !=null){
			if (!telephoneNumberValidation(client.getCellPhone())) {
				errorString = "Cellphone Number is invalid";
				return errorString;
			}
		}
		//verify that the passwords match
		if (!passwordValidation(client.getPassword(), passwordTwo)) {
			errorString = "Passwords do not match";
			return errorString;
		}
		

		return errorString;


	}

	/**
	 *  This method will verify that all the contact info is within the appropriate lengths 
	 *  
	 * @param client
	 * 
	 * @return a message with which field is invalid
	 */
	public String checkLength(ClientBean client) {
		String errorString = "";

		//missing home & cell #, password, email, last search
		if (client.getTitle().length() > 4)
			errorString = "Title length too long" ;
		else{
			if (client.getFirstName().length() > 100)
				errorString = "First Name length too long" ;
			else{
				if (client.getLastName().length() > 100)
					errorString = "Last Name length too long" ;
				else{
					if (client.getLastName() != null){
						if (client.getCompanyName().length() > 100)
							errorString = "Company length too long" ;
					}
					else{
						if (client.getAddress1().length() > 200)
							errorString = "Address1 length too long" ;
						else{
							if (client.getAddress2() != null){
								if (client.getAddress2().length() > 200)
									errorString = "Address2 length too long" ;
							}
							else{
								if (client.getCity().length() > 100)
									errorString = "City length too long";
								else{
									if (client.getProvince().length() > 45)
										errorString = "Province length too long" ;
									else{
										if (client.getCountry().length() > 45)
											errorString = "Country length too long";
										else{
											if ( client.getPostalCode().length() != 6)
												errorString = "Postal Code length incorrect" ;

										}
									}

								}
							}

						}

					}

				}
			}
		}

		return errorString;
	}

	/**
	 * This method will validate that all the mandatory personal info 
	 * is not equal to null.
	 * 
	 * @param client
	 * @return
	 */
	public String validateClientInfo(ClientBean client) {

		if (client.getTitle() == null)
			return "Invalid title";
		if (client.getLastName() == null)
			return "Invalid last name";
		if (client.getFirstName() == null)
			return "Invalid first name";
		if (client.getAddress1() == null)
			return "Invalid primary address";
		if (client.getCity() == null)
			return "Invalid city";
		if (client.getProvince() == null)
			return "Invalid province";
		if (client.getCountry() == null)
			return "Invalid country";
		if (client.getPostalCode() == null)
			return "Invalid postal code";
		if (client.getHomePhone() == null)
			return "Invalid home phone";
		if (client.getEmail() == null)
			return "Invalid email";
		if (client.getPassword() == null)
			return "Invalid password";

		return "";
	}

	/**
	 * This method will verify that the e-mail address is correct
	 * 
	 * @param email - the submitted e-mail address
	 * @return
	 */
	private boolean emailValidation(String email) {
		boolean isValid = false;

		//verify that the e-mail is not null
		if (email != null)
			//verify that the e-mail is in the correct format 
			if (email
					.matches("^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$"))
				isValid = true;
		return isValid;
	}

	/**
	 * This method will verify that the two passwords match
	 * 
	 * @param passwordOne - the first password
	 * @param passwordTwo - the second password
	 * @return
	 */
	private boolean passwordValidation(String passwordOne, String passwordTwo) {
		boolean isMatched = false;

		//verify that the passwords are not null
		if(passwordOne!=null || passwordTwo!=null){
			//verify that the passwords match 
			if (passwordOne.equals(passwordTwo))
				isMatched = true;
		}

		return isMatched;
	}

	/**
	 * This method will verify that the postal is in the correct format
	 * 
	 * @param postalCode - the submitted postal code
	 * @return
	 */
	private boolean postalCodeValidation(String postalCode) {
		boolean isValid = false;

		//verify that the postal code is not null
		if (postalCode != null)
			//verify that the postal matches the correct format 
			if (postalCode.matches("^(\\D\\d\\D)(\\d\\D\\d)$"))
				isValid = true;

		return isValid;
	}

	/**
	 * This method will verify that the phone number in the correct format 
	 * 
	 * @param phoneNumber - the submitted phone number 
	 * @return
	 */
	private boolean telephoneNumberValidation(String telephoneNumber) {
		boolean valid = false;

		//verify that the telephone number is not null
		if (telephoneNumber != null){
			telephoneNumber = telephoneNumber.replaceAll(" ", "");
			//verify that the telephone number is the correct format
			if (telephoneNumber.matches("^\\((\\d{3})\\)(\\d{3})[-](\\d{4})$"))
				valid = true;
		}
		return valid;
	}

}
