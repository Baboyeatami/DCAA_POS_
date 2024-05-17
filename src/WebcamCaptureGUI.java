
import dcaa_pos_.DBConnection;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.CvType;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Rect;
import org.opencv.core.Rect2d;
import org.opencv.core.Point;
import org.opencv.core.CvType;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfInt;
import org.opencv.videoio.VideoCapture;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class WebcamCaptureGUI {

    private VideoCapture videoCapture;
    private Mat currentFrame;

    public static void main(String[] args) {
        new WebcamCaptureGUI().initialize();
    }

    public void initialize() {
        // Load the OpenCV native library
        System.load("C:\\Users\\Jamie\\Downloads\\opencv\\build\\java\\x64\\opencv_java3416.dll");

        // Initialize video capture
        videoCapture = new VideoCapture(0); // 0 for default camera

        if (!videoCapture.isOpened()) {
            System.out.println("Error: Camera not detected!");
            return;
        }

        // Create the main frame
        JFrame frame = new JFrame("Webcam Capture");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        JButton captureButton = new JButton("Capture");
        buttonPanel.add(captureButton);

        // Add action listener to the capture button
        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                captureImage();
            }
        });

        // Create a panel for displaying video feed
        VideoPanel videoPanel = new VideoPanel();
        frame.add(videoPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Set up a timer to continuously update the video feed
        javax.swing.Timer timer = new javax.swing.Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateVideoFeed(videoPanel);
            }
        });
        timer.start();

        // Set frame properties
        frame.setSize(640, 480);
        frame.setVisible(true);
    }

    private void updateVideoFeed(VideoPanel videoPanel) {
        // Read a frame from the webcam
        currentFrame = new Mat();
        videoCapture.read(currentFrame);

        // Update the video panel with the current frame
        videoPanel.updateImage(currentFrame);
    }

    private void captureImage() {
        if (currentFrame != null) {
            try {
                // Save the captured image to the database

                String imagePath = "captured_image.png";
                System.out.println("Image saved: " + System.getProperty("user.dir"));

                Imgcodecs.imwrite(System.getProperty("user.dir") + "\\image.png", currentFrame);

                System.out.println("Image saved: " + System.getProperty("user.dir"));

                System.out.println("capture");
                saveImageToDatabase(currentFrame);
            } catch (SQLException ex) {
                Logger.getLogger(WebcamCaptureGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public BufferedImage matToBufferedImage(Mat mat) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".png", mat, matOfByte);
        byte[] byteArray = matOfByte.toArray();

        try {
            return ImageIO.read(new ByteArrayInputStream(byteArray));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] convertMatToByteArray(Mat mat) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".png", mat, matOfByte);
        return matOfByte.toArray();
    }

    private static byte[] readImageBytes(String imagePath) {
        try {
            Path path = Paths.get(imagePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveImageToDatabase(Mat image) throws SQLException {
        try {
            // Implement database connectivity and insertion logic here
            // Use JDBC to connect to your MySQL database and store the image data
            // Make sure to handle exceptions and close the database connection properly

            DBConnection.ReadIPaddress();
            DBConnection.init();
            PreparedStatement ps;
            ResultSet rs;
            Connection c = DBConnection.getConnection();

            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\image.png");

            ps = c.prepareStatement("Update student_info set image_data=?  where Student_ID=?");
            ps.setBinaryStream(1, fileInputStream, fileInputStream.available());
            ps.setString(2, "200");

            System.out.println(ps.execute());

        } catch (IOException ex) {
            Logger.getLogger(WebcamCaptureGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Custom JPanel to display the video feed
    private class VideoPanel extends JPanel {

        private Mat currentFrame;

        public void updateImage(Mat newFrame) {
            this.currentFrame = newFrame;
            repaint();
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);

            if (currentFrame != null) {
                // Convert the OpenCV Mat to BufferedImage for display
                MatOfByte matOfByte = new MatOfByte();
                Imgcodecs.imencode(".jpg", currentFrame, matOfByte);
                byte[] byteArray = matOfByte.toArray();

                try {
                    java.awt.Image img = javax.imageio.ImageIO.read(new java.io.ByteArrayInputStream(byteArray));
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
