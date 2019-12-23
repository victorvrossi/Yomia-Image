package rossi.devil.image;

import java.io.File;
import javax.swing.tree.TreePath;

public class Diretorio {

    private String path = "";

    public Diretorio(String pathFavorito) {
        this.path = pathFavorito;
        System.out.println("" + pathFavorito);
    }

    public String criaDiretorio(String[] ramoLocalNovo) {
        String old = "";
        for (String g : ramoLocalNovo) {
            old += g + File.separatorChar;
            File f = new File(path + old);
            final boolean mkdir = f.mkdir();
            if (mkdir) {
                System.out.println("Criada:" + path + old);
            }
        }
        return old;
    }

    public String criaDiretorio(String src, String[] ramoLocalNovo) {
        String old = "";
        for (String g : ramoLocalNovo) {
            old += g + File.separatorChar;
            File f = new File(path + src + old);
            final boolean mkdir = f.mkdir();
            if (mkdir) {
                System.out.println("Criada:" + path + src + old);
            }
        }
        return src + old;
    }

    public String montaPathDaImagemSelecionada(final TreePath selectionPath) {
        String imagemSelecionada = path;
        for (int i = 1; i < selectionPath.getPath().length; i++) {
            Object l = selectionPath.getPath()[i];
            imagemSelecionada += File.separatorChar + l.toString();
        }
        imagemSelecionada += File.separatorChar;
        return imagemSelecionada;
    }

    public String montaPath(final TreePath selectionPath) {
        String imagemSelecionada = "";
        for (int i = 1; i < selectionPath.getPath().length; i++) {
            Object l = selectionPath.getPath()[i];
            imagemSelecionada += File.separatorChar + l.toString();
        }
        imagemSelecionada += File.separatorChar;
        return imagemSelecionada;
    }

    public static String imagemSelecionada(final TreePath selectionPath) {
        return selectionPath.getPath()[(selectionPath.getPath().length - 1)].toString();
    }

}
