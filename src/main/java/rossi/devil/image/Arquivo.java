package rossi.devil.image;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

public class Arquivo {

    public DefaultMutableTreeNode carregaArquivos(String src) {
        File f = new File(src);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("" + f.getName());
        for (File k : f.listFiles()) {
            if (!k.isDirectory()) {
                if (verificaSeImagem(k)) {
                    DefaultMutableTreeNode arquivo = new DefaultMutableTreeNode("" + k.getName());
                    root.add(arquivo);
                }
            } else {
                DefaultMutableTreeNode dir = new DefaultMutableTreeNode(k.getName());
                root.add(dir);
                carregaSubNo(dir, src + File.separatorChar + k.getName());
            }
        }
        return root;
    }

    private void carregaSubNo(DefaultMutableTreeNode root, String src) {
        File f = new File(src);
        for (File k : f.listFiles()) {
            if (!k.isDirectory()) {
                if (verificaSeImagem(k)) {
                    DefaultMutableTreeNode arquivo = new DefaultMutableTreeNode("" + k.getName());
                    root.add(arquivo);
                }
            } else {
                DefaultMutableTreeNode diretorio = new DefaultMutableTreeNode(k.getName());
                root.add(diretorio);
                carregaSubNo(diretorio, src + File.separatorChar + k.getName());
            }
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
