package com.arkhi.gxt3.client;

import java.util.List;

import com.arkhi.gxt3.client.ui.MetadadoGrid;
import com.arkhi.gxt3.shared.model.Metadado;
import com.arkhi.gxt3.shared.model.Tipo;
import com.arkhi.gxt3.shared.service.FieldVerifier;
import com.arkhi.gxt3.shared.service.MetadadoService;
import com.arkhi.gxt3.shared.service.MetadadoServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.StringComboBox;

public class Arkhi implements EntryPoint, IsWidget{
	
	private final MetadadoServiceAsync service = GWT.create(MetadadoService.class);
	
	/**
	 * The message displayed to the user when the server cannot be reached or returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	 
	private MetadadoGrid grid;
	
	
	public void onModuleLoad() {
		final Label labelNome = new Label("Nome:");
		final Label labelRotulo = new Label("Rótulo:");
		final Label labelTipo = new Label("Tipo:");
		final Label labelCampoBD = new Label("Campo no BD:");
		final Button sendButton = new Button("Gravar");
		final Button botaoGerarTela = new Button("Gerar Tela Dinâmica");
		final TextBox nameField = new TextBox();
		final TextBox rotuloField = new TextBox();
		final RadioButton tipoFieldText = new RadioButton("grupo", "TextBox");
		final RadioButton tipoFieldDate = new RadioButton("grupo", "DatePiker");
		final Label errorLabel = new Label();
		final StringComboBox campoBD = new StringComboBox();
		campoBD.add("nome");
		campoBD.add("telefone");
		campoBD.add("email");
		campoBD.add("dataNascimento");
		campoBD.add("endereco");
		campoBD.add("profissao");
		campoBD.add("idade");

		
		tipoFieldText.setChecked(true);
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(10);
		panel.add(labelNome);
		panel.add(nameField);
		panel.add(labelRotulo);
		panel.add(rotuloField);
		panel.add(labelTipo);
		panel.add(tipoFieldText);
		panel.add(tipoFieldDate);
		panel.add(labelCampoBD);
		panel.add(campoBD);
		panel.add(sendButton);
		panel.add(botaoGerarTela);
		panel.add(errorLabel);
		
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		RootPanel.get().add(panel);
		RootPanel.get().add(this);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Metadado gravado com sucesso:</b> "));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
				RootPanel.get().clear();
				onModuleLoad();
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {

			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendEventToServer();
				
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendEventToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendEventToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String nameFieldText = nameField.getText();
				String rotuloFieldText = rotuloField.getText();
				String tipoField = "";
				if (tipoFieldDate.getValue())
					tipoField = Tipo.DATE.getLabel();
				else if (tipoFieldText.getValue())
					tipoField = Tipo.TEXT.getLabel();
				String campoDB = campoBD.getText();

				if (!FieldVerifier.isValidName(nameFieldText, rotuloFieldText)) {
					errorLabel.setText("Por favor, insira pelo menos quatro caracteres.");
					return;
				}

				Metadado metadado = new Metadado(nameFieldText, rotuloFieldText, tipoField, campoDB);

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(nameFieldText);
				serverResponseLabel.setText("");
				
				service.saveMetadado(metadado, new AsyncCallback<Metadado>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					@Override
					public void onSuccess(Metadado result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result.getNome());
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
		}
		
		class HandlerGerarTela implements ClickHandler, KeyUpHandler {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					limpaTelaEChamaTelaDinamica();
				}
			}

			@Override
			public void onClick(ClickEvent event) {
				limpaTelaEChamaTelaDinamica();
			}
			
			/**
			 * Limpa a tela atual e chama a tela dinamica
			 */
			private void limpaTelaEChamaTelaDinamica(){
				RootPanel.get().clear();
				TelaAutomatica a = new TelaAutomatica();
				a.onModuleLoad();
			}
			
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		
		HandlerGerarTela gerarTelaHandler = new HandlerGerarTela();
		botaoGerarTela.addClickHandler(gerarTelaHandler);
	}
	 
	  @Override
	  public Widget asWidget() {
		  
	    grid = new MetadadoGrid();
	    populateGrid();
	 
	    FramedPanel cp = new FramedPanel();
	    cp.setCollapsible(true);
	    cp.setHeading("Metadados cadastrados");
	    cp.setPixelSize(500, 300);
	    cp.addStyleName("margin-10");
	    
	    VerticalLayoutContainer con = new VerticalLayoutContainer();
	    con.setBorders(true);
	    con.add(grid, new VerticalLayoutData(1, 1));
	    cp.setWidget(con);
	 
	    return cp;
	  }
	  
	  public void populateGrid(){
		  
		  service.getMetadados(0, 25, new AsyncCallback<List<Metadado>>() {
			
			@Override
			public void onSuccess(List<Metadado> result) {
				grid.getStore().addAll(result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	  }
}
