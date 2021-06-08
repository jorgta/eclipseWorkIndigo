package com.struts2.actions;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import com.struts2.Beans.*;
import com.bituos.*;

import org.hibernate.classic.Session;

/*
 @Validations(requiredStrings = { 
 @RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required"), 
 @RequiredStringValidator(fieldName = "password", type = ValidatorType.FIELD, message = "Password is required")
 }, expressions = {
 @ExpressionValidator(expression = "company.trim().equals('bituos') == true", message = "Empresa debe ser bituos."),

 })*/
public class DeviceChangeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;

	private String id;
	private String process;

	private String masterid;
	private String detailid;

	private String devicemaster;
	private String detail;
	
	private String counterid;
	
	private String timetoreport;
	
	

	private HttpSession httpSession = null;
	private HttpServletRequest request = null;

	public void validate() {
		String fieldr = "Campo requerido";
		String fieldnospaces = "El campo no debe tener espacio";
		String fieldinvalid = "Formato Invalido";

		HttpServletRequest request = ServletActionContext.getRequest();
		httpSession = request.getSession();
		httpSession.setAttribute("module", "pre");
		httpSession = null;
		request = null;

		if (getProcess().equals("selectDevice2Change")) {
			if (id != null) {
				id = id.trim();
				if (id.length() == 0)
					this.addFieldError("id", getText(fieldr));
				if (id.indexOf(" ") > 0)
					this.addFieldError("id", getText(fieldnospaces));
			} else
				this.addFieldError("id", getText(fieldr));
		}

		if (getProcess().equals("OnChangeMaster")) {

			if (masterid != null) {
				masterid = masterid.trim();
				if (masterid.length() == 0)
					this.addFieldError("masterid", getText(fieldr));
				if (masterid.indexOf(" ") > 0)
					this.addFieldError("masterid", getText(fieldnospaces));
			} else
				this.addFieldError("masterid", getText(fieldr));

		}
		
		
		if (getProcess().equals("add")) {

			if (timetoreport != null) {
				timetoreport = timetoreport.trim();
				if (timetoreport.length() == 0)
					this.addFieldError("timetoreport", getText(fieldr));
				if (timetoreport.indexOf(" ") > 0)
					this.addFieldError("timetoreport", getText(fieldnospaces));
			} else
				this.addFieldError("timetoreport", getText(fieldr));

		}
		
		

	}

	public String execute() throws Exception {
		// ActionErrors errors = new ActionErrors();
		// ActionForward forward = new ActionForward();
		String forward = SUCCESS;
		// return value

		// LoginForm loginForm = (LoginForm) form;

		// httpSession = request.getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		httpSession = request.getSession();

		Config config = new Config(request);

		SessionFactory sessionFactory = null;

		Session session = null;
		Transaction tx = null;

		try {
			sessionFactory = config.getConfiguration(request)
					.buildSessionFactory();
			session = sessionFactory.openSession();

			BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
			if (beanUser == null) {
				forward = "logout";
			} else {

				if (process.equals("selectDevice2Change")) {
					httpSession.removeAttribute("listCounter");
					BeanDevice bean = (BeanDevice) session.get(
							BeanDevice.class, new Integer(getId().toString()));
					httpSession.setAttribute("deviceSelected", bean);

					String queryCounter = " FROM BeanCounter u"
							+ " WHERE u.id_device = " + getId().toString();

					List listCounter = session.createQuery(queryCounter).list();

					// Change Counters

					String queryMaster = " FROM BeanType_Counter_Master u"
							+ " WHERE u.active = 1"
							+ " ORDER BY u.name_type_counter_master";

					List listMaster = session.createQuery(queryMaster).list();
					String id = String
							.valueOf(((BeanType_Counter_Master) listMaster
									.get(0)).getId());

					if (!listMaster.isEmpty()) {
						String queryDetail = " FROM BeanType_Counter_Detail u"
								+ " WHERE u.id_type_counter_master = "
								+ ((BeanType_Counter_Master) listMaster.get(0))
										.getId() + " AND u.active = 1"
								+ " ORDER BY u.name_type_counter_detail";

						List listDetail = session.createQuery(queryDetail)
								.list();

						httpSession.setAttribute("changeDeviceListMaster",
								listMaster);
						httpSession.setAttribute("changeDeviceListDetail",
								listDetail);

						if (!listCounter.isEmpty()) {
							httpSession
									.setAttribute("listCounter", listCounter);
						}
					} else {
						httpSession.setAttribute("information",
								"No hay dispositivos registrados.");
					}

				} else if (process.equals("OnChangeMaster")) {
					BeanType_Counter_Master beanMaster = (BeanType_Counter_Master) session
							.get(BeanType_Counter_Master.class, new Integer(
									Integer.valueOf(getMasterid())));

					httpSession.setAttribute("masterSelected",
							beanMaster.getId());
					// bean.setActive(0);

					if (beanMaster.getId() != 0) {
						String queryDetail = " FROM BeanType_Counter_Detail u"
								+ " WHERE u.id_type_counter_master = "
								+ beanMaster.getId() + " AND u.active = 1"
								+ " ORDER BY u.name_type_counter_detail";

						List listDetail = session.createQuery(queryDetail)
								.list();

						httpSession.setAttribute("changeDeviceListDetail",
								listDetail);
					} else {
						httpSession.setAttribute("information","No hay dispositivos registrados.");
					}

				} else if (process.equals("add")) {
					// BeanType_Counter_Master beanMaster =
					// (BeanType_Counter_Master) session.get(
					// BeanType_Counter_Master.class, new Integer(
					// Integer.valueOf(getDevicemaster())));
					BeanType_Counter_Detail beanDetail = (BeanType_Counter_Detail) session.get(BeanType_Counter_Detail.class, new Integer(Integer.valueOf(getDetail())));
					BeanDevice bean = (BeanDevice) session.get(BeanDevice.class, new Integer(getId().toString()));

					// httpSession.setAttribute("masterSelected",beanMaster.getId());
					// bean.setActive(0);
					tx = session.beginTransaction();
					BeanCounter counter = new BeanCounter();
					counter.setId_device(bean);
					counter.setId_type_counter_detail(beanDetail);
					counter.setTimeToReport(new Integer(getTimetoreport()));

					session.save(counter);
					
					httpSession.setAttribute("done", "Contador agregado correctamente");

					String queryCounter = " FROM BeanCounter u"
							+ " WHERE u.id_device = " + getId().toString();

					List listCounter = session.createQuery(queryCounter).list();

					if (!listCounter.isEmpty()) {
						httpSession.setAttribute("listCounter", listCounter);
					} else {
						httpSession.setAttribute("information",
								"No hay contadores registrados.");
					}

					tx.commit();

				}
				else if (process.equals("DeleteCounter")) 
				  {
					BeanCounter beanCounter = (BeanCounter) session.get(BeanCounter.class, new Integer(getCounterid().toString()));

					tx = session.beginTransaction();

					//Borrar Log Detail
					String queryDelete =    " DELETE " 
							              + " FROM BeanCounterLogDetail u"
								          + " WHERE u.id_counter_log IN ( SELECT id"
								          + "                             FROM BeanCounterLog"
								          + "                             WHERE id_counter.id = " + getCounterid().toString()
								          + "                           )";

					Query query = session.createQuery( queryDelete );
					query.executeUpdate();
					
					//Borrar Log
					queryDelete =   " DELETE " 
					              + " FROM BeanCounterLog"
						          + " WHERE id_counter.id = " + getCounterid().toString();

					query = session.createQuery( queryDelete );
					query.executeUpdate();
					
					//Borrar Counter
					session.delete(beanCounter);

					String queryCounter = " FROM BeanCounter u"
										+ " WHERE u.id_device = " + getId().toString();

					tx.commit();

					List listCounter = session.createQuery(queryCounter).list();

					httpSession.setAttribute("listCounter", listCounter);

					if ( listCounter.isEmpty() ) 
					  {
						httpSession.setAttribute("information", "No hay contadores registrados.");
					  }

					httpSession.setAttribute("done", "Contador eliminado correctamente");
				  }

			}
		} catch (Exception e) {
			e.printStackTrace();

			httpSession.setAttribute("module", "pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "
					+ e.getMessage()));
			forward = ERROR;

			// errors.add("unknown", new
			// org.apache.struts.action.ActionError("error.unknow"));
		} finally {
			if (session != null)
				session.close();

			if (sessionFactory != null)
				sessionFactory.close();
		}

		// Write logic determining how the user should be forwarded.

		/*
		 * if (forward !="") {
		 * 
		 * forward = ERROR; } else forward = "links";
		 */

		// Finish with
		return (forward);

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getDetailid() {
		return detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

	public String getDevicemaster() {
		return devicemaster;
	}

	public void setDevicemaster(String devicemaster) {
		this.devicemaster = devicemaster;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCounterid() {
		return counterid;
	}

	public void setCounterid(String counterid) {
		this.counterid = counterid;
	}

	public String getTimetoreport() {
		return timetoreport;
	}

	public void setTimetoreport(String timetoreport) {
		this.timetoreport = timetoreport;
	}
	
	
	

}
