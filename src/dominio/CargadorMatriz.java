package dominio;

import dominio.implementaciones.Camino;
import dominio.implementaciones.Informacion;

import java.util.*;

public class CargadorMatriz {

    private static List<Camino>[][] crearMatrix(List<String> lugares){
        List<Camino>[][] caminos = new List[lugares.size()][lugares.size()];
        for(int i = 0; i < lugares.size(); i++){
            for(int j = 0; j < lugares.size(); j++){
                if(i!=j)
                caminos[i][j] = new ArrayList<>();
            }
        }
        return caminos;
    }

    public static List<Camino>[][] cargarMatriz(List<String> lugares, List<Informacion> informaciones){
         List<Camino>[][] caminos = crearMatrix(lugares);
         for (Informacion informacion: informaciones){
             int i = lugares.indexOf(informacion.getOrigen());
             int j = lugares.indexOf(informacion.getDestino());
             caminos[i][j].add(new Camino(informacion.getKms(),informacion.getMinutos()));
             caminos[j][i].add(new Camino(informacion.getKms(),informacion.getMinutos()));
         }
        return caminos;
    }
}
