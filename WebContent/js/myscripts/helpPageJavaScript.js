/* Kimberly Noel	0932295
 helpPageJavascript.js */

function switchContent(thetext){

	var headhertext, contenttext;

	if(thetext === "albumlink"){
		headertext = "Album Page";
		contenttext = "This is the help for the album page."
	}

	if(thetext === "tracklink"){
		headertext = "Track Page";
		contenttext = "This is the help for the track page."
	}

	if(thetext === "loginlink"){
		headertext = "Login Page";
		contenttext = "This is the help for the login page."
	}

	if(thetext === "reglink"){
		headertext = "Registration Page";
		contenttext = "This is the help for the registration page."
	}

	if(thetext === "profilelink"){
		headertext = "Profile Page";
		contenttext = "This is the help for the profile page."
	}

	if(thetext === "searchlink"){
		headertext = "Search Page";
		contenttext = "This is the help for the search page."
	}

	if(thetext === "checkoutlink"){
		headertext = "Cart Page";
		contenttext = "This is the help for the search page."
	}

	if(thetext === "paymentlink"){
		headertext = "Payment Page";
		contenttext = "This is the help for the search page."
	}

	if(thetext === "confirmlink"){
		headertext = "Confirmation Page";
		contenttext = "This is the help for the search page."
	}

	if(thetext === "downloadlink"){
		headertext = "Download Page";
		contenttext = "This is the help for the search page."
	}


	document.getElementById("theheader").innerHTML=headertext;
	document.getElementById("para").innerHTML=contenttext;
	return false;
}

function bigger(x){
	x.style.fontSize="20pt";
}

function normal(x){
	x.style.fontSize="16pt";
}