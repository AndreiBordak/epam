package com.epam.framework.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

import com.epam.framework.logger.LoggerUtils;
import com.epam.framework.utils.exception.UtilException;

public class Utils {

	private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String READ_WRITE_ACCESS = "rw";
	private static Random rnd = null;

	static {
		rnd = new Random();
	}

	public static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	public static File getRandomFile(long size) throws UtilException {
		File file = new File("file.txt");
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file,
					READ_WRITE_ACCESS);
			randomAccessFile.setLength(size);
			randomAccessFile.close();
		} catch (FileNotFoundException e) {
			throw new UtilException("File not found", e);
		} catch (IOException e) {
			throw new UtilException("Ioexception", e);
		}
		return file;
	}

	public static void deleteFile(File file) {
		file.delete();
	}

	public static void attachFile(StringSelection selection)
			throws UtilException {
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(selection, null);
		try {
			LoggerUtils.info("Robot created");
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(7000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			throw new UtilException("Some problem with robot", e);
		}
	}
	
}
