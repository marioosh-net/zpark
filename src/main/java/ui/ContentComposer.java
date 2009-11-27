package ui;

import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class ContentComposer extends GenericForwardComposer {
	private Div div1;
	
	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
	}	
	
	public void onLoginLogout$div1() {
		if(session.getAttribute("user") != null) {
			System.out.println("logged 2");
			div1.appendChild(new Label("logged"));
		} else {
			System.out.println("logged out 2");
			div1.appendChild(new Label("logged out"));
		}		
	}
}
