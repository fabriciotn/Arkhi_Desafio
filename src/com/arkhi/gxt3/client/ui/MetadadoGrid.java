package com.arkhi.gxt3.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.arkhi.gxt3.shared.model.Metadado;
import com.arkhi.gxt3.shared.model.MetadadoProperties;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class MetadadoGrid extends Grid<Metadado> implements IsWidget {

	private static final MetadadoProperties props = GWT.create(MetadadoProperties.class);

	private static ColumnConfig<Metadado, String> nameCol = new ColumnConfig<Metadado, String>(props.nome(), 100, "Name");
	private static ColumnConfig<Metadado, String> rotuloCol = new ColumnConfig<Metadado, String>(props.rotulo(), 100, "Rotulo");
	private static ColumnConfig<Metadado, String> tipoCol = new ColumnConfig<Metadado, String>(props.tipo(), 100, "Tipo");
	private static ColumnConfig<Metadado, String> campoBdCol = new ColumnConfig<Metadado, String>(props.campoBD(), 100, "Campo BD");

	public MetadadoGrid() {

		super(generateData(), createColumnModel());

		this.getView().setStripeRows(true);
		this.getView().setColumnLines(true);
		this.getView().setAutoExpandColumn(nameCol);
		this.setBorders(false);
		this.setColumnReordering(true);
	}

	private static ColumnModel<Metadado> createColumnModel(){

		List<ColumnConfig<Metadado, ?>> columnConfigList = new ArrayList<ColumnConfig<Metadado, ?>>();
		columnConfigList.add(nameCol);
		columnConfigList.add(rotuloCol);
		columnConfigList.add(tipoCol);
		columnConfigList.add(campoBdCol);

		return new ColumnModel<Metadado>(columnConfigList);
	}

	private static ListStore<Metadado> generateData(){

		ListStore<Metadado> store = new ListStore<Metadado>(props.key());

		return store;
	}

	@Override
	public Widget asWidget() {
		return this;
	}
}
