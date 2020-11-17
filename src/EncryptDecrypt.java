public class EncryptDecrypt {
    int bin[]   = new int[100];
    int xor[]   = new int[100];
    int len;
    int xorlen;
 
    // This methods converts the binary value of a number into the decimal
    public int binaryToDecimal(int binary)
    {
        int dec = 0;
        int no = binary;
        int i = 0;
        int n = 0;
        // Get the number of binary values inputted
        while (no > 0)
        {
            n++;
            no = no / 10;
        }
        // Get the ascii value of the inputted binary
        no = binary;
        for (i = 0; i < n; i++)
        {
            int temp = no % 10;
            dec = dec + temp * ((int) Math.pow(2, i));
            no = no / 10;
        }
        return dec;
    }
 
    //This method gets the int value of the ascii code and converts it to binary
    public int decimalToBinary(int decimal)
    {
        int j, i = -1, no, temp = 0;
        no = decimal;
        int t[] = new int[100];
        while (no > 0)
        {
            i++;
            temp = no % 2;
            t[i] = temp;
            no = no / 2;
        }
        len = (i + 1);
        j = -1;
        for (i = len; i >= 0; i--)
        {
            j++;
            bin[j] = t[i];
        }
        return len;
    }
 
    // This method gives the binary value at a specific position
    public int binaryArrayAtPosition(int pos)
    {
        return bin[pos];
    }
 
    //This method returns the return of the xor operation at a specific position
    public int xorinArrayAt(int pos)
    {
        return xor[pos];
    }
 
    // This method performs the XOR operation in the plaintext
    public void xorop(int binary[], int key[], int length)
    {
        int i;
        for (i = 0; i < length; i++)
        {
            xor[i] = (binary[i] == key[i]) ? 0 : 1;
        }
    }
 
    // perform the binary XOR operation in the ciphertext
    public int xorop(String letters, char[] key)
    {
        int i = -1;
        for (i = 0; i < letters.length(); i++)
        {
            xor[i] = (letters.charAt(i) == key[i]) ? 0 : 1;
        }
        xorlen = i;
        return xorlen;
    }
}