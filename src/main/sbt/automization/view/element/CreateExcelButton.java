package sbt.automization.view.element;

import sbt.automization.core.util.FileExport;
import sbt.automization.view.ViewConstant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateExcelButton extends CustomButton{
    public CreateExcelButton(String text) {
        super(text);
        addListener();
    }

    public CreateExcelButton(String text, Rectangle position) {
        super(text, position);
        addListener();
    }

    private void addListener()
    {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String fileName = "/datenbank-template.xlsx";
                    FileExport.copyFileToUserDirectory(fileName);

                    ViewConstant.pathComponent.setText(System.getProperty("user.dir").concat(fileName));
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
