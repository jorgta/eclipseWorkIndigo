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

import contactcenter.forms.UserDeleteForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class UserDeleteAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		// Recibe  valores del  jsp y se los pasa al bean de tipo BeanUser
		UserDeleteForm userDeleteForm = (UserDeleteForm) form;

		HttpSession httpSession = request.getSession();

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

				//Lista los o el usuario(s) activos
				String query =  " FROM BeanUser u" +
								" WHERE u.id = " + userDeleteForm.getId() +
								" AND u.active='Y'";

				List list = session.createQuery( query ).list();

				if(list.size() != 0)
				  {

					//Recibiendo valores de userDeleteForm
					BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( userDeleteForm.getId() ) );

					// borrar relacion entre user vs user
					query = " FROM BeanUserUser u" +
							" WHERE u.id_supervisor.id = " + userDeleteForm.getId() +
							" OR    u.id_user.id = " + userDeleteForm.getId();

					list = session.createQuery( query ).list();
					
					Iterator iter = list.iterator();
					
					while ( iter.hasNext() )
					  {
						session.delete( (BeanUserUser) iter.next() );
					  }

					// borrar log
					query = " FROM BeanLog u" +
							" WHERE u.id_user.id = " + userDeleteForm.getId();

					list = session.createQuery( query ).list();
					
					iter = list.iterator();
					
					while ( iter.hasNext() )
					  {
						session.delete( (BeanLog) iter.next() );
					  }


					// borrar permisos a procesos
					query = " FROM BeanProcessUsers u" +
							" WHERE u.id_user.id = " + userDeleteForm.getId();

					list = session.createQuery( query ).list();
					
					iter = list.iterator();
					
					while ( iter.hasNext() )
					  {
						session.delete( (BeanProcessUsers) iter.next() );
					  }

					// borrar permisos a profiles
					query = " FROM BeanUserProfile u" +
							" WHERE u.id_user.id = " + userDeleteForm.getId();

					list = session.createQuery( query ).list();
					
					iter = list.iterator();
					
					while ( iter.hasNext() )
					  {
						session.delete( (BeanUserProfile) iter.next() );
					  }

					//borrar user
					query = " FROM BeanClient u" +
							" WHERE u.id = " + userDeleteForm.getId() +
							" AND u.id NOT IN ( SELECT DISTINCT c.id_user_capture.id" +
							"                   FROM BeanCase c " +
							"                 )"  +
							" AND u.id NOT IN ( SELECT DISTINCT c.id_user.id" +
							"                   FROM BeanCase c " +
							"                 )" +
							" AND u.id NOT IN ( SELECT DISTINCT c.id_user.id" +
							"                   FROM BeanCaseNotes c " +
							"                 )" +
							" AND u.id NOT IN ( SELECT DISTINCT c.id_user.id" +
							"                   FROM BeanFiles c " +
							"                 )" ;

					list = session.createQuery( query ).list();
					
					

					//Actualiza la informacion el la BD
					tx = session.beginTransaction();
					
					
					if ( list.size() > 0 )
					  {
						session.delete( bean );
					  }
					else
					  {
						bean.setActive("N");
						session.update( bean );
					  }
					

					tx.commit();

					// query to return the list of the active users
					//Actualiza la lista de usuarios activos
					query =  " FROM BeanUser u" +
						     " WHERE u.active='Y'";
					list = session.createQuery( query ).list();
					httpSession.setAttribute("filterList", list );


					httpSession.setAttribute("done", new String("user/userDelete.jsp"));
					//forward userNew declarado en struts-config.xml
					forward = mapping.findForward("ok");
				  }
				else 
				  {
					com.tecunsa.Error e = new com.tecunsa.Error("El identificador de usuario no es válido o ya ha sido eliminado.", request);
					httpSession.setAttribute("done", "user/userDelete.jsp");
					//forward userNew declarado en struts-config.xml
					forward = mapping.findForward("error");
			 	  }

			} 
		  catch (Exception e) 
		    {
		    	if ( tx != null)
		    	  tx.rollback();
		    	  
				e.printStackTrace();

				com.tecunsa.Error error = new com.tecunsa.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
				httpSession.setAttribute("done", "javascript:history.back();");
				//forward userNew declarado en struts-config.xml
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
