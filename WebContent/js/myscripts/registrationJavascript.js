/* Kimberly Noel	0932295
 registrationJavascript.js */

function init() {

    //retrieve all fields from the form
    var firstName             = document.getElementById("firstname"),
        lastName              = document.getElementById("lastname"),
        email                 = document.getElementById("email"),
        emailTwo              = document.getElementById("emailTwo"),
        passwordOne           = document.getElementById("passwordOne"),
        passwordTwo           = document.getElementById("passwordTwo"),
        addressOne            = document.getElementById("addressOne"),
        city                  = document.getElementById("city"),
        postalCode            = document.getElementById("postalcode"),
        homePhone             = document.getElementById("homephone"),
        registrationInfoArray = [firstName, lastName, email, emailTwo, passwordOne, passwordTwo, addressOne, city, postalCode, homePhone],
        passwordArray         = new Array(passwordOne, passwordTwo),
        emailArray            = new Array(email, emailTwo),
        regForm = document.forms["registrationForm"];
       
    addEvent(regForm, "submit", validateForm, false);
}

function addEvent(obj, type, functionName, cap) {
if (obj.attachEvent)
obj.attachEvent("on" + type, functionName);
else if (obj.addEventListener)
obj.addEventListener(type, functionName, cap);
else
alert("Incompatible browser.");
}


function validateForm(){


	
	return( validateFields() &&
	validateMatches(passwordArray) &&
	validateMaches(emailArray) );
	
}

//verify that passwords & emails match
function validateMatches(theArray) {
	var isMatch = false;
		
	if (theArray[0].value === theArray[1].value) {
		theArray[0].style.backgroundColor = "#fff";
		theArray[1].style.backgroundColor = "#fff";
		isMatch = true;
	} else{
		theArray[0].style.backgroundColor = "#f33";
		theArray[1].style.backgroundColor = "#f33";
		isMatch = false;
	}
	return isMatch;
}

//verify all fields are filled in, no empty fields
function validateFields() {
	var isNotEmpty = true;
	
	for (var i in registrationinfoArray){
	//check all fields are filled out 
	if (!registrationInfoArray[i].value){
		registrationInfoArray[i].style.backgroundColor = "#f33";
		isNotEmpty = false;
	}	
	else{
		registrationInfoArray[i].style.backgroundColor = "#fff";
	}
	}
	
	return isNotEmpty;
}



/* this function verifies that the postal is in the proper format
if not it clears the field */
function postalCodeValidate(postal) {

    var regex = new RegExp(/^[A-Z]\d[A-Z]( )?\d[A-Z]\d$/i);
    if (!regex.test(postal)){
    document.getElementById('postalcode').value = "" ;
    return false;
    }
    else{
    return true;
    }
}

/* this function validates a given e-mail
if it is incorrect the field is cleared */
function emailValidate(email) {
	
    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (!emailRegex.test(email.value)){
    document.getElementById('email').value = "" ;
    return false;
    }
    else{
    return true;
    }
}

//this function only allows numbers to be entered
function numValidate(evt) {

  var theEvent = evt || window.event;
  var key = theEvent.keyCode || theEvent.which;
  key = String.fromCharCode( key );
  var regex = /[0-9]/;
  if( !regex.test(key) ) {
    theEvent.returnValue = false;
    if(theEvent.preventDefault) theEvent.preventDefault();
  }
}

//this function only allows letters to be entered
function nameValidate(evt) {
  var theEvent = evt || window.event;
  var key = theEvent.keyCode || theEvent.which;
  key = String.fromCharCode( key );
  var regex = /[A-Za-z]+/;
  if( !regex.test(key) ) {
    theEvent.returnValue = false;
    if(theEvent.preventDefault) theEvent.preventDefault();
  }
}

//this function only allows letters & spaces to be entered
function namesValidate(evt) {
  var theEvent = evt || window.event;
  var key = theEvent.keyCode || theEvent.which;
  key = String.fromCharCode( key );
  var regex = /[A-Za-z ]+/;
  if( !regex.test(key) ) {
    theEvent.returnValue = false;
    if(theEvent.preventDefault) theEvent.preventDefault();
  }
}


 
//variable declarations used for the email validation
var zChar = new Array(' ', '(', ')', '-', '.');
var maxphonelength = 14;
var phonevalue1;
var phonevalue2;
var cursorposition;

/* the following code will only allow numbers to be entered,
   will format a given telephone number, & also
   restrict the field to only excepting 10 digits */

function ParseForNumber1(object){
  phonevalue1 = ParseChar(object.value, zChar);
}

function ParseForNumber2(object){
  phonevalue2 = ParseChar(object.value, zChar);
}

function backspacerUP(object,e) { 
  if(e){ 
    e = e 
  } else {
    e = window.event 
  } 
  if(e.which){ 
    var keycode = e.which 
  } else {
    var keycode = e.keyCode 
  }

  ParseForNumber1(object)

  if(keycode >= 48){
    ValidatePhone(object)
  }
}

function backspacerDOWN(object,e) { 
  if(e){ 
    e = e 
  } else {
    e = window.event 
  } 
  if(e.which){ 
    var keycode = e.which 
  } else {
    var keycode = e.keyCode 
  }
  ParseForNumber2(object)
} 

function GetCursorPosition(){

  var t1 = phonevalue1;
  var t2 = phonevalue2;
  var bool = false
  for (i=0; i<t1.length; i++)
  {
    if (t1.substring(i,1) != t2.substring(i,1)) {
      if(!bool) {
        cursorposition=i
        window.status=cursorposition
        bool=true
      }
    }
  }
}

function ValidatePhone(object){

  var p = phonevalue1

  p = p.replace(/[^\d]*/gi,"")

  if (p.length < 3) {
    object.value=p
  } else if(p.length==3){
    pp=p;
    d4=p.indexOf('(')
    d5=p.indexOf(')')
    if(d4==-1){
      pp="("+pp;
    }
    if(d5==-1){
      pp=pp+")";
    }
    object.value = pp;
  } else if(p.length>3 && p.length < 7){
    p ="(" + p; 
    l30=p.length;
    p30=p.substring(0,4);
    p30=p30+") " 

    p31=p.substring(4,l30);
    pp=p30+p31;

    object.value = pp; 

  } else if(p.length >= 7){
    p ="(" + p; 
    l30=p.length;
    p30=p.substring(0,4);
    p30=p30+") " 

    p31=p.substring(4,l30);
    pp=p30+p31;

    l40 = pp.length;
    p40 = pp.substring(0,9);
    p40 = p40 + "-"

    p41 = pp.substring(9,l40);
    ppp = p40 + p41;

    object.value = ppp.substring(0, maxphonelength);
  }

  GetCursorPosition()

  if(cursorposition >= 0){
    if (cursorposition == 0) {
      cursorposition = 2
    } else if (cursorposition <= 2) {
      cursorposition = cursorposition + 1
    } else if (cursorposition <= 4) {
      cursorposition = cursorposition + 3
    } else if (cursorposition == 5) {
      cursorposition = cursorposition + 3
    } else if (cursorposition == 6) { 
      cursorposition = cursorposition + 3 
    } else if (cursorposition == 7) { 
      cursorposition = cursorposition + 4 
    } else if (cursorposition == 8) { 
      cursorposition = cursorposition + 4
      e1=object.value.indexOf(')')
      e2=object.value.indexOf('-')
      if (e1>-1 && e2>-1){
        if (e2-e1 == 4) {
          cursorposition = cursorposition - 1
        }
      }
    } else if (cursorposition == 9) {
      cursorposition = cursorposition + 4
    } else if (cursorposition < 11) {
      cursorposition = cursorposition + 3
    } else if (cursorposition == 11) {
      cursorposition = cursorposition + 1
    } else if (cursorposition == 12) {
      cursorposition = cursorposition + 1
    } else if (cursorposition >= 13) {
      cursorposition = cursorposition
    }

    var txtRange = object.createTextRange();
    txtRange.moveStart( "character", cursorposition);
    txtRange.moveEnd( "character", cursorposition - object.value.length);
    txtRange.select();
  }

}

function ParseChar(sStr, sChar)
{

  if (sChar.length == null) 
  {
    zChar = new Array(sChar);
  }
    else zChar = sChar;

  for (i=0; i<zChar.length; i++)
  {
    sNewStr = "";

    var iStart = 0;
    var iEnd = sStr.indexOf(sChar[i]);

    while (iEnd != -1)
    {
      sNewStr += sStr.substring(iStart, iEnd);
      iStart = iEnd + 1;
      iEnd = sStr.indexOf(sChar[i], iStart);
    }
    sNewStr += sStr.substring(sStr.lastIndexOf(sChar[i]) + 1, sStr.length);

    sStr = sNewStr;
  }

  return sNewStr;
}
// end of the telephone number validation

window.onload = init;
