import java.util.Scanner;

public class OneTimePad {
	static int randomBitPattern[] = new int[8];
 
    public static void main(String[] args) {
    	System.out.println("Enter message: ");
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        sc.close();
        String encryptedMessage = encryptMessage(message);
        System.out.println("'" + message + "' in encrypted message : " + encryptedMessage);
        System.out.println("'" + encryptedMessage + "' in decrypted message : " 
        		+ decryptMessage(encryptedMessage) + "\n");
    }
    
    /*
     * This method encrypts the message. It first gets the unencrypted message.
     * Then, it goes through every character adding it to an array.
     * For every character, we get the ascii value and convert it to binary.
     * Then that binary value is XOR´d with the pseudorandom key
     */
    private static String encryptMessage(String unencryptedMessage) {
        int m, n;
    	createOneTimePad();
        char asc[] = new char[unencryptedMessage.length()];
        asc = getCharFromMessage(unencryptedMessage, asc);
        
        EncryptDecrypt ed = new EncryptDecrypt();
        String cipherText = new String("");
        for (m = 0; m < asc.length; m++) {
            int asciiInt = (int) (asc[m]);
            int binary = ed.decimalToBinary(asciiInt);
            int bintemp[] = new int[7];
            for (n = 1; n <= binary; n++)
            {
                bintemp[n - 1] = ed.binaryArrayAtPosition(n);
            }
            
            //Perform XOR operation
            ed.xorop(bintemp, randomBitPattern, binary);
       
            //Go through the results of the XOR operation retrieving the cipherText
            int xor[] = new int[binary];
            for (n = 0; n < binary; n++)
            {
                xor[n] = ed.xorinArrayAt(n);
                cipherText = cipherText + xor[n];
            }
            cipherText += " ";
        }
        return (cipherText);
    }

    //This method goes through the input by the user and gets every character individually, adding it to a character array
	private static char[] getCharFromMessage(String unencryptedMessage, char[] asc) {
		int i;
		for (i = 0; i < unencryptedMessage.length(); i++)
        {
            asc[i] = (char) ((int) unencryptedMessage.charAt(i));
        }
		return asc;
	}

	//This method creates a pseudorandom key
	private static void createOneTimePad() {
		int i;
		for (i = 0; i < 7; i++)
        {
            randomBitPattern[i] = ((Math.round(Math.random()*2) % 2 == 0) ? 1 : 0); 
        }
	}
 
	/*
	 * This method is used to decrypt the ciphertext. It first gets the ciphertext and goes through the characters 
	 * and divides text by words. It then does the XOR operation of the key with the ciphertext and finally converts
	 * the result of the XOR operaion back to ascii code.
	 */
    private static String decryptMessage(String cipherText)
    {
        int m, n;
        char cipherChar[] = new char[(cipherText.length())];
        //Parse the ciphertext getting the value of each character and putting it into an array
        for (m = 0; m < cipherText.length(); m++)
        {
            cipherChar[m] = cipherText.charAt(m);
        }
        
        //Parse the array of values grouping the different binary characters by letters
        String string1 = String.valueOf(cipherChar);
        String letters[] = string1.split(" ");
        int data[] = new int[letters.length];
        for (m = 0; m < letters.length; m++)
        {
            data[m] = Integer.parseInt(letters[m]);
        }
        
        //Get the individual bits from the key
        char randomBitPattern1[] = new char[7];
        for (m = 0; m < 7; m++)
        {
            randomBitPattern1[m] = (char) (randomBitPattern[m]+'0');
        }
        
        EncryptDecrypt b1 = new EncryptDecrypt();
        String plain = new String("");
        // Perform the XOR Operation
        for (m = 0; m < letters.length; m++)
        {
            int xorlen = b1.xorop(letters[m], randomBitPattern1);
            int xor[] = new int[xorlen];
            for (n = 0; n < xorlen; n++)
            {
                xor[n] = b1.xorinArrayAt(n);
                plain += xor[n];
            }
            plain += " ";
        }
        
        //Go through plaintext in binary and convert it to characters
        String p[] = plain.split(" ");
        EncryptDecrypt ed = new EncryptDecrypt();
        int decryptedInt[] = new int[p.length];
        char plainTextChar[] = new char[p.length];
        for (m = 0; m < p.length; m++)
        {
            decryptedInt[m] = ed.binaryToDecimal(Integer.parseInt(p[m]));
            plainTextChar[m] = (char) decryptedInt[m];
        }
        return (new String(plainTextChar));
    }
}