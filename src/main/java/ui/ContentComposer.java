package ui;

import java.util.List;

import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import dao2.*;

public class ContentComposer extends GenericForwardComposer {
	private Div div1;
	//private Tabpanel tabClients;
	
	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		reload();
		//System.out.println(tabClients); // null
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
	
	// przegladanie danych
	public void listing$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("data.zul", div1, null);
		} else {
			div1.appendChild(new Label("Please login"));
		}		
	}
	
	// wprowadzamy do systemu nowego klienta
	public void newClient$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("client.zul", div1, null);
		} else {
			div1.appendChild(new Label("Please login"));
		}		
	}
	
	// wprowadzamy do systemu nowe auto
	public void newAuto$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("auto.zul", div1, null);
		} else {
			div1.appendChild(new Label("Please login"));
		}		
	}		

	// parkujemy nowe auto
	public void newTime$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("time.zul", div1, null);
		} else {
			div1.appendChild(new Label("Please login"));
		}		
	}	
}
