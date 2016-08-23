package com.arkhi.gxt3.shared.service;

import java.util.List;

import com.arkhi.gxt3.shared.model.Contact;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gxt3/contactService")
public interface ContactService extends RemoteService {

	void deleteContact(int id);

	List<Contact> getContacts(int start, int limit);

	int getTotalContacts();

	Contact saveContact(Contact contact);

}
