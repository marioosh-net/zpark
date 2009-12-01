package ui;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import dao2.*;

public class ClientComposer extends GenericForwardComposer {
	Textbox clientName;
	Textbox clientSurname;
	Textbox clientPesel;

	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		System.out.println(this.getClass().toString());
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
