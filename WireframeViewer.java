import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WireframeViewer extends JPanel {
    private List<Vector3> projectedVertices;
    private List<int[]> edges;

    public WireframeViewer(List<Vector3> projectedVertices, List<int[]> edges) {
        this.projectedVertices = projectedVertices;
        this.edges = edges;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        // Draw each edge of the wireframe
        for (int[] edge : edges) {
            int startVertexIndex = edge[0];
            int endVertexIndex = edge[1];

            Vector3 startVertex = projectedVertices.get(startVertexIndex);
            Vector3 endVertex = projectedVertices.get(endVertexIndex);

            int x1 = (int) (startVertex.getX() * 50) + 300; // Scale and center
            int y1 = (int) (startVertex.getY() * 50) + 300;
            int x2 = (int) (endVertex.getX() * 50) + 300;
            int y2 = (int) (endVertex.getY() * 50) + 300;

            g.drawLine(x1, y1, x2, y2);
        }
    }

    public static void display(List<Vector3> projectedVertices, List<int[]> edges) {
        JFrame frame = new JFrame("3D Wireframe Viewer");
        WireframeViewer panel = new WireframeViewer(projectedVertices, edges);
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
