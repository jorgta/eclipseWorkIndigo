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


import com.tecunsa.*;
import com.tecunsa.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import contactcenter.forms.CaseCloseForm;
import contactcenter.forms.CaseNewForm;
import contactcenter.forms.ClientNewForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class CaseCloseAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  y retorna valores
		CaseCloseForm caseCloseForm = (CaseCloseForm) form;

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

			/*casetNewForm.getProcess().equals()
			  obtiene  el valor de la variable process y compara con una cadena
			*/

			if(caseCloseForm.getProcess().equals("caseClose")) //Cerrar caso
			  {
				try 
				  {
					//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					//Obteniendo objeto de la bd con el valor que recibe de caseCloseForm
					BeanCase beanCase = (BeanCase) session.load( BeanCase.class, new Integer(caseCloseForm.getId_case()));


					beanCase.setDate_close(Utils.Today());
					
					if ( caseCloseForm.getSuccessful().equals("Y") )
					  beanCase.setSuccessful("Y");
					else
					  beanCase.setSuccessful("N");

					//Obteniendo objeto de la bd con el valor 3
					BeanTypeCaseStatus type_case_status = (BeanTypeCaseStatus) session.load( BeanTypeCaseStatus.class, new Integer(3));

					beanCase.setId_type_case_status(type_case_status);

					//Guarda la informacion el la BD
					tx = session.beginTransaction();
					session.save( beanCase );
					tx.commit();

					//Se avisa al supervisor que este caso ha sido cerrado
					String query =  " FROM BeanUserUser u" +
									" WHERE u.id_user.id = " + beanCase.getId_user().getId();

					List list = session.createQuery( query ).list();

					new com.tecunsa.Mail(  beanUser.getEmail(),
										"Hola:\n\n" +
										"Uno de sus subordinados ha cerrado un caso:" + "\n\n" +
										"Datos del caso:\n" + 
										"Numero de caso: " + beanCase.getId() + "\n" +
										"Nombre del cliente:" + beanCase.getId_client().getName() + "\n" +
										"Nombre del usuario que captura:" + beanCase.getId_user().getName()+ "\n" +
										"Descripcion corta: " + beanCase.getShort_description() + "\n" +  
										"Descripcion larga: " + beanCase.getDescription() + "\n\n\n" +
										"Sistema Contact Center."
										,
										"Contact Center: caso cerrado: " +  beanCase.getId(),
										request);



					//Actualiza la lista de casos resueltos
					query =  " FROM BeanCase u" +
							" WHERE u.id_user_capture = " + beanUser.getId() +
							" AND u.id_type_case_status = 2" +
							" ORDER BY u.id_type_case, u.date_reg";

					list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						  {
							httpSession.setAttribute("list", list );
							    //forward  declarado en struts-config.xml
							forward = mapping.findForward("caseClose");
						  }
					else
						  {
							com.tecunsa.Error e = new com.tecunsa.Error("No existen mas casos para cerrar.", request);
							httpSession.setAttribute("done", "javascript:window.close();" );
							    //forward  declarado en struts-config.xml
							forward = mapping.findForward("error");
						  }



				  } 
				catch (Exception m) 
				  {


					m.printStackTrace();
					httpSession.setAttribute("done", "javascript:history.back();");
					com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
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
			//process="caseRead"
			else if(caseCloseForm.getProcess().equals("caseRead"))//Leer caso
			  {
					//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					//Obtiene un objeto de la BD con el valor que recibe de caseCloseForm
				    BeanCase beanCase = (BeanCase) session.load( BeanCase.class, new Integer(caseCloseForm.getId_case()));

					//Retorna la descripción  del caso al jsp
					caseCloseForm.setCase_description( beanCase.getDescription() );

					String id_case = new Integer( beanCase.getId() ).toString();

					//Pone en sesión
					httpSession.setAttribute("id_caseSelected", id_case );
					httpSession.setAttribute("showCaseData", "yes" );
					
					//Crea lista de files 
				   String query =   " FROM BeanFiles u" +
									" WHERE u.id_case.id = " + beanCase.getId() +
									" ORDER BY u.date_reg";
	
				   List list = session.createQuery( query ).list();
				   httpSession.setAttribute("fileList", list );

					//Crea lista de notas 
				   query =  " FROM BeanCaseNotes u" +
							" WHERE u.id_case.id = " + beanCase.getId() +
							" ORDER BY u.date_reg";
		
				   list = session.createQuery( query ).list();
				   httpSession.setAttribute("notesList", list );
	

					  //forward  declarado en struts-config.xml
					forward = mapping.findForward("caseClose");

			  }
		  }

		// Termina con
		return (forward);

	}
}

