/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1CMD;

/**
 *
 * @author andre
 */
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class funciones {
    public File dir;
    
    public funciones(String pathname){
        dir= new File(pathname);
    }
            
    public String Mkdir(String pathname){
        
        File folder = new File(pathname);
        
        if(folder.exists()){
            return"Esta carpeta ya existe";
        }
        else{
            folder.mkdir();
            return"Se ha creado una nueva carpeta";
        }
    }
    public String Mfile(String pathname){
        File archivo= new File(pathname);
        try{
        if(archivo.exists()){
            return"Este file ya existe";
        }else{
        
            archivo.createNewFile();
        }
        }catch(IOException e){
            return"No se pudo crear el archivo";
        }
    return"";}
    
    public String Rm(File file) {
        if (file.isDirectory()) {
            for (File fl : file.listFiles()) {
                if (fl.isDirectory()) {
                    emptyDir(fl);
                    fl.delete();
                } else {
                    fl.delete();
                }
            }
            file.delete();
            return "Carpeta eliminada";
        }

        if (file.isFile()) {
            file.delete();
            return "Archivo eliminado";
        }

        return "Error";
    }
    public void emptyDir(File file){
        if(file.isDirectory()){
            for(File fl:file.listFiles()){
                fl.delete();
            }
        }
    }
    public String Cd(String pathname) {
        if (pathname.charAt(0) != '/') {
            File newDir = new File(dir.getAbsolutePath() + "/" + pathname);

            if (!newDir.isDirectory()) {
                return " La direccion tiene que ser de una carpeta";
            }
            dir = newDir;
            
            return "NUEVO DIR ES: " + newDir.getAbsolutePath();
        }

        dir = new File(pathname);
        return "";
    }
    public static String listar(String path) {
        
        String lista = "";
        File listado = new File(path);

        if (listado.isDirectory()&&!listado.isHidden()) {
            lista+=listado.getName();

            for (File archivo : listado.listFiles()) {
                lista += "\n    ->" + archivo.getName();
            }

            return lista;
        } else {
            return "La direccion tiene que ser de una carpeta";
        }
    }
    
    public String escribir(String text, String pathname){
        File file = new File(pathname);
        

        if (file.exists()) {
            if (file.isFile()) {
                try {

                    FileWriter fr = new FileWriter(file);
                    fr.write(text);
                    fr.flush();
                } catch (IOException e) {
                    return"Error";
                }
                return"Escritura completada";
                
            } else {
                return "Debe seleccionar un archivo";
                
            }
        } else {
            return "No existe ningun archivo con este path";
            
        }
    }
    public String leer(String pathname){    
        File file = new File(pathname);
        

        if (file.exists()) {
            if (file.isFile()) {
                try {
                    FileReader fr = new FileReader(file);

                    String text = "";

                    for (int i = fr.read(); i != -1; i = fr.read()) {
                        text += (char) i;
                    }
                    return text;

                } catch (IOException e) {
                    return "Error: no se pudo leer";
                    
                }
            } else {
                return "Error: debe seleccionar un archivo";
                
            }
        } else {
            return "Error: Archivo inexistente";
            
        }
        
    }
    
    public String getCurrentPath(){
        try {
            return dir.getCanonicalPath();
        } catch (Exception e){
            System.out.println("Un error ha ocurrido intentando obtener el canonicalPath");
            return dir.getAbsolutePath();
        }

    }
}
