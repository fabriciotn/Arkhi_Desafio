package com.arkhi.gxt3.shared.service;

import java.util.List;

import com.arkhi.gxt3.shared.model.Contact;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ContactServiceAsync {

	void getContacts(int start, int limit, AsyncCallback<List<Contact>> callback);
	
	void deleteContact(int id, AsyncCallback<Void> callback);
	
	void saveContact(Contact contact, AsyncCallback<Contact> callback);
	
	void getTotalContacts(AsyncCallback<Integer> callback);
}
