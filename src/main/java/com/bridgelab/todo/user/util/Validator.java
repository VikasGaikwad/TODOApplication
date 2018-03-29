/**
 * 
 */
package com.bridgelab.todo.user.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bridgeit
 *
 */
public class Validator {

	private static Pattern pattern;
	private static Matcher matcher;

	private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";


	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


	/*(			# Start of group
  (?=.*\d)		#   must contains one digit from 0-9
  (?=.*[a-z])		#   must contains one lowercase characters
  (?=.*[A-Z])		#   must contains one uppercase characters
  (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
              .		#     match anything with previous condition checking
                {6,20}	#        length at least 6 characters and maximum of 20	
)			# End of group
	 */


	private static final String PASSWORD_PATTERN = 
			"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";


	public static boolean validate(final String username) {
		pattern = Pattern.compile(USERNAME_PATTERN);
		matcher = pattern.matcher(username);
		return matcher.matches();

	}
	public static boolean validateEmail(final String hex) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
	public static boolean validatePassword(final String password){
		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		return matcher.matches();

	}

}
