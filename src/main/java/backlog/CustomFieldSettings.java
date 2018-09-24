package backlog;

import com.nulabinc.backlog4j.*;
import com.nulabinc.backlog4j.conf.BacklogConfigure;
import com.nulabinc.backlog4j.conf.BacklogJpConfigure;

import java.util.HashMap;

public class CustomFieldSettings {

    private final ResponseList<CustomFieldSetting> customFields;

    private HashMap<String, CustomFieldSetting> customFieldSettingHashMap = new HashMap<>();

    public CustomFieldSettings() throws Exception{
        CertificationInfo certificationInfo = new CertificationInfo();
        BacklogConfigure configure = new BacklogJpConfigure(certificationInfo.SPACE_ID).apiKey(certificationInfo.API_KEY);
        BacklogClient backlog = new BacklogClientFactory(configure).newClient();
        Project project = backlog.getProject(certificationInfo.PROJECT_KEY);
        customFields = backlog.getCustomFields(project.getId());
        customFields.stream().forEach(field -> {customFieldSettingHashMap.put(field.getName(), field);});
    }

    public CustomFieldSetting getCustomFieldSettingByName(String customFildName) {
        return customFieldSettingHashMap.get(customFildName);
    }
}
