package cbn.methode;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.Locale;
import java.util.Scanner;

public class SerializationEngine {

	public static void writeObject(Object object, PrintWriter writer) throws Exception {

		/* Generic classes : primitive and String */
		

		Class<?> metadata = object.getClass();
		if (metadata == Byte.class || metadata == Short.class || metadata == Integer.class || metadata == Long.class
				|| metadata == Float.class || metadata == Double.class || metadata == Boolean.class) {
			writer.print("" + object);
		} else if (metadata == String.class || metadata == Character.class) {
			writer.print("\"" + object + "\"");
		} else {
			throw new Exception("Not actually implemented");
		}
		writer.print(System.getProperty("line.separator"));
	}

	private static Object readObject(Class<?> metadata, Scanner scanner) throws Exception {
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
			String value = scanner.findInLine("\".*?\"");
			return value.substring(1, value.length() - 1);
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
