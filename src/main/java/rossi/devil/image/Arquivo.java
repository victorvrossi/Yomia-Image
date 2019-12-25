package rossi.devil.image;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class Arquivo {

    public DefaultMutableTreeNode carregaDiretorio(String src, boolean carregaArquivo) throws FalhaSistema {
        File f = new File(src);        
        try {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("" + f.getName());
            for (File k : f.listFiles()) {
                if (!k.isDirectory() && carregaArquivo) {
                    carregaArquivo(k, root, carregaArquivo);
                } else {
                    carregaArvoreDiretorio(k, root, src, carregaArquivo);
                }
            }
            return root;
        } catch (NullPointerException e) {
            throw new FalhaSistema("Houve um problema ao abrir o diretorio de Favoritos. \n\n Você escolheu o seguinte local:"+src);
        }
    }

    private void carregaSubNo(DefaultMutableTreeNode root, String src, boolean carregaArquivo) {
        File f = new File(src);
        if (f != null && f.listFiles() != null && f.listFiles().length > 0) {
            for (File k : f.listFiles()) {
                if (!k.isDirectory() && carregaArquivo) {
                    carregaArquivo(k, root, carregaArquivo);
                } else {
                    carregaArvoreDiretorio(k, root, src, carregaArquivo);
                }
            }
        }
    }

    private void carregaArvoreDiretorio(File k, DefaultMutableTreeNode root, String src, boolean carregaArquivo) {
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
