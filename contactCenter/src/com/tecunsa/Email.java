package com.tecunsa;

public class Email {
	
	public static boolean isEmail(String str) {
		int posArroba= str.indexOf("@");
	//	int posPoint=str.indexOf(".",posArroba);
		int posEnd = str.length()-1;
		String user,domain;
		domain=str.substring(posArroba+1,posEnd+1);
		///////user=str.substring(0,posArroba);
		if (posArroba ==-1)
			return false;
		else
			user=str.substring(0,posArroba)	;
		
		int posPoint=str.indexOf(".",posArroba);
		if(posPoint==-1)
			return false;
				    
	    if(posArroba>=posEnd||str.endsWith("-"))
	    	return false;
			
			
		if(!isValidString(user)||!isValidString(domain)||domain.startsWith("- ")||domain.indexOf("-")>-1)
			return false;
		else 
		    return true;		
	
	}
	public static boolean isValidString(String str){
		String stringValidChars="zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP0123456789_-. ";
		String sprb;
		int prb;		
		if(!str.startsWith(".")&&!str.endsWith(".")&&!str.startsWith("-")&& str.length()>0){
		  	for(int i=0;i<str.length();i++){
		  		prb=stringValidChars.indexOf(stringValidChars.substring(i,i+1));
		  		sprb=str.substring(i,i+1);
		  		if(stringValidChars.indexOf(str.substring(i,i+1))==-1)
					return false;		
			}
		}else
			return false;
		
		return true;	
	
	}
 
}