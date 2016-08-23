package com.arkhi.gxt3.shared.service;

import java.util.List;

import com.arkhi.gxt3.shared.model.Metadado;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gxt3/metadadoService")
public interface MetadadoService extends RemoteService {

	void deleteMetadado(int id);

	List<Metadado> getMetadados(int start, int limit);

	int getTotalMetadados();

	Metadado saveMetadado(Metadado contact);

}
