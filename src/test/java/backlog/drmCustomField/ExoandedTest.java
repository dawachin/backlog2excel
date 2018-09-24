package backlog.drmCustomField;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExoandedTest {

    @Test
    public void test_1() throws Exception{
        Expanded expandedField = new Expanded();
        assertAll("横展開有無",
                () -> assertEquals("有", expandedField.getAvailableSetting().getName()),
                () -> assertEquals("無", expandedField.getNotAvailableSetting().getName())
        );
    }
}
