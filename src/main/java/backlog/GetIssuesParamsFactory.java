package backlog;

import com.nulabinc.backlog4j.BacklogClient;
import com.nulabinc.backlog4j.BacklogClientFactory;
import com.nulabinc.backlog4j.Project;
import com.nulabinc.backlog4j.api.option.GetIssuesParams;
import com.nulabinc.backlog4j.conf.BacklogConfigure;
import com.nulabinc.backlog4j.conf.BacklogJpConfigure;

import java.util.Arrays;

public class GetIssuesParamsFactory {

    public static GetIssuesParams getIssuesParamsImp(CertificationInfo certificationInfo) throws Exception{
        BacklogConfigure configure = new BacklogJpConfigure(certificationInfo.SPACE_ID).apiKey(certificationInfo.API_KEY);
        BacklogClient backlog = new BacklogClientFactory(configure).newClient();
        Project project = backlog.getProject(certificationInfo.PROJECT_KEY);
        return new GetIssuesParams(Arrays.asList(project.getId()));

    }

}
