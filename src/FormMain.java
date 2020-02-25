import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by User on 4/1/2016.
 */
public class FormMain {
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton goButton;
    private JSpinner spinner1;
    private JButton LEFTMOUSEButton;
    private JCheckBox checkBox1;
    private JCheckBox chkCSGO;
    private TextTransfer clipboard = new TextTransfer();
    private String textBuffer = "";
    private int sleepBuffer = 100;
    public FormMain() {
        goButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    textBuffer = textArea1.getText();
                    sleepBuffer = (int) spinner1.getValue();
                    do {
                        Thread.sleep(sleepBuffer);
                        String block = textBuffer.length()>=140?textBuffer.substring(0,140) : textBuffer;
                        do {
                            block=block.substring(0,block.length()-2);
                        } while (!block.endsWith(" "));
                        if (chkCSGO.isSelected()){
                            block = "say " + block;
                        }
                        clipboard.setClipboardContents(block);
                        textBuffer=textBuffer.substring(block.length());

                        try
                        {
                            Robot r = new Robot();
                            r.keyPress(KeyEvent.VK_CONTROL);
                            r.keyPress(KeyEvent.VK_V);
                            r.keyRelease(KeyEvent.VK_CONTROL);
                            r.keyRelease(KeyEvent.VK_V);
                            r.keyPress(KeyEvent.VK_ENTER);
                            r.keyRelease(KeyEvent.VK_ENTER);
                        }
                        catch(Exception ee) {}
                    } while (textBuffer.length()>1);
                } catch (InterruptedException ie) {
                    //Handle exception
                }
            }
        });
        LEFTMOUSEButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Robot r = new Robot();
                    while (checkBox1.isSelected()) {
                        Thread.sleep(1000);
                        r.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                    }
                } catch (Exception ev) {
                    ev.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FormMain");
        frame.setContentPane(new FormMain().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
