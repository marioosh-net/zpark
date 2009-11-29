package ui;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;

import dao2.Client;
import dao2.ClientHome;

public class Tabs1Composer extends GenericForwardComposer {
	private Tabbox tabbox;
	private Tabpanel tabClients;
	private Tab tab1, tab2, tab3;
	private Grid grid_clients;
	
	public void doAfterCompose(Component tabbox) throws Exception {
		super.doAfterCompose(tabbox);
		System.out.println(tabbox);
		System.out.println("Tabs1Composer...");
		System.out.println(tabClients);
		
		//grid_clients.setModel(getAllClients());
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
		//ClientHome c = new ClientHome();
		//return c.findAll();
		return null;
	}
	
	public List<Client> getAllTimes() {
		//ClientHome c = new ClientHome();
		//return c.findAll();
		return null;
	}	
}
