package cbn.introspection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class BeanPropertiesTable extends JScrollPane{
	  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable table;
    private Object instance;
    
    public BeanPropertiesTable() {
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
            private PropertyDescriptor [] propertyDescriptors;
            
            // Le constructeur de la classe anonyme
            {
                try {
                    metadata = instance.getClass();
                    BeanInfo beanInfo = Introspector.getBeanInfo( metadata );
                    propertyDescriptors = beanInfo.getPropertyDescriptors();
                } catch( Exception exception ) {
                    System.err.println( "Cannot access bean properties" );
                }
            }
            
            @Override public int getRowCount() {
                return propertyDescriptors.length;
            }
            
            @Override public int getColumnCount() {
                return 2;       // property name / property value
            }

            @Override public Object getValueAt( int rowIndex, int columnIndex ) {
                switch( columnIndex ) {
                    case 0:
                        return propertyDescriptors[rowIndex].getName();
                    case 1:
                        try {
                            return "" + propertyDescriptors[rowIndex].getReadMethod().invoke( instance );
                        } catch ( Exception exception ) {
                            return "";
                        }
                    default:
                        throw new RuntimeException( "Bad column index" );
                    
                }
            }
            
            @Override
            public String getColumnName( int columnIndex ) {
                switch( columnIndex ) {
                    case 0:
                        return "Property name";
                    case 1:
                        return "Value";
                    default:
                        throw new RuntimeException( "Bad column index" );
                    
                }
            }
            
        } );
    }
}
