package cbn.test;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import cbn.introspection.BeanViewer;

public class StartFrame extends JFrame{

	
	  private BeanViewer beanViewer = new BeanViewer();
	    private JLabel rightPart = new JLabel( "Imagine a graphical editor here!" );
	    
	    public StartFrame() {
	        super( "Bean viewer" );
	        
	        JPanel contentPane = (JPanel) this.getContentPane();
	        contentPane.add( new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, beanViewer, rightPart ) );

	        beanViewer.setPreferredSize( new Dimension( 340, 0 ) );
	        beanViewer.setInstance( new JButton( "Click me!" ) );
	        rightPart.setHorizontalAlignment( JLabel.CENTER );
	        
	        this.setSize( 800, 400 );
	        this.setLocationRelativeTo( null );
	        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	        this.setVisible( true );
	    }

	    public static void main( String[] args ) throws Exception {
	        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
	        new StartFrame();
	    }
}
