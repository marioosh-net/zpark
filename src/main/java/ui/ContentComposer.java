package ui;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

public class ContentComposer extends GenericForwardComposer {
	private Div div1;
	
	//AnnotateDataBinder binder;
	
	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		System.out.println(this.getClass().toString());
		
		// przelacz od razu na przegladanie
		onListing$div1();
	}	
	
	// przegladanie danych
	public void onListing$div1() {
		reload();
	}
	
	// wprowadzamy do systemu nowego klienta
	public void onNewClient$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("client.zul", div1, null);
		} else {
			div1.appendChild(new Label("Prosz� si� zalogowa�"));
		}		
	}
	
	// wprowadzamy do systemu nowe auto
	public void onNewAuto$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("auto.zul", div1, null);
		} else {
			div1.appendChild(new Label("Prosz� si� zalogowa�"));
		}		
	}		

	// parkujemy nowe auto
	public void onNewTime$div1() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("time.zul", div1, null);
		} else {
			div1.appendChild(new Label("Prosz� si� zalogowa�"));
		}		
	}
	
	public void onLoginLogout$div1() {
		reload();
	}
	
	private void reload() {
		div1.getChildren().clear();
		if(session.getAttribute("user") != null) {
			Executions.createComponents("data.zul", div1, null);
		} else {
			div1.appendChild(new Label("Prosz� si� zalogowa�"));
		}				
	}
}
