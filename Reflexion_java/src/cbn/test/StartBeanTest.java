package cbn.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import javax.swing.JButton;

public class StartBeanTest {

	public static void main(String[] args) throws IntrospectionException {
		// TODO Auto-generated method stub
		BeanInfo beanInfo = Introspector.getBeanInfo( JButton.class );
        System.out.println( beanInfo );
        PropertyDescriptor [] properties = beanInfo.getPropertyDescriptors();
        for ( PropertyDescriptor propertyDescriptor : properties ) {
            System.out.printf( "%-50s %-30s %c/%c\n",
                propertyDescriptor.getPropertyType(),
                propertyDescriptor.getName(),
                propertyDescriptor.getReadMethod() != null ? 'R' : '-',
                propertyDescriptor.getWriteMethod() != null ? 'W' : '-'        
            );
        }
	}

}
