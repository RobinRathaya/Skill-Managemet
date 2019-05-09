package com.chainsys.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Services {
	public void importFromExcel() {
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(
					"questions.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
