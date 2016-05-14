/**
 * 
 */
package com.ezijing.qa.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.ezijing.qa.io.IRegionCoordinate;
import com.ezijing.qa.io.IRegionCoordinate.RegionProperty;

/**
 * @author cuixiaohui
 *
 */
public class PoiUtils {

	public static String filename = System.getProperty("user.dir")
			+ "//data//TestData.xlsx";
	public static String path;
	public static FileInputStream fis = null;
	public static FileOutputStream fos = null;
	private static XSSFSheet sheet = null;
	private static XSSFRow row = null;
	private static XSSFCell cell = null;

	public static Workbook readExcel(String filepath) {
		Workbook result = null;
		try {

			try {
				result = new XSSFWorkbook(filepath); // 支持2007
			} catch (Exception ex) {
				result = new HSSFWorkbook(new FileInputStream(filepath)); // 支持2003及以前
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * @author cuixiaohui
	 * @param sheetName
	 * @return int row number
	 * */
	public static int getRowCount(Workbook workbook, String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = (XSSFSheet) workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}

	/**
	 * @author cuixiaohui
	 * @param sheetName
	 * @param colNum
	 * @param rowNum
	 * @return String cell data
	 * */
	public static String getCellData(Workbook workbook, String sheetName,
			int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
			sheet = getSheetThroughName(workbook, sheetName);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			return getCellContent(cell);
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum
					+ " does not exist  in xls";
		}
	}

	/**
	 * @author cuixiaohui
	 * @param String
	 *            sheetName
	 * @param String
	 *            colname
	 * @param int rowNum
	 * @return String cell data
	 * */
	public static String getCellData(Workbook workbook, String sheetName,
			String colName, int rowNum) {
		try {
			int col_Num = -1;
			if (rowNum <= 0)
				return "";
			sheet = getSheetThroughName(workbook, sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim()
						.equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";
			// sheet = workBook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);
			return getCellContent(cell);
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colName
					+ " does not exist in xls";
		}
	}

	/**
	 * @author cuixiaohui Name: setCellData
	 * @param String
	 *            sheetName
	 * @param String
	 *            colname
	 * @param int rowNum
	 * @return String cell data
	 * @param String
	 *            data
	 * 
	 * 
	 * */

	public static boolean setCellData(Workbook workbook, String sheetName,
			String colName, int rowNum, String data) {
		try {
			if (rowNum <= 0)
				return false;
			int colNum = -1;
			sheet = getSheetThroughName(workbook, sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			if (colNum == -1)
				return false;
			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);
			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);
			// cell style
			// CellStyle cs = workbook.createCellStyle();
			// cs.setWrapText(true);
			// cell.setCellStyle(cs);
			cell.setCellValue(data);

			fos = new FileOutputStream(path);

			workbook.write(fos);

			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @author cuixiaohui Name: setCellData
	 * @param String
	 *            sheetName
	 * @param String
	 *            colname
	 * @param int rowNum
	 * @param String
	 *            url
	 * @param String
	 *            data
	 * @return String cell data
	 * */
	public static boolean setCellData(Workbook workbook, String sheetName,
			String colName, int rowNum, String data, String url) {
		// System.out.println("setCellData setCellData******************");
		try {
			int colNum = -1;
			if (rowNum <= 0)
				return false;

			sheet = getSheetThroughName(workbook, sheetName);

			row = sheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim()
						.equalsIgnoreCase(colName))
					colNum = i;
			}

			if (colNum == -1)
				return false;
			sheet.autoSizeColumn(colNum); // ashish
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);
			XSSFCreationHelper createHelper = (XSSFCreationHelper) workbook
					.getCreationHelper();

			// cell style for hyperlinks
			// by default hypelrinks are blue and underlined
			CellStyle hlink_style = workbook.createCellStyle();
			XSSFFont hlink_font = (XSSFFont) workbook.createFont();
			hlink_font.setUnderline(XSSFFont.U_SINGLE);
			hlink_font.setColor(IndexedColors.BLUE.getIndex());
			hlink_style.setFont(hlink_font);
			// hlink_style.setWrapText(true);

			XSSFHyperlink link = createHelper
					.createHyperlink(XSSFHyperlink.LINK_FILE);
			link.setAddress(url);
			cell.setHyperlink(link);
			cell.setCellStyle(hlink_style);

			fos = new FileOutputStream(path);
			workbook.write(fos);

			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @author cuixiaohui Name: addSheet
	 * @param String
	 *            sheetName
	 * @return if create sucessfully return true, else return false
	 * */
	public static boolean addSheet(Workbook workbook, String sheetname) {

		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetname);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @author cuixiaohui Name: removeSheet
	 * @param String
	 *            sheetName
	 * @return returns true if sheet is removed successfully else false if sheet
	 *         does not exist
	 * */

	public static boolean removeSheet(Workbook workbook, String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return false;

		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @author cuixiaohui Name: addColumn
	 * @param String
	 *            sheetName
	 * @param String
	 *            colName
	 * @return returns true if column is created successfully
	 * */

	public static boolean addColumn(Workbook workbook, String sheetName,
			String colName) {
		// System.out.println("**************addColumn*********************");

		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			sheet = (XSSFSheet) workbook.getSheetAt(index);

			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);

			// cell = row.getCell();
			// if (cell == null)
			// System.out.println(row.getLastCellNum());
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());

			cell.setCellValue(colName);
			cell.setCellStyle(style);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	// removes a column and all the contents
	public static boolean removeColumn(Workbook workbook, String sheetName,
			int colNum) {
		try {
			if (!isSheetExist(workbook, sheetName))
				return false;

			sheet = (XSSFSheet) workbook.getSheet(sheetName);
			XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			//XSSFCreationHelper createHelper = (XSSFCreationHelper) workbook.getCreationHelper();
			style.setFillPattern(HSSFCellStyle.NO_FILL);
			for (int i = 0; i < getRowCount(workbook, sheetName); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(colNum);
					if (cell != null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	// find whether sheets exists
	public static boolean isSheetExist(Workbook workbook, String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// returns number of columns in a sheet
	public static int getColumnCount(Workbook workbook, String sheetName) {
		// check if sheet exists
		if (!isSheetExist(workbook, sheetName))
			return -1;
		sheet = (XSSFSheet) workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		if (row == null)
			return -1;
		return row.getLastCellNum();
	}

	// String sheetName, String testCaseName,String keyword ,String URL,String
	// message
	public static boolean addHyperLink(Workbook workbook, String sheetName,
			String screenShotColName, String testCaseName, int index,
			String url, String message) {
		// System.out.println("ADDING addHyperLink******************");

		url = url.replace('\\', '/');
		if (!isSheetExist(workbook, sheetName))
			return false;

		sheet = (XSSFSheet) workbook.getSheet(sheetName);

		for (int i = 2; i <= getRowCount(workbook, sheetName); i++) {
			if (getCellData(workbook, sheetName, 0, i).equalsIgnoreCase(
					testCaseName)) {
				// System.out.println("**caught "+(i+index));
				setCellData(workbook, sheetName, screenShotColName, i + index,
						message, url);
				break;
			}
		}

		return true;
	}

	public static int getCellRowNum(Workbook workbook, String sheetName,
			String colName, String cellValue) {

		for (int i = 2; i <= getRowCount(workbook, sheetName); i++) {
			if (getCellData(workbook, sheetName, colName, i).equalsIgnoreCase(
					cellValue)) {
				return i;
			}
		}
		return -1;
	}

	public static int getCellColumnNum(Workbook workbook, String sheetName,
			String columnName) {
		int result = 0;
		sheet = getSheetThroughName(workbook, sheetName);
		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
			// System.out.println(row.getCell(i).getStringCellValue().trim());
			if (row.getCell(i).getStringCellValue().trim()
					.equals(columnName.trim()))
				result = i;
		}
		return result;
	}

	// traversal cell
	public void traversalCell(Workbook workbook, String filePath) {
		try {
			// 获得Excel中工作表个数
			System.out.println("工作表个数 :" + workbook.getNumberOfSheets());

			// 循环每个工作表
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				// 创建工作表
				Sheet sheet = workbook.getSheetAt(i);

				int rows = sheet.getPhysicalNumberOfRows(); // 获得行数

				System.out.println("工作表" + sheet.getSheetName() + " 行数 :"
						+ sheet.getPhysicalNumberOfRows());

				if (rows > 0) {
					sheet.getMargin(Sheet.TopMargin);
					for (int r = 0; r < rows; r++) { // 行循环
						Row row = sheet.getRow(r);
						if (row != null) {

							int cells = row.getLastCellNum();// 获得列数
							for (short c = 0; c < cells; c++) { // 列循环
								Cell cell = row.getCell(c);

								if (cell != null) {
									String value = getCellData(cell);
									System.out.println("第" + r + "行 " + "第" + c
											+ "列：" + value);
								}
							}
						}
					}
				}

				// 查询合并的单元格
				for (i = 0; i < sheet.getNumMergedRegions(); i++) {
					System.out.println("第" + i + "个合并单元格");
					CellRangeAddress region = sheet.getMergedRegion(i);
					int row = region.getLastRow() - region.getFirstRow() + 1;
					int col = region.getLastColumn() - region.getFirstColumn()
							+ 1;
					System.out.println("起始行:" + region.getFirstRow());
					System.out.println("起始列:" + region.getFirstColumn());
					System.out.println("所占行:" + row);
					System.out.println("所占列:" + col);
					System.out.println("单元格值:"
							+ getCellData(workbook, "DataCenterPlayer",
									region.getFirstColumn(),
									region.getFirstRow() + 1));
				}

				System.out.println("TestClass: "
						+ getCellData(workbook, "DataCenterPlayer", 2, 8));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public Object[][] getData(String caseName, String dataFile, int colNum) {
		return null;
	}

	public Object[][] getData(String caseName, String dataFile, int beginNum,
			int endNum) {
		return null;
	}

	public static HashMap<String, String> getAPIParams(Workbook workbook,
			String sheetName, String APIName) {
		HashMap<String, String> result = new HashMap<String, String>();
		EnumMap<RegionProperty, Object> regioninfo = null;
		int colnum = 0;

		sheet = getSheetThroughName(workbook, sheetName);

		colnum = getCellColumnNum(workbook, sheetName, "TestClass");

		if (colnum == -1)
			return null;
		for (int i = 1; i < sheet.getPhysicalNumberOfRows() - 2; i++) {
			
			if (!isMergeRegion(sheet, sheet.getRow(i).getCell(colnum)))
			{
				if (getCellData(sheet.getRow(i).getCell(colnum)).trim().equals(
						APIName.trim())) 
				{
					result.put(
							getCellData(workbook, sheetName, colnum + 1, i + 1),
							getCellData(workbook, sheetName, colnum + 2, i + 1));
					return result;
				}
			}
		}
		regioninfo = getRegionCoordinate(workbook, sheetName, APIName);

		for (int i = 0; i < (Integer) regioninfo
				.get(IRegionCoordinate.RegionProperty.LENGTH); i++) {

			result.put(
					getCellData(
							workbook,
							sheetName,
							(Integer) regioninfo
									.get(IRegionCoordinate.RegionProperty.FIRSTCOL),
							(Integer) regioninfo
									.get(IRegionCoordinate.RegionProperty.FIRSTROW)
									+ i),
					getCellData(
							workbook,
							sheetName,
							(Integer) regioninfo
									.get(IRegionCoordinate.RegionProperty.FIRSTCOL) + 1,
							(Integer) regioninfo
									.get(IRegionCoordinate.RegionProperty.FIRSTROW)
									+ i));
		}
		return result;
	}

	public static String getCellContent(XSSFCell cell) {
		if (cell == null)
			return "";
		if (cell.getCellType() == Cell.CELL_TYPE_STRING)
			return cell.getStringCellValue();
		else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
				|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			String cellText = String.valueOf(cell.getNumericCellValue());
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				// format in form of M/D/YY
				double d = cell.getNumericCellValue();

				Calendar cal = Calendar.getInstance();
				cal.setTime(HSSFDateUtil.getJavaDate(d));
				cellText = (String.valueOf(cal.get(Calendar.YEAR)))
						.substring(2);
				cellText = cal.get(Calendar.MONTH) + 1 + "/"
						+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
				// System.out.println(cellText);
			}
			return cellText;
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
			return "";
		else
			return String.valueOf(cell.getBooleanCellValue());
	}

	// returns the data from cell
	public static String getCellData(Cell cell) {
		String value = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数值型
			if (DateUtil.isCellDateFormatted(cell)) {
				// 如果是date类型则 ，获取该cell的date值
				value = DateUtil.getJavaDate(cell.getNumericCellValue())
						.toString();
			} else {// 纯数字
				value = String.valueOf(cell.getNumericCellValue());
			}
			break;
		/* 此行表示单元格的内容为string类型 */
		case Cell.CELL_TYPE_STRING: // 字符串型
			value = cell.getRichStringCellValue().toString();
			break;
		case Cell.CELL_TYPE_FORMULA:// 公式型
			// 读公式计算值
			value = String.valueOf(cell.getNumericCellValue());
			if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
				value = cell.getRichStringCellValue().toString();
			}
			// cell.getCellFormula();读公式
			break;
		case Cell.CELL_TYPE_BOOLEAN:// 布尔
			value = " " + cell.getBooleanCellValue();
			break;
		/* 此行表示该单元格值为空 */
		case Cell.CELL_TYPE_BLANK: // 空值
			value = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			value = "";
			break;
		default:
			value = cell.getRichStringCellValue().toString();
		}
		return value;
	}

	public static XSSFSheet getSheetThroughName(Workbook workbook,
			String sheetName) {
		XSSFSheet result = null;
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return null;
		result = (XSSFSheet) workbook.getSheetAt(index);
		return result;
	}

	public static EnumMap<RegionProperty, Object> getRegionCoordinate(
			Workbook workbook, String sheetName, String regionValue) {
		int firstColumn = 0;
		int firstRow = 0;
		int paramsNum = 0;
		IRegionCoordinate regioncoord = new RegionCoordinateImpl();
		int tmp[] = new int[3];

		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {

			CellRangeAddress region = sheet.getMergedRegion(i);
			if (region.getFirstColumn() == getCellColumnNum(workbook,
					sheetName, "TestClass")) {
				firstColumn = region.getFirstColumn() + 1;
				if (getCellData(workbook, sheetName, firstColumn - 1,
						region.getFirstRow() + 1).trim().equals(regionValue)) {
					firstRow = region.getFirstRow() + 1;
					paramsNum = region.getLastRow() - region.getFirstRow() + 1;
				}
			}

		}
		tmp[0] = firstRow;
		tmp[1] = firstColumn;
		tmp[2] = paramsNum;
		EnumMap<RegionProperty, Object> regioninfo = regioncoord
				.getRegionInfo(tmp);
		return regioninfo;
	}

	public static HashMap<String, String> getTestData(String xlsxFileName,
			String modelName, String testClass) {
		HashMap<String, String> result = null;
		String filepath = "";
		System.setProperty("WORKDIR", System.getProperty("user.dir"));
		if(OSUtils.isWindows()){
			filepath = System.getProperty("user.dir") + "\\data\\"
				+ xlsxFileName + ".xlsx";
		}else if(OSUtils.isMacOS()||OSUtils.isLinux())
		{
			filepath = System.getProperty("user.dir") + "/data/"
					+ xlsxFileName + ".xlsx";
		}
		result = getAPIParams(readExcel(filepath), modelName, testClass);
		return result;
	}

	public static boolean isMergeRegion(Sheet sheet, Cell cell) {
		int sheetmergerCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetmergerCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			// 得到合并单元格的起始行, 结束行, 起始列, 结束列
			int firstC = ca.getFirstColumn();
			int lastC = ca.getLastColumn();
			int firstR = ca.getFirstRow();
			int lastR = ca.getLastRow();
			// 判断该单元格是否在合并单元格范围之内, 如果是, 则返回 true
			if (cell.getColumnIndex() <= lastC
					&& cell.getColumnIndex() >= firstC) {
				if (cell.getRowIndex() <= lastR && cell.getRowIndex() >= firstR) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		HashMap<String, String> apiparams = null;
		System.setProperty("WORKDIR", System.getProperty("user.dir"));
		String filePath = System.getProperty("user.dir")
				+ "//data//datacenter.xlsx";
		Workbook workbook = PoiUtils.readExcel(filePath);
		sheet = PoiUtils.getSheetThroughName(workbook, "DataCenterPlayer");
		System.out.println("sheet physical number: "
				+ sheet.getPhysicalNumberOfRows());
		apiparams = getAPIParams(workbook, "DataCenterPlayer",
				"PostPlayerReview");

		Iterator<Entry<String, String>> iter = apiparams.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) iter.next();
			System.out.println("key is : " + entry.getKey());
			System.out.println("value is :" + entry.getValue());
		}
	}

	public static boolean isBlankCell(Cell cell) {
		boolean result = true;
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK || cell == null
				|| getCellData(cell).trim().equals("")) {
			return false;
		}

		return result;
	}
}

// http://www.51testing.com/html/29/324829-858855.html