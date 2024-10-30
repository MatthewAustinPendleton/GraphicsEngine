import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CoordinateSystem extends Shape {
    private float size;

    public CoordinateSystem(float size) {
        this.size = size;
        createAxes();
    }

    private void createAxes() {
        // Clear existing vertices / edges
        vertices.clear();
        edges.clear();

        // Create vertices for each axis
        // x-axis vertices
        addVertex(new Vector3(-size, 0, 0));
        addVertex(new Vector3(size, 0, 0));

        // y-axis vertices
        addVertex(new Vector3(0, -size, 0));
        addVertex(new Vector3(0, size, 0));

        // z-axis vertices
        addVertex(new Vector3(0, 0, -size));
        addVertex(new Vector3(0, 0, size));

        // create edges for the axes!
        // x-axis edge
        addEdge(0, 1);

        // y-axis edge
        addEdge(2, 3);

        // z-axis edge
        addEdge(4, 5);
    }

    public List<Color> getAxisColors() {
        List<Color> axisColors = new ArrayList<>();
        axisColors.add(Color.RED);
        axisColors.add(Color.GREEN);
        axisColors.add(Color.BLUE);
        return axisColors;
    }
}