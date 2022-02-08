package cbn.introspection;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.Introspector;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class BeanEventsTable extends JScrollPane{
	// Le composant JTable affichant les gestionnaires d'événements
    private JTable table;
    // Le bean sur lequel réaliser l'introspection
    private Object instance;
    
    public BeanEventsTable() {
        // On crée le JScrollPane par-dessus un JTable
        super( new JTable() );
        // On récupère le composant JTable
        this.table = (JTable) this.getViewport().getView();
    }
    
    public Object getInstance() {
        return instance;
    }
    
    // Si on change l'instance à introspecter alors on produit un nouveau modèle.
    public void setInstance( Object instance ) {
        this.instance = instance;
        // Le modèle (la source de données) utilisé par la table 
        // doit être compatible avec le type AbstractTableModel.
        this.table.setModel( new AbstractTableModel() {

            private Class<?> metadata;
            private List<String> eventListenerMethods = new ArrayList<>();
            
            // Le constructeur de la classe anonyme
            {
                try {
                    metadata = instance.getClass();
                    BeanInfo beanInfo = Introspector.getBeanInfo( metadata );
                    EventSetDescriptor [] eventSetDescriptors = beanInfo.getEventSetDescriptors();
                    for ( EventSetDescriptor eventSetDescriptor : eventSetDescriptors ) {
                        Method [] methods = eventSetDescriptor.getListenerMethods();
                        for( Method method : methods ) {
                            eventListenerMethods.add( method.getName() );
                        }
                    }
                    // On veut les méthodes présentées dans l'ordre alphabétique.
                    eventListenerMethods.sort( (l1, l2) -> l1.compareTo( l2 ) );
                } catch( Exception exception ) {
                    System.err.println( "Cannot access bean listeners" );
                }
            }
            
            @Override public int getRowCount() {
                return eventListenerMethods.size();
            }
            
            @Override public int getColumnCount() {
                return 2;       // property name / property value
            }

            @Override public Object getValueAt( int rowIndex, int columnIndex ) {
                switch( columnIndex ) {
                    case 0:
                        return eventListenerMethods.get( rowIndex );
                    case 1:
                        return "";
                    default:
                        throw new RuntimeException( "Bad column index" );
                    
                }
            }
            
            @Override
            public String getColumnName( int columnIndex ) {
                switch( columnIndex ) {
                    case 0:
                        return "Event";
                    case 1:
                        return "Method";
                    default:
                        throw new RuntimeException( "Bad column index" );
                    
                }
            }
            
        } );
    
    }
}
