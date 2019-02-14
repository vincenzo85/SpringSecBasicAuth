/**
 * robgion
 * www.2clever.it
 *
 * @${date}
 * For further information please write to info@2clever.it
 */
package it.clever.springsec.repo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenericDaoImpl<T> implements GenericDao<T> {

	/**
	 * Iniezione dell'EntityManager in modo automatico.
	 */
	@PersistenceContext
	protected EntityManager em;

	private Class<T> type;

	@PostConstruct
	public void init() {

		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];

	}
	
	public T create(final T t) {
		this.em.persist(t);
		return t;
	}

	public void delete(final Object id) {
		this.em.remove(this.em.getReference(type, id));
	}

	public T find(final Object id) {
		return (T) this.em.find(type, id);
	}

	public T update(final T t) {
		return this.em.merge(t);
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	/**
	 * Metodo per la gestione della tipica findAll tramite CriteriaQuery.
	 */
	@Override
	public List<T> findAll() {

		// Step1: ottenimento del CriteriaBuilder dall'EntityManager.
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		/*
		 * Step1: ottenimento della CriteriaQuery dal CriteriaBuilder.
		 * Rappresenta una query (select fino alla versione 2.0, insert/update
		 * dalla 2.1) JPQL. Modella ogni possibile clausola per una query JPQL.
		 * 
		 */
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(type);

		/*
		 * Step 2. Creazione del criteria root per definire la root entity a
		 * partire dalla quale ha origine la navigazione.
		 */
		Root root = criteriaQuery.from(type);

		criteriaQuery.select(root);

		return em.createQuery(criteriaQuery).getResultList();
	}
}
