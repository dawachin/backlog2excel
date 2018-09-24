package backlog;

import com.nulabinc.backlog4j.*;
import com.nulabinc.backlog4j.conf.BacklogConfigure;
import com.nulabinc.backlog4j.conf.BacklogJpConfigure;

import java.util.HashMap;

public class IssueTypes {

    private final ResponseList<IssueType> issueTypes;
    private HashMap<String, IssueType> issueHashMap = new HashMap<>();

    public IssueTypes(CertificationInfo certificationInfo) throws Exception{
        BacklogConfigure configure = new BacklogJpConfigure(certificationInfo.SPACE_ID).apiKey(certificationInfo.API_KEY);
        BacklogClient backlog = new BacklogClientFactory(configure).newClient();
        Project project = backlog.getProject(certificationInfo.PROJECT_KEY);
        issueTypes = backlog.getIssueTypes(project.getId());
        for (IssueType issueType : issueTypes){
            issueHashMap.put(issueType.getName(), issueType);
        }
    }

    public IssueType getIssueTypeByName(String issueTypeName){
        return issueHashMap.get(issueTypeName);
    }
}
