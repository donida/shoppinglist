package com.trier.exam.shopping.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;

import com.trier.exam.shopping.model.Product;
import com.trier.exam.shopping.service.ProductService;

@Path("/product")
@Stateless
public class ProductResource {

	@EJB
	private ProductService productService;
	
	public ProductResource() {
		super();
	}
	
	@Path("query")
	@GET
	@Consumes({ "application/x-www-form-urlencoded", "text/plain" })
	@Produces({ "application/json", "application/xml" })
	public List<Product> query(@QueryParam("name") String name, @QueryParam("notin") List<Long> ids) {
		if (ids != null && !ids.isEmpty())
			return productService.findByIdNotIn(ids);
		if (StringUtils.isNotEmpty(name))
			return productService.findByName(name);
		return productService.findAll();
	}
	
	@GET
	@Consumes({ "application/x-www-form-urlencoded", "text/plain" })
	@Produces({ "application/json", "application/xml" })
	public Product get(@QueryParam("id") Long id) {
		return productService.findById(id);
	}

	@DELETE
	@Consumes({ "application/x-www-form-urlencoded", "text/plain" })
	@Produces({ "text/plain" })
	public String delete(@QueryParam("id") Long id) {
		productService.delete(id);
		return "success";
	}

	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public Product save(Product product) {
		if (product.getId() != null && product.getId() <= 0)
			product.setId(null);
		return productService.persist(product);
	}
	
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public Product update(Product product) {
		return productService.persist(product);
	}
	
}
