package rossi.devil.image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;

public class ListaDeFavorito {

    private javax.swing.JList<String> listLocalFavorito;
    private List<String> listaFavorita = new ArrayList<String>();
    

    public ListaDeFavorito(javax.swing.JList<String> listLocalFavorito) {
        this.listLocalFavorito = listLocalFavorito;
    }

    public void adicionaLocalNovoAoFavorito(String novo) {
        listaFavorita.add(novo);
    }

    public void remontaLista() {
        List<String> selectedValuesList = listaFavorita;
        exibeListaNovaDeFavorito(selectedValuesList);
    }

    public void removeItem() {
        List<String> selectedValuesList = geraListaDeFavoritos();
        removeDaListaFavoritoItemSelecionado(selectedValuesList);
        exibeListaNovaDeFavorito(selectedValuesList);
    }

    private void exibeListaNovaDeFavorito(List<String> selectedValuesList) {
        listLocalFavorito.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = montaArrayDeLista(selectedValuesList);

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }

    private void removeDaListaFavoritoItemSelecionado(List<String> selectedValuesList) {
        int remove = 0;
        for (int i = 0; i < selectedValuesList.size(); i++) {
            String select = selectedValuesList.get(i);
            if (select.equals(listLocalFavorito.getSelectedValue())) {
                remove = i;
                break;
            }
        }
        selectedValuesList.remove(remove);
    }

    private List<String> geraListaDeFavoritos() {
        final ListModel<String> model = listLocalFavorito.getModel();
        List<String> selectedValuesList = new ArrayList<>();
        for (int k = 0; k < model.getSize(); k++) {
            String elementAt = model.getElementAt(k);
            selectedValuesList.add(elementAt);
        }
        return selectedValuesList;
    }

    private void reescreveListaDeFavorito() {
        exibeListaNovaDeFavorito(listaFavorita);
    }

    private String[] montaArrayDeLista(List<String> listaFavorita) {
        String[] l = new String[listaFavorita.size()];
        int i = 0;
        for (String s : listaFavorita) {
            l[i] = s;
            i++;
        }
        System.out.println(">>" + listaFavorita);
        return l;
    }

    public void gravaArquivo(String fileName) {
        BufferedWriter buffWrite = null;
        try {
            buffWrite = new BufferedWriter(new FileWriter(fileName));
            for (String linha : listaFavorita) {
                buffWrite.append(linha + "\n");
            }   buffWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(ListaDeFavorito.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                buffWrite.close();
            } catch (IOException ex) {
                Logger.getLogger(ListaDeFavorito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
