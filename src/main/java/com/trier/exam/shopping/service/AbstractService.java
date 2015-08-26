package com.trier.exam.shopping.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractService<T> {
	
	protected abstract EntityManager getEntityManager();
	
	protected abstract Class<T> getEntityClass();
	
	protected abstract Long getEntityId(T entity);

	protected CriteriaQuery<T> getDefaultSelect() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(getEntityClass());
		Root<T> root = q.from(getEntityClass());
		return q.select(root).distinct(true);
	}
	
	public List<T> findAll() {
		CriteriaQuery<T> defaultSelect = getDefaultSelect();
		TypedQuery<T> typedQuery = getEntityManager().createQuery(defaultSelect);
		return typedQuery.getResultList();
	};
	
	public T persist(T entity) {
		return getEntityManager().merge(entity);
	}

	public T findById(Long id) {
		return getEntityManager().find(getEntityClass(), id);
	}
	
	public void delete(Long id) {
		T entity = findById(id);
		if (entity != null)
			getEntityManager().remove(entity);
	}
	
}
