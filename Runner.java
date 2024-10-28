import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Shape unitCube = new Shape();
        Shape unitCube2 = new Shape();
        Shape unitCube3 = new Shape();

        unitCube.createCube(2.0f);
        unitCube2.createCube(3.0f);
        unitCube3.createCube(1.0f);

        unitCube.translate(0, 0, -5);
        unitCube2.translate(0, 0, -4);
        unitCube3.translate(0, 1, -3);
        unitCube.rotateAlongX(30);
        unitCube.rotateAlongY(45);
        unitCube2.rotateAlongX(25);
        unitCube2.rotateAlongZ(10);
        unitCube3.rotateAlongX(45);

        // Project cube to 2D screen
        List<Vector3> projectedVertices = unitCube.perspectiveProject(10);
        List<Vector3> projectedVertices2 = unitCube2.perspectiveProject(10);
        List<Vector3> projectedVertices3 = unitCube3.perspectiveProject(10);

        // Combine projected vertices and edges of all cubes
        List<Vector3> allVertices = new ArrayList<>();
        List<int[]> allEdges = new ArrayList<>();

        // Combine shapes
        ShapeUtilities.combineShapes(allVertices, allEdges, projectedVertices, unitCube.getEdges(), 0);
        ShapeUtilities.combineShapes(allVertices, allEdges, projectedVertices2, unitCube2.getEdges(), projectedVertices.size());
        ShapeUtilities.combineShapes(allVertices, allEdges, projectedVertices3, unitCube3.getEdges(), projectedVertices2.size());

        // Display wireframe
        WireframeViewer.display(allVertices, allEdges);
    }
    public static int getRandomNumber(int a, int b) {
        return (int)(Math.random() * (b - a + 1)) + a;
    }
}
