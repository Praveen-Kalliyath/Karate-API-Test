/**
	 * @author Praveen Kalliyath
	 */
package eon.qa.testutils;

import static com.jayway.jsonpath.JsonPath.using;
import java.io.File;
import java.io.FileInputStream;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class CommonUtil {

	private static DateFormat dateFormat;
	private static Date date;
	private static String[] splitedString;

	/**
	 * Method to use default wait
	 * 
	 * @param seconds
	 */
	public static void sleep(int seconds) {
		try {
			System.out.println("Waiting for " + seconds + " seconds");
			Thread.sleep(seconds * 1000);
			System.out.println("Waited for " + seconds + " seconds");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to retrieve random alphabets
	 * 
	 * @param len
	 * 
	 * @return random alphabets
	 */
	public static String getRandomAlpha(int len) {
		char ch[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		char c[] = new char[len];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		System.out.println("Random Alphabet: " + new String(c));
		return new String(c);
	}

	/**
	 * Method to retrieve random charaacters
	 * 
	 * @param len
	 * 
	 * @return random alphabets
	 */
	public static String getRandomChars(int len) {
		char ch[] = "~`!@#$%^&*() {}[]:\'\",./?><;".toCharArray();
		char c[] = new char[len];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		System.out.println("Random Alphabet: " + new String(c));
		return new String(c);
	}

	/**
	 * Method to retrieve random mix of alphabets & numbers
	 * 
	 * @param len
	 * 
	 * @return random alphaNumeric
	 */
	public static String getRandomAlphaNumeric(int len) {
		char ch[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		char c[] = new char[len];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		System.out.println("Random AlphaNumeric: " + new String(c));
		return new String(c);
	}

	/**
	 * Method to retrieve random mix of alphabets, numbers & characters
	 * 
	 * @param len
	 * 
	 * @return random alphaNumeric
	 */
	public static String getRandomAlphaNumericChars(int len) {
		char ch[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~`!@#$%^&*() {}[]:\\'\\\",./?><;"
				.toCharArray();
		char c[] = new char[len];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		System.out.println("Random AlphaNumeric: " + new String(c));
		return new String(c);
	}
	
	/**
	 * Method to retrieve random mix of numbers & characters
	 * 
	 * @param len
	 * 
	 * @return random alphaNumeric
	 */
	public static String getRandomNumericChars(int len) {
		char ch[] = "0123456789~`!@#$%^&*() {}[]:\\'\\\",./?><;"
				.toCharArray();
		char c[] = new char[len];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		System.out.println("Random AlphaNumeric: " + new String(c));
		return new String(c);
	}
	
	/**
	 * Method to retrieve random mix of alphabets & characters
	 * 
	 * @param len
	 * 
	 * @return random alphaNumeric
	 */
	public static String getRandomAlphaChars(int len) {
		char ch[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~`!@#$%^&*() {}[]:\\'\\\",./?><;"
				.toCharArray();
		char c[] = new char[len];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		System.out.println("Random AlphaNumeric: " + new String(c));
		return new String(c);
	}

	/**
	 * Method to retrieve random number
	 * 
	 * @param len
	 * 
	 * @return random number
	 */
	public static String getRandomNumber(int len) {
		char ch[];
		if (len == 1 || len == 2)
			ch = "123456789".toCharArray();
		else
			ch = "0123456789".toCharArray();

		char c[] = new char[len];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < len; i++) {
			c[i] = ch[random.nextInt(ch.length)];
		}
		System.out.println("Random Number: " + new String(c));
		return new String(c);
	}

	/**
	 * Method to retrieve random number
	 * 
	 * @param min
	 * 
	 * @param max
	 * 
	 * @return random number within range of minimum value and maximum value
	 */
	public static String getRandomNumberInRange(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("Minimum value provided is greater that maximum");
		}
		Random random = new Random();

		return String.valueOf(random.nextInt((max - min) + 1) + min);
	}

	/**
	 * Method to validate regular expression
	 * 
	 * @param value
	 * 
	 * @param expression
	 * 
	 * @return boolean
	 */
	public static boolean regexValidator(String value, String expression) {
		Pattern pattern = Pattern.compile(expression);
		return pattern.matcher(value).matches();
	}

	/**
	 * Method to retrieve todays date
	 * 
	 * @return date
	 */
	public static String returnToday() {
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		date = new Date();
		System.out.println("Today's Date: " + dateFormat.format(date));
		return dateFormat.format(date);
	}

	/**
	 * Method to retrieve date in desired pattern
	 * 
	 * @param pattern
	 * 
	 * @return date
	 */
	public static String returnToday(String pattern) {
		dateFormat = new SimpleDateFormat(pattern);
		date = new Date();
		System.out.println("Today's Date: " + dateFormat.format(date));
		return dateFormat.format(date);
	}

	/**
	 * Method to retrieve date in specific pattern
	 * 
	 * @param currentDate
	 * 
	 * @param currentPattern
	 * 
	 * @param requestedPattern
	 * 
	 * @return date
	 */
	public static String convertDateToFormat(String currentDate, String currentPattern, String requestPattern) {
		Date requestedDate = null;
		try {
			requestedDate = new SimpleDateFormat(currentPattern).parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Requested Date in format: [" + requestPattern + "] :- "
				+ new SimpleDateFormat(requestPattern).format(requestedDate));
		return new SimpleDateFormat(requestPattern).format(requestedDate);
	}

	/**
	 * Convert HashMap to Json
	 * 
	 * @param hashMap
	 * 
	 * @return jsonString
	 */
	public static String convertHashMapToJson(HashMap<String, String> hashMap) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			System.out.println("Map : " + hashMap);
			json = mapper.writeValueAsString(hashMap);
			System.out.println("Converted Map to Json: " + json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * Convert Json to HashMap
	 * 
	 * @param jsonString
	 * 
	 * @return hashMap
	 */
	public static HashMap<String, Object> convertJsonToHashMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> jsonMap = new HashMap<>();
		try {
			System.out.println("Json : " + json);
			jsonMap = mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
			});
			System.out.println("Converted Map : " + jsonMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMap;
	}

	/**
	 * Get Random Index From List
	 * 
	 * @return index
	 */
	public static int getRandomIndex(int size) {
		Random random = new Random();
		int index = random.nextInt(size);
		System.out.println("Returning Index: " + index);
		return index;
	}

	/**
	 * Split String
	 * 
	 * @param text
	 * 
	 * @para pattern
	 * 
	 * @return String[]
	 * 
	 */
	public static String[] splitString(String text, String pattern) {
		splitedString = text.split(pattern);
		// for (int i = 0; i < splitedString.length; i++) {
		// System.out.println(i + " :: " + splitedString[i]);
		// }
		return splitedString;

	}

	/**
	 * MathRound Function
	 * 
	 * @param value
	 * 
	 * @return roundedNumber
	 */
	public static String roundNumber(String value) {
		long roundedNumber = Math.round(Double.valueOf(value));
		System.out.println("Rounded Number: " + roundedNumber);
		return String.valueOf(roundedNumber);
	}

	/**
	 * Method to return all digit from String
	 * 
	 * @param text
	 * 
	 * @return numbers
	 */
	public static String getDigitsFromString(String text) {
		char[] ch = text.toCharArray();
		String number = "";
		for (char c : ch) {
			if (Character.isDigit(c) | Character.toString(c).equals(".")) {
				number += c;
			}
		}
		return number;
	}

	/**
	 * Method to return all letters from String
	 * 
	 * @param text
	 * 
	 * @return numbers
	 */
	public static String getAlphabetsFromString(String text) {
		char[] ch = text.toCharArray();
		String value = "";
		for (char c : ch) {
			if (Character.isLetter(c)) {
				value += c;
			}
		}
		return value;
	}

	/**
	 * Method to return all letters & numbers removing special chars from String
	 * 
	 * @param text
	 * 
	 * @return alphanumeric
	 */
	public static String getAlphaNumericFromString(String text) {
		char[] ch = text.toCharArray();
		String value = "";
		for (char c : ch) {
			if (Character.isLetter(c) | Character.isDigit(c)) {
				value += c;
			}
		}
		return value;
	}

	/**
	 * GET JSON FROM FILE
	 * 
	 * @param jsonFilePath
	 * @return
	 */
	public static DocumentContext getJsonFromFile(String jsonFilePath) {
		FileInputStream json = null;
		try {
			json = new FileInputStream(new File(jsonFilePath + ".json"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
				.mappingProvider(new JacksonMappingProvider()).build();
		return using(configuration).parse(json);
	}

	/**
	 * GET JSON FROM PAYLOAD
	 * 
	 * @param payload
	 * @return
	 */
	public static DocumentContext getJsonFromPayLoad(String payload) {
		Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
				.mappingProvider(new JacksonMappingProvider()).build();
		return using(configuration).parse(payload);
	}

	/**
	 * Java main to test utility method
	 */
	public static void main(String args[]) {
		System.out.println("STRINGS ARE: " + getRandomChars(5));
	}

}
