package Models;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PanelConFondo extends JPanel {
    private Image imagen;

    public PanelConFondo(String fileName) {
        try {

            imagen = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen de fondo
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

