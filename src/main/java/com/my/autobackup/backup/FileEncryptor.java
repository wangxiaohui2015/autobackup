package com.my.autobackup.backup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.my.autobackup.backup.util.ServiceConfigPropertiesUtil;
import com.my.autobackup.common.FileUtil;
import com.my.autobackup.common.HashUtil;

/**
 * File encryptor, use AES algorithm to encrypt file.
 * 
 * @author Administrator
 */
public class FileEncryptor {

    private static Logger logger = Logger.getLogger(FileEncryptor.class);

    private static final String ALGORITHM = "AES";
    private static final String ALGORITHM_PKCS5PADDING = "AES/CBC/PKCS5Padding";
    private static final int CACHE_SIZE = 1024 * 1024;
    private static final String CHARSET_UTF8 = "UTF-8";
    private static byte[] keyBytes;

    static {
        try {
            String key = ServiceConfigPropertiesUtil.getInstance().getBackupKey();
            keyBytes = HashUtil.getSHA256HashValue(key.getBytes(CHARSET_UTF8));
            String keyHashString = HashUtil.getSHA256HashStringValue(keyBytes);
            keyBytes = HashUtil.getSHA256HashValue(keyHashString.getBytes(CHARSET_UTF8));
            keyBytes = HashUtil.getSHA256HashValue(keyBytes);
        } catch (Exception e) {
            logger.error("Failed to init key bytes.", e);
            System.exit(-1);
        }
    }

    /**
     * Encrypt file.
     * 
     * @param sourceFilePath source file path
     * @param destFilePath destination file path
     * @throws Exception Exception
     */
    public static void encryptFile(String sourceFilePath, String destFilePath) throws Exception {
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath);
        if (!sourceFile.exists() || !sourceFile.isFile()) {
            throw new Exception("sourceFile doesn't exist or sourceFile isn't a file, sourceFile:"
                            + sourceFile);
        }
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        try {
            destFile.createNewFile();
        } catch (IOException e) {
            logger.error("Failed to create new file.", e);
            throw e;
        }

        InputStream in = null;
        OutputStream out = null;
        CipherInputStream cin = null;
        try {
            in = new FileInputStream(sourceFile);
            out = new FileOutputStream(destFile);

            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, 0, 16, ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(keyBytes, 16, 16);
            Cipher cipher = Cipher.getInstance(ALGORITHM_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            cin = new CipherInputStream(in, cipher);

            byte[] bytes = new byte[CACHE_SIZE];
            int length = -1;
            while ((length = cin.read(bytes)) != -1) {
                out.write(bytes, 0, length);
            }
            out.flush();
        } catch (Exception e) {
            logger.error("Exception occurred when encryptFile.", e);
            throw e;
        } finally {
            FileUtil.closeOutputStream(out);
            FileUtil.closeInputStream(cin);
            FileUtil.closeInputStream(in);
        }
    }
}
