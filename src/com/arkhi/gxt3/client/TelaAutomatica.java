package com.arkhi.gxt3.client;

import java.util.List;

import com.arkhi.gxt3.client.ui.ContactGrid;
import com.arkhi.gxt3.shared.model.ContactProperties;
import com.arkhi.gxt3.shared.model.Metadado;
import com.arkhi.gxt3.shared.model.Tipo;
import com.arkhi.gxt3.shared.service.ContactService;
import com.arkhi.gxt3.shared.service.ContactServiceAsync;
import com.arkhi.gxt3.shared.service.MetadadoService;
import com.arkhi.gxt3.shared.service.MetadadoServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

public class TelaAutomatica implements EntryPoint, IsWidget {

	private final ContactServiceAsync serviceContact = GWT.create(ContactService.class);
	private final MetadadoServiceAsync serviceMetadado = GWT.create(MetadadoService.class);

	private final ContactProperties props = GWT.create(ContactProperties.class);

	private ContactGrid grid;

	@Override
	public void onModuleLoad() {
		final VerticalPanel panel = new VerticalPanel();
		
		final Button buttonInserir = new Button("Inserir");
		final Button buttonRemover = new Button("Remover");
		final Button buttonAlterar = new Button("Alterar");
		final Button buttonPesquisar = new Button("Pesquisar");
		
		serviceMetadado.getMetadados(0, 25, new AsyncCallback<List<Metadado>>() {
			@Override
			public void onSuccess(List<Metadado> result) {
				HorizontalPanel painelHorizontal = null;

				for (Metadado metadado : result) {
					painelHorizontal = new HorizontalPanel();
					painelHorizontal.setSpacing(5);
					painelHorizontal.add(new Label(metadado.getRotulo()));
					if(metadado.getTipo().equals(Tipo.TEXT.getLabel())){
						painelHorizontal.add(new TextBox());
					}else if(metadado.getTipo().equals(Tipo.DATE.getLabel())){
						painelHorizontal.add(new DatePicker());
					}
					panel.add(painelHorizontal);
				}
				
			}
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
		
		HorizontalPanel p2 = new HorizontalPanel();
		p2.setSpacing(10);
		p2.add(buttonInserir);
		p2.add(buttonRemover);
		p2.add(buttonAlterar);
		p2.add(buttonPesquisar);
		
		panel.add(p2);
		
		RootPanel.get().add(panel);
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}



}
