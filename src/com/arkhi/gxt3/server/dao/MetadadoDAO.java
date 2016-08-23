package com.arkhi.gxt3.server.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.arkhi.gxt3.shared.model.Metadado;

@Repository
public class MetadadoDAO {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * Get List of metadados from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<Metadado> getMetadados(int start, int limit) {

		DetachedCriteria criteria = DetachedCriteria.forClass(Metadado.class);

		return hibernateTemplate.findByCriteria(criteria, start, limit);
	}

	/**
	 * Delete a metadado with the id passed as parameter
	 * @param id
	 */
	public void deleteMetadado(int id){
		Object record = hibernateTemplate.load(Metadado.class, id);
		hibernateTemplate.delete(record);
	}

	/**
	 * Create a new Metadado on the database or
	 * Update metadado
	 * @param metadado
	 * @return metadado added or updated in DB
	 */
	public Metadado saveMetadado(Metadado metadado){
		hibernateTemplate.saveOrUpdate(metadado);
		return metadado;
	}

	/**
	 * Get total of Metadados from database
	 * @return
	 */
	public int getTotalMetadados(){
		return DataAccessUtils.intResult(hibernateTemplate.find("SELECT COUNT(*) FROM Metadodo"));
	}
}
