package backlog.service;

import backlog.CertificationInfo;
import backlog.IssueStatusType;
import backlog.drmCustomField.Expanded;
import com.nulabinc.backlog4j.BacklogClient;
import com.nulabinc.backlog4j.BacklogClientFactory;
import com.nulabinc.backlog4j.api.option.CustomFiledValue;
import com.nulabinc.backlog4j.api.option.UpdateIssueParams;
import com.nulabinc.backlog4j.conf.BacklogConfigure;
import com.nulabinc.backlog4j.conf.BacklogJpConfigure;
import excel.RowTemplate;

/**
 *
 * 課題の更新処理を行う
 *
 */
public class UpdateIssueService {

    Expanded expandedField = Expanded.getExpanded();

    public void doUpdate(RowTemplate inputData) throws Exception{

        UpdateIssueParams updateParams = new UpdateIssueParams(inputData.getIssueKey());
        updateParams.status(IssueStatusType.labelOf(inputData.getStatus()));
        updateParams.assigneeId(inputData.getAssignUserId());
        updateParams.summary(inputData.getSummary());
        updateParams.description(inputData.getDescription());

        if (inputData.getExpanded() != null) {
            CustomFiledValue expanded = new CustomFiledValue(
                    expandedField.getExpandedField().getId(),
                    expandedField.getSettingByName(inputData.getExpanded()).getId());
            updateParams.customFieldOtherValue(expanded);
        }

        doUpdate(updateParams);
    }

    public void doUpdate(UpdateIssueParams updateParams) throws Exception{
        BacklogConfigure configure = new BacklogJpConfigure(CertificationInfo.SPACE_ID).apiKey(CertificationInfo.API_KEY);
        BacklogClient backlog = new BacklogClientFactory(configure).newClient();
        backlog.updateIssue(updateParams);
    }
}
