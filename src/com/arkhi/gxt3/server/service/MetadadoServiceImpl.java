package com.arkhi.gxt3.server.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arkhi.gxt3.server.dao.MetadadoDAO;
import com.arkhi.gxt3.shared.model.Metadado;
import com.arkhi.gxt3.shared.service.MetadadoService;

@Service("metadadoService")
public class MetadadoServiceImpl implements MetadadoService {
	
	@Autowired
	private MetadadoDAO metadadoDao;
	
	@PostConstruct
	public void init() throws Exception {}
	
	@PreDestroy
	public void destroy() {}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteMetadado(int id) {
		metadadoDao.deleteMetadado(id);
	}

	@Override
	public List<Metadado> getMetadados(int start, int limit) {
		return metadadoDao.getMetadados(start, limit);
	}

	@Override
	public int getTotalMetadados() {
		return metadadoDao.getTotalMetadados();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Metadado saveMetadado(Metadado metadado) {
		return metadadoDao.saveMetadado(metadado);
	}

}
