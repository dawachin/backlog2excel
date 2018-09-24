package backlog;

import com.nulabinc.backlog4j.*;
import com.nulabinc.backlog4j.api.option.GetIssuesParams;
import com.nulabinc.backlog4j.conf.BacklogConfigure;
import com.nulabinc.backlog4j.conf.BacklogJpConfigure;
import com.nulabinc.backlog4j.internal.json.customFields.ListItem;
import com.nulabinc.backlog4j.internal.json.customFields.SingleListCustomField;
import excel.RowMapper;
import excel.RowTemplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;

public class Issues {

    private final ResponseList<Issue> issues;

    public Issues (CertificationInfo certificationInfo, GetIssuesParams issuesParams) throws Exception{
        BacklogConfigure configure = new BacklogJpConfigure(certificationInfo.SPACE_ID).apiKey(certificationInfo.API_KEY);
        BacklogClient backlog = new BacklogClientFactory(configure).newClient();
        issues = backlog.getIssues(issuesParams);
    }

    public ArrayList<RowTemplate> createRowList(){
        ArrayList<RowTemplate> output = new ArrayList<>();
        issues.stream().forEach(issue -> {output.add(RowMapper.createRow(issue));});
        return output;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        issues.stream().forEach(issue -> {sb.append(issueToString(issue));});
        return sb.toString();
    }

    public ResponseList<Issue> asIssues(){
        return issues;
    }

    private String issueToString(Issue issue) {
        StringBuilder sb = new StringBuilder();
        sb.append("############################################################");
        sb.append(StringUtils.LF);
        sb.append("*"+ issue.getSummary());
        sb.append(StringUtils.LF);
        sb.append("ID        :: "+ issue.getIdAsString());
        sb.append(StringUtils.LF);
        sb.append("ISSUE_KEY :: "+ issue.getIssueKey());
        sb.append(StringUtils.LF);
        sb.append("MILESTONE :: "+ issue.getMilestone().get(0).getName());
        sb.append(StringUtils.LF);
        sb.append("VERSIONS  :: "+ issue.getVersions());
        sb.append(StringUtils.LF);
        sb.append("STATUS    :: "+ issue.getStatus().getName());
        sb.append(StringUtils.LF);
        sb.append("CREATE_U  :: "+ issue.getCreatedUser().getName());
        sb.append(StringUtils.LF);
        sb.append("ASSIGN_U  :: "+ issue.getAssignee().getName());
        sb.append(StringUtils.LF);
        sb.append("CREATE_D  :: "+ issue.getCreated());
        sb.append(StringUtils.LF);
        sb.append("UPDATE_D  :: "+ issue.getUpdated());
        sb.append(StringUtils.LF);
        sb.append("DESC      :: "+ issue.getDescription());
        sb.append(StringUtils.LF);


        for (CustomField customField : issue.getCustomFields()){
            sb.append("CustomF   :: Name        -> " + customField.getName());
            sb.append(StringUtils.LF);
            sb.append("          :: ID          -> " + customField.getId());
            sb.append(StringUtils.LF);
            sb.append("          :: FieldType   -> " + customField.getFieldType().name());
            sb.append(StringUtils.LF);
            sb.append("          :: FieldTypeId -> " + customField.getFieldTypeId());

            SingleListCustomField singleListCustomField = (SingleListCustomField) customField;
            ListItem listItem = singleListCustomField.getValue();

            if (listItem == null) continue;

            sb.append(StringUtils.LF);
            sb.append("          :: ListItemId  -> " + listItem.getId());
            sb.append(StringUtils.LF);
            sb.append("          :: ListItemName-> " + listItem.getName());


        }
        sb.append(StringUtils.LF);
        return  sb.toString();
    }
}
