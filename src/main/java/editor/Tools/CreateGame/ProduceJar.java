package editor.Tools.CreateGame;

import com.google.gson.Gson;
import editor.Editor;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
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

        //Writing the bullshit manifest
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, "engine.Engine");

        //Opening the output on the user specified path
        File file = new File(path + "/" + world.getName() + "_BWC.jar");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        JarOutputStream jarOutputStream = new JarOutputStream(fileOutputStream, manifest);

        Path path_class = Paths.get(url.toURI());

        Files.walk(path_class)
                .filter(Files::isRegularFile)
                .forEach(path_file-> {

                    try {

                        FileInputStream fi = new FileInputStream(path_file.toFile());
                        BufferedInputStream bi = new BufferedInputStream(fi);

                        String file_full = path_class.relativize(path_file).toString();

                        jarOutputStream.putNextEntry(new JarEntry(file_full));

                        byte[] buff = new byte[1024];
                        int count;

                        do {

                            count = bi.read(buff);

                            if (count == -1) break;

                            jarOutputStream.write(buff, 0, count);

                        } while (true);

                        jarOutputStream.closeEntry();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                });

        try {

            URL gson_url = Gson.class.getProtectionDomain().getCodeSource().getLocation();
            JarInputStream in = new JarInputStream(new BufferedInputStream(Files.newInputStream(Paths.get(gson_url.toURI()))));

                JarEntry entry = null;

                while ((entry = in.getNextJarEntry()) != null) {

                    jarOutputStream.putNextEntry(new JarEntry(entry));

                    byte[] buff = new byte[1024];
                    int count;

                    do {

                        count = in.read(buff);

                        if (count == -1) break;

                        jarOutputStream.write(buff, 0, count);

                    } while (true);

                    jarOutputStream.closeEntry();
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //copying the save
        add("", jarOutputStream, world);

        try
        {
            jarOutputStream.close();
        }
        catch (IOException e){ }

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

    private void rDelete(File file) throws IOException {
        if (file.isDirectory()){
            for (File file1 : file.listFiles()){
                rDelete(file1);
            }

        }
        file.delete();
    }
}
