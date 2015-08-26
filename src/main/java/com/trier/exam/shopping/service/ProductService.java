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

import com.trier.exam.shopping.model.Product;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
public class ProductService extends AbstractService<Product> {
	
	@PersistenceContext(unitName = "ShoppingPU")
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}
	
	@Override
	protected Long getEntityId(Product entity) {
		if (entity == null)
			return null;
		return entity.getId();
	}
	
	public List<Product> findByName(String name) {
		CriteriaQuery<Product> defaultSelect = getDefaultSelect();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		Root<?> from = defaultSelect.getRoots().iterator().next();
		Expression<String> path = from.get("name");
		defaultSelect.where(cb.like(path, "%"+name+"%"));
		TypedQuery<Product> typedQuery = entityManager.createQuery(defaultSelect);
		return typedQuery.getResultList();
	}
	
	public List<Product> findByIdNotIn(List<Long> ids) {
		CriteriaQuery<Product> defaultSelect = getDefaultSelect();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		Root<?> from = defaultSelect.getRoots().iterator().next();
		Expression<String> path = from.get("id");
		defaultSelect.where(cb.not(path.in(ids)));
		TypedQuery<Product> typedQuery = entityManager.createQuery(defaultSelect);
		return typedQuery.getResultList();
	}

}
