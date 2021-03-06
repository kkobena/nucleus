package com.kobe.nucleus.config;

/**
 * Application constants.
 */
public final class Constants {

	// Regex for acceptable logins
	public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

	public static final String SYSTEM_ACCOUNT = "system";
	public static final String DEFAULT_LANGUAGE = "fr";
	public static final String ANONYMOUS_USER = "anonymoususer";
	public static final String ADMIN = "ADMIN";
	public static final long PRINCIPAL = 1;
	public static final long POINT_DE_VENTE = 2;
	public static final long SAFETY_STOCK = 3;

	private Constants() {
	}
}
