package cbn.test;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import cbn.methode.SerializationEngine;

public class StartTest {

	public static void main( String[] args ) throws Exception {
        
		
		/* Test write */
		
        String file = "./test.json";
        
        try ( PrintWriter writer = new PrintWriter( file ) ) {
            /* Test float int string */
        	//SerializationEngine.writeObject( 10, writer );
            //SerializationEngine.writeObject( 18.1415, writer );
            SerializationEngine.writeObject( "Test serialisation", writer );
        }
        
        /* Test read */
        
        
        try ( FileReader reader = new FileReader( new File( file ) ) ) {
            //double data = SerializationEngine.readObject( Double.class, reader );
            String data = SerializationEngine.readObject( String.class, reader );
            System.out.println( data );
        }
    
    }

}
