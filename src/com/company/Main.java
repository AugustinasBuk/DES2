package com.company;

import javax.crypto.*;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        String strDataToEncrypt = new String();
        String strCipherText = new String();
        String strDecryptedText = new String();

        System.out.println("Įveskite pradini tekstą");
        String Str;
        Scanner scanIn = new Scanner(System.in);
        Str = scanIn.nextLine();
        strDataToEncrypt = Str;

        try {
            /**
             *  Step 1. Generate a DES key using KeyGenerator
             *
             */
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            System.out.println("Įveskite raktą (min 8 char )");
            String key;
            key = scanIn.nextLine();
            DESKeySpec dks = new DESKeySpec(key.getBytes());





            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = skf.generateSecret(dks);
            System.out.println(dks);

            /**
             *  Step2. Create a Cipher by specifying the following parameters
             *          a. Algorithm name - here it is DES
             *          b. Mode - here it is CBC
             *          c. Padding - PKCS5Padding
             */

            System.out.println("1-CBC 2-ECB 3-PCBC 4-CFB 5-OFB");
            System.out.println("Pasirinkite moda");
            int pasirinkimai;
            pasirinkimai = scanIn.nextInt();
            Cipher desCipher;

            switch (pasirinkimai) {
                case 1:
                    System.out.println("Pasirinkote CBC");
                    desCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    /**
                     *  Step 3. Initialize the Cipher for Encryption
                     */
                    desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
                    break;

                case 2:
                    System.out.println("Pasirinkote ECB");
                    desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                    /**
                     *  Step 3. Initialize the Cipher for Encryption
                     */
                    desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
                    break;
                case 3:
                    System.out.println("Pasirinkote PCBC");
                    desCipher = Cipher.getInstance("DES/PCBC/PKCS5Padding");
                    /**
                     *  Step 3. Initialize the Cipher for Encryption
                     */
                    desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
                    break;
                case 4:
                    System.out.println("Pasirinkote CFB");
                    desCipher = Cipher.getInstance("DES/CFB/PKCS5Padding");
                    /**
                     *  Step 3. Initialize the Cipher for Encryption
                     */
                    desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
                    break;
                case 5:
                    System.out.println("Pasirinkote OFB");
                    desCipher = Cipher.getInstance("DES/OFB/PKCS5Padding");
                    /**
                     *  Step 3. Initialize the Cipher for Encryption
                     */
                    desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
                    break;



                    default:
                    throw new IllegalStateException("Unexpected value: " + pasirinkimai);
            }



                /**
                 *  Step 4. Encrypt the Data
                 *          1. Declare / Initialize the Data. Here the data is of type String
                 *          2. Convert the Input Text to Bytes
                 *          3. Encrypt the bytes using doFinal method
                 */
                byte[] byteDataToEncrypt = strDataToEncrypt.getBytes();
                byte[] byteCipherText = desCipher.doFinal(byteDataToEncrypt);
                strCipherText = Base64.getEncoder().withoutPadding().encodeToString(byteCipherText);
                if (pasirinkimai == 1) {
                    System.out.println("Cipher Text generated using DES with CBC mode and PKCS5 Padding is " + strCipherText);
                }
                else if (pasirinkimai == 2){
                    System.out.println("Cipher Text generated using DES with ECB mode and PKCS5 Padding is " + strCipherText);

                }
                else if (pasirinkimai == 3){
                    System.out.println("Cipher Text generated using DES with PCBC mode and PKCS5 Padding is " + strCipherText);
                }
                else if (pasirinkimai == 4){
                    System.out.println("Cipher Text generated using DES with CFB mode and PKCS5 Padding is " + strCipherText);
                }
                else if (pasirinkimai == 5){
                    System.out.println("Cipher Text generated using DES with OFB mode and PKCS5 Padding is " + strCipherText);
                }
                /**
                 *  Step 5. Decrypt the Data
                 *          1. Initialize the Cipher for Decryption
                 *          2. Decrypt the cipher bytes using doFinal method
                 */
                desCipher.init(Cipher.DECRYPT_MODE, secretKey, desCipher.getParameters());
                //desCipher.init(Cipher.DECRYPT_MODE,secretKey);

                byte[] byteDecryptedText = desCipher.doFinal(byteCipherText);
                strDecryptedText = new String(byteDecryptedText);
                System.out.println(" Decrypted Text message is " + strDecryptedText);

            }

        catch (NoSuchAlgorithmException noSuchAlgo)
        {
            System.out.println(" No Such Algorithm exists " + noSuchAlgo);
        }

        catch (NoSuchPaddingException noSuchPad)
        {
            System.out.println(" No Such Padding exists " + noSuchPad);
        }

        catch (InvalidKeyException invalidKey)
        {
            System.out.println(" Invalid Key " + invalidKey);
        }

        catch (BadPaddingException badPadding)
        {
            System.out.println(" Bad Padding " + badPadding);
        }

        catch (IllegalBlockSizeException illegalBlockSize)
        {
            System.out.println(" Illegal Block Size " + illegalBlockSize);
        }

        catch (InvalidAlgorithmParameterException invalidParam)
        {
            System.out.println(" Invalid Parameter " + invalidParam);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

}