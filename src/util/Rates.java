package util;

/**
 * Get the rates per province/territory
 * 
 * @author Brendan
 *
 */
public class Rates {

	/**
	 * Get the pst tax rate depending on the province
	 * 
	 * @param prov The province to get the rate
	 * @return  The pst tax rate
	 */
	public static float getPST(String prov){
		
		float rate = 0.0F;
		
		switch(prov){
			
		case "BC":
			rate = 0.07F;
			break;
			
		case "MB":
			rate = 0.07F;
			break;
			
		case "SK":
			rate = 0.05F;
			break;
			
		case "QC":
			rate = 0.0975F;
			break;
			
		default:
			rate = 0.0F;
			break;
		}
		return rate;
		
	}
	
	/**
	 * Get the hst tax rate depending on the province
	 * 
	 * @param prov The province to get the rate
	 * @return  The hst tax rate
	 */
	public static float getHST(String prov){
		
		float rate = 0.0F;
		
		switch(prov){
			
		case "NB":
			rate = 0.13F;
			break;
			
		case "NL":
			rate = 0.13F;
			break;
			
		case "NS":
			rate = 0.15F;
			break;

		case "ON":
			rate = 0.13F;
			break;
			
		case "PEI":
			rate = 0.14F;
			break;
			
		default:
			rate = 0.0F;
			break;
		}
		return rate;
		
		
	}
	
	/**
	 * Get the gst tax rate depending on the province
	 * 
	 * @param prov The province to get the rate
	 * @return  The gst tax rate
	 */
	public static float getGST(String prov){
		
		float rate = 0.0F;
		
		switch(prov){
		
		case "AB":
			rate = 0.05F;
			break;
			
		case "BC":
			rate = 0.05F;
			break;
			
		case "MB":
			rate = 0.05F;
			break;
			
		case "NT":
			rate = 0.05F;
			break;
			
		case "NU":
			rate = 0.05F;
			break;

		case "SK":
			rate = 0.05F;
			break;
			
		case "QC":
			rate = 0.05F;
			break;
			
		case "YT":
			rate = 0.05F;
			break;
			
		default:
			rate = 0.0F;
			break;
		}
		return rate;
	}

}
