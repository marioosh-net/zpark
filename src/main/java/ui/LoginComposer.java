package ui;

import java.util.Collection;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.lang.Strings;
import org.zkoss.zul.Include;

public class LoginComposer extends GenericForwardComposer {
	// autowired
	private Textbox usertb;
	private Textbox pwdtb;
	private Div loginDiv;
	private Div userDiv;
	private Label userName;
	private Button logoutb;
	//private Window content;

	public void doAfterCompose(Component loginWin) throws Exception {
		super.doAfterCompose(loginWin);
		// div nie jest spaceownerem!!!! nigdy !!! dlate w sciezce sie go nie uwzglednia przechodzac glebiej
		// Path.getComponent leci po space-ownerach, na koncu podajemy obiekt, ktory juz nie musi byc space-ownerem
		//System.out.println(Path.getComponent("//pindex/indexWin/content/div2"));
		doInit();
	}	
	
	public void onClick$loginb() throws Exception {
		String user = usertb.getValue();
		String pwd = pwdtb.getValue();
		if(Strings.isBlank(user) || Strings.isEmpty(pwd)){
			Messagebox.show("*Need user name and password!", "Error", Messagebox.OK, Messagebox.EXCLAMATION);
			return;
		}
		
		if(!new dao.UserDAO().checkUser(new dao.User(user,pwd))) {
			Messagebox.show("*Wrong password!", "Error", Messagebox.OK, Messagebox.EXCLAMATION);			
			return;			
		}
		/*
		if(!"1234".equals(pwd)){
			Messagebox.show("*Wrong password!", "Error", Messagebox.OK, Messagebox.EXCLAMATION);			
			return;
		}
		*/
		session.setAttribute("user",user);
		loginDiv.setVisible(false);
		userDiv.setVisible(true);
		userName.setValue(user);

		Div div1 = (Div)Path.getComponent("//pindex/indexWin/content/div1");
		Events.sendEvent(new Event("onLoginLogout", div1)); // reload content div1
	}
	
	public void onClick$logoutb() throws Exception {
		session.removeAttribute("user");
		loginDiv.setVisible(true);
		userDiv.setVisible(false);
		userName.setValue("");
		
		Div div1 = (Div)Path.getComponent("//pindex/indexWin/content/div1");
		Events.sendEvent(new Event("onLoginLogout", div1)); // reload content div1		
	}
	
	public void doInit() throws Exception {
		// sprawdzam ustawienie zmiennej sesyjnej
		if(session.getAttribute("user") != null) {
			loginDiv.setVisible(false);
			userDiv.setVisible(true);		
		} else {
			loginDiv.setVisible(true);
			userDiv.setVisible(false);			
		}
	}
}
