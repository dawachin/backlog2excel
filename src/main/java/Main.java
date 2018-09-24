import backlog.*;
import backlog.service.UpdateIssueService;
import com.nulabinc.backlog4j.*;
import com.nulabinc.backlog4j.api.option.GetIssuesParams;
import excel.Input;
import excel.RowTemplate;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception{

        CertificationInfo certificationInfo = new CertificationInfo();

        // 課題の種類取得
        IssueTypes issueTypes = new IssueTypes(certificationInfo);
        IssueType issueType = issueTypes.getIssueTypeByName("故障");

        // Versionの取得
        Versions versions = new Versions(certificationInfo);
        Version version = versions.getVersionByName("backlog4excel");

        // 課題取得条件
        GetIssuesParams issuesParams = GetIssuesParamsFactory.getIssuesParamsImp(certificationInfo);
        issuesParams.count(100);
        issuesParams.issueTypeIds(Arrays.asList(issueType.getId()));
        issuesParams.milestoneIds(Arrays.asList(version.getId()));

        // 課題取得
        Issues issues = new Issues(certificationInfo, issuesParams);
        System.out.println(issues);

        // Excel出力
//        Output output = new Output(issues);
//        output.excel();

        // Excel入力
        UpdateIssueService issueUpdateService = new UpdateIssueService();

        Input input = new Input();
        for (RowTemplate inputData : input.excel()) {
            issueUpdateService.doUpdate(inputData);
        }

    }
}
