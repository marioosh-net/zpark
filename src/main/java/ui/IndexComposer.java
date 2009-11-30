package ui;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

public class IndexComposer extends GenericForwardComposer {
	//private Div icontent;
	private Div ilogin;
	private Div ileftpanel;
	
	public void doAfterCompose(Component indexWin) throws Exception {
		super.doAfterCompose(indexWin);
		//Executions.createComponents("content.zul",icontent,null);		
		Executions.createComponents("login.zul",ilogin,null);
		//Executions.createComponents("leftpanel.zul",ileftpanel,null);

		// po przeladowaniu ewentualnym strony trzeba dodac albo wywalic 
		// left panel w zaleznosci od stanu zalogowania
		// UWAGA mozna by to zrobic elegancko w composerze, ale trzeba by go miec dostepnego 
		// w index.zul, a zrobilem tak ze jest dopiero includowany razem z leftpanel.zul
		leftPanelInit();
	}		
	
	public void leftPanelInit() {
		if(session.getAttribute("user") != null) {
			Div div2 = (Div)Path.getComponent("//pindex/indexWin/div2");
			div2.getChildren().clear();
			Executions.createComponents("leftpanel.zul",div2,null);
		} else {
			Div div2 = (Div)Path.getComponent("//pindex/indexWin/div2");
			div2.getChildren().clear(); // wywal panel po wylogowaniu			
		}
	}
}
