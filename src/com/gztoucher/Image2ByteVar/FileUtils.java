package com.gztoucher.Image2ByteVar;

import java.io.*;
import java.util.*;

/**
 * 文件处理
 *
 * @author 李玉江[QQ:1023694760]
 * @since 2014/04/18
 */
public final class FileUtils {

    public static void closeSilently(Closeable c) {
        if (c == null) {
            return;
        }
        try {
            c.close();
        } catch (IOException t) {
            // do nothing
            t.printStackTrace();
        }
    }

    /**
     * 列出指定目录下的所有文件
     */
    public static File[] listFiles(String startDirPath, final String[] allowExtensions) {
        File file = new File(startDirPath);
        //noinspection Convert2Lambda
        return file.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                //返回当前目录所有以某些扩展名结尾的文件
                String extension = getExtension(name);
                return Arrays.toString(allowExtensions).contains(extension);
            }

        });
    }

    /**
     * 列出指定目录下的所有文件
     */
    public static File[] listFiles(String startDirPath, String allowExtension) {
        return listFiles(startDirPath, new String[]{allowExtension});
    }

    /**
     * 获取文件后缀,不包括“.”
     */
    public static String getExtension(String pathOrUrl) {
        int dotPos = pathOrUrl.lastIndexOf('.');
        if (0 <= dotPos) {
            return pathOrUrl.substring(dotPos + 1);
        } else {
            return "ext";
        }
    }

    /**
     * 保存文本内容
     */
    public static boolean writeText(String filepath, String content) {
        try {
            return writeBytes(filepath, content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存文件内容
     */
    public static boolean writeBytes(String filepath, byte[] data) {
        File file = new File(filepath);
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.getParentFile().mkdirs();
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            }
            fos = new FileOutputStream(filepath);
            fos.write(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeSilently(fos);
        }
    }

    /**
     * 读取文件内容, 失败将返回空串
     */
    public static byte[] readBytes(String filepath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filepath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, len);
            }
            byte[] data = baos.toByteArray();
            baos.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeSilently(fis);
        }
    }

}
