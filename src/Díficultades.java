
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Díficultades {

    public void medio() {
        JFrame frame = new JFrame("Moya Bricks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton start = new JButton("Iniciar");
        BlockBreakerPanel panel = new BlockBreakerPanel(frame, frame);
        start.addActionListener(listener -> {
            frame.setVisible(true);
        });
       
        frame.getContentPane().add(panel);

        frame.setVisible(false);
        frame.setSize(490, 600);

        frame.setResizable(false);
    }

}
