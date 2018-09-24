package backlog;

import com.nulabinc.backlog4j.*;
import com.nulabinc.backlog4j.conf.BacklogConfigure;
import com.nulabinc.backlog4j.conf.BacklogJpConfigure;

import java.util.HashMap;


public class Versions{

    private final ResponseList<Version> versions;

    private HashMap<String, Version> versionMap = new HashMap<>();;

    public Versions(CertificationInfo certificationInfo) throws Exception{
        BacklogConfigure configure = new BacklogJpConfigure(certificationInfo.SPACE_ID).apiKey(certificationInfo.API_KEY);
        BacklogClient backlog = new BacklogClientFactory(configure).newClient();
        Project project = backlog.getProject(certificationInfo.PROJECT_KEY);
        versions = backlog.getVersions(project.getId());
        for (Version version : versions) {
            versionMap.put(version.getName(), version);
        }
    }

    public Version getVersionByName(String versionName) {
        return versionMap.get(versionName);
    }

}
