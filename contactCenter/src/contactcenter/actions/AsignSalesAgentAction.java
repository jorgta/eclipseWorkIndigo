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

import contactcenter.forms.AsignSalesAgentForm;
import contactcenter.forms.AsignSupervisorForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class AsignSalesAgentAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		//Recibe y retorna valores
		AsignSalesAgentForm asignSalesAgentForm = (AsignSalesAgentForm) form;

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

				String query = 	" FROM BeanUser u" +
								" WHERE u.id = " + asignSalesAgentForm.getId_user();
								
				List list = session.createQuery( query ).list();

				if ( list.size() == 0 )
					{
						com.tecunsa.Error error = new com.tecunsa.Error( "No existe usuario con ese numero (" + asignSalesAgentForm.getId_user() + ")", request);
						httpSession.setAttribute("done", "javascript:history.back();");
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					}
				else
				  	{
						query = 	" FROM BeanClient u" +
									" WHERE u.id = " + asignSalesAgentForm.getId_client();
								
						list = session.createQuery( query ).list();
						
						if ( list.size() == 0 )
							{
								com.tecunsa.Error error = new com.tecunsa.Error( "No existe cliente con ese numero (" + asignSalesAgentForm.getId_client() + ")", request);
								httpSession.setAttribute("done", "javascript:history.back();");
								//forward declarado en struts-config.xml
								forward = mapping.findForward("error");
							}
						else
						  {
							//Listado de client relacionados con  el usuario que esta en sesión
							query = 	" FROM BeanUserClient u" +
										" WHERE u.id_client = " + asignSalesAgentForm.getId_client() +
										" ORDER BY u.id";

							list = session.createQuery( query ).list();

							//Obtiene un objeto de la BD con el valor que le pasa asignSalesAgentForm
							BeanClient id_client = (BeanClient) session.load( BeanClient.class, new Integer(asignSalesAgentForm.getId_client()));

							////Obtiene un objeto de la BD con el valor que le pasa asignSalesAgentForm
							BeanUser id_user = (BeanUser) session.load( BeanUser.class, new Integer(asignSalesAgentForm.getId_user()));

							if(list.size()==0)
							  {
								//Objeto nuevo
								BeanUserClient bean = new BeanUserClient();



								//Se le pasa al bean los objetos
								bean.setId_client(id_client);
								bean.setId_user(id_user);


								//Gurda el objeto en la BD
								tx = session.beginTransaction();
								session.save(bean);
								tx.commit();

								//forward declarado en struts-config.xml
								forward = mapping.findForward("okGlobal");
								httpSession.setAttribute("done", "user/asignSupervisor.jsp" );

							  }
							else
							  {
								//Se obtiene el primer elemento de la lista
								BeanUserClient bean = (BeanUserClient)list.get(0);

								//Se le pasa al bean el objeto
								bean.setId_user( id_user );

								//Actualiza el objeto en la BD
								tx = session.beginTransaction();
								session.update(bean);
								tx.commit();

								//forward declarado en struts-config.xml
								forward = mapping.findForward("okGlobal");
								httpSession.setAttribute("msg","El agente de ventas fue reasignado.");
								httpSession.setAttribute("done", "user/asignSupervisor.jsp" );
							  }
						  }
				  	}
								

			  } 
			catch (Exception e) 
			  {
				e.printStackTrace();

				com.tecunsa.Error error = new com.tecunsa.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
				httpSession.setAttribute("done", "javascript:history.back();");
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
