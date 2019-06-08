package editor.Tools.CreateGame;

import editor.Editor;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.jar.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ProduceJar {
    public void produce(String path) throws IOException, URISyntaxException {
        Editor.world.exportMap(path);
        String projectName = Editor.world.projectName;
        File world = new File(path + "/" + projectName);

        //Getting the path to our jar && opening it
        URL url = ProduceJar.class.getProtectionDomain().getCodeSource().getLocation();
        InputStream inputStream = new BufferedInputStream(Files.newInputStream(Paths.get(url.toURI())));
        JarInputStream jarInputStream = new JarInputStream(inputStream);

        //Writing the bullshit manifest
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, "engine.Engine");

        //Opening the output on the user specified path
        File file = new File(path + "/" + world.getName() + "_BWC.jar");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        JarOutputStream jarOutputStream = new JarOutputStream(fileOutputStream, manifest);

        BufferedInputStream bufferedInputStream;
        JarEntry entry = jarInputStream.getNextJarEntry();

        //Iterating over all the JarEntries in INPUT
        while (entry != null){
            jarOutputStream.putNextEntry(new JarEntry(entry));
            bufferedInputStream = new BufferedInputStream(jarInputStream);

            //Writing all the data until the next JarEntry
            byte[] buff = new byte[1024];
            int count;
            do{
                count = bufferedInputStream.read(buff);
                if (count == -1)
                    break;
                jarOutputStream.write(buff, 0, count);
            } while (true);
            jarOutputStream.closeEntry();
            entry = jarInputStream.getNextJarEntry();
        }

        //copying the save
        add("", jarOutputStream, world);

        world.delete();

        try {
            jarOutputStream.close();
        } catch (IOException e){ }
        System.out.println("Im done");
    }

    private void add(String path, JarOutputStream jarOutputStream, File file) throws IOException {
        jarOutputStream.putNextEntry(new ZipEntry(path + file.getName()));
        if (file.isDirectory()){
            for (File file1 : file.listFiles()){
                add(path + file.getName() + '/', jarOutputStream, file1);
            }
        } else {
            jarOutputStream.write((new FileInputStream(file).readAllBytes()));
        }
    }
}
