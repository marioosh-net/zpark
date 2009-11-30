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
	}		
}
