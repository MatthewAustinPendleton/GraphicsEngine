import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Runner {
    public static void main(String[] args) {
        // Create a scene
        Scene scene = new Scene();

        // Create coordinate system
        CoordinateSystem coordinateSystem = new CoordinateSystem(10.0f);
        scene.addShape(coordinateSystem);

        // Create shapes to display
        Shape cube = new Shape();
        cube.createCube(2.0f);
        cube.translate(-4, 0, 0);

        Shape pyramid = new Shape();
        pyramid.createPyramid(2.0f, 4.0f);
        pyramid.translate(4, 0, 0);

        Shape cylinder = new Shape();
        cylinder.createCylinder(1.5f, 3.0f, 25);
        cylinder.translate(0, -3.5f, 0);

        Shape sphere = new Shape();
        sphere.createSphere(2.5f, 25, 25);
        sphere.translate(0, 2, 0);

        // Add shapes to the scene
        scene.addShape(cube);
        scene.addShape(pyramid);
        scene.addShape(cylinder);
        scene.addShape(sphere);

        // Initialize the camera
        Camera camera = new Camera(new Vector3(0, 0, 10), 1.0f);
        CameraController cameraController = new CameraController(camera);

        // Get initial projected vertices and edges
        List<Vector3> projectedVertices = scene.getAllProjectedVertices(camera, 10);
        List<int[]> edges = scene.getAllEdges();

        // Create the wireframe viewer
        WireframeViewer viewer = new WireframeViewer(projectedVertices, edges, coordinateSystem.getAxisColors());
        JFrame frame = new JFrame("3D Wireframe Viewer");
        frame.add(viewer);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(cameraController);
        frame.addMouseMotionListener(cameraController);
        frame.setVisible(true);

        // Update the frame focus for input handling
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        Timer timer = new Timer(30, e -> {
            cameraController.update(0.03f);
            List<Vector3> updatedVertices = scene.getAllProjectedVertices(camera, 10);
            viewer.updateWireframe(updatedVertices, scene.getAllEdges());
            viewer.repaint();
        });
        timer.start();
    }
}
