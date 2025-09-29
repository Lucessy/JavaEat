package com.utilidad;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Productos {
    
    
    
    public static ImageIcon resizedIcon(int width, int height, String path) {
	if (width == 0) {
	    width = 330;
	}
	if (height == 0) {
	    height = 330;
	}
	BufferedImage img;
	ImageIcon imageIcon = null;
	try {
	    img = ImageIO.read(new File(path));
	    Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    imageIcon = new ImageIcon(dimg);
	} catch (IOException e) {
	    try {
		ImageIcon imagen = new ImageIcon(Productos.class.getResource(path));
		imageIcon = new ImageIcon(imagen.getImage().getScaledInstance(width, height, 1));
	    } catch (Exception error) {
		System.out.println("Error: ninguna imagen encontrada con path:" + path);
	    }
	}

	return imageIcon;
    
}
}