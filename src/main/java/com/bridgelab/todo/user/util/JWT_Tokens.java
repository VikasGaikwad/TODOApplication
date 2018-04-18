/**
 * 
 */
package com.bridgelab.todo.user.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Creates a json web token which is a digitally signed token that contains a
 * payload (e.g. userId to identify the user). The signing key is secret. That
 * ensures that the token is authentic and has not been modified. Using a jwt
 * eliminates the need to store authentication session information in a
 * database.
 * 
 * @author bridgeit
 * @author Vikas Gaikwad
 *
 */
public class JWT_Tokens {
	public static final String SIGNIN_KEY = "CnmKHNIUnhtIMZYugfidx";

	/**
	 * @param id
	 *            : access the parameter of type String.
	 * @return generated token : id contains the generated token.
	 */
	public static String createToken(int id) {
		/*
		 * SignatureAlgorithm is used to sign the token.return the corresponding {@code
		 * SignatureAlgorithm} enum instance based on a case
		 */
		@SuppressWarnings("unused")
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long currentTimeInMillis = System.currentTimeMillis();
		Date currentDate = new Date(currentTimeInMillis);
		Date expireDate = new Date(currentTimeInMillis + 24 * 60 * 60 * 1000);
		/* JwtBuilder is a interface which provides the abstract method , used below. */

		/*
		 * Jwts - Java Web Tokens is a Class, which defines several method , builder() -
		 * Returns a new {@link JwtBuilder} instance that can be configured and then
		 * used to create JWT compact serialized strings.
		 */

		JwtBuilder builder = Jwts.builder().setId(Integer.toString(id)).setIssuedAt(currentDate)
				.signWith(SignatureAlgorithm.HS256, SIGNIN_KEY).setExpiration(expireDate);
		/*
		 * compact() - WheAuthorizationn you are ready to give the token to your end user, you need
		 * to compact it. This will turn it into a Base64 URL encoded string, making it
		 * safe to pass around in browsers without any unexpected formatting applied to
		 * it.
		 */
		String generatedToken = builder.compact();
		return generatedToken;

	}

	/**
	 * @param token
	 *            : it takes input as a generated token.
	 * @return id : returns verified generated token.
	 */
	public static int verifyToken(String token) {
		System.out.println("***********inside verify token**********");
		int id = 0;
		/*
		 * Claim interface provides the getId() method which returns the JWTs jti (JWT
		 * ID) value or null if not present.
		 */
		System.out.println("token isss.."+token);
		Claims claims = Jwts.parser().setSigningKey(SIGNIN_KEY).parseClaimsJws(token).getBody();
		//System.out.println(" claim id - " + claims.getId());
		id = Integer.parseInt(claims.getId());
		System.out.println("***********outside verify token**********");
		return id;

	}

}
