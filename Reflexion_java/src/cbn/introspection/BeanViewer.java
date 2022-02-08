package cbn.introspection;

import javax.swing.JTabbedPane;

public class BeanViewer extends JTabbedPane{
	
	 private BeanPropertiesTable propertiesTable = new BeanPropertiesTable();
	    private BeanEventsTable eventsTable = new BeanEventsTable();
	    private Object instance;
	    
	    public BeanViewer() {
	        // On place les onglets en bas
	        this.setTabPlacement( JTabbedPane.BOTTOM );
	        
	        // On ajoute les deux JTable
	        this.add( "Properties", propertiesTable );
	        this.add( "Event listeners", eventsTable );
	    }
	    
	    public Object getInstance() {
	        return instance;
	    }
	    
	    // En cas de changement d'instance à introspecter, on la transfert aux sous-tables.
	    public void setInstance( Object instance ) {
	        this.instance = instance;
	        this.propertiesTable.setInstance( instance );
	        this.eventsTable.setInstance( instance );
	    }

}
