package ui;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Window;

import dao2.*;

public class ContentComposer extends GenericForwardComposer {
	private Window win;
	private Div div1;
	private Tabbox tabbox;
	private Tabpanel tabClients;
	private Tab tab1, tab2, tab3;
	private Grid grid_clients;
	private Grid grid_autos;
	private Grid grid_times;
	
	AnnotateDataBinder binder;
	
	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		this.win = (Window)win;
		System.out.println(this.getClass().toString());
		
		//grid_clients.setModel(new ListModelList(getAllClients()));
		//grid_autos.setModel(new ListModelList(getAllAutos()));
		//grid_times.setModel(new ListModelList(getAllTimes()));

		// przelacz od razu na przegladanie
		onListing$div1();
	}	
	
	public void onCreate$tabbox() throws Exception {
		System.out.println("onCreate$tabbox");
	}
	
	public void onClick$tab1() throws Exception {
		System.out.println("onClick$tab1");
	}
	
	// przy zmianie zakladki
	public void onSelect$tabbox() throws Exception {
		System.out.println("onSelect$tabbox_");
	}
	
	public List<Client> getAllClients() {
		System.out.println("getAllClients();");
		ClientHome c = new ClientHome();
		return c.findAll();
	}

	public List<Client> getAllAutos() {
		System.out.println("getAllAutos();");		
		AutoHome c = new AutoHome();
		return c.findAll();
	}
	
	public List<Client> getAllTimes() {
		System.out.println("getAllTimes();");
		TimeHome c = new TimeHome();
		return c.findAll();
	}
	
	public void updateTabContents() {
		AnnotateDataBinder binder = new AnnotateDataBinder(tabbox);
		//copy UI components values to data bean properties in one method call.
		binder.loadAll();		
	}
	
	/*****/
	
	// przegladanie danych
	public void onListing$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("data.zul", div1, null);
			// TO JEST WAZNE - zaladuj dane z bindera
			//http://www.zkoss.org/forum/listComment/6263/			
			binder = new AnnotateDataBinder(win);
			binder.loadAll();			
		} else {
			System.out.println("Please login...");
			div1.appendChild(new Label("Please login"));
		}		
	}
	
	// wprowadzamy do systemu nowego klienta
	public void onNewClient$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("client.zul", div1, null);
		} else {
			div1.appendChild(new Label("Please login"));
		}		
	}
	
	// wprowadzamy do systemu nowe auto
	public void onNewAuto$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("auto.zul", div1, null);
		} else {
			div1.appendChild(new Label("Please login"));
		}		
	}		

	// parkujemy nowe auto
	public void onNewTime$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("time.zul", div1, null);
		} else {
			div1.appendChild(new Label("Please login"));
		}		
	}
	
	public void onLoginLogout$div1() {
		reload();
	}
	
	private void reload() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			System.out.println("logged 2");
			//div1.appendChild(new Label("logged"));
			Executions.createComponents("data.zul", div1, null);
			System.out.println("create data.zul");
		} else {
			System.out.println("logged out 2");
			div1.appendChild(new Label("Please login"));
		}				
	}
}
