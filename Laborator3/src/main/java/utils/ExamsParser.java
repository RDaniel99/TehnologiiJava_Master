package utils;

import java.util.ArrayList;
import java.util.List;

public class ExamsParser {

    public static List<Integer> getExamIdsForStudent(String studentExams) {

        int currentExamId = 0;

        List<Integer> examsIds = new ArrayList<>();
        for(int currentChar = 0; currentChar < studentExams.length(); currentChar++) {
            if(studentExams.charAt(currentChar) != ',') {
                currentExamId = currentExamId * 10 + (int)(studentExams.charAt(currentChar)) - (int)('0');
            }
            else {
                examsIds.add(currentExamId);
                currentExamId = 0;
            }
        }

        if(currentExamId > 0) {

            examsIds.add(currentExamId);
        }

        return examsIds;
    }
}
