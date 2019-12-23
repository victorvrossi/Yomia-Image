package rossi.devil.image;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class Arquivo {

    public DefaultMutableTreeNode carregaDiretorio(String src, boolean carregaArquivo) {
        File f = new File(src);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("" + f.getName());
        for (File k : f.listFiles()) {
            if (!k.isDirectory() && carregaArquivo) {
                carregaArquivo(k, root, carregaArquivo);
            } else {
                carregaDiretorio(k, root, src, carregaArquivo);
            }
        }
        return root;
    }

    private void carregaSubNo(DefaultMutableTreeNode root, String src, boolean carregaArquivo) {
        File f = new File(src);
        for (File k : f.listFiles()) {
            if (!k.isDirectory()) {
                carregaArquivo(k, root, carregaArquivo);
            } else {
                carregaDiretorio(k, root, src, carregaArquivo);
            }
        }
    }

    private void carregaDiretorio(File k, DefaultMutableTreeNode root, String src, boolean carregaArquivo) {
        DefaultMutableTreeNode diretorio = new DefaultMutableTreeNode(k.getName());
        root.add(diretorio);
        carregaSubNo(diretorio, src + File.separatorChar + k.getName(), carregaArquivo);
    }

    private void carregaArquivo(File k, DefaultMutableTreeNode root, boolean carregaArquivo) {
        if (!carregaArquivo) {
            return;
        }
        if (verificaSeImagem(k)) {
            DefaultMutableTreeNode arquivo = new DefaultMutableTreeNode("" + k.getName());
            root.add(arquivo);
        }
    }

    private boolean verificaSeImagem(File k) {
        String name = k.getName().toLowerCase();
        if (name.contains(".jpg")) {
            return true;
        }
        if (name.contains(".jpeg")) {
            return true;
        }
        if (name.contains(".gif")) {
            return true;
        }
        if (!name.contains(".")) {
            return true;
        }
        return false;
    }
}
