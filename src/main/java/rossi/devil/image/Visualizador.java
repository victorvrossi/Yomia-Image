package rossi.devil.image;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.tree.TreePath;

public class Visualizador {

    private javax.swing.JLabel lb_image;
    private String pathEscolhido;

    public Visualizador(JLabel lb_image,String pathEscolhido) {
        this.lb_image = lb_image;
        this.pathEscolhido = pathEscolhido;
    }
    
    
    
    public void alteraImagemNoVisualizador(final TreePath selectionPath) {
        String imagemSelecionada = montaPathDaImagemSelecionada(selectionPath) ;
        
        File f = new File(imagemSelecionada);
        if (f.isFile()) {
            mudaImagemVisualizada(imagemSelecionada);
        }
    }

    private String montaPathDaImagemSelecionada(final TreePath selectionPath) {
        String imagemSelecionada = pathEscolhido ;
        for (int i = 1; i < selectionPath.getPath().length; i++) {
            Object l = selectionPath.getPath()[i];
            imagemSelecionada += File.separatorChar + l.toString();
        }
        return imagemSelecionada;
    }

    public boolean validaPathTree(final TreePath selectionPath) {
        if (selectionPath == null) {
            return true;
        }
        if (selectionPath.getPath() == null) {
            return true;
        }
        return false;
    }

    public void mudaImagemVisualizada(String imagemCaminho) {
        try {
            Imagem image = new Imagem();
            image.carregaImagem(imagemCaminho);
            lb_image.setIcon(image.geraImagemParaExibicao());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
