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

public class TimeComposer extends GenericForwardComposer {


	Auto selected = null;
	Combobox combo;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		System.out.println(this.getClass().toString());
		//combo.setModel(new SimpleListModel(this.getAllClients()));
		//combo.setModel(new SimpleListModel(new ClientHome().findAll()));
		AnnotateDataBinder binder = new AnnotateDataBinder(comp);
		binder.loadAll();		
	}
	
	public Auto getSelected() {
		return selected;
	}
	public void setSelected(Auto selected) {
		this.selected = selected;
	}	
	
	public List getAutos() {
		System.out.println("getAutos");
		return new AutoHome().findAll2();
	}
	
	public void onClick$parkButton() throws Exception {
		System.out.println("onClick$addButton()");
		
		if(selected == null){
			Messagebox.show("Podaj wyszystkie dane");
		} else {
			Time time = new Time();
			time.setIdAuto(selected.getIdAuto());
			
			// czy jest zaparkowane juz dane auto ?
			if(!new TimeHome().isExist(selected)) {
				
				// parkuj
				if(new TimeHome().park(selected)) {
					Messagebox.show("Auto zaparkowano - czas parkowania rozpoczêty");
				} else {
					Messagebox.show("DB error!");
				}				
			} else {
				Messagebox.show("Auto ju¿ jest zaparkowane");				
			}
		}
	}	

}
