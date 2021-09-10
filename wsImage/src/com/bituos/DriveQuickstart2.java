package com.bituos;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

https://o7planning.org/11889/manipulating-files-and-folders-on-google-drive-using-java
https://stackoverflow.com/questions/65756266/error-403-access-denied-the-developer-hasn-t-given-you-access-to-this-app-despi	
https://www.ladrupalera.com/es/drupal/desarrollo/javascript/como-usar-una-api-de-google-con-autenticacion-traves-de-oauth2


https://cloud.google.com/bigquery/docs/samples/bigquery-auth-user-flow?hl=es-419

Registra tu aplicación para Google Drive API en Google Cloud Platform
https://console.developers.google.com/start/api?id=drive




https://www.programcreek.com/java-api-examples/?code=googleads%2Fgoogleads-java-lib%2Fgoogleads-java-lib-master%2Fexamples%2Fadmanager_axis%2Fsrc%2Fmain%2Fjava%2Fadmanager%2Faxis%2Fauth%2FAdvancedCreateCredentialFromScratch.java

*/
public class DriveQuickstart2 {
	 
 
	private static final String APPLICATION_NAME = "Drive API Quickstart Desktop";
	 
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    
    //en caso de que sea una proyecto web en la consola de desarolladores de Google API
 // i.e redirect_uri http://localhost:61984/Callback
    private static final int LOCAL_RECEIVER_PORT = 61984;

    
    
 
    // Directory to store user credentials for this application.
    private static final java.io.File CREDENTIALS_FOLDER  = new java.io.File(System.getProperty("user.home"), "credentials");
    
    //private static final java.io.File CREDENTIALS_FOLDER = new java.io.File("/WEB-INF/", "credentials");
 
    private static final String CLIENT_SECRET_FILE_NAME = "client_secret.json";
 
    //
    // Global instance of the scopes required by this quickstart. If modifying these
    // scopes, delete your previously saved credentials/ folder.
    //
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
 
 
    
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
    	 
    	/* java.io.File clientSecretFilePath = new java.io.File(CREDENTIALS_FOLDER, CLIENT_SECRET_FILE_NAME);
 
        if (!clientSecretFilePath.exists()) {
            throw new FileNotFoundException("Please copy " + CLIENT_SECRET_FILE_NAME //
                    + " to folder: " + CREDENTIALS_FOLDER.getAbsolutePath());
        }
 
        // Load client secrets file.
       InputStream in = new FileInputStream(clientSecretFilePath);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        */
        
    	String CALLBACK_URL = "urn:ietf:wg:oauth:2.0:oob";
        Details details= new Details();
        String clientId = "793299907798-ojbu2b2d7bu1or56ldbr48s12859kbpa.apps.googleusercontent.com";
        String authUri = "https://accounts.google.com/o/oauth2/auth";
        String tokenUri = "https://oauth2.googleapis.com/token";
        String clientSecret = "eYEyGSeWIOH1rhl9V5cGTsOo";
        List<String> redirectUris = new ArrayList<String>();
        redirectUris.add(CALLBACK_URL);
        redirectUris.add("http://localhost");
        HashMap<String, Object> unknownFields = new HashMap<String, Object>();
        unknownFields.put("project_id", "psyched-choir-325517");
        unknownFields.put("auth_provider_x509_cert_url", "https://www.googleapis.com/oauth2/v1/certs");
        
        details.setClientId(clientId);
        details.setAuthUri(authUri);
        details.setTokenUri(tokenUri);
        details.setClientSecret(clientSecret);
        details.setRedirectUris(redirectUris);
        details.setUnknownKeys(unknownFields);
        
        GoogleClientSecrets clientSecrets1  = new GoogleClientSecrets().setInstalled(details);
        clientSecrets1.setFactory(JSON_FACTORY);
 
       
        //asi pediria cada vez autorizacion
        /*DataStoreFactory dataStoreFactory= new MemoryDataStoreFactory();
        GoogleAuthorizationCodeFlow flow4 = new GoogleAuthorizationCodeFlow.Builder(
        		HTTP_TRANSPORT, JSON_FACTORY,
                clientSecrets1, SCOPES)
        		.setDataStoreFactory(dataStoreFactory)
        		//.setApprovalPrompt("auto")
                .setAccessType("offline").build();
       */
        
      //asi guarda un archivo de autorizacion y no hay que dar autorizacion cada vez 
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        		HTTP_TRANSPORT, JSON_FACTORY,
                clientSecrets1, SCOPES)
        		.setDataStoreFactory(new FileDataStoreFactory(CREDENTIALS_FOLDER))
        		//.setApprovalPrompt("auto")
                .setAccessType("offline").build();
         
        
       // en este caso no lo uso pues es un web service y no tiene interface web,  si fuera una app web asi se usa pues al autorizar el acceso redirecciona a este puerto
     // Exchange an authorization code for  refresh token
        /*LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(LOCAL_RECEIVER_PORT).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return credential;*/
        
        
        String authorizeUrl = flow.newAuthorizationUrl().setRedirectUri(CALLBACK_URL).build();
        //String authorizeUrl = flow.newAuthorizationUrl().setRedirectUri( "http://localhost:" + LOCAL_RECEIVER_PORT).build();
        
       
        System.out.printf("Paste this url in your browser:%n%s%n", authorizeUrl);
        // Wait for the authorization code.
        System.out.println("Type the code you received here: ");
        @SuppressWarnings("DefaultCharset") // Reading from stdin, so default charset is appropriate.
        String authorizationCode = new BufferedReader(new InputStreamReader(System.in)).readLine();
        
        /// Authorize the OAuth2 token.
        GoogleAuthorizationCodeTokenRequest tokenRequest = flow.newTokenRequest(authorizationCode);
        tokenRequest.setRedirectUri(CALLBACK_URL);
        GoogleTokenResponse tokenResponse = tokenRequest.execute();
        
        Credential credential = flow.createAndStoreCredential(tokenResponse, "user");
        return credential;
        //return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
    
    
    public static Credential getCredentials1(final NetHttpTransport HTTP_TRANSPORT, GoogleTokenResponse tokenResponse, GoogleAuthorizationCodeFlow flow) throws IOException {
 
        
        Credential credential = flow.createAndStoreCredential(tokenResponse, "user");
        return credential;
        
    }
    
    
   public static String getAuthorizeUrl(GoogleAuthorizationCodeFlow flow,String CALLBACK_URL )
   {
	   String authorizeUrl = flow.newAuthorizationUrl().setRedirectUri(CALLBACK_URL).build();
	   return authorizeUrl;
   }
    
   public static GoogleTokenResponse Authorize(String authorizationCode,GoogleAuthorizationCodeFlow flow , String CALLBACK_URL) throws IOException
   {
	   GoogleAuthorizationCodeTokenRequest tokenRequest = flow.newTokenRequest(authorizationCode);
       tokenRequest.setRedirectUri(CALLBACK_URL);
       GoogleTokenResponse tokenResponse = tokenRequest.execute();
       
	   return tokenResponse;
   }
   
    
   public static GoogleAuthorizationCodeFlow Initialize(NetHttpTransport HTTP_TRANSPORT, Details details) throws Exception {
 
       GoogleClientSecrets clientSecrets  = new GoogleClientSecrets().setInstalled(details);
       clientSecrets.setFactory(JSON_FACTORY);
       
       GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
       		HTTP_TRANSPORT, JSON_FACTORY,
               clientSecrets, SCOPES)
       		.setDataStoreFactory(new FileDataStoreFactory(CREDENTIALS_FOLDER))
       		//.setApprovalPrompt("auto")
               .setAccessType("offline").build();
       

	   return flow;
   }
   public static void save(byte[] infoBytes, GoogleTokenResponse tokenResponse, NetHttpTransport HTTP_TRANSPORT ) throws Exception {
	   
	   
	   Credential credential = getCredentials(HTTP_TRANSPORT);
       //Credential credential1 = getCredentials1(HTTP_TRANSPORT,tokenResponse,flow);
       

       // 6: Create Google Drive Service.
       Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential) //
               .setApplicationName(APPLICATION_NAME).build();

       // Print the names and IDs for up to 10 files.
       FileList result = service.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)").execute();
       List<File> files = result.getFiles();
       if (files == null || files.isEmpty()) {
           System.out.println("No files found.");
       } else {
           System.out.println("Files:");
           for (File file : files) {
               System.out.printf("%s (%s)\n", file.getName(), file.getId());
           }
       }
       

       
       
       String FolderID = getFolderID( "registrovisitante",service);
    
       if (FolderID.isEmpty())
       {
   	   doCreateFolder( service , "registrovisitante", infoBytes);
       }
       else
       {   if(infoBytes != null) 
       		doCreateFile(FolderID, String.valueOf( getName() ) , service,infoBytes);
       }
   }
   
   
    
    public static void init(byte[] infoBytes ) throws Exception {
    	System.out.println("CREDENTIALS_FOLDER: " + CREDENTIALS_FOLDER.getAbsolutePath());
    	 
        // 1: Create CREDENTIALS_FOLDER
        if (!CREDENTIALS_FOLDER.exists()) {
            CREDENTIALS_FOLDER.mkdirs();
 
            System.out.println("Created Folder: " + CREDENTIALS_FOLDER.getAbsolutePath());
            System.out.println("Copy file " + CLIENT_SECRET_FILE_NAME + " into folder above.. and rerun this class!!");
            return;
        }
 
        
        //GoogleAuthorizationCodeFlow flow= Initialize();
        
        
        //getAuthorizeUrl(flow, CALLBACK_URL);
        
        String authorizationCode ="";
         
        
        //4
        
        //GoogleTokenResponse tokenResponse= Authorize(authorizationCode, CALLBACK_URL, flow);
        
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        
        // 5: Read client_secret.json file & create Credential object.
        Credential credential = getCredentials(HTTP_TRANSPORT);
        //Credential credential1 = getCredentials1(HTTP_TRANSPORT,tokenResponse,flow);
        
 
        // 6: Create Google Drive Service.
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential) //
                .setApplicationName(APPLICATION_NAME).build();
 
        // Print the names and IDs for up to 10 files.
        FileList result = service.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)").execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s (%s)\n", file.getName(), file.getId());
            }
        }
        
 
        
        
        String FolderID = getFolderID( "registrovisitante",service);
     
        if (FolderID.isEmpty())
        {
    	   doCreateFolder( service , "registrovisitante", infoBytes);
        }
        else
        {   if(infoBytes != null) 
        		doCreateFile(FolderID, String.valueOf( getName() ) , service,infoBytes);
        }
    }
    
    private static void doCreateFolder(Drive driveService, String foldername,byte[] infoBytes) throws Exception {
	    	File fileMetadata = new File();
	    	fileMetadata.setName(foldername);
	    	fileMetadata.setMimeType("application/vnd.google-apps.folder");
	
	    	File file = driveService.files().create(fileMetadata)
	    	    .setFields("id")
	    	    .execute();
	    	System.out.println("Folder ID: " + file.getId());
	    	
	    	 
	    	if(infoBytes != null) 
	          doCreateFile( file.getId(), String.valueOf( getName() ) , driveService,infoBytes);
	        

    	}
    

    
    private static void doCreateFile(String folderId, String filename,Drive driveService,byte[] infoBytes) throws Exception {
    	
    	String filePath = "C:/eclipseWorkIndigo/files/WebContent/uploadAndroid/temp.jpg";
        String result = "";
       
        FileOutputStream fos = new FileOutputStream(filePath);
        BufferedOutputStream outputStream = new BufferedOutputStream(fos);
        outputStream.write(infoBytes);
        outputStream.close();
            
    	
    	//String folderId = "0BwwA4oUTeiV1TGRPeTVjaWRDY1E";
    	File fileMetadata = new File();
    	fileMetadata.setName(filename);
    	fileMetadata.setParents(Collections.singletonList(folderId));

     
    	java.io.File tempfile = new java.io.File(filePath);
    	InputStream inputStream = new ByteArrayInputStream(infoBytes);
    	
    	
    	FileContent mediaContent = new FileContent("image/jpeg", tempfile);
    	 
    	File file = driveService.files().create(fileMetadata, mediaContent)
    	    .setFields("id, parents")
    	    .execute();
    	System.out.println("File ID: " + file.getId());

    	 

         
	}
    
    private static String getFolderID( String name,Drive driveService) throws IOException
    {
    	 
    	
    	String FolderID = "";
    	FileList result = driveService.files().list().execute();
    	List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
            	if(file.getName().equals(name ))
            	{
            		System.out.printf("%s (%s)\n", file.getName(), file.getId());
            		FolderID = file.getId();
            		break;
            	}
            }
        }
    	
    	
    	
    			
    	return FolderID;
    	
    }
    
    
    public static int getName() {
        int min = 500;
        int max = 100000;
          
        //Generate random int value from 50 to 100 
        System.out.println("Random value in int from "+min+" to "+max+ ":");
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        System.out.println(random_int);
        
        return random_int;
      }
    

	public static void main(String[] args) throws Exception {

		init(null);
	}

}
