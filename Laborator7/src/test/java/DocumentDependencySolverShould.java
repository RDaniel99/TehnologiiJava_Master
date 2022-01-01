import org.junit.jupiter.api.Test;
import services.DocumentsDependencySolver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentDependencySolverShould {

    @Test
    public void solveCorrectly() {

        // setup
        DocumentsDependencySolver solver = new DocumentsDependencySolver();
        solver.addId(1);
        solver.addId(2);
        solver.addId(3);
        solver.addEdge(1, 2);
        solver.addEdge(1, 3);
        solver.addEdge(2, 3);

        // execute
        int[] solution = solver.solve();

        // verify
        assertEquals(3, solution[0]);
        assertEquals(2, solution[1]);
        assertEquals(1, solution[2]);
    }
}
