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

import com.trier.exam.shopping.model.Category;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
public class CategoryService extends AbstractService<Category> {

	@PersistenceContext(unitName = "ShoppingPU")
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	protected Class<Category> getEntityClass() {
		return Category.class;
	}

	@Override
	protected Long getEntityId(Category entity) {
		if (entity == null)
			return null;
		return entity.getId();
	}

	public List<Category> findByName(String name) {
		CriteriaQuery<Category> defaultSelect = getDefaultSelect();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		Root<?> from = defaultSelect.getRoots().iterator().next();
		Expression<String> path = from.get("name");
		defaultSelect.where(cb.like(path, "%"+name+"%"));
		TypedQuery<Category> typedQuery = entityManager.createQuery(defaultSelect);
		return typedQuery.getResultList();
	}
	
	
}
