package com.bituos.bar.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import com.bituos.bar.Beans.*;
import com.bituos.bar.forms.*;

import java.util.*;

import com.bituos.*;


//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

// href=<%="'"%><bean:write name="id" property="id_preForm.getProcess().url" /><%="'"%> >
//<html:submit /> <bean:write name="id" property="id_preForm.getProcess().description" /> </html:submit>


public class DeleteProductAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value

		DeleteProductForm deleteProductForm = (DeleteProductForm) form;

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		String query;
		List list;
	    
		 
			try
			  {
				
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				
				if ( deleteProductForm.getProcess().equals("deleteProduct") ) //delete user
				  {
					
					BeanDetalleComanda beanDetalleComanda = (BeanDetalleComanda)session.load( BeanDetalleComanda.class, new Integer(deleteProductForm.getId_detail()));
					
					tx = session.beginTransaction();
					BeanDetalleComanda beanDetalleComandaDelete = new BeanDetalleComanda();
					beanDetalleComandaDelete.setId_comanda(beanDetalleComanda.getId_comanda());
					beanDetalleComandaDelete.setId_producto(beanDetalleComanda.getId_producto());					
					 beanDetalleComandaDelete.setCantidad(-1);					
					beanDetalleComandaDelete.setPrecio_unit(beanDetalleComanda.getPrecio_unit());
					beanDetalleComandaDelete.setProducto_descripcion(beanDetalleComanda.getProducto_descripcion());
					Date ahora = new Date();				    
					beanDetalleComandaDelete.setDate_capture(ahora);
					
					session.save( beanDetalleComandaDelete );
			    	
			    	tx.commit();
					

					 
			    	 forward = mapping.findForward("ok"); //http://localhost/smsfreeworld/pre.jsp?&process='campaignNew'&action='pre.do'&target='_self'
					 httpSession.setAttribute("done", new String("/bar/tableSelect.do?process='currentTable'&id_table="+beanDetalleComanda.getId_comanda().getId_mesa().getId()+"&id_agent="+beanDetalleComanda.getId_comanda().getId_agente().getId()));
			
		
				  }
				
				
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				
				com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				
				forward = mapping.findForward("error");
			  }
			finally
			  {
 

				if(session != null)
				 session.close();
				
				
				if(sessionFactory != null)
					sessionFactory.close();
				
				
			  }
		 
	
		

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		// Write logic determining how the user should be forwarded.

		// Finish with
		return (forward);

	}
}
