package solver;

import inputs.ExamBean;
import inputs.StudentBean;
import utils.ExamsParser;

import java.util.*;

public class MatchingSolver {

    private final List<StudentBean.Student> students;
    private final List<ExamBean.Exam> exams;
    private final Map<Integer, List<Integer>> studentToExamList;
    private final Map<Integer, List<Integer>> examsGraph;
    private final List<List<Integer>> connectedComponents;
    private final List<Set<Integer>> repartition;

    private Set<Integer> visited;
    private List<Integer> component;

    public MatchingSolver(List<StudentBean.Student> students, List<ExamBean.Exam> exams) {

        this.students = students;
        this.exams = exams;

        examsGraph = new HashMap<>();
        studentToExamList = new HashMap<>();
        connectedComponents = new ArrayList<>();
        repartition = new ArrayList<>();
    }

    public List<Set<Integer>> solve() {

        prepareMap();
        buildGraph();
        computeConnectedComponents();

        return buildSolution();
    }

    private void prepareMap() {

        for(StudentBean.Student student: students) {
            studentToExamList.put(student.getId(), ExamsParser.getExamIdsForStudent(student.getExams()));
        }
    }

    private void buildGraph() {

        for(ExamBean.Exam exam1: exams) {
            for(ExamBean.Exam exam2: exams) {

                if(exam1.getId() >= exam2.getId()) continue;

                int startingHourInMinutesForExam1 = exam1.getStartHour() * 60;
                int startingHourInMinutesForExam2 = exam2.getStartHour() * 60;

                int endingHourInMinutesForExam1 = startingHourInMinutesForExam1 + exam1.getDuration();
                int endingHourInMinutesForExam2 = startingHourInMinutesForExam2 + exam2.getDuration();

                if(doExamsOverlap(startingHourInMinutesForExam1, endingHourInMinutesForExam1,
                        startingHourInMinutesForExam2, endingHourInMinutesForExam2)) {

                    if(existStudentWithThisExams(exam1.getId(), exam2.getId())) {

                        addToGraphEdge(exam1.getId(), exam2.getId());
                        addToGraphEdge(exam2.getId(), exam1.getId());
                    }
                }
            }
        }
    }

    private void addToGraphEdge(Integer examId1, Integer examId2) {

        if(!examsGraph.containsKey(examId1)) {

            examsGraph.put(examId1, new ArrayList<>());
        }

        examsGraph.get(examId1).add(examId2);
    }

    private boolean doExamsOverlap(int startExam1, int stopExam1, int startExam2, int stopExam2) {

        if(startExam2 <= startExam1 && startExam1 <= stopExam2) {

            return true;
        }

        return startExam1 <= startExam2 && startExam2 <= stopExam1;
    }

    private boolean existStudentWithThisExams(Integer examId1, Integer examId2) {

        for (StudentBean.Student student : students) {

            Integer studentId = student.getId();

            if (!studentToExamList.containsKey(studentId)) {

                continue;
            }

            if (studentToExamList.get(studentId).contains(examId1) &&
                    studentToExamList.get(studentId).contains(examId2)) {

                return true;
            }
        }

        return false;
    }

    private void computeConnectedComponents() {

        visited = new HashSet<>();
        for(ExamBean.Exam exam: exams) {

            if(!visited.contains(exam.getId())) {

                component = new ArrayList<>();
                DFS(exam.getId());

                connectedComponents.add(component);
            }
        }
    }

    private void DFS(Integer nodeId) {

        visited.add(nodeId);
        component.add(nodeId);

        if(!examsGraph.containsKey(nodeId)) return;

        for(Integer nextId: examsGraph.get(nodeId)) {

            if(!visited.contains(nextId)) {
                DFS(nextId);
            }
        }
    }

    private List<Set<Integer>> buildSolution() {

        visited = new HashSet<>();

        int totalExamsSolved = 0;
        int[] totalVisitedPerComponent = new int[connectedComponents.size()];

        while(totalExamsSolved < exams.size()) {

            Set<Integer> currentDayRepartition = new HashSet<>();

            for(int componentIdx = 0; componentIdx < connectedComponents.size(); componentIdx++) {

                if(totalVisitedPerComponent[componentIdx] == connectedComponents.get(componentIdx).size()) {

                    continue;
                }

                component = connectedComponents.get(componentIdx);
                Set<Integer> currentVisited = new HashSet<>();
                boolean flag = true;
                while (flag) {

                    flag = false;
                    int minimumEdges = component.size() + 1;
                    int minimumExamId = -1;
                    for(Integer examId: component) {

                        if(!visited.contains(examId) && !currentVisited.contains(examId)) {

                            int toCompare = 0;

                            if(examsGraph.containsKey(examId)) {

                                toCompare = examsGraph.get(examId).size();
                            }

                            if(minimumEdges > toCompare) {

                                flag = true;

                                if(toCompare > 0) {

                                    for(Integer neighbourExamId: examsGraph.get(examId)) {

                                        if(currentVisited.contains(neighbourExamId)) {

                                            flag = false;
                                            break;
                                        }
                                    }
                                }

                                if(flag) {

                                    minimumEdges = toCompare;
                                    minimumExamId = examId;
                                }
                            }
                        }
                    }

                    if(minimumExamId != -1) {

                        currentVisited.add(minimumExamId);
                    }
                }

                visited.addAll(currentVisited);
                currentDayRepartition.addAll(currentVisited);
                totalVisitedPerComponent[componentIdx] += currentVisited.size();
                totalExamsSolved += currentVisited.size();
            }

            repartition.add(currentDayRepartition);
        }

        return repartition;
    }

    private static class Pair<K, V> {

        private final K key;
        private final V value;

        public Pair(K key, V value) {

            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
