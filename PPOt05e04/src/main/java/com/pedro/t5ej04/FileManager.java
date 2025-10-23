package com.pedro.t5ej04;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileManager {
    private String directorio = "uploadDir";


    private final Path rootLocation = Paths.get(directorio);

    public String getDirectorio() {
        return directorio;
    }
    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }   
    //Este metodo recibe un fichero por argumento y puede soltar excepciones de leida
    public String guardarFichero(MultipartFile file, String dni) throws RuntimeException{
        //Comprobamos si el fichero esta vacio y asi paramos la ejecucion con una excepcion a capturar cuando invoquemos al metodo
        if(file.isEmpty()){
            throw new RuntimeException("Fichero Vacio");
        }
        //Almacenamos el nombre como String del fichero en una variable para trabajar con el
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        //Si el mismo contiene 2 puntos, lanzara una excepcion, ya que es reservado para una carpeta de sistema
        if(filename.contains("..")){
            throw new RuntimeException("Ruta de fichero incorrecta");
        }
        //Almacenamos la extension con otro metodo asi como el nombre que le daremos al archivo al guardarlo
        String extension = StringUtils.getFilenameExtension(filename);
        String nuevoNombre = dni.toUpperCase() + "." + extension;
        // Crear directorio si no existe
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
        } catch (IOException e) {
        }
        /*Intentamos acceder al archivo en modo lectura ya que queremos recibir datos del mismo, en este caso copiarlo, cogiendo el archivo,
        copiandolo en el rootLocation quie designamos cambiandole el nombre a nuevoNombre, y reemplazandolo si ya hay uno igual
        Si falla a la hora de acceder al archivo saltara una excepcion tambien
        Devuelve una String para quedarnos si queremos con la ruta del archivo ya almacenado*/
        try(InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, this.rootLocation.resolve(nuevoNombre), StandardCopyOption.REPLACE_EXISTING);
            return nuevoNombre;
        }catch(IOException ioe){
            throw new RuntimeException("Error a la hora de copiar el archivo y cambairle el nombre");
        }  
    }

    //Metodo que borra un archivo en base al nombre recibido como String
    public void delete(String filename) throws RuntimeException{
        try{
            //Primero busca la direccion del archivo en el rootLocation que marcamos
            Path file = rootLocation.resolve(filename);
            //Si no existe el archivo lanzamos excepcion
            if (!Files.exists(file)) {
                throw new RuntimeException("No existe el fichero");
            }
            //Si existe lo borramos
            Files.delete(file);
        //Capturamos posibles excepciones a la hora de borrar
        }catch(IOException ioe){
            throw new RuntimeException("Error en borrado");
        }
    }

    //Metodo que carga el archivo como un recurso
    public Resource loadAsResource(String filename) throws RuntimeException{
        //Primero busca el archivo y su ubiucacion, lo convierte a un recurso mediante 
        //un toUri y si es accesible lo devuelve, si no lanza excepcion
        try{
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("Error IO");
            }
        }catch(RuntimeException | MalformedURLException e){
            throw new RuntimeException("Error IO");
        }
    }
}
