package cbn.test;

import java.io.PrintWriter;

import cbn.methode.SerializationEngine;

public class StartTest {

	public static void main( String[] args ) throws Exception {
        
        String file = "./file.json";
        
        try ( PrintWriter writer = new PrintWriter( file ) ) {
            /* Test float int string */
        	
        	SerializationEngine.writeObject( 10, writer );
            SerializationEngine.writeObject( 18.1415, writer );
            SerializationEngine.writeObject( "Test serialisation", writer );
        }
    
    }

}
