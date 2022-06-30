package com.bridglabz.bookstoreapps.tokenutil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

 public final String TOKEN_SECRET = "secret";

 /**
  * This method is for encrypting id to generate token, which will be used for authentication.
  */
 public String createToken(int id) {

  try {
   Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

   String token = JWT.create().withClaim("user_id", id).sign(algorithm);
   return token;
  }
  catch (JWTCreationException exception) {
   exception.printStackTrace();
  }
  catch (IllegalArgumentException exception) {
   exception.printStackTrace();
  }
  return null;
 }

 /**
  * This method is for decrypting token to generate id, which will be used for authentication.
  */
 public int decodeToken(String token) {
  Integer userid;
  Verification verification = null;

  Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

  try {
   verification = JWT.require(algorithm);
  }
  catch (IllegalArgumentException exception) {
   exception.printStackTrace();
  }
  JWTVerifier jwtVerifier = verification.build();

  DecodedJWT decodedJWT = jwtVerifier.verify(token);
  Claim claim = decodedJWT.getClaim("user_id");
  userid = claim.asInt();
  return  userid;
 }

}
