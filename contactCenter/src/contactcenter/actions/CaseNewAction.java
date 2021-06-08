package contactcenter.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.util.*;
import org.apache.struts.upload.*;
import java.io.*;

import com.tecunsa.*;
import com.tecunsa.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import contactcenter.forms.CaseNewForm;
import contactcenter.forms.ClientNewForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class CaseNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		CaseNewForm caseNewForm = (CaseNewForm) form;

		HttpSession httpSession = request.getSession();

		httpSession.removeAttribute("done");

		//Usuario que se logeo y que esta en sesión
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else 
		  {
			Config config = new Config( request );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

			try 
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();


				//Objeto de tipo  BeanCase
				BeanCase bean = new BeanCase();

				String error = new String();

				/*caseNewForm.getProcess().equals()
				    obtiene  el valor de la variable process y compara con una cadena
				*/

				//si el process = "validate"
				
				String query = "nothing";
				
				if(caseNewForm.getProcess().equals("validate"))
				  {
					//Busca al cliente
					query = " FROM BeanClient u" +
						    " WHERE u.id =" + caseNewForm.getId_client();

					List list = session.createQuery( query ).list();

					httpSession.removeAttribute("clientName");

					if( list.size()!=0 )
					  {
						httpSession.setAttribute("clientName", ((BeanClient) list.get(0)).getName() );
						
						String query1 = " FROM BeanUserClient u" +
								        " WHERE u.id_client.id =" + caseNewForm.getId_client();
					  	
						list = session.createQuery( query1 ).list();

						if( list.size()!=0 )
						  {
							httpSession.setAttribute("visibleUserData","yes");
							httpSession.setAttribute("visibleCase","yes");
							httpSession.setAttribute("client", ((BeanUserClient) list.get(0)).getId_client() );

							//forward declarado en struts-config.xml
							forward = mapping.findForward("loop");
						  }
						else
						  {
							httpSession.setAttribute("done", "javascript:history.back();");
							com.tecunsa.Error e = new com.tecunsa.Error("El cliente no tiene asignado agente de ventas", request);
							//forward declarado en struts-config.xml
							forward = mapping.findForward("error");
						  }
					  }
					else
					  {
						httpSession.setAttribute("visibleUserData","no");
						httpSession.setAttribute("visibleCase","no");

						httpSession.setAttribute("done", "/contactCenter/cases/caseNew.jsp");
						com.tecunsa.Error e = new com.tecunsa.Error("El cliente no existe", request);
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					  }
				  }

				//si el process = "register"
				else if( caseNewForm.getProcess().equals("register") )
				  {
					BeanClient client = (BeanClient) httpSession.getAttribute("client");
				  	boolean ok = false;
				  	
					BeanTypeCase id_type_case =(BeanTypeCase) session.load( BeanTypeCase.class, new Integer(caseNewForm.getId_type_case()));

					//Se pasan los valores a el objeto bean de tipo BeanCase
					bean.setId_user_capture( beanUser );
					bean.setId_supervisor( beanUser );
					bean.setId_client( client );
					bean.setId_type_case( id_type_case);
					bean.setDescription( caseNewForm.getDescription());
					bean.setShort_description( caseNewForm.getShort_description());

					BeanTypeCaseStatus type_case_status = (BeanTypeCaseStatus) session.load( BeanTypeCaseStatus.class, new Integer(1));

					bean.setId_type_case_status(type_case_status);
					bean.setDate_reg( Utils.Today() );
					bean.setDate_last_change( Utils.Today() );
					bean.setId_user( beanUser );
					bean.setSuccessful("N");
					bean.setLazy("N");
					bean.setDate_delivery( Utils.Date_plus( Utils.Today(), id_type_case.getDays()) );

					//Guarda la informacion el la BD
					tx = session.beginTransaction();
					
					try
					  {
						session.save( bean );
					
					    if ( caseNewForm.getFileDescription().length() > 0)
					      {
							BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );

							///////////////////////////////////////////////////
							//PATH PARA GUARDAR LOS ARCHIVOS 
							///////////////////////////////////////////////////
							//String PATH_SERVER = "/Tomcat/webapps/files/uploadContactCenter/";
							String PATH_SERVER = control_panel.getPath_server();
	
							//String wwwPath = "/files/uploadContactCenter/";
							String wwwPath = control_panel.getPath_www();

							FormFile theFile = caseNewForm.getFile();
							String contentType = theFile.getContentType();
							String fileName = theFile.getFileName();
							int fileSize = theFile.getFileSize();
							byte[] fileData = theFile.getFileData();
					
							File dir = new File( PATH_SERVER );
							dir.mkdirs();

							BeanFiles beanFiles = new BeanFiles();
						
							beanFiles.setDescription( caseNewForm.getFileDescription() );
							beanFiles.setFileName( fileName );
							// se pone nombre temporal al archivo
							beanFiles.setFullFileName( wwwPath + bean.getId() + "_" + fileName );
							beanFiles.setDate_reg( Utils.Today() );
							beanFiles.setId_user( beanUser );
							beanFiles.setId_case( bean );
							beanFiles.setRealFileName( bean.getId() + "_" + fileName );
							session.save( beanFiles );

							// se copia finalmente el archivo
							FileOutputStream out = new FileOutputStream( PATH_SERVER + "/" + bean.getId() + "_" + beanFiles.getId() + "_" + fileName );

							out.write( theFile.getFileData() );
							out.close();
			
							// se actualiza el nombre del archivo
							beanFiles.setFullFileName( wwwPath + "/" + bean.getId() + "_" + beanFiles.getId() + "_" + fileName );
							beanFiles.setRealFileName( bean.getId() + "_" + beanFiles.getId() + "_" + fileName );
							session.update( beanFiles );
					      }

						tx.commit();
						ok = true;
					  }
					catch (Exception m) 
					  {
					  	tx.rollback();
					  	
						m.printStackTrace();
						httpSession.setAttribute("done", "javascript:history.back();");
						com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					  }
					  
					if ( ok )
					  {
						//envia email
						new com.tecunsa.Mail(  beanUser.getEmail(),
											"Hola:\n\n" +
											"Ud tiene un nuevo caso asignado" + "\n\n" +
											"Datos del caso:\n" + 
											"Numero de caso: " + bean.getId() + "\n" +
											"Nombre del cliente:" + bean.getId_client().getName() + "\n" +
											"Nombre del usuario que captura:" + beanUser.getName()+ "\n" +
											"Descripcion corta: " + bean.getShort_description() + "\n" +  
											"Descripcion larga: " + bean.getDescription() + "\n\n\n" +
											"Sistema Contact Center."
											,
											"Contact Center: caso nuevo: " +  bean.getId(),
											request);
										
						httpSession.setAttribute("done", "pre.do?process=caseNew" );
						httpSession.setAttribute("msg", "Su numero de caso es " + bean.getId());
						//forward declarado en struts-config.xml
						forward = mapping.findForward("okGlobal");
					  }
				  }
				else
				  {
					httpSession.setAttribute("done", "javascript:history.back();");
					com.tecunsa.Error e = new com.tecunsa.Error("Opcion no programada", request);
					//forward declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }
			  } 
			catch (Exception m) 
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");
				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally	
			  {
				if ( session != null )
					session.close();

				if ( sessionFactory != null )
					sessionFactory.close();
			  }
		  }

		// Termina con
		return (forward);

	}
}

