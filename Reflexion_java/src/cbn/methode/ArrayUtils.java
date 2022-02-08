package cbn.methode;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {

	/* Convert array on List<Object> */
	
	public static List<Object> toObjectList( Object array ) throws Exception {
        List<Object> result = new ArrayList<>();

        if ( array instanceof byte[] ) {
            for ( byte value : (byte[]) array ) result.add( value );
        } else if ( array instanceof short[] ) {
            for ( short value : (short[]) array ) result.add( value );
        } else if ( array instanceof int[] ) {
            for ( int value : (int[]) array ) result.add( value );
        } else if ( array instanceof long[] ) {
            for ( long value : (long[]) array ) result.add( value );
        } else if ( array instanceof float[] ) {
            for ( float value : (float[]) array ) result.add( value );
        } else if ( array instanceof double[] ) {
            for ( double value : (double[]) array ) result.add( value );
        } else if ( array instanceof boolean[] ) {
            for ( boolean value : (boolean[]) array ) result.add( value );
        } else if ( array instanceof char[] ) {
            for ( char value : (char[]) array ) result.add( value );
        } else if ( array.getClass().isArray() ) {
            for ( Object value : (Object[]) array ) result.add( value );
        } else {
            throw new Exception( "Not supported" );
        }
        
        return result;
    }
}
