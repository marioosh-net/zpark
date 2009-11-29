package ui;

import java.util.List;

import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import dao2.*;

public class ContentComposer extends GenericForwardComposer {
	private Div div1;
	
	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		reload();
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
		} else {
			System.out.println("logged out 2");
			div1.appendChild(new Label("Please login"));
		}				
	}
	
	public List<Client> getAllClients() {
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
