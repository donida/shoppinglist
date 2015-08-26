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

import com.trier.exam.shopping.model.Category;
import com.trier.exam.shopping.service.CategoryService;

@Path("/category")
@Stateless
public class CategoryResource {

	@EJB
	private CategoryService categoryService;
	
	public CategoryResource() {
		super();
	}
	
	@Path("query")
	@GET
	@Consumes({ "application/x-www-form-urlencoded", "text/plain" })
	@Produces({ "application/json", "application/xml" })
	public List<Category> query(@QueryParam("name") String name) {
		if (StringUtils.isNotEmpty(name))
			return categoryService.findByName(name);
		return categoryService.findAll();
	}
	
	@GET
	@Consumes({ "application/x-www-form-urlencoded", "text/plain" })
	@Produces({ "application/json", "application/xml" })
	public Category get(@QueryParam("id") Long id) {
		return categoryService.findById(id);
	}

	@DELETE
	@Consumes({ "application/x-www-form-urlencoded", "text/plain" })
	@Produces({ "text/plain" })
	public String delete(@QueryParam("id") Long id) {
		categoryService.delete(id);
		return "success";
	}

	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public Category save(Category category) {
		if (category.getId() != null && category.getId() <= 0)
			category.setId(null);
		return categoryService.persist(category);
	}
	
	@PUT
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public Category update(Category category) {
		return categoryService.persist(category);
	}

}
