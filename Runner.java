import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Runner {
    public static int getRandomNumber(int a, int b) {
        return (int)(Math.random() * (b - a + 1)) + a;
    }

    public static void main(String[] args) {
        // Create a scene
        Scene scene = new Scene();

        // Create coordinate system
        CoordinateSystem coordinateSystem = new CoordinateSystem(10.0f);
        scene.addShape(coordinateSystem);

        // Crate the shapes to display
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

        // Add the shapes to the scene
        scene.addShape(cube);
        scene.addShape(pyramid);
        scene.addShape(cylinder);
        scene.addShape(sphere);

        // Get initial projected vertices/edges
        List<Vector3> allProjectedVertices = scene.getAllProjectedVertices(10);
        List<int[]> allEdges = scene.getAllEdges();
        List<Color> axisColors = coordinateSystem.getAxisColors();

        // Create the wireframe viewer
        WireframeViewer viewer = new WireframeViewer(allProjectedVertices, allEdges, axisColors);
        JFrame frame = new JFrame("3D Wireframe Viewer");
        frame.add(viewer);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        startRotation(sphere, 0.0f, 0.0f, 1.0f, 4, scene, viewer);
        startRotation(cube, 1.0f, 0.0f, 0.0f, 6, scene, viewer);
        startRotation(cylinder, 0.0f, 1.0f, 0.0f, 10, scene, viewer);
        startRotation(pyramid, 0.0f, 1.0f, 0.0f, 20, scene, viewer);

    }

    public static void startRotation(Shape shape, float rotationX, float rotationY, float rotationZ, int delay, Scene scene,
                                     WireframeViewer viewer) {
        // Create a Timer to update rotation continuously
        Timer timer = new Timer(delay, e -> {
            // Rotate the cube continuously
            shape.rotate(rotationX, rotationY, rotationZ);

            // Get all the projected vertices and edges
            List<Vector3> updatedProjectedVertices = scene.getAllProjectedVertices(10);
            List<int[]> updatedEdges = scene.getAllEdges();

            // Update the viewer
            viewer.updateWireframe(updatedProjectedVertices, updatedEdges);
            viewer.repaint();
        });

        // Start the timer
        timer.start();
    }

}
