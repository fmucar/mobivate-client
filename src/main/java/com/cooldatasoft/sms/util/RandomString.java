package com.cooldatasoft.sms.util;

public class RandomString {

	private static final int NUM_OF_NUMERIC = 10;
	private static final int NUM_OF_LOWERCASE = 26;
	private static final int NUM_OF_UPPERCASE = 26;

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++)
			System.out.println(RandomString.getString("*********"));
	}

	/***************
	 * MAPPING*********************** # Numeric * Numeric | Uppercase | lowercase > uppercase <
	 * lowercase $ alphabetic (uppercase | lowercase) ] Numeric | lowercase [ Numeric | uppercase
	 *********************************************/

	public static String getString(String expression) {
		String result = "";

		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '#') {
				result += getRandomString(true, false, false, 1);
			} else if (expression.charAt(i) == '*') {
				result += getRandomString(true, true, true, 1);
			} else if (expression.charAt(i) == '>') {
				result += getRandomString(false, false, true, 1);
			} else if (expression.charAt(i) == '<') {
				result += getRandomString(false, true, false, 1);
			} else if (expression.charAt(i) == '$') {
				result += getRandomString(false, true, true, 1);
			} else if (expression.charAt(i) == ']') {
				result += getRandomString(true, true, false, 1);
			} else if (expression.charAt(i) == '[') {
				result += getRandomString(true, false, true, 1);
			}
		}

		return result;
	}

	public static String[] getRandomString(boolean numeric, boolean lower, boolean upper, int stringLength, int numOfStrings) throws NullPointerException {
		String[] result = new String[numOfStrings];

		for (int i = 0; i < numOfStrings; i++) {
			result[i] = getRandomString(numeric, lower, upper, stringLength);
		}
		return result;
	}

	public static String getRandomString(boolean numeric, boolean lower, boolean upper, int stringLength) throws NullPointerException {
		if (!numeric && !lower && !upper) {
			throw new NullPointerException("Zero Length Random String cannot be generated!");
		}
		String result = "";
		for (int i = 0; i < stringLength; i++) {
			result += getRandomChar(numeric, lower, upper);
		}

		return result;
	}

	private static char getRandomChar(boolean numeric, boolean lower, boolean upper) {
		int totalSetSize = getTotalSet(numeric, lower, upper);
		char[] charArray = initCharArray(numeric, lower, upper, totalSetSize);

		return charArray[(int) (Math.random() * totalSetSize)];
	}

	private static char[] initCharArray(boolean numeric, boolean lower, boolean upper, int totalSetSize) {
		char[] result = new char[totalSetSize];
		int lastIndex = 0;
		int i = 0;
		if (numeric) {
			i = 0;
			for (i = 0; i < 10; i++) {
				result[i] = (char) (48 + i - lastIndex);
			}
			lastIndex = i;
		}
		if (lower) {
			i = lastIndex;
			for (; i < 26 + lastIndex; i++) {
				result[i] = (char) (97 + i - lastIndex);
			}
			lastIndex += 26;
		}
		if (upper) {
			i = lastIndex;
			for (; i < 26 + lastIndex; i++) {
				result[i] = (char) (65 + i - lastIndex);
			}
			lastIndex += 26;
		}
		return result;
	}

	private static int getTotalSet(boolean numeric, boolean lower, boolean upper) {
		int result = 0;

		if (numeric)
			result += NUM_OF_NUMERIC;
		if (lower)
			result += NUM_OF_LOWERCASE;
		if (upper)
			result += NUM_OF_UPPERCASE;
		return result;
	}
}
