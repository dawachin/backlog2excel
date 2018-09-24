package backlog;

import com.nulabinc.backlog4j.*;
import com.nulabinc.backlog4j.conf.BacklogConfigure;
import com.nulabinc.backlog4j.conf.BacklogJpConfigure;

import java.util.HashMap;

/**
 *
 * User全情報取得
 *
 * ※このメソッドを実行するためにはプロジェクトの管理者権限が付与されている必要があります。
 *
 * */
public class Users {

    private final ResponseList<User> users;
    private HashMap<String, User> userHashMap = new HashMap<>();

    public Users() throws Exception{
        CertificationInfo certificationInfo = new CertificationInfo();
        BacklogConfigure configure = new BacklogJpConfigure(certificationInfo.SPACE_ID).apiKey(certificationInfo.API_KEY);
        BacklogClient backlog = new BacklogClientFactory(configure).newClient();
        this.users = backlog.getUsers();
        users.stream().forEach(user -> {userHashMap.put(user.getName(), user);});
    }

    public User getUserByName(String userName) {
        return userHashMap.get(userName);
    }

    /**
     *
     * User情報取得
     *
     * @deprecated 管理者権限がないので一時的にUserIdから同定出来るメソッドを用意した。
     * @param userId
     * @return
     */
    public static User getUserByUserId(String userId) throws Exception{
        CertificationInfo certificationInfo = new CertificationInfo();
        BacklogConfigure configure = new BacklogJpConfigure(certificationInfo.SPACE_ID).apiKey(certificationInfo.API_KEY);
        BacklogClient backlog = new BacklogClientFactory(configure).newClient();
        return backlog.getUser(userId);
    }


}
