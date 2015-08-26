package com.trier.exam.shopping.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.trier.exam.shopping.model.ProductList;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
public class ProductListService extends AbstractService<ProductList> {

	@PersistenceContext(unitName = "ShoppingPU")
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	protected Class<ProductList> getEntityClass() {
		return ProductList.class;
	}
	
	@Override
	protected Long getEntityId(ProductList entity) {
		if (entity == null)
			return null;
		return entity.getId();
	}

	public List<ProductList> findByNameAndProductNameAndCategoryName(String name, String productname, String categoryname) {
		CriteriaQuery<ProductList> defaultSelect = getDefaultSelect();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		Root<?> from = defaultSelect.getRoots().iterator().next();
		Expression<String> path = null;
		if (StringUtils.isNotBlank(name)) {
			path = from.get("name");
			defaultSelect.where(cb.like(path, "%"+name+"%"));
		}
		if (StringUtils.isNotBlank(productname)) {
			if (path == null) {
				path = from.join("items").join("product").get("name");
				defaultSelect.where(cb.like(path, "%"+productname+"%"));
			} else {
				path = from.join("items").join("product").get("name");
				cb.and(cb.like(path, "%"+productname+"%"));
			}
		}
		if (StringUtils.isNotBlank(categoryname)) {
			if (path == null) {
				path = from.join("category").get("name");
				defaultSelect.where(cb.like(path, "%"+categoryname+"%"));
			} else {
				path = from.join("category").get("name");
				cb.and(cb.like(path, "%"+categoryname+"%"));
			}
		}
		TypedQuery<ProductList> typedQuery = entityManager.createQuery(defaultSelect);
		return typedQuery.getResultList();
	}
	
}
