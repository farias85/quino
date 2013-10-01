/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

/**
 *
 * @author farias
 */
public class EnrejadoTimer extends TimerTask {

    private JLabel jLabel1;
    private int fs;                     // sampling frequency in frames / s
    //private int nms;                    // test duration miliseconds
    //private int nf;                     // number of frames
    private double fspa_cpi_x;          // spatial frequency in x, cicles / inch
    private double fspa_cpi_y;          // spatial frequency in y, cicles / inch
    private int ppi;                    //screen pixels x inch
    private double fspa_cpp_x;          // spatial frequency in x,cicles / pixels
    private double fspa_cpp_y;          // spatial frequency in x,cicles / pixels
    private Mat mtx = new Mat(470, 660, CvType.CV_32SC1, new Scalar(255));
    private int count = 0;

    public EnrejadoTimer(JLabel jLabel1) {
        this.jLabel1 = jLabel1;

        fs = 90;
        //nms = 500;
        //nf = (int)(nms * fs / 1000);

        fspa_cpi_x = 6;
        fspa_cpi_y = 1.0;

        ppi = 96;

        fspa_cpp_x = fspa_cpi_x / ppi;
        fspa_cpp_y = fspa_cpi_y / ppi;
    }

    @Override
    public void run() {
        for (int i = 0; i < mtx.cols(); i++) {
            double periodo = i / fs;

            for (int j = 0; j < mtx.rows(); j++) {
                double intensidad = 128 + 40.8
                        * Math.cos(2.0 * Math.PI * (fspa_cpp_x * (i + count)
                        + fspa_cpp_y * (j + count) + periodo));

                mtx.put(j, i, (byte) intensidad);
            }
        }

        MatOfByte matOfByte = new MatOfByte();
        Highgui.imencode(".jpg", mtx, matOfByte);

        byte[] byteArray = matOfByte.toArray();

        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            jLabel1.setIcon(new ImageIcon(ImageIO.read(in)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(count + " enrejado");
        count++;
    }
}
