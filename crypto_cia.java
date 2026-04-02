import java.util.*;

class Main {
    
    static int tonum(char c) {
        if (c >= 'a' && c <= 'z')
            return c - 'a';
        if (c >= 'A' && c <= 'Z')
            return c - 'A' + 26;
        if (c >= '0' && c <= '9')
            return c - '0' + 52;
        if (c == '.') return 62;
        if (c == '?') return 63;
        if (c == '!') return 64;
        if (c == ',') return 65;
        return -1; // invalid
    }

    static char tochar(int num) {
        if (num >= 0 && num <= 25)
            return (char) ('a' + num);
        if (num >= 26 && num <= 51)
            return (char) ('A' + (num - 26));
        if (num >= 52 && num <= 61)
            return (char) ('0' + (num - 52));
        if (num == 62) return '.';
        if (num == 63) return '?';
        if (num == 64) return '!';
        if (num == 65) return ',';
        return '#'; // fallback
    }

    static char cipher(char c1, char c2) {
        int n1 = tonum(c1);
        int n2 = tonum(c2);
        int sum = (n1 + n2) % 66;
        return tochar(sum);
    }
    static char decipher(char ciph, char keyc) {
    int n1 = tonum(ciph);
    int n2 = tonum(keyc);

    int diff = (n1 - n2 + 66) % 66;

    return tochar(diff);
}
static int xorHashCipher(String ciphered) {
    int hash = 0;

    for (int i = 0; i < ciphered.length(); i++) {
        int val = tonum(ciphered.charAt(i)); // same mapping

        hash ^= val;                          // XOR
        hash = (hash << 5) | (hash >>> 27);   // rotate for mixing
    }

    return Math.abs(hash);
}
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plaintext ");
        String plaintext = sc.nextLine();
        String key = "Jyoshitha";
        System.out.println(plaintext);
        
        // FIX 1: Define text variable (was missing)
        String text = key + plaintext;
        String ciphered = "";
        
        // FIX 2: Fix loop condition - use plaintext.length() not text.length()
        for(int i = 0; i < plaintext.length(); i++) {
            char c;
            
            // FIX 3: Fix spelling of length() (was lenth)
            if(i < key.length())
                c = key.charAt(i);
            else
                // FIX 4: For autokey, use plaintext characters as key
                c = plaintext.charAt(i - key.length());
            
            char c2 = plaintext.charAt(i);
            char ciph = cipher(c2, c);
            ciphered = ciphered + ciph;
        }
     
String decrypted = "";

for (int i = 0; i < ciphered.length(); i++) {
    char k;

    if (i < key.length())
        k = key.charAt(i);
    else
        k = decrypted.charAt(i - key.length()); // AUTOKEY

    char ciph = ciphered.charAt(i);
    char plain = decipher(ciph, k);

    decrypted = decrypted + plain;
}

        
        System.out.println("Encrypted: " + ciphered);
        System.out.println("Decrypted: " + decrypted);
int hashValue = xorHashCipher(ciphered);
System.out.println("Hash value: " + hashValue);
        
        sc.close();
    }
}