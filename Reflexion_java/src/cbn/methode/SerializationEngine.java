package cbn.methode;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SerializationEngine{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void writeObject( Object object, PrintWriter writer ) throws Exception {
	    Class<?> metadata = object.getClass();
	    if ( metadata == Byte.class ||
	         metadata == Short.class ||
	         metadata == Integer.class ||
	         metadata == Long.class ||
	         metadata == Float.class ||
	         metadata == Double.class ||
	         metadata == Boolean.class ) {
	        // --- Manage base type ---
	        writer.print( "" + object );
	    } else if ( metadata == String.class || metadata == Character.class ) {
	        // --- Manage String ---
	        writer.print( "\"" + object + "\"" );
	    } else if ( metadata.isArray() || object instanceof List ) {
	        // --- Manage Array and Collection ---
	        @SuppressWarnings("rawtypes") 
	        List collection = object instanceof List 
	                ? (List) object : ArrayUtils.toObjectList( object ) ;
	        int size = collection.size();
	        int i = 0;

	        writer.print( "[" );
	        for( Object value : collection ) {
	            writeObject( value, writer );
	            if ( i++ < size - 1 ) writer.print( "," );
	        }
	        writer.print( "]" );
	    } else {
	        // --- Other types ---
	        throw new Exception( "Not actually implemented" );
	    }
//		writer.print(System.getProperty("line.separator"));
	}
	
	

	private static Object readObject(Class<?> metadata, Scanner scanner) throws Exception {
		
		/*Note : findInLine() vs next(Pattern)
		 * Next => read until a separation char 
		 * InLine => read the char until a different type of character */
		
		//System.out.println("value scanner : " + scanner.findInLine("\".*?\""));
		System.out.println(metadata.toString());
		
		if (metadata == Byte.class || metadata == byte.class) {
			return Byte.parseByte(scanner.findInLine("[0-9]+"));
		} else if (metadata == Short.class || metadata == short.class) {
			return Short.parseShort(scanner.findInLine("[0-9]+"));
		} else if (metadata == Integer.class || metadata == int.class) {
			return Integer.parseInt(scanner.findInLine("[0-9]+"));
		} else if (metadata == Long.class || metadata == long.class) {
			return Long.parseLong(scanner.findInLine("[0-9]+"));
		} else if (metadata == Float.class || metadata == float.class) {
			return Float.parseFloat(scanner.findInLine("[0-9]+(\\.[0-9]+)?"));
		} else if (metadata == Double.class || metadata == double.class) {
			return Double.parseDouble(scanner.findInLine("[0-9]+(\\.[0-9]+)?"));
		} else if (metadata == Boolean.class || metadata == boolean.class) {
			return Boolean.parseBoolean(scanner.findInLine("true|false"));
		} else if (metadata == String.class || metadata == Character.class || metadata == char.class) {
			String valString = null;
			valString =scanner.findInLine("\".*?\"");
			System.out.println("valString : " + valString);
			return valString.substring(1, valString.length() - 1);
			
		} else {
			throw new Exception("Not actually implemented");
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T readObject(Class<T> metadata, Reader reader) throws Exception {
		try (Scanner scanner = new Scanner(reader)) {
			scanner.useLocale(Locale.US); 
			return (T) readObject(metadata, scanner);
		}
	}
}
