package backlog;

import com.nulabinc.backlog4j.Issue;

public enum IssueStatusType {

    Open(1, Issue.StatusType.Open, "未対応"),
    InProgress(2, Issue.StatusType.InProgress, "処理中"),
    Resolved(3, Issue.StatusType.Resolved, "処理済み"),
    Closed(4, Issue.StatusType.Closed, "完了");

    IssueStatusType(int intValue, Issue.StatusType statusType, String label) {
        this.intValue = intValue;
        this.statusType = statusType;
        this.label = label;
    }

    public int getIntValue() {
        return intValue;
    }

    public Issue.StatusType getStatusType() {
        return statusType;
    }

    public String getLabel() {
        return label;
    }

    public static IssueStatusType valueOf(final int anIntValue) {
        for (IssueStatusType d : values()) {
            if (d.getIntValue() == anIntValue) {
                return d;
            }
        }
        return null;
    }

    public static Issue.StatusType labelOf(String label) {
        for (IssueStatusType d : values()) {
            if (d.getLabel().equals(label)) {
                return d.getStatusType();
            }
        }
        return null;
    }

    private int intValue;
    private Issue.StatusType statusType;
    private String label;
}
