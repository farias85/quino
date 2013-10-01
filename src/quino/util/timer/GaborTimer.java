/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quino.util.timer;

import java.awt.Point;
import java.awt.geom.Line2D;
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
public class GaborTimer extends TimerTask {

    private int count = 0;
    private JLabel jLabel1;
    //Image Matrix
    private Mat mtx = new Mat(470, 460, CvType.CV_32SC1, new Scalar(0));
    private Point centro;
    //Inputs parameters
    private int fs = 60;                  // sampling frequency in frames / s
    //int nms = 500;                      // test duration miliseconds
    //double nf = nms * fs / 1000;        //number of frames
    private double contrat = 0.99;
    //central stimulus
    private double fspa_cpi_x = -1;                //spatial frequency in x, cicles / inch
    private double fspa_cpi_y = 0.0;               //spatial frequency in y, cicles / inch
    private double ftemp = 2;                      //temporal frequency in cicles / seconds (Hz)
    private double gaussianStdpix = 40;           //gaussian standar deviation [pixel]
    //periferal stimulis
    private double fspa_cpi_x_per = 0;             //spatial frequency in x, cicles / inch
    private double fspa_cpi_y_per = 1.0;           //spatial frequency in y, cicles / inch
    private double ftemp_per = 2;                  //temporal frequency in cicles / seconds (Hz)
    private int radio1 = 70;                       //internal radious
    private int radio2 = 150;                      //external radious
    private int ppi = 26;                          //screen pixels x inch
    private double fspa_cpp_x;                     //spatial frequency in x,cicles / pixels
    private double fspa_cpp_y;                     //spatial frequency in x,cicles / pixels
    private double fspa_cpp_x_per;                 //spatial frequency in x,cicles / pixels
    private double fspa_cpp_y_per;                 //spatial frequency in x,cicles / pixels
    private byte intensidadMedia = (byte) 128;
    private byte intensidadMax;

    public GaborTimer(JLabel jlabel1) {
        this.jLabel1 = jlabel1;

        centro = new Point(mtx.width() / 2, mtx.height() / 2);

        fspa_cpp_x = fspa_cpi_x / ppi;
        fspa_cpp_y = fspa_cpi_y / ppi;
        fspa_cpp_x_per = fspa_cpi_x_per / ppi;
        fspa_cpp_y_per = fspa_cpi_y_per / ppi;

        intensidadMax = (byte) (contrat * intensidadMedia);
    }

    @Override
    public void run() {

        for (int i = 0; i < mtx.cols(); i++) {
            double periodo = i / fs;

            //I1 = Imax* exp(- (( x - x0).^2 + (y - y0).^2) / (2*(gaussianStdpix).^2) );
            for (int j = 0; j < mtx.rows(); j++) {
                Point point = new Point(i, j);

                double distancia = Line2D.ptSegDist(point.x, point.y, point.x, point.y, centro.x, centro.y);

                if (distancia < radio2) {
                    double intensidad1 = 0;
                    intensidad1 = Math.pow(i - centro.x, 2) + Math.pow(j - centro.y, 2);
                    intensidad1 /= 2 * Math.pow(gaussianStdpix, 2);
                    intensidad1 = intensidadMax * Math.exp(-intensidad1);
                    intensidad1 *= Math.cos(2.0 * Math.PI * (fspa_cpp_x * (i + count)
                            + fspa_cpp_y * (j + count) + periodo * ftemp));

                    double intensidad2 = intensidadMedia + intensidadMax * Math.cos(2.0 * Math.PI * (fspa_cpp_x_per * (i + count)
                            + fspa_cpp_y_per * (j + count) + periodo * ftemp_per));

                    mtx.put(j, i, gaborLocation(point) ? (byte) intensidad1 : (byte) intensidad2);
                }
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

        System.out.println(count + " esto es una prueba");
        count++;
    }

    private boolean gaborLocation(Point point) {
        double distancia = Line2D.ptSegDist(point.x, point.y, point.x, point.y, centro.x, centro.y);
        return !(distancia > radio1 && distancia < radio2);
    }
}
