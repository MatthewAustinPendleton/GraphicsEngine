import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WireframeViewer extends JPanel {
    private List<Vector3> projectedVertices;
    private List<int[]> edges;
    private List<Color> edgeColors;

    public WireframeViewer(List<Vector3> projectedVertices, List<int[]> edges, List<Color> edgeColors) {
        this.projectedVertices = projectedVertices;
        this.edges = edges;
        this.edgeColors = edgeColors;
    }

    public void updateWireframe(List<Vector3> vertices, List<int[]> edges) {
        this.projectedVertices = vertices;
        this.edges = edges;
    }

    public void updateEdgeColors(List<Color> colors) {
        this.edgeColors = colors;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        // Draw each edge of the wireframe
        for (int i = 0; i < edges.size(); i++) {
            int[] edge = edges.get(i);
            int startVertexIndex = edge[0];
            int endVertexIndex = edge[1];

            Vector3 startVertex = projectedVertices.get(startVertexIndex);
            Vector3 endVertex = projectedVertices.get(endVertexIndex);

            int x1 = (int) (startVertex.getX() * 50) + centerX;
            int y1 = (int) (startVertex.getY() * 50) + centerY;
            int x2 = (int) (endVertex.getX() * 50) + centerX;
            int y2 = (int) (endVertex.getY() * 50) + centerY;

            // Set color for the current edge
            if (i < edgeColors.size()) {
                g2d.setColor(edgeColors.get(i));
            }
            else {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawLine(x1, y1, x2, y2);
        }
    }
}
