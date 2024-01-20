package sbt.automization.view.element;

import javax.swing.*;
import java.awt.*;

public class InfoDialog
        extends JDialog
{

    public InfoDialog(InfoTexts infoTexts)
    {
        this.setTitle("Help");
        this.setSize(1000, 600);

        JPanel dialogPanel = new JPanel();
        dialogPanel.setSize(1000, 600);
        dialogPanel.setBackground(Color.WHITE);

        JTextArea infoText = new JTextArea();
        infoText.setBounds(10, 10, 980, 580);
        infoText.setEditable(false);
        infoText.setWrapStyleWord(true);
        infoText.setLineWrap(true);
        infoText.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
        infoText.setText(infoTexts.getInfoText());
        infoText.setVisible(true);
        dialogPanel.add(infoText);
        this.add(dialogPanel);
        Dimension dim = Toolkit.getDefaultToolkit()
                               .getScreenSize();

        // calculate the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;

        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        this.setLocation(x, y);
        this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }


}
