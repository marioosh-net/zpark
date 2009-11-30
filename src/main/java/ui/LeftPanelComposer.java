package ui;

import java.util.List;

import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import dao2.*;

public class LeftPanelComposer extends GenericForwardComposer {
	private Div div2;
	private Button listing;
	private Button newClient;
	private Button newAuto;
	private Button newTime;
	
	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		System.out.println("leftPanelComposer doAfterCompose");
		//reload();
	}	
	
	public void onLoginLogout$div2() {
		reload();
	}
	
	private void reload() {
		div2.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("leftpanel.zul", div2, null);
			System.out.println("create leftpanel.zul");
		} else {
			div2.getChildren().clear();
			//div2.appendChild(new Label("Please login"));
		}				
	}

	public void onClick$listing() {
		System.out.println("listing button");
		// wyslij eventa do ContentComposer'a		
		Div div1 = (Div)Path.getComponent("//pindex/indexWin/content/div1");
		Events.sendEvent(new Event("onListing", div1)); // reload content div1
	}
		
	public void onClick$newClient() {
		System.out.println("newClient button");
		// wyslij eventa do ContentComposer'a		
		Div div1 = (Div)Path.getComponent("//pindex/indexWin/content/div1");
		Events.sendEvent(new Event("onNewClient", div1)); // reload content div1
	}
	
	public void onClick$newAuto() {
		System.out.println("newAuto button ");
		// wyslij eventa do ContentComposer'a		
		Div div1 = (Div)Path.getComponent("//pindex/indexWin/content/div1");
		Events.sendEvent(new Event("onNewAuto", div1)); // reload content div1		
	}

	public void onClick$newTime() {
		System.out.println("newTime button");
		// wyslij eventa do ContentComposer'a
		Div div1 = (Div)Path.getComponent("//pindex/indexWin/content/div1");		
		Events.sendEvent(new Event("onNewTime", div1)); // reload content div1		
	}	
}
