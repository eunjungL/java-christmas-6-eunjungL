package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.Constants;

public class InputView {
    public String getVisitDate() {
        System.out.println(Constants.GET_VISIT_DATE_COMMENT);
        return Console.readLine();
    }
}
