package com.gztoucher.Image2ByteVar;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 图片转为字节数组变量
 *
 * @author 李玉江[QQ:1023694760]
 * @since 2015/05/24
 */
public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        CodeTool tool = new CodeTool();
        String iconsPath = Main.class.getResource("/icons/").getPath();
        iconsPath = URLDecoder.decode(iconsPath, "utf-8");
        System.out.println("icons path is " + iconsPath);
        String javaCode = tool.convertFromDir(iconsPath);
        String savePath = Main.class.getResource("/").getPath();
        savePath = URLDecoder.decode(savePath, "utf-8") + "/XyzIcon.java";
        System.out.println("save path is " + savePath);
        FileUtils.writeText(savePath, javaCode);
        //System.out.println(javaCode);
    }

}
