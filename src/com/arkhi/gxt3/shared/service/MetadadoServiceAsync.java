package com.arkhi.gxt3.shared.service;

import java.util.List;

import com.arkhi.gxt3.shared.model.Metadado;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MetadadoServiceAsync {

	void getMetadados(int start, int limit, AsyncCallback<List<Metadado>> callback);
	
	void deleteMetadado(int id, AsyncCallback<Void> callback);
	
	void saveMetadado(Metadado metadado, AsyncCallback<Metadado> callback);
	
	void getTotalMetadados(AsyncCallback<Integer> callback);
}
