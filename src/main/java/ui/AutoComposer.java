package ui;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.SimpleListModel;

import dao2.*;

public class AutoComposer extends GenericForwardComposer {

	Object selected;
	Combobox combo;
	Object[] comboValues; // tablica tablic [comboVal, Client] 

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		System.out.println(this.getClass().toString());
		//combo.setModel(new SimpleListModel(this.getAllClients()));
		//combo.setModel(new SimpleListModel(new ClientHome().findAll()));
	}
	
	public void onChanging$combo(Event evt) {
		System.out.println("onChanging...");

		System.out.println(combo.getSelectedItem());
	}
	
	public List getClients() {
		return new ClientHome().findAll();
	}

	public Object[] getAllClients() {
		List<Client> l = new ClientHome().findAll();
		String[] a = new String[l.size()];
		this.comboValues = new Object[l.size()];
		int i = 0;
		for (Client c : l) {
			new Comboitem();
			a[i] = c.getName().trim() + " [" + c.getPesel().trim() + "]";
			this.comboValues[i] = new Object[]{a[i],c};
			i++;
		}
		return a;
	}

}
