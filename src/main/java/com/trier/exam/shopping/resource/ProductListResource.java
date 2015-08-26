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

import com.trier.exam.shopping.model.ProductList;
import com.trier.exam.shopping.service.ProductListService;

@Path("/productlist")
@Stateless
public class ProductListResource {

	@EJB
	private ProductListService productListService;
	
	@Path("query")
	@GET
	@Consumes({ "application/x-www-form-urlencoded", "text/plain" })
	@Produces({ "application/json", "application/xml" })
	public List<ProductList> query(@QueryParam("name") String name, 
			@QueryParam("productname") String productname, 
			@QueryParam("categoryname") String categoryname) {
		if (StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(productname) || StringUtils.isNotEmpty(categoryname))
			return productListService.findByNameAndProductNameAndCategoryName(name, productname, categoryname);
		else
			return productListService.findAll();
	}
	
	@GET
	@Consumes({ "application/x-www-form-urlencoded", "text/plain" })
	@Produces({ "application/json", "application/xml" })
	public ProductList get(@QueryParam("id") Long id) {
		return productListService.findById(id);
	}
	
	@DELETE
	@Consumes({ "application/x-www-form-urlencoded", "text/plain" })
	@Produces({ "text/plain" })
	public String delete(@QueryParam("id") Long id) {
		productListService.delete(id);
		return "success";
	}
	
	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public ProductList save(ProductList productList) {
		if (productList.getId() != null && productList.getId() <= 0)
			productList.setId(null);
		return productListService.persist(productList);
	}
	
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public ProductList update(ProductList productList) {
		return productListService.persist(productList);
	}

}
