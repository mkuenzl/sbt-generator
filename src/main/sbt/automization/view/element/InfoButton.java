package sbt.automization.view.element;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

//<div>Icons made by <a href="https://www.flaticon.com/authors/apien" title="apien">apien</a> from <a href="https" +
//		"://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>

public class InfoButton
        extends CustomButton
{
    private final InfoTexts infoTexts;
    private final boolean withQuestionMarkIcon;

    public InfoButton(String text, Rectangle position, InfoTexts infoTexts, boolean withQuestionMarkIcon)
    {
        super(text, position);
        addListener();
        this.withQuestionMarkIcon = withQuestionMarkIcon;
        if (this.withQuestionMarkIcon)
        {
            this.setBorder(new LineBorder(Color.white));
            setImage("/icons/question-mark-icon.png");
            this.setOpaque(false);
            this.setContentAreaFilled(false);
            this.setBorderPainted(false);
        }
        this.infoTexts = infoTexts;
    }

    private void setImage(String path)
    {
        ImageIcon infoIcon = new ImageIcon(getClass().getResource(path));
        Image scaledInstance = infoIcon.getImage()
                                       .getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon infoIconScaled = new ImageIcon(scaledInstance);
        this.setIcon(infoIconScaled);
    }

    private void addListener()
    {
        this.addActionListener(e -> new InfoDialog(infoTexts));
    }


    @Override
    public void mouseEntered(MouseEvent e)
    {
        if (e.getSource() == this && this.withQuestionMarkIcon)
        {
            this.setOpaque(false);
            this.setContentAreaFilled(true);
            this.setBorderPainted(true);
            this.setBackground(this.hoverColor);
        }
        else
        {
            super.mouseEntered(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        if (e.getSource() == this && this.withQuestionMarkIcon)
        {
            this.setOpaque(false);
            this.setContentAreaFilled(false);
            this.setBorderPainted(false);
            this.setBackground(this.backgroundColor);
        }
        else
        {
            super.mouseExited(e);
        }
    }
}
