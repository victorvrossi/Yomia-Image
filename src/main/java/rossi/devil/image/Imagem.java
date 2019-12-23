package rossi.devil.image;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Imagem {

    private BufferedImage imageFromFile;

    private int widthDefault = 480;
    private int heightDefault = 480;

    public void carregaImagem(String caminho) throws IOException {
        if(!caminho.contains(".")){
            caminho +=".jpg";
        }
        imageFromFile = ImageIO.read(new File(caminho));
    }
    
    public void alteraDimensao(int widthDefault ,int heightDefault ,URL resource){
        try {
            imageFromFile = ImageIO.read(resource);
            this.widthDefault = widthDefault;
            this.heightDefault = heightDefault;
        } catch (IOException ex) {
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ImageIcon geraImagemParaExibicao() throws IOException {
        return geraImagemIcone(novaLargura(), novaAltura());
    }

    private AffineTransform redimendionaImagem(double imageWidth, double imageHeight) {
        return AffineTransform.getScaleInstance(imageWidth, imageHeight);
    }

    private ImageIcon geraImagemIcone(double imageWidth, double imageHeight) {
        BufferedImage imageDestiny = new BufferedImage(widthDefault, heightDefault, BufferedImage.TYPE_INT_RGB);
        reescreveImagem(imageDestiny, redimendionaImagem(imageWidth, imageHeight));
        return new ImageIcon(imageDestiny);
    }

    private double novaAltura() {
        if (imageFromFile.getWidth() < imageFromFile.getHeight()) {
            this.heightDefault += 160;
        }
        return (double) heightDefault / imageFromFile.getHeight();
    }

    private double novaLargura() {
        if (imageFromFile.getWidth() > imageFromFile.getHeight()) {
            this.widthDefault += 160;
        }
        return (double) widthDefault / imageFromFile.getWidth();
    }

    private void reescreveImagem(BufferedImage imageDestiny, AffineTransform at) {
        Graphics2D g = imageDestiny.createGraphics();
        g.drawRenderedImage(imageFromFile, at);
    }

}
