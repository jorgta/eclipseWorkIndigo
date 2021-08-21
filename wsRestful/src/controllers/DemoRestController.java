package controllers;


import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Product;


//fuente 
//https://learningprogramming.net/category/java/java-restful-web-services/

@Path("api/demo")
public class DemoRestController {
    //http://localhost:8080/wsRestful/api/demo/helloworld
	@GET
	@Path("helloworld")
	@Produces({MediaType.TEXT_PLAIN})
	public Response helloWorld() {
		try {
			return Response.ok("Hello World").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	//http://localhost:8080/wsRestful/api/demo/helloworld2
	@GET
	@Path("helloworld2")
	@Produces({ MediaType.TEXT_HTML })
	public Response helloWorld2() {
		try {
			return Response.ok("<b><i><u>Hello World</u></i></b>").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	//http://localhost:8080/wsRestful/api/demo/find
	@GET
	@Path("find")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response find() {
		try {
			Product product = new Product("p01", "name 1", 100);
			return Response.ok(product, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	@GET
	@Path("find2")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response find2() {
		try {
			List<Product> products = new ArrayList<Product>();
			products.add(new Product("p01", "name 1", 100));
			products.add(new Product("p02", "name 2", 200));
			products.add(new Product("p03", "name 3", 300));
			GenericEntity<List<Product>> genericEntity = new GenericEntity<List<Product>>(products){};
			return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	
	//http://localhost:8080/wsRestful/api/demo/create 
	//enviar datos raw en formato jason {"id":"p01","name":"name 1","price":"100.0"} en el body de postman
	@POST
	@Path("create")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response create(Product product) {
		try {
			System.out.println("New Product Information");
			System.out.println("Id: " + product.getId());
			System.out.println("Name: " + product.getName());
			System.out.println("Price: " + product.getPrice());
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	
	//http://localhost:8080/wsRestful/api/demo/update
	 //Use PUT Method in Java Restful Web Services
	//enviar datos raw en formato jason {"id":"p01","name":"name 1","price":"100.0"} en el body de postman
	@PUT
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response update(Product product) {
		try {
			System.out.println("Update Product Information");
			System.out.println("Id: " + product.getId());
			System.out.println("Name: " + product.getName());
			System.out.println("Price: " + product.getPrice());
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		}

	
	
	
	//Use DELETE Method in Java Restful Web Services
	//http://localhost:8080/wsRestful/api/demo/delete/2
	@DELETE
	@Path("delete/{id}")
	public Response delete(@PathParam("id") String id) {
		try {
			System.out.println("Id is deleted: " + id);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	@GET
	@Path("work1")
	@Produces({ MediaType.TEXT_PLAIN })
	@PermitAll
	public Response work1() {
		try {
			return Response.ok("Work 1").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("work2")
	@Produces({ MediaType.TEXT_PLAIN })
	@RolesAllowed({ "superadmin" })
	public Response work2() {
		try {
			return Response.ok("Work 2").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("work3")
	@Produces({ MediaType.TEXT_PLAIN })
	@RolesAllowed({ "superadmin", "admin" })
	public Response work3() {
		try {
			return Response.ok("Work 3").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("work4")
	@Produces({ MediaType.TEXT_PLAIN })
	@RolesAllowed({ "superadmin", "admin", "employee" })
	public Response work4() {
		try {
			return Response.ok("Work 4").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	

}
