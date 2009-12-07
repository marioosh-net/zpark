package ui;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import dao2.*;

public class ClientComposer extends GenericForwardComposer {
	Textbox clientName;
	Textbox clientSurname;
	Textbox clientPesel;
	Client edited;
	Button addButton;
	Button deleteButton;
	Button saveButton;
	Button cancelButton;
	Window win;

	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		System.out.println(this.getClass().toString());
		this.win = (Window)win;
		System.out.println("ID:" + win.getId());		

		if(arg.get("clientObject") != null) {
			edited = (Client)arg.get("clientObject");
			//edited = (Client)desktop.getAttribute("clientObject");			
			clientName.setValue(edited.getName());
			clientSurname.setValue(edited.getSurname());
			clientPesel.setValue(edited.getPesel());
			deleteButton.setVisible(true);
			saveButton.setVisible(true);
			cancelButton.setVisible(true);
			addButton.setVisible(false);
		}
	}
	
	public void onClick$cancelButton() throws Exception {
		
	}
	
	public void onClick$addButton() throws Exception {
		System.out.println("onClick$addButton()");
		
		String cName = clientName.getValue();
		String cSName = clientSurname.getValue();
		String cPesel = clientPesel.getValue();

		if(Strings.isBlank(cName) || Strings.isBlank(cSName) || Strings.isBlank(cPesel)){
			Messagebox.show("Podaj wyszystkie dane");
		} else {
			Client client = new Client();
			client.setName(cName);
			client.setSurname(cSName);
			client.setPesel(cPesel);
			
			if(!new ClientHome().isExist(client)) {
				if(new ClientHome().insert(client)) {
					Messagebox.show("Dane zapisane");
				} else {
					Messagebox.show("DB error!");
				}				
			} else {
				Messagebox.show("Klient ju¿ istnieje w bazie");				
			}

		}
	}

}
