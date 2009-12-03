package ui;

import java.util.List;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;

import dao2.*;

public class AutoComposer extends GenericForwardComposer {

	Client selected = null;
	Textbox autoType;
	Textbox autoNr;
	Combobox combo;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		System.out.println(this.getClass().toString());
		//combo.setModel(new SimpleListModel(this.getAllClients()));
		//combo.setModel(new SimpleListModel(new ClientHome().findAll()));
		AnnotateDataBinder binder = new AnnotateDataBinder(comp);
		binder.loadAll();		
	}
	
	public void onChanging$combo(Event event) {
		System.out.println("onChanging...");
		System.out.println(combo.getSelectedItem());
	}
	
	public void onSelect$combo(Event event) {
		System.out.println("onSelect "+selected);
	}
	
	public Client getSelected() {
		return selected;
	}
	public void setSelected(Client selected) {
		this.selected = selected;
	}	
	
	public List getClients() {
		System.out.println("getClients");
		return new ClientHome().findAll();
	}
	
	public void onClick$addButton() throws Exception {
		System.out.println("onClick$addButton()");
		
		Client client = selected;
		String aType = autoType.getValue();
		String aNr = autoNr.getValue();

		if(Strings.isBlank(aNr) || Strings.isBlank(aType) || selected == null){
			Messagebox.show("Podaj wyszystkie dane");
		} else {
			Auto auto = new Auto();
			auto.setNr(aNr);
			auto.setType(0);
			auto.setIdClient(client.getIdClient());
			
			if(!new AutoHome().isExist(auto)) {
				if(new AutoHome().insert(auto)) {
					Messagebox.show("Dane zapisane");
				} else {
					Messagebox.show("DB error!");
				}				
			} else {
				Messagebox.show("Auto ju¿ istnieje w bazie");				
			}
		}
	}	

}
