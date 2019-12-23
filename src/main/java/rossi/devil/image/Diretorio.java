package rossi.devil.image;

import java.io.File;

public class Diretorio {

    private String pathFavorito = "";

    public Diretorio(String pathFavorito) {
        this.pathFavorito = pathFavorito;
    }

    public  String criaDiretorio(String[] ramoLocalNovo) {
        String old = "";
        for (String g : ramoLocalNovo) {
            old += g + File.separatorChar;
            File f = new File(pathFavorito + old);
            final boolean mkdir = f.mkdir();
            if (mkdir) {
                System.out.println("Criada");
            }
        }
        return old;
    }

}
