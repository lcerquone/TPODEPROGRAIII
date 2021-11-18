package dominio;

import dominio.implementaciones.Horario;
import dominio.implementaciones.Informacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

public class Lector {

    public static List<Informacion> leerArchivoCaminos(){
        List<Informacion> informaciones = new ArrayList<>();
        //Cargo la iformación del archivo.
        try {
            String cadena;
            FileReader archivo = new FileReader("src/resource/Caminos1.txt");
            BufferedReader b = new BufferedReader(archivo);
            //Cargo el archivo completo en el objeto m de la clase metodos, para separar los campos.
            b.readLine();
            while((cadena = b.readLine())!=null) {
                String[] datos = cadena.split("\t");

                    String km = datos[3];
                    String kms = km.replaceAll(",", ".");
                    Informacion c = new Informacion(datos[0], datos[1], Double.valueOf(kms), Integer.parseInt(datos[2]));
                    informaciones.add(c);

            }
            b.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo con los datos.");
        } catch (IOException e) {
            System.out.println("Problemas para leer el archivo.");
        }
        return informaciones;
    }

    public static List<Horario> leerArchivoDatosClientes(){
        List<Horario> horarios = new ArrayList<>();
        //Cargo la iformación del archivo.
        try {
            String cadena;
            FileReader archivo = new FileReader("src/resource/DatosClientes1.txt");
            BufferedReader b = new BufferedReader(archivo);
            //Cargo el archivo completo en el objeto m de la clase metodos, para separar los campos.
            b.readLine();
            while((cadena = b.readLine())!=null) {
                String[] datos = cadena.split("\t");


                    if (!datos[0].contains("A"))
                        horarios.add(new Horario(datos[0],LocalTime.of(Integer.valueOf(datos[4]),0),LocalTime.of(Integer.valueOf(datos[5]),0)));
                    else
                        horarios.add(new Horario(datos[0],LocalTime.of(0,0),LocalTime.of(0,0)));

            }
            b.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo con los datos.");
        } catch (IOException e) {
            System.out.println("Problemas para leer el archivo.");
        }
        return horarios;
    }
}
