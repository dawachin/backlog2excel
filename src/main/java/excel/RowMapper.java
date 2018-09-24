package excel;

import com.nulabinc.backlog4j.CustomField;
import com.nulabinc.backlog4j.Issue;
import com.nulabinc.backlog4j.internal.json.customFields.ListItem;
import com.nulabinc.backlog4j.internal.json.customFields.SingleListCustomField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * RowMapper
 *
 * APIより取得した情報をRowTemplate形式に変換したり、
 * Excelの1行をRowTemplate形式にします。
 *
 * */
public class RowMapper {

    /**
     *
     * Issue -> RowTemplate
     *
     * @param issue 課題ひとつ分
     * @return writeRow
     */
    public static RowTemplate createRow (Issue issue){
        RowTemplate writeRow = new RowTemplate();
        writeRow.setId(issue.getIdAsString());
        writeRow.setIssueKey(issue.getIssueKey());
        writeRow.setStatus(issue.getStatus().getName());
        writeRow.setCreateUser(issue.getCreatedUser().getName());
        writeRow.setAssignUser(issue.getAssignee().getName());
        writeRow.setAssignUserId(String.valueOf(issue.getAssignee().getId()));
        writeRow.setCreateDate(issue.getCreated().toString());
        writeRow.setUpdateDate(issue.getUpdated().toString());
        writeRow.setSummary(issue.getSummary());
        writeRow.setDescription(issue.getDescription());
        for (CustomField customField : issue.getCustomFields()){
            SingleListCustomField singleListCustomField = (SingleListCustomField) customField;
            ListItem listItem = singleListCustomField.getValue();
            if (listItem == null) continue;
            writeRow.setExpanded(listItem.getName());
        }
        return  writeRow;
    }

    /**
     *
     * Row -> RowTemplate
     *
     * @param row Excelの1行分
     * @return readRow
     */
    public static RowTemplate readRow (Row row){
        RowTemplate readRow = new RowTemplate();
        readRow.setId(getCellValue(row.getCell(0)).toString());
        readRow.setIssueKey(getCellValue(row.getCell(1)).toString());
        readRow.setStatus(getCellValue(row.getCell(2)).toString());
        readRow.setCreateUser(getCellValue(row.getCell(3)).toString());
        readRow.setAssignUser(getCellValue(row.getCell(4)).toString());
        readRow.setAssignUserId(getCellValue(row.getCell(5)).toString());
        readRow.setCreateDate(getCellValue(row.getCell(6)).toString());
        readRow.setUpdateDate(getCellValue(row.getCell(7)).toString());
        readRow.setSummary(getCellValue(row.getCell(8)).toString());
        readRow.setDescription(getCellValue(row.getCell(9)).toString());

        if (row.getCell(10) != null) {
            readRow.setExpanded(getCellValue(row.getCell(10)).toString());
        }

        return readRow;
    }

    /**
     *
     * セルの形式同定
     *
     * セルの設定情報より、入力されている値の形式を判定します。
     *
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {

            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();

            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();

            default:
                return "";

        }

    }
}
