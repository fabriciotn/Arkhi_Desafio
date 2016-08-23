package com.arkhi.gxt3.shared.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ContactProperties extends PropertyAccess<Contact> {

	@Path("id")
	ModelKeyProvider<Contact> key();
	
	ValueProvider<Contact, String> nome();
	
	ValueProvider<Contact, String> telefone();
	
	ValueProvider<Contact, String> email();
	
	ValueProvider<Contact, String> dataNascimento();
	
	ValueProvider<Contact, String> endereco();
	
	ValueProvider<Contact, String> profissao();
	
	ValueProvider<Contact, Integer> idade();
}
