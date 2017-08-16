package utilitarios;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
public class Utilidades {

	  public static String Encriptar(String texto) {
		  
	        String secretKey = "encriptadorfosvoc"; //llave para encriptar datos
	        String base64EncryptedString = "";
	 
	        try {
	 
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
	            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
	 
	            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	            Cipher cipher = Cipher.getInstance("DESede");
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	 
	            byte[] plainTextBytes = texto.getBytes("utf-8");
	            byte[] buf = cipher.doFinal(plainTextBytes);
	            byte[] base64Bytes = Base64.encodeBase64(buf);
	            base64EncryptedString = new String(base64Bytes);
	 
	        } catch (Exception ex) {
	        }
	        return base64EncryptedString;
	    }
	  public static String Encriptar(char[] textoarray) {
		  
	        String secretKey = "encriptadorfosvoc"; //llave para encriptar datos
	        String base64EncryptedString = "";
	 
	        try {
	 
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
	            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
	 
	            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	            Cipher cipher = Cipher.getInstance("DESede");
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            String texto = new String(textoarray);
	            byte[] plainTextBytes = texto.getBytes("utf-8");
	            byte[] buf = cipher.doFinal(plainTextBytes);
	            byte[] base64Bytes = Base64.encodeBase64(buf);
	            base64EncryptedString = new String(base64Bytes);
	 
	        } catch (Exception ex) {
	        }
	        return base64EncryptedString;
	    }
	 
	    public static String Desencriptar(String textoEncriptado) {
	 
	        String secretKey = "encriptadorfosvoc"; //llave para encriptar datos
	        String base64EncryptedString = "";
	 
	        try {
	            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
	            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
	            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	 
	            Cipher decipher = Cipher.getInstance("DESede");
	            decipher.init(Cipher.DECRYPT_MODE, key);
	 
	            byte[] plainText = decipher.doFinal(message);
	 
	            base64EncryptedString = new String(plainText, "UTF-8");
	 
	        } catch (Exception ex) {
	        }
	        return base64EncryptedString;
	    }
	    public static char[] tocharArray( String s ) {

	    	   if ( s == null ) {
	    	     return null;
	    	   }
	    	   int len = s.length();
	    	   char[] array = new char[len];
	    	   for (int i = 0; i < len ; i++) {
	    	      array[i] = s.charAt(i);
	    	   }
	    	   return array;
	    	}
	    public static String generarPasswordAleatorio(int cuantosCaracteres)
	    {
	    	 char[] caracteres;
	         caracteres = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	         String pass = "";
	             for (int i = 0; i < cuantosCaracteres; i++) {
	                 pass += caracteres[new Random().nextInt(62)];
	             }
	            // System.out.println( "La contraseña es: "+pass);	         
	         return pass;
	    }
}
