package common;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;

import static common.Read.read;

public class EncryptInput {

    public static void encrypt() throws
            NoSuchAlgorithmException,
            InvalidKeySpecException,
            NoSuchPaddingException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            IOException {

        List<String> passwordSaltTagList = read("common", "Input_Password.TXT");

        File[] dayDirArr = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator).listFiles();

        if (dayDirArr == null) return;

        for (File dayDir : dayDirArr) {

            if (! dayDir.getName().startsWith("Day")) continue;

            File inputFile = new File(dayDir.getAbsolutePath() + File.separator + "Input.TXT");

            if (!Files.exists(inputFile.toPath())) continue;

            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));

            StringBuilder fileContent = new StringBuilder();
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                fileContent.append(currentLine);
                fileContent.append(System.lineSeparator());
            }

            bufferedReader.close();

            byte[] initialVector = new byte[32];
            new SecureRandom().nextBytes(initialVector);

            char[] password = passwordSaltTagList.getFirst().toCharArray();
            byte[] salt = passwordSaltTagList.get(1).getBytes(StandardCharsets.UTF_8);

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            KeySpec keySpec = new PBEKeySpec(password, salt, 262144, 256);

            SecretKey secretKey = new SecretKeySpec(secretKeyFactory.generateSecret(keySpec).getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(128, initialVector));

            try (FileOutputStream fileOutputStream = new FileOutputStream(inputFile.getAbsolutePath() + ".Encrypted")) {
                CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
                fileOutputStream.write(initialVector);
                cipherOutputStream.write(fileContent.toString().getBytes());
                cipherOutputStream.flush();
                cipherOutputStream.close();
            }

        }

    }

    public static void main(String[] args) throws
            NoSuchAlgorithmException,
            InvalidKeySpecException,
            NoSuchPaddingException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            IOException {
        encrypt();
    }

}
