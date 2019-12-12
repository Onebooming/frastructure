package com.onebooming.frastructure.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.Random;

/**
 * 工具类
 *
 * @author Onebooming
 * @version 1.0
 * @date 2019/12/12 8:52
 */
public class Tools {
    public static final Random random = new Random();

    /**
     * 通过FileChannel实现创建文件复制通道
     *
     * @param source 源文件
     * @param dest   目标文件
     * @throws IOException
     */
    public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            ///实例化输入输出流，而且获取相相应的FileChannel实例
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            //通过transferForm方法实现文件复制
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            assert inputChannel != null;
            inputChannel.close();
            assert outputChannel != null;
            outputChannel.close();
        }
    }

    /**
     * 返回一个[min,max]之间的随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int rand(int min, int max) {
        /**
         * random.nextInt(max)表示生成[0,max]之间的随机数，然后对(max-min+1)取模。
         *
         * 以生成[10,20]随机数为例，首先生成0-20的随机数，然后对(20-10+1)取模得到[0-10]之间的随机数，
         * 然后加上min=10，最后生成的是10-20的随机数
         */
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 将一个double类型的数据进行GB-MB-KB的单位装换
     * 如果value 大于 1073741824，则转换为GB单位，如value = 1073741850，则返回 1GB
     * 如果value 小于 1073741824，但是大于 1048576， 则转换为MB单位，如3048576，则返回 3 MB
     *
     * @param value
     * @return
     */
    public static String flowAutoShow(double value) {
        // Math.round 方法接收 float 和 double 类型,如果参数是 int 的话,会强转为 float,这个时候调用该方法无意义
        int kb = 1024;
        int mb = 1048576;
        int gb = 1073741824;
        double abs = Math.abs(value);
        if (abs > gb) {
            /**
             * Math.round(11.5)的返回值是12，Math.round(-11.5)的返回值是-11。四舍五入的原理是在参数上加0.5然后进行下取整。
             */
            return Math.round(abs / gb) + "GB";
        } else if (abs > mb) {
            return Math.round(abs / mb) + "MB";
        } else if (abs > kb) {
            return Math.round(abs / kb) + "KB";
        }
        return Math.round(abs) + "";
    }

    /**
     * 根据key对数据进行加密，返回一个加密后的密文，为String类型
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String enAes(String data, String key) throws Exception {
        /**
         * public static final Cipher getInstance(String transformation)  返回实现指定转换的 Cipher 对象。
         * transformation - 转换的名称，例如 DES/CBC/PKCS5Padding
         */
        Cipher cipher = Cipher.getInstance("AES");
        /**
         * SecretKeySpec类是KeySpec接口的实现类，用于构建秘密密钥规范。可根据一个字节数组构造一个SecretKey
         */
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        /**
         * public final void init(int opmode, Key key) 用密钥初始化此 Cipher。
         * 参数： opmode - 此 Cipher 的操作模式（为以下之一：ENCRYPT_MODE、DECRYPT_MODE、WRAP_MODE 或 UNWRAP_MODE）
         */
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        /**
         * BASE64加密算法。用来给字符串加密的。已经不安全了。
         * 一直以来Base64的加密解密都是使用sun.misc包下的BASE64Encoder及BASE64Decoder的sun.misc.BASE64Encoder/BASE64Decoder类。
         * 这个类是sun公司的内部方法，并没有在java api中公开过，不属于JDK标准库范畴，但在JDK中包含了该类，可以直接使用。
         */
        return new BASE64Encoder().encode(encryptedBytes);
    }

    /**
     * 根据秘钥对数据进行解密，并返回解密后String对象
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String deAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] cipherTextBytes = new BASE64Decoder().decodeBuffer(data);
        byte[] decValue = cipher.doFinal(cipherTextBytes);
        return new String(decValue);
    }

    /**
     * 判断字符串是否为数字和有正确的值
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        // Pattern pattern=Pattern.compile("[0-9]*");
        // return pattern.matcher(str).matches();
        if (null != str && 0 != str.trim().length() && str.matches("\\d*")) {
            return true;
        }

        return false;
    }
}
