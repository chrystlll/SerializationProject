package cbn.test;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Locale;
import java.util.Scanner;

import cbn.methode.SerializationEngine;

public class StartTest2 {



	private static Object readObject( Class<?> metadata, Scanner scanner ) throws Exception {
	    if ( metadata == Byte.class || metadata == byte.class ) {
	        return Byte.parseByte( scanner.findInLine( "[0-9]+" ) );
	    } else if ( metadata == Short.class || metadata == short.class  ) {
	        return Short.parseShort( scanner.findInLine( "[0-9]+" ) );
	    } else if ( metadata == Integer.class || metadata == int.class ) {
	        return Integer.parseInt( scanner.findInLine( "[0-9]+" ) );
	    } else if ( metadata == Long.class || metadata == long.class  ) {
	        return Long.parseLong( scanner.findInLine( "[0-9]+" ) );
	    } else if ( metadata == Float.class || metadata == float.class  ) {
	        return Float.parseFloat( scanner.findInLine( "[0-9]+(\\.[0-9]+)?" ) );
	    } else if ( metadata == Double.class || metadata == double.class ) {
	        return Double.parseDouble( scanner.findInLine( "[0-9]+(\\.[0-9]+)?" ) );
	    } else if ( metadata == Boolean.class || metadata == boolean.class  ) {
	        return Boolean.parseBoolean( scanner.findInLine( "true|false" ) );
	    } else if ( metadata == String.class || metadata == Character.class || metadata == char.class ) {
	        String value = scanner.findInLine( "\".*?\"" );
	        return value.substring( 1, value.length()-1 );
	    } else {
	        throw new Exception( "Not actually implemented" );
	    }
	}

	// J'introduis ici la généricité pour plus de confort.
	
	@SuppressWarnings( "unchecked" )
	public static <T> T readObject( Class<T> metadata, Reader reader ) throws Exception {
	    try ( Scanner scanner = new Scanner( reader ) ) {
	        scanner.useLocale(Locale.US);    // double stocké avec le caractère . (et non la virgule)
	        return (T) readObject( metadata, scanner );    
	    }
	}
	
	public static void main( String[] args ) throws Exception {
	    
	    String file = "./test.json";
	    
	    try ( FileReader reader = new FileReader( new File( file ) ) ) {
	        //double data = SerializationEngine.readObject( Double.class, reader );
	        String data = StartTest2.readObject( String.class, reader );
	        System.out.println( data );
	    }
	            
	}

}
