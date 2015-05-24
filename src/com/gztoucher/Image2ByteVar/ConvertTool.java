package com.gztoucher.Image2ByteVar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ConvertTool {

	public void convert() {
		byte[] data = null;
		try {
			InputStream input = getClass()
					.getResourceAsStream("/liyujiang.png");
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			String str = "{";
			for (int i = 0; i < data.length; i++) {
				if (i == data.length - 1) {
					str += data[i];
				} else {
					str += data[i] + ",";
				}
			}
			str += "}";
			System.out.println("public final static byte IMAGE[] = " + str
					+ ";");
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
	}

}
