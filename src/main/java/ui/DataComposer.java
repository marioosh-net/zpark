package ui;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Rows;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.Desktop;

import dao2.*;

/*
 * Komposer do tabelek w zakladkach
 */

public class DataComposer extends GenericForwardComposer {
	private Tabbox tabbox;
	private Tabpanel tabClients;
	private Tab tab1, tab2, tab3;
	private Grid grid_clients;
	private Grid grid_autos;
	private Grid grid_times;
	private Row client;
	private Row auto;
	private Row time;

	AnnotateDataBinder binder;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		System.out.println(this.getClass().toString());

		//grid_clients.setModel(new ListModelList(getAllClients()));
		//grid_autos.setModel(new ListModelList(getAllAutos()));
		//grid_times.setModel(new ListModelList(getAllTimes()));

		// TO JEST WAZNE - zaladuj dane z bindera
		//http://www.zkoss.org/forum/listComment/6263/			
		binder = new AnnotateDataBinder(comp);
		binder.loadAll();

		// Grid dla kielntow komplekosowo
		/*
		 * tak mozna tez ustawic model i renderer
		grid_clients.setRowRenderer(new ClientRowRenderer());
		grid_clients.setModel(new SimpleListModel(getAllClients()));
		*/
		/*Rows rs = grid_clients.getRows();
		for (Row row : (List<Row>) rs.getChildren()) {
			row.addEventListener("onClick", new EventListener() {
				public void onEvent(Event event) throws Exception {
					Row row = (Row) event.getTarget();
					Messagebox.show("ROW: " + event.getTarget());
					for (Object o : row.getChildren()) {
						// labels
					}
				}
			});
		}*/

	}

	public ClientRowRenderer getRenderer() {
		return new ClientRowRenderer();
	}

	public void test(Event evt) throws Exception {
		Messagebox.show("ssss:" + evt.getTarget());
	}

	public void onCreate$tabbox() throws Exception {
		System.out.println("onCreate$tabbox");
	}

	// on row clicks
	public void onClick$client(ForwardEvent event) throws Exception {
		Row rowClicked = (Row) event.getOrigin().getTarget();
		Client clientClicked = (Client) rowClicked.getValue();
		System.out.println(clientClicked);
		
		//desktop.setAttribute("clientObject", clientClicked);
		java.util.Map params = new java.util.HashMap();
		params.put("clientObject", clientClicked);
		Window win = (Window) Executions.createComponents("client.zul", null, params);
		win.setClosable(true);
		win.setId("Okienko");
		win.doModal();
	}

	public void onClick$auto(ForwardEvent event) throws Exception {
		Row rowClicked = (Row) event.getOrigin().getTarget();
		AutoClient autoClicked = (AutoClient) rowClicked.getValue();
		System.out.println(autoClicked);
	}

	public void onClick$time(ForwardEvent event) throws Exception {
		Row rowClicked = (Row) event.getOrigin().getTarget();
		AutoTime timeClicked = (AutoTime) rowClicked.getValue();
		System.out.println(timeClicked);
	}

	public void onClick$tab1() throws Exception {
		System.out.println("onClick$tab1");
	}

	// przy zmianie zakladki
	public void onSelect$tabbox() throws Exception {
		System.out.println("onSelect$tabbox_");
	}

	public void onPaging$grid_clients(Event evt) {
		System.out.println("Paging...");
	}

	// models
	public List<Client> getAllClients() {
		System.out.println("getAllClients();");
		ClientHome c = new ClientHome();
		return c.findAll();
	}

	public List<AutoClient> getAllAutos() {
		System.out.println("getAllAutos();");
		AutoHome c = new AutoHome();
		return c.findAll();
	}

	public List<AutoTime> getAllTimes() {
		System.out.println("getAllTimes();");
		TimeHome c = new TimeHome();
		return c.findAll();
	}

}

class ClientRowRenderer implements RowRenderer {
	public void render(Row row, Object data) {
		Client client = (Client) data;
		new Label(client.getName()).setParent(row);
		new Label(client.getSurname()).setParent(row);
		new Label(client.getPesel()).setParent(row);
		row.setHeight("30px");
	}
}
