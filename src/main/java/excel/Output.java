package excel;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import backlog.*;

import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Output {

    static final String INPUT_DIR = "out/";

    private final Issues outputIssues;

    public Output(Issues outputIssues) {
        this.outputIssues = outputIssues;
    }

    public void excel() {

        ArrayList<RowTemplate> rowList = outputIssues.createRowList();


        try {

            //xlsxの場合はこちらを有効化
            Workbook wb = new XSSFWorkbook();
            FileOutputStream fileOut = new FileOutputStream(INPUT_DIR + "sample2.xlsx");

            String safeName = WorkbookUtil.createSafeSheetName("['aaa's test*?]");
            Sheet sheet1 = wb.createSheet(safeName);

            CreationHelper createHelper = wb.getCreationHelper();

            int rowIndex = 0;
            for (RowTemplate template : rowList){
                Row oneRow = sheet1.createRow((short)rowIndex);
                oneRow.createCell(0).setCellValue(
                        createHelper.createRichTextString(template.getId()));
                oneRow.createCell(1).setCellValue(
                        createHelper.createRichTextString(template.getIssueKey()));
                oneRow.createCell(2).setCellValue(
                        createHelper.createRichTextString(template.getStatus()));
                oneRow.createCell(3).setCellValue(
                        createHelper.createRichTextString(template.getCreateUser()));
                oneRow.createCell(4).setCellValue(
                        createHelper.createRichTextString(template.getAssignUser()));
                oneRow.createCell(5).setCellValue(
                        createHelper.createRichTextString(template.getAssignUserId()));
                oneRow.createCell(6).setCellValue(
                        createHelper.createRichTextString(template.getCreateDate()));
                oneRow.createCell(7).setCellValue(
                        createHelper.createRichTextString(template.getUpdateDate()));
                oneRow.createCell(8).setCellValue(
                        createHelper.createRichTextString(template.getSummary()));
                oneRow.createCell(9).setCellValue(
                        createHelper.createRichTextString(template.getDescription()));
                oneRow.createCell(10).setCellValue(
                        createHelper.createRichTextString(template.getExpanded()));
                rowIndex = rowIndex +1;
            }

            wb.write(fileOut);
            fileOut.close();


        }catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }




}

