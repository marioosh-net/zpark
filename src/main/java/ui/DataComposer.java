package ui;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;

import dao2.AutoHome;
import dao2.Client;
import dao2.ClientHome;
import dao2.TimeHome;
/*
 * Komposer do tabelek w zakladkach
 */
public class DataComposer extends GenericForwardComposer {
	private Tabbox tabbox;
	private Tabpanel tabClients;
	private Tab tab1, tab2, tab3;
	private Grid grid_clients;
	private Grid grid_autos;
	private Grid grid_times;
	
	AnnotateDataBinder binder;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		System.out.println(this.getClass().toString());

		//grid_clients.setModel(new ListModelList(getAllClients()));
		//grid_autos.setModel(new ListModelList(getAllAutos()));
		//grid_times.setModel(new ListModelList(getAllTimes()));
		
		// TO JEST WAZNE - zaladuj dane z bindera
		//http://www.zkoss.org/forum/listComment/6263/			
		binder = new AnnotateDataBinder(comp);
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

	public void onPaging$grid_clients(Event evt) {
		System.out.println("Paging...");
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
}
