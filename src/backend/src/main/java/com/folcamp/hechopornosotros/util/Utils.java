package com.folcamp.hechopornosotros.util;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static File getImageFromBase64(String base64String, String fileName) {
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            case "data:image/webp;base64":
                extension = "webp";
                break;
            default:
                throw new RuntimeException("Codificacion incorrecta");

        }

        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        File file = new File(fileName + "." + extension);

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static Date getTwentyYearsFromNow() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, 20);
        Date newDate = c.getTime();
        return newDate;
    }
}
