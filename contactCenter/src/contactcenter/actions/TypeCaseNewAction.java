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

import contactcenter.forms.CaseNewForm;
import contactcenter.forms.ClientNewForm;
import contactcenter.forms.TypeCaseNewForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class TypeCaseNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// Retorna y Obtiene los valores
		TypeCaseNewForm typeCaseNewForm = (TypeCaseNewForm) form;

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("done");

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {
			Config config = new Config( request );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

			try {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				if(typeCaseNewForm.getProcess().equals("add"))
				{
					boolean exist=false;


					//Lista los typos de casos
				       String query = " FROM BeanTypeCase u" +
							          " WHERE u.id = u.id";

					List list = session.createQuery( query ).list();

					Iterator iter =list.iterator();
					while(iter.hasNext())
					{
						BeanTypeCase beanTypeCase = (BeanTypeCase)iter.next();

						if( beanTypeCase.getDescription().equals(typeCaseNewForm.getDescription()))
						{
							exist=true;
						}
					}

					if(!exist)
					{
						BeanTypeCase bean= new BeanTypeCase();

						bean.setDescription(typeCaseNewForm.getDescription());
						bean.setDays(new Integer(typeCaseNewForm.getDays()).intValue());
						//Guarda el tipo de caso en la BD
						tx = session.beginTransaction();
						session.save( bean );
						tx.commit();
						httpSession.setAttribute("done", "pre.do?process=typeCaseNew" );
						//forward declarado en struts-config.xml
						forward = mapping.findForward("okGlobal");
					}
					else
					{
						httpSession.setAttribute("done", "javascript:history.back();");
						com.tecunsa.Error e = new com.tecunsa.Error(" Ese tipo ya existe ", request);
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					}
				}
				else
				if(typeCaseNewForm.getProcess().equals("delete"))
				{
					//Lista los casos por status y los borra
					String query = " FROM BeanTypeCase u" +
							       " WHERE u.id = " + typeCaseNewForm.getId_type_case() +
								   " AND u.id NOT IN ( SELECT c.id_type_case.id" +								   "                   FROM BeanCase c" +
								   "		         )"; 
//								   " AND u-id_type_case.id NOT IN ( SELECT cn.id_type_case.id" +
//								   "                                FROM BeanCaseNote cn"+
//								   "                              )";


					List list = session.createQuery( query ).list();
					if(list.size()>0)
						{
								BeanTypeCase bean = (BeanTypeCase) session.load( BeanTypeCase.class, new Integer(typeCaseNewForm.getId_type_case()));
								//Borra el tipo de caso
								tx = session.beginTransaction();
										session.delete( bean );
								tx.commit();

								query = " FROM BeanTypeCase u" +
										" WHERE u.id = u.id" +
										" ORDER BY u.id";

								list = session.createQuery( query ).list();

								httpSession.setAttribute("typeslist", list );
								//forward declarado en struts-config.xml
								forward = mapping.findForward("loop");
						}
					else
						{
							httpSession.setAttribute("done", "javascript:history.back();");
							com.tecunsa.Error e = new com.tecunsa.Error("No se puede realizar la operación. Hay casos relacionados con este tipo.", request);
							//forward declarado en struts-config.xml
							forward = mapping.findForward("error");
						}

				}

			} catch (Exception m) {


				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");
				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			}
			finally	{
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

