package christmas.service;

import christmas.Constants;

public class UserService {
    public Integer getVisitDate(String visitDate) {
        validateVisitDate(visitDate);

        return Integer.parseInt(visitDate);
    }

    private void validateVisitDate(String visitDate) {
        try {
            int date = Integer.parseInt(visitDate);

            if (date < 1 || date > 31) {
                throw new IllegalArgumentException(Constants.VISIT_DATE_ERROR);
            }
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(Constants.VISIT_DATE_ERROR);
        }
    }
}
