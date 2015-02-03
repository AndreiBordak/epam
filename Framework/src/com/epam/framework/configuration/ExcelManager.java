package com.epam.framework.configuration;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.epam.framework.configuration.constant.ConfigurationConstant;
import com.epam.framework.entity.User;
import com.epam.framework.logger.LoggerUtils;

public class ExcelManager extends ConfigurationManager implements Closeable {

	private HashMap<String, User> usersMap = new HashMap<String, User>();
	private static ExcelManager instance = null;
	private static final String FILE_PATH = ConfigurationConstant.EXCEL_FILE_PATH;
	private static final int LOGIN_CELL_NUMBER = 0;
	private static final int PWD_CELL_NUMBER = 1;
	private static final int SHEET_NUMBER = 0;
	private static final int FINAL_ROW = 4;
	private static String USER_KEY = "user";
	private File excelFile = null;
	private FileInputStream fis = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;

	private ExcelManager() {
		super();

	}

	public synchronized static ConfigurationManager getInstance() {
		if (instance == null) {
			instance = new ExcelManager();
		}

		return instance;
	}

	private void initFile() {
		excelFile = new File(FILE_PATH);
		try {
			fis = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(SHEET_NUMBER);
		} catch (IOException e) {
			LoggerUtils.error(e);
		}
	}

	public HashMap<String, User> initUsers() {
		initFile();
		int rowNumber = 1;
		Cell loginCell = null;
		Cell pwdCell = null;
		Iterator<Row> rows = sheet.rowIterator();
		if (rows.hasNext()) { // пропускаем шапку таблицы
			rows.next();
		}

		while (rows.hasNext()) {
			if (rowNumber == FINAL_ROW) 
				break;
			Row row = rows.next();
			loginCell = row.getCell(LOGIN_CELL_NUMBER);
			pwdCell = row.getCell(PWD_CELL_NUMBER);

			usersMap.put(
					USER_KEY + rowNumber,
					new User(loginCell.getStringCellValue(), pwdCell
							.getStringCellValue()));
			rowNumber++;
		}

		try {
			close();
		} catch (IOException e) {
			LoggerUtils.error(e);
		}
		return usersMap;
	}

	@Override
	public void close() throws IOException {
		fis.close();
	}

}
