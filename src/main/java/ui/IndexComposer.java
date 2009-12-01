package ui;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

public class IndexComposer extends GenericForwardComposer {
	//private Div icontent;
	private Div ilogin;
	private Div ileftpanel;
	private Div icontent;
	
	public void doAfterCompose(Component indexWin) throws Exception {
		super.doAfterCompose(indexWin);
		System.out.println(this.getClass().toString());
		//Executions.createComponents("content.zul",icontent,null);		
		Executions.createComponents("login.zul",ilogin,null);
		//Executions.createComponents("leftpanel.zul",ileftpanel,null);

		// po przeladowaniu ewentualnym strony trzeba dodac albo wywalic 
		// left panel w zaleznosci od stanu zalogowania
		// UWAGA mozna by to zrobic elegancko w composerze, ale trzeba by go miec dostepnego 
		// w index.zul, a zrobilem tak ze jest dopiero includowany razem z leftpanel.zul
		leftPanelInit();
		contentInit();
	}		
	
	public void leftPanelInit() {
		if(session.getAttribute("user") != null) {
			ileftpanel.getChildren().clear();
			Executions.createComponents("leftpanel.zul",ileftpanel,null);
		} else {
			if(!ileftpanel.getChildren().isEmpty()) {
				ileftpanel.getChildren().clear(); // wywal panel po wylogowaniu
			}
		}
	}
	
	public void contentInit() {
		if(session.getAttribute("user") != null) {
			icontent.getChildren().clear();
			Executions.createComponents("content.zul",icontent,null);
			
			// a potem zaladuj do div1 data.zul
			//Div div1 = (Div)Path.getComponent("//pindex/indexWin/content/div1");
			//Executions.createComponents("data.zul", div1, null);
			
			// sendEvent moge zrobic jedynie w obsludze zdarzenia
			//Div div1 = (Div)Path.getComponent("//pindex/indexWin/content/div1");
			//Events.sendEvent(new Event("onListing", div1)); // reload content div1			
		} else {
			if(!icontent.getChildren().isEmpty()) {
				icontent.getChildren().clear(); // wywal panel po wylogowaniu
				icontent.appendChild(new Label("Proszê siê zalogowaæ"));
			}
		}
	}
}
