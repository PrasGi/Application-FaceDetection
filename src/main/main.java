/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import static org.opencv.objdetect.Objdetect.CASCADE_SCALE_IMAGE;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author REPUBLIC OF GAMERS
 */
public class main extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    public main() {
        initComponents();
    }
    
    String source = "D:\\Development\\ResourceCode\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml";
    CascadeClassifier faceDetector = new CascadeClassifier(source);

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wrfgg = new javax.swing.JPanel();
        button = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblnumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        wrfgg.setBackground(new java.awt.Color(255, 255, 255));

        button.setBackground(new java.awt.Color(102, 102, 102));
        button.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        button.setText("Start");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        lblnumber.setBackground(new java.awt.Color(255, 255, 255));
        lblnumber.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblnumber.setForeground(new java.awt.Color(51, 51, 51));
        lblnumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblnumber.setText("No Face Detec");

        javax.swing.GroupLayout wrfggLayout = new javax.swing.GroupLayout(wrfgg);
        wrfgg.setLayout(wrfggLayout);
        wrfggLayout.setHorizontalGroup(
            wrfggLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrfggLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wrfggLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(wrfggLayout.createSequentialGroup()
                        .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                        .addComponent(lblnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        wrfggLayout.setVerticalGroup(
            wrfggLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrfggLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wrfggLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wrfgg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wrfgg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        
        (new Thread(){
            public void run(){
                VideoCapture capture = new VideoCapture(0);//0 mean your default web cam
         //   VideoCapture capture = new VideoCapture("C:\Users\ccs\Desktop\videoplayback.mp4");
                MatOfRect rostros = new MatOfRect();
                MatOfByte mem = new MatOfByte();
                
                Mat frame = new Mat();
                Mat frame_gray = new Mat();
                
                Rect[] facesArray;      
                Graphics g;
                BufferedImage buff = null;

                
                while(capture.read(frame)){
                    if(frame.empty()){
                        System.out.println("No detection");
                        break;
                    }else{
                        try {
                            g = wrfgg.getGraphics();
                            Imgproc.cvtColor(frame, frame_gray, Imgproc.COLOR_BGR2GRAY);
                            Imgproc.equalizeHist(frame_gray, frame_gray);
                            double w = frame.width();
                            double h = frame.height();
                            faceDetector.detectMultiScale(frame_gray, rostros, 1.1, 2, 0|CASCADE_SCALE_IMAGE, new Size(30, 30), new Size(w, h) );
                            facesArray = rostros.toArray();
                            System.out.println("No of faces: "+facesArray.length);
                         
                            for (int i = 0; i < facesArray.length; i++) {
                               
                                Point center = new Point((facesArray[i].x + facesArray[i].width * 0.5), 
                                        (facesArray[i].y + facesArray[i].height * 0.5));
                                Imgproc.ellipse(frame, 
                                        center, 
                                        new Size(facesArray[i].width * 0.5, facesArray[i].height * 0.5), 
                                        0, 
                                        0, 
                                        360, 
                                        new Scalar(255, 0, 255), 4, 8, 0);
                             
                                Mat faceROI = frame_gray.submat(facesArray[i]);
                                Imgproc.rectangle(frame,
                                        new Point(facesArray[i].x,facesArray[i].y),
                                        new Point(facesArray[i].x+facesArray[i].width,facesArray[i].y+facesArray[i].height),
                                        new Scalar(123, 213, 23, 220));
                              //  Imgproc.putText(frame, "Width: "+faceROI.width()+" Height: "+faceROI.height()+" X = "+facesArray[i].x+
                               //         " Y = "+facesArray[i].y, new Point(facesArray[i].x, facesArray[i].y-20), 1, 1, new Scalar(255,255,255));
                                Imgproc.putText(frame, "This is human "
                                     , new Point(facesArray[i].x, facesArray[i].y-20), 1, 1, new Scalar(255,255,255));
                            }
                           
                            int no= facesArray.length;
                            lblnumber.setText(String.valueOf(no));
                            
                             Imgcodecs.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            buff = (BufferedImage) im;
                            if(g.drawImage(buff, 0, 0, wrfgg.getWidth(), wrfgg.getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null)){
                            }
                            
                            
                            
                        } catch (Exception ex) {
                   
                        }
                    }
                    
                }
            }
            
        }).start();
    }//GEN-LAST:event_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblnumber;
    private javax.swing.JPanel wrfgg;
    // End of variables declaration//GEN-END:variables
}
