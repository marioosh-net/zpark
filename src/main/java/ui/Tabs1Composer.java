package ui;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;

import dao2.*;


public class Tabs1Composer extends GenericForwardComposer {
	private Tabbox tabbox;
	private Tabpanel tabClients;
	private Tab tab1, tab2, tab3;
	private Grid grid_clients;
	private Grid grid_autos;
	private Grid grid_times;
	
	public void doAfterCompose(Component tabbox) throws Exception {
		super.doAfterCompose(tabbox);
		System.out.println(tabbox);
		System.out.println("Tabs1Composer...");
		System.out.println(tabClients);
		
		//grid_clients.setModel(new ListModelList(getAllClients()));
		//grid_autos.setModel(new ListModelList(getAllAutos()));
		//grid_times.setModel(new ListModelList(getAllTimes()));

		// TO JEST WAZNE
		//http://www.zkoss.org/forum/listComment/6263/
		AnnotateDataBinder binder = new AnnotateDataBinder(tabbox);
		binder.loadAll();		
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
}
