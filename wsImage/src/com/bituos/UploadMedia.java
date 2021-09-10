package com.bituos;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;



public class UploadMedia {
 
	  
        
	    String CALLBACK_URL = "";
	    GoogleAuthorizationCodeFlow flow;
	    GoogleTokenResponse tokenResponse;
	    NetHttpTransport HTTP_TRANSPORT = null;
	    
		public String authorizeUrl() 
	    {
			  
			   //init("");
			   CALLBACK_URL="urn:ietf:wg:oauth:2.0:oob";
		        String authorizeUrl = "";
		        try {
		        	GoogleAuthorizationCodeFlow flow = DriveQuickstart.init(CALLBACK_URL);
					authorizeUrl = DriveQuickstart.getAuthorizeUrl(flow, CALLBACK_URL);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			  
			
			return authorizeUrl;
	    }
		
		private Credential authorizationCode(String authorizationCode) 
	    {
			CALLBACK_URL="urn:ietf:wg:oauth:2.0:oob";
			Credential credential = null;
			 try {
				 	flow=DriveQuickstart.init(CALLBACK_URL); 
				    if(!authorizationCode.isEmpty())
				    {
				    	
				    	tokenResponse = DriveQuickstart.Authorize(authorizationCode, flow, CALLBACK_URL);
				    	credential = flow.createAndStoreCredential(tokenResponse, "user");
				    }
				    else
				    {
				    	/*LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(61984).build();
				        credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
				    	 	*/
				      
				    	credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");	
				    }
					
					
				    
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return credential;
	    }


        public String saveFile(byte[] infoBytes,String authorizationCode) 
	    {

        	try {
	        		Credential credential =authorizationCode(authorizationCode);
	        		HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
					DriveQuickstart.save(infoBytes,HTTP_TRANSPORT, credential );
			     
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
	        return  "";

	      }
}
