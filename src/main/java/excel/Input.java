package excel;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Input {


    //適当なディレクトリに書き換えてください
    static final String INPUT_DIR = "out/";

    public ArrayList<RowTemplate> excel() {

        // 読み取った情報
        ArrayList<RowTemplate> readResult = new ArrayList<>();

        try {

            String xlsxFileAddress = INPUT_DIR + "Sample2.xlsx";

            //共通インターフェースを扱える、WorkbookFactoryで読み込む
            Workbook wb = WorkbookFactory.create(new FileInputStream(xlsxFileAddress));

            //1シート目を読み込む
            Sheet sheet = wb.getSheetAt(0);

            // 1行ごとに読み込む
            for (Row row : sheet) {
                readResult.add(RowMapper.readRow(row));
            }
            wb.close();

        }catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return readResult;
    }
}
