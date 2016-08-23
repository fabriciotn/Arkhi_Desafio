package com.arkhi.gxt3.shared.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface MetadadoProperties extends PropertyAccess<Metadado> {

	@Path("id")
	ModelKeyProvider<Metadado> key();
	
	ValueProvider<Metadado, String> nome();
	
	ValueProvider<Metadado, String> rotulo();
	
	ValueProvider<Metadado, String> tipo();
	
	ValueProvider<Metadado, String> campoBD();
}
