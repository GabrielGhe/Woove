package util;

/**
 * Validates a given credit card number & type
 *  
 *  @author Brendan & Kim
 */
public class CardCheck {

	/**
	 * Verifies if a given credit card number is the appropriate type
	 * if not it will return false 
	 * 
	 * @param ccNumber the given credit card number
	 * @param type the selected type of credit card (visa or mastercard)
	 * @return 
	 */
	 public static boolean validateCard (String ccNumber, String type)
	 {
		 boolean cValid = false;
		 int oddSum=0,evenSum=0,numOfOddPos = 0;
		 int ePosNum,oPosNum;
		 int creditCheck = 0;
		 
		 String cc = ccNumber.replace("-", "").trim();
		 cc = ccNumber.replace(" ", "").trim();
		 
		 //Check the length of the card 
		 if(cc.length() == 16)
		 {
			 for(int i=0;i<cc.length();i+=2)
			 {
				 oPosNum = Integer.parseInt(cc.substring(i,i+1));
				 oddSum+= oPosNum;

				 if(oPosNum > 4)
					 numOfOddPos++;
			 }

			 for(int j=1;j<cc.length();j+=2)
			 {
				 ePosNum = Integer.parseInt(cc.substring(j,j+1));
				 evenSum += ePosNum;
			 }

			 creditCheck = (2*(oddSum)+(evenSum)+(numOfOddPos));
			 
			 if((creditCheck % 10) != 0)
				 return false;
			 
			 //Check Mastercard
			 if(type.equalsIgnoreCase("Mastercard")){
				 if(validateMasterCard(cc))
					 cValid=true;
			 }
			 
			 //Check Visa
			 else if(type.equalsIgnoreCase("Visa")){
				 if(validateVisa(cc))
					 cValid=true;
			 }
		 }
		 return cValid;
	 }
	 
	 /**
	  * Verifies card number for a mastercard
	  * 
	  * @param ccNumber
	  * @return
	  */
	 private static boolean validateMasterCard (String ccNumber)
	  {
	 	 boolean valid = false;
	     String ccNum = ccNumber.substring(0,2);
	     int vNum = Integer.parseInt(ccNum);

		 if(vNum >= 51 && vNum <= 54)
		   valid = true;

		   return valid;
	   }

	 /**
	  * Verifies card number for a visa card
	  * 
	  * @param ccNumber
	  * @return
	  */
	 private static boolean validateVisa (String ccNumber)
	  {
	  	 boolean valid = false;
	      if(ccNumber.charAt(0) == '4')
	         valid = true;

	         return valid;
	  }

	 /**
	  * This method will verify that the given input is valid.
	  * 
	  * @param cardName - the card holders name
	  * @param creditCardNum - the credit card number
	  * @param expirationMonth - the specified month of expiration 
	  * @param expirationYear  - the specified year of expiration 
	  * @param ccSecurityCode  - the given security code 
	  * @return
	  */
	public static boolean validateCard(String cardName, String creditCardNum,
			String expirationMonth, String expirationYear, String ccSecurityCode) {
		
		boolean isValid = true;

		//verify that the parameters are not null & of appropriate length 
		if (cardName == null || cardName.length() > 100)
			isValid = false;
		if (creditCardNum == null || creditCardNum.length() > 60)
			isValid = false;
		if (expirationMonth == null || expirationMonth.length() > 2)
			isValid = false;
		if (expirationYear == null || expirationMonth.length() > 4)
			isValid = false;
		if (ccSecurityCode == null || expirationMonth.length() > 4)
			isValid = false;
		
		//verify that the parameters are the appropriate type
		
		//verify card name is only letters
		if(cardName.matches("\\w+\\.?"))
			isValid = true;
		else
			isValid = false;
		
		//verify credit card number is only numbers
		if(cardName.matches("^\\d"))
			isValid = true;
		else
			isValid = false;
		
		//verify expiration month
		if(expirationMonth.matches("^\\d{2}$"))
			isValid = true;
		else
			isValid = false;
		
		//verify expiration year
		if(expirationYear.matches("^\\d{4}$"))
			isValid = true;
		else
			isValid = false;
		
		
		//verify that the security code is only numbers and is 2-4 characters long
		if (ccSecurityCode.matches("^[0-9]{2,4}$"))
			isValid = true;
		else
			isValid = false;
		
		return isValid;
	}
}

	 