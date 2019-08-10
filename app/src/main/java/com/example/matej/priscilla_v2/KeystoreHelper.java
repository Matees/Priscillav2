package com.example.matej.priscilla_v2;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class KeystoreHelper {

    @TargetApi(Build.VERSION_CODES.M)
    public static void encode(String key, String alias){
        try {
            final KeyGenerator keyGenerator = KeyGenerator
                    .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            final KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(alias,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build();
//                }

            keyGenerator.init(keyGenParameterSpec);
            final SecretKey secretKey = keyGenerator.generateKey();

            final Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte [] IV = cipher.getIV();

            byte [] encryptedKeyBytes = cipher.doFinal(key.getBytes("UTf-8"));

            String base64encryptedPassword = Base64.encodeToString(encryptedKeyBytes, Base64.DEFAULT);

            Constants.instance().storeValueString(alias, base64encryptedPassword);
            Constants.instance().storeValueString(alias + "IV", Base64.encodeToString(IV, Base64.DEFAULT));

        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | InvalidAlgorithmParameterException |
                BadPaddingException | NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static String decodeKey(String alias){
        String unencryptedKey = "";

        String base64EncryptedPassword = Constants.instance().fetchValueString(alias);
        String base64IV = Constants.instance().fetchValueString(alias + "IV");

        byte [] IV = Base64.decode(base64IV, Base64.DEFAULT);
        byte [] encryptedKey = Base64.decode(base64EncryptedPassword, Base64.DEFAULT);

        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);

            SecretKey secretKey = (SecretKey) keyStore.getKey(alias, null);

            final Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV));

            byte [] passwordBytes = cipher.doFinal(encryptedKey);

            return new String(passwordBytes, "UTF-8");

        } catch (CertificateException | IOException | NoSuchAlgorithmException | UnrecoverableEntryException |
                KeyStoreException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException |
                BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return unencryptedKey;
    }
}
