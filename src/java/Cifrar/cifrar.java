package Cifrar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class cifrar {

    public static final String ruta = "C:\\Users\\Juanv\\Desktop\\recuperararchivo\\";//cambiar por la ruta en l aque tengas tu carpeta

    public static int Cifrar(String cadena, String clave) throws Exception {
        int jalo = 0;
        try {
            byte[] encodedKey = clave.getBytes();
            System.out.println(encodedKey);
            SecretKey subllave = new SecretKeySpec(encodedKey, 0, encodedKey.length, "DES");
            Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cifrado.init(Cipher.ENCRYPT_MODE, subllave);
            byte[] buffer = new byte[1000];
            byte[] bufferCifrado;
            FileInputStream in = new FileInputStream(ruta + cadena);
            FileOutputStream outs = new FileOutputStream(ruta + "Recuperado.txt.cifrado");

            int bytesleidos = in.read(buffer, 0, 1000);
            while (bytesleidos != -1) {
                bufferCifrado = cifrado.update(buffer, 0, bytesleidos);
                outs.write(bufferCifrado);
                bytesleidos = in.read(buffer, 0, 1000);
            }
            bufferCifrado = cifrado.doFinal();
            outs.write(bufferCifrado);

            in.close();
            outs.close();
            jalo = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jalo = 2;
        }
        return jalo;
    }

    public static int Descifrar(String cadena, String clave) throws Exception {
        int jalo = 0;
        try {
            byte[] encodedKey = clave.getBytes();
            SecretKey subllave = new SecretKeySpec(encodedKey, 0, encodedKey.length, "DES");
            Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
            byte[] buffer = new byte[1000];
            cifrado.init(Cipher.DECRYPT_MODE, subllave);
            byte[] bufferPlano;
            FileInputStream in = new FileInputStream(ruta + cadena);
            FileOutputStream outs = new FileOutputStream(ruta + "Recuperado.txt.descifrado");
            int bytesleidos = in.read(buffer, 0, 1000);
            while (bytesleidos != -1) {
                bufferPlano = cifrado.update(buffer, 0, bytesleidos);
                outs.write(bufferPlano);
                bytesleidos = in.read(buffer, 0, 1000);
            }
            bufferPlano = cifrado.doFinal();
            outs.write(bufferPlano);
            in.close();
            outs.close();
            jalo = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jalo = 2;
        }
        return jalo;
    }

    public static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);
    }
}
