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
import org.apache.struts.upload.*;
import java.io.*;

import java.util.*;

import com.tecunsa.*;
import com.tecunsa.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import contactcenter.forms.CaseResolveForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class CaseResolveAction extends Action {


	public static Date Date_plus( Date d, int days  )
		{
		String test = Utils.DateToStrInv( d );
		Date f = d;

		//f.setDate( f.getDate()+1 );
		GregorianCalendar c = new GregorianCalendar();

		c.setTime( d );

		c.add( GregorianCalendar.DATE, days);

		test = Utils.DateToStrInv( c.getTime() );

		//return f;
		return c.getTime();
		}


	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  y retorna valores
		CaseResolveForm caseResolveForm = (CaseResolveForm) form;

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("done");

		//Usuario que se logeo y que esta en sesión
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {
			Config config = new Config( request );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

			try 
			  {

				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();


				if (caseResolveForm.getProcess().equals("selectOrder")) // Seleccciona el order en que se presentaran los casos
				  {
					httpSession.setAttribute("id_orderSelected", new Integer(caseResolveForm.getId_type_case_order()).toString() );

					String order = "u.id_client.name";
				  	
					if ( caseResolveForm.getId_type_case_order() == 1 )
					  order = "u.id";
					else if ( caseResolveForm.getId_type_case_order() == 2 )
					  order = "u.date_reg";
					 
					
					//Lista de casos asignados a al supervisor
					String query =  " FROM BeanCase u" +
									" WHERE u.id_user.id = " + beanUser.getId() +
									" AND u.id_type_case_status.id = 1" +
									" ORDER BY " + order;

					List list = session.createQuery( query ).list();

					httpSession.setAttribute("casesList", list );

					forward = mapping.findForward("loop");
				  }
				else if (caseResolveForm.getProcess().equals("selectCase")) // Seleccciona un caso, crea notas
				  {
				  	
					//lista de notas
					String query =   " FROM BeanCaseNotes u" +
									 " WHERE u.id_case = " + caseResolveForm.getId_case() +
									 " ORDER BY u.id";

					List list = session.createQuery( query ).list();
					//Pone en sesión
					httpSession.removeAttribute("notesList" );
					httpSession.setAttribute("notesList", list );
					
					
					//Crea lista de files 
				   query =  " FROM BeanFiles u" +
							" WHERE u.id_case.id = " + caseResolveForm.getId_case() +
							" ORDER BY u.date_reg";

				   list = session.createQuery( query ).list();
				   httpSession.setAttribute("fileList", list );
					

					//Obtiene un objeto de la BD con el valor que recibe de caseResolveForm
					BeanCase beanCase = (BeanCase) session.load( BeanCase.class, new Integer(caseResolveForm.getId_case()));
					String id_case = new Integer(beanCase.getId()).toString();

					//Pone en sesión
					httpSession.setAttribute("id_caseSelected", id_case );
					httpSession.setAttribute("case_description", beanCase.getDescription());
					httpSession.setAttribute("id_case", beanCase );

					httpSession.removeAttribute("id_noteSelected");

					 //forward  declarado en struts-config.xml
					forward = mapping.findForward("loop");

					caseResolveForm.setNote_readed("");
				  }
				else if(caseResolveForm.getProcess().equals("selectNote"))
				  {


					String query =   " FROM BeanCaseNotes u" +
								     " WHERE u.id = " + caseResolveForm.getId_note() +
								     " ORDER BY u.id";
					List list = session.createQuery( query ).list();

					//Toma el primer elemento de las lista
					BeanCaseNotes beanCaseNotes = (BeanCaseNotes)list.get(0);
					String id_note = new Integer(beanCaseNotes.getId()).toString();

					//Retorna la nota al jsp
					caseResolveForm.setNote_readed(beanCaseNotes.getNote());

					httpSession.setAttribute("id_noteSelected", id_note );

					//httpSession.setAttribute("notesList", list );
					forward = mapping.findForward("loop");
				  }


				else if(caseResolveForm.getProcess().equals("forward"))
				  {
					boolean ok = false;
					BeanUser userToForward = (BeanUser) session.load( BeanUser.class, new Integer( caseResolveForm.getId_user() ));
					BeanCase beanCase = (BeanCase) session.load( BeanCase.class, new Integer( caseResolveForm.getId_case() ));
					BeanCaseNotes beanCaseNotes = new BeanCaseNotes();
					BeanTypeCase beanTypeCase = (BeanTypeCase) session.load( BeanTypeCase.class, new Integer( caseResolveForm.getId_type_case() ));

					beanCaseNotes.setDate_reg(Utils.Today());
					beanCaseNotes.setId_case(beanCase);
					beanCaseNotes.setId_user(beanUser);
					if(caseResolveForm.getIsPrivate().equals("N"))
						beanCaseNotes.setIs_private("N");
					else
						beanCaseNotes.setIs_private("Y");

					beanCaseNotes.setNote( beanUser.getName() + " reenvio este caso: " +  caseResolveForm.getNotes());
					beanCaseNotes.setForwarded("Y");
					beanCaseNotes.setDays( beanTypeCase.getDays() );
					
					beanCase.setId_user( userToForward );
					beanCase.setDate_last_change( Utils.Today() );
					beanCase.setId_type_case( beanTypeCase );
					beanCase.setLazy("N");
					//beanCase.setDate_delivery( Utils.Date_plus( Utils.Today(), beanTypeCase.getDays()) );
					beanCase.setDate_delivery( Date_plus( Utils.Today(), beanTypeCase.getDays()) );
					
						//Guarda el objeto el la BD
					tx = session.beginTransaction();
					try 
					  {
						session.update(beanCase);
						session.save(beanCaseNotes);


						if ( caseResolveForm.getFileDescription().length() > 0)
						  {
							BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );

							///////////////////////////////////////////////////
							//PATH PARA GUARDAR LOS ARCHIVOS 
							///////////////////////////////////////////////////
							String PATH_SERVER = control_panel.getPath_server();
							String wwwPath = control_panel.getPath_www();

							FormFile theFile = caseResolveForm.getFile();
							String contentType = theFile.getContentType();
							String fileName = theFile.getFileName();
							int fileSize = theFile.getFileSize();
							byte[] fileData = theFile.getFileData();
					
							File dir = new File( PATH_SERVER );
							dir.mkdirs();

			
							BeanFiles beanFiles = new BeanFiles();
						
							beanFiles.setDescription( caseResolveForm.getFileDescription() );
							beanFiles.setFileName( fileName );
							// se pone nombre temporal al archivo
							beanFiles.setFullFileName( wwwPath + beanCase.getId() + "_" + fileName );
							beanFiles.setDate_reg( Utils.Today() );
							beanFiles.setId_user( beanUser );
							beanFiles.setId_case( beanCase );
							session.save( beanFiles );

							// se copia finalmente el archivo
							FileOutputStream out = new FileOutputStream( PATH_SERVER + "/" + beanCase.getId() + "_" + beanFiles.getId() + "_" + fileName );
							
							out.write( theFile.getFileData() );
							out.close();

							// se actualiza el nombre del archivo
							beanFiles.setFullFileName( wwwPath + "/"  + beanCase.getId() + "_" + beanFiles.getId() + "_" + fileName );
							beanFiles.setRealFileName( beanCase.getId() + "_" + beanFiles.getId() + "_" + fileName );
							session.update( beanFiles );
						  }


						tx.commit();
						ok = true;
					  }
					catch (Exception e) 
					  {
					  	   tx.rollback();
					  	   
						   e.printStackTrace();

						   com.tecunsa.Error error = new com.tecunsa.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
						   httpSession.setAttribute("done", "javascript:history.back();");
						   //forward  declarado en struts-config.xml
						   forward = mapping.findForward("error");
					   }

					if ( ok )
					  {
							//Envia Email diciendolo al nuevo user que tiene un caso
						new com.tecunsa.Mail(  userToForward.getEmail(),
											"Hola:\n\n" +
											"A Ud se le ha reenviado un caso" + "\n\n" +
											"Datos del caso:\n" + 
											"Numero de caso: " + beanCase.getId() + "\n" +
											"Nombre del cliente:" + beanCase.getId_client().getName() + "\n" +
											"Nombre del usuario que reenvia:" + beanUser.getName() + "\n" +
											"Descripcion corta: " + beanCase.getShort_description() + "\n" +  
											"Descripcion larga: " + beanCase.getDescription() + "\n\n\n" +
											"Sistema Contact Center."
											,
											"Contact Center: caso reenviado: " +  beanCase.getId(),
											request);
	
	
						//Actualiza lista de casos
						forward = mapping.findForward("okGlobal");
					  }

				  }
				else if(caseResolveForm.getProcess().equals("resolveCase"))
				{
					boolean ok = false;
					//Obtiene un objeto de la BD con el valor id_case que toma de sesión
					BeanCase beanCase = (BeanCase) httpSession.getAttribute("id_case");

					//Obtiene un objeto de la BD
					BeanTypeCaseStatus type_case_status = (BeanTypeCaseStatus) session.load( BeanTypeCaseStatus.class, new Integer(2));

					beanCase.setId_type_case_status(type_case_status);
					beanCase.setDate_close(Utils.Today());
					beanCase.setId_user( beanCase.getId_user_capture() );
					beanCase.setLazy("N");

					//Crea un nuevo objeto
					BeanCaseNotes beanCaseNotes = new BeanCaseNotes();

					beanCaseNotes.setDate_reg(Utils.Today());
					beanCaseNotes.setId_case(beanCase);
					beanCaseNotes.setId_user( beanUser );
					if(caseResolveForm.getIsPrivate().equals("N"))
					  beanCaseNotes.setIs_private("N");
					else
					  beanCaseNotes.setIs_private("Y");
					beanCaseNotes.setNote(caseResolveForm.getNotes());
					beanCaseNotes.setForwarded("N");


					//Guarda el objeto en la BD
					tx = session.beginTransaction();
					try
					  {
						session.update(beanCase);
						session.save(beanCaseNotes);
						tx.commit();
						ok = true;
					  }
					catch (Exception e) 
					  {
						   tx.rollback();
						   e.printStackTrace();

						   com.tecunsa.Error error = new com.tecunsa.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
						   httpSession.setAttribute("done", "javascript:history.back();");
						   //forward  declarado en struts-config.xml
						   forward = mapping.findForward("error");
					   }
					  
					if ( ok )
					  {
						if(beanCaseNotes.getIs_private().equals("N"))
							{
	
							//envia email
							//Envia Email al cliente
							new com.tecunsa.Mail( beanCase.getId_client().getEmail(),
													"Hola:\n\n" +
													"A Ud como cliente se le esta informando de un cambio en el caso registrado a su nombre:" + "\n\n" +
													"Datos del caso:\n" + 
													"Numero de caso: " + beanCase.getId() + "\n" +
													"Nombre del usuario que hizo el cambio:" + beanUser + "\n" +
													"Descripcion corta: " + beanCase.getShort_description() + "\n" +  
													"Descripcion larga: " + beanCase.getDescription() + "\n\n\n" +
													"Nota: " + beanCaseNotes.getNote() +
													"Sistema Contact Center.",
													"contactCenter Mercatek.",
													request);
							}

						 //Actualiza la lista de casos
						 String query =   " FROM BeanCase u" +
										  " WHERE u.id_supervisor = " + beanUser.getId() +
										  " AND u.id_type_case_status.id=1"+
										  " ORDER BY u.id";

						  List list = session.createQuery( query ).list();

						  if ( list.size() != 0 )
							  {
								httpSession.setAttribute("casesList", list );

								query =  " FROM BeanCaseNotes u" +
										 " WHERE u.id = 98989898989898989898" +
										 " ORDER BY u.id";
								list = session.createQuery( query ).list();
								httpSession.setAttribute("notesList", list );
								httpSession.removeAttribute("id_case");
								httpSession.removeAttribute("case_description");
								   //forward  declarado en struts-config.xml
								forward = mapping.findForward("okGlobal");
								//forward = mapping.findForward("loop");

							  }
						  else
							  {
								com.tecunsa.Error e = new com.tecunsa.Error("No existen casos para este usuario..", request);
								httpSession.setAttribute("done", "/contactCenter/pre.do?process=caseResolve" );
								   //forward  declarado en struts-config.xml
								forward = mapping.findForward("error");
							  }
					  }
				  }
				else if(caseResolveForm.getProcess().equals("addNote"))
				  {
					boolean ok = false;

					BeanCaseNotes beanCaseNotes = new BeanCaseNotes();


					//Obtiene un objeto de la BD con el valor id_case que toma de sesión
					BeanCase beanCase = (BeanCase) httpSession.getAttribute("id_case");


					beanCaseNotes.setDate_reg(Utils.Today());
					beanCaseNotes.setId_case(beanCase);
					beanCaseNotes.setId_user(beanUser);
					if(caseResolveForm.getIsPrivate().equals("N"))
						beanCaseNotes.setIs_private("N");
					else
						beanCaseNotes.setIs_private("Y");

					beanCaseNotes.setNote(caseResolveForm.getNotes());
					beanCaseNotes.setForwarded("N");
					beanCaseNotes.setDays( 0 );

					beanCase.setDate_last_change(Utils.Today());

					//Guarda el objeto en la BD
					tx = session.beginTransaction();
					session.save(beanCaseNotes);
					session.update(beanCase);
					
					
					if ( caseResolveForm.getFileDescription().length() > 0)
					  {
						BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );

						///////////////////////////////////////////////////
						//PATH PARA GUARDAR LOS ARCHIVOS 
						///////////////////////////////////////////////////
						String PATH_SERVER = control_panel.getPath_server();
						String wwwPath = control_panel.getPath_www();

						FormFile theFile = caseResolveForm.getFile();
						String contentType = theFile.getContentType();
						String fileName = theFile.getFileName();
						int fileSize = theFile.getFileSize();
						byte[] fileData = theFile.getFileData();
					
						File dir = new File( PATH_SERVER );
						dir.mkdirs();

			
						BeanFiles beanFiles = new BeanFiles();
						
						beanFiles.setDescription( caseResolveForm.getFileDescription() );
						beanFiles.setFileName( fileName );
						// se pone nombre temporal al archivo
						beanFiles.setFullFileName( wwwPath + beanCase.getId() + "_" + fileName );
						beanFiles.setDate_reg( Utils.Today() );
						beanFiles.setId_user( beanUser );
						beanFiles.setId_case( beanCase );
						session.save( beanFiles );

						// se copia finalmente el archivo
						FileOutputStream out = new FileOutputStream( PATH_SERVER + "/" + beanCase.getId() + "_" + beanFiles.getId() + "_" + fileName );

						out.write( theFile.getFileData() );
						out.close();

						// se actualiza el nombre del archivo
						beanFiles.setFullFileName( wwwPath + "/" + beanCase.getId() + "_" + beanFiles.getId() + "_" + fileName );
						beanFiles.setRealFileName( beanCase.getId() + "_" + beanFiles.getId() + "_" + fileName );
						session.update( beanFiles );
					  }
					
					try
					  {
						tx.commit();
						ok = true;
					  }
					catch (Exception e) 
					  {
						   tx.rollback();
						   e.printStackTrace();

						   com.tecunsa.Error error = new com.tecunsa.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
						   httpSession.setAttribute("done", "javascript:history.back();");
						   //forward  declarado en struts-config.xml
						   forward = mapping.findForward("error");
					   }


					if ( ok )
					  {
						if(caseResolveForm.getIsPrivate().equals("N"))
							{
	
								//Envia Email al cliente (primer email)
								new com.tecunsa.Mail( beanCase.getId_client().getEmail(),
														"Hola:\n\n" +
														"A Ud como cliente se le esta informando de un cambio en el caso registrado a su nombre:" + "\n\n" +
														"Datos del caso:\n" + 
														"Numero de caso: " + beanCase.getId() + "\n" +
														"Nombre del usuario que hizo el cambio:" + beanUser.getName() + "\n" +
														"Descripcion corta: " + beanCase.getShort_description() + "\n" +  
														"Descripcion larga: " + beanCase.getDescription() + "\n\n\n" +
														"Nota: " + beanCaseNotes.getNote() +
														"Sistema Contact Center.",
														"contactCenter Mercatek.",
														request);
														
													
								if ( !beanCase.getId_client().getEmail1().equals("") )						
									new com.tecunsa.Mail( beanCase.getId_client().getEmail1(),
															"Hola:\n\n" +
															"A Ud como cliente se le esta informando de un cambio en el caso registrado a su nombre:" + "\n\n" +
															"Datos del caso:\n" + 
															"Numero de caso: " + beanCase.getId() + "\n" +
															"Nombre del usuario que hizo el cambio:" + beanUser + "\n" +
															"Descripcion corta: " + beanCase.getShort_description() + "\n" +  
															"Descripcion larga: " + beanCase.getDescription() + "\n\n\n" +
															"Nota: " + beanCaseNotes.getNote() +
															"Sistema Contact Center.",
															"contactCenter Mercatek.",
															request);
							}

						//Pone en sesión
						httpSession.setAttribute("done", "/contactCenter/pre.do?process=caseResolve" );
					
						//Remueve de sesión
						httpSession.removeAttribute("caseResolveForm");
						httpSession.removeAttribute("id_caseSelected");
						httpSession.removeAttribute("id_noteSelected");
						httpSession.removeAttribute("id_case");
						httpSession.removeAttribute("case_description");

						forward = mapping.findForward("okGlobal");
					  }
				}


			} 
		 catch (Exception e) 
		   {
				e.printStackTrace();

				com.tecunsa.Error error = new com.tecunsa.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
				httpSession.setAttribute("done", "javascript:history.back();");
				//forward  declarado en struts-config.xml
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
