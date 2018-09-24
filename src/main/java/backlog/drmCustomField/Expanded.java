package backlog.drmCustomField;

import backlog.CustomFieldSettings;
import com.nulabinc.backlog4j.CustomFieldListItemSetting;
import com.nulabinc.backlog4j.CustomFieldSetting;
import com.nulabinc.backlog4j.internal.json.customFields.ListItemSetting;

import java.util.HashMap;
import java.util.List;

public class Expanded {

    private final CustomFieldSetting expandedField;

    private HashMap<String, ListItemSetting> itemMap = new HashMap<>();

    private final static String LABEL = "水平展開有無";
    private final static String LABEL_AVAILABLE = "有";
    private final static String LABEL_NOT_AVAILABLE = "無";

    public Expanded()  throws Exception{
        CustomFieldSettings fieldSettings = new CustomFieldSettings();
        expandedField = fieldSettings.getCustomFieldSettingByName(LABEL);
        List<? extends CustomFieldListItemSetting> itemSettings = expandedField.getItems();
        itemSettings.stream()
                .forEach(item -> {itemMap.put(((CustomFieldListItemSetting) item).getName(), (ListItemSetting)item);});
    }

    public static Expanded getExpanded() {
        try{
            return new Expanded();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ListItemSetting getAvailableSetting() {
        return itemMap.get(LABEL_AVAILABLE);
    }

    public ListItemSetting getNotAvailableSetting() {
        return itemMap.get(LABEL_NOT_AVAILABLE);
    }

    public ListItemSetting getSettingByName(String name){
        return itemMap.get(name);
    }

    public CustomFieldSetting getExpandedField() {
        return expandedField;
    }
}
