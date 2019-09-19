package com.alodiga.wallet.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import org.apache.log4j.Logger;

public class Utils {

    private static Properties messages;
    private static Properties validationMessages;

    private static final Logger logger = Logger.getLogger(Utils.class);

    public static String generarCodigoRandom(int longitud) {
        String codigoGenerado = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            codigoGenerado += "" + r.nextInt(10);
            i++;
        }
        System.out.println("codGenerado: ----> " + codigoGenerado);
        return codigoGenerado;
    }

    public static String generarNumeroDeCuenta() {
        return "256344" + Utils.generarCodigoRandom(10);

    }





    public static String obtienePropiedad(String key) {
        try {
            if (messages == null) {
                messages = new Properties();
                String path = Utils.class.getClassLoader()
                        .getResource("config.properties").getFile();
                messages.load(new FileInputStream(path));
            }
            return messages.getProperty(key).trim();
        } catch (IOException ex) {
            return "(El sistema no puede encontrar el archivo .properties)";
        }
    }

    public static String obtieneMensaje(String key) {
        try {
            if (validationMessages == null) {
                validationMessages = new Properties();
                String path = Utils.class.getClassLoader()
                        .getResource("ValidationMessages.properties").getFile();
                validationMessages.load(new FileInputStream(path));
            }
            return validationMessages.getProperty(key).trim();
        } catch (IOException ex) {
            return "(El sistema no puede encontrar el archivo .properties)";
        }
    }
    
    public static Timestamp[] DateBeggining() {
        Timestamp[ ] dates = new Timestamp[2];
        Calendar calTodayBeggining = Calendar.getInstance();
        calTodayBeggining.set(Calendar.HOUR_OF_DAY,0);
        calTodayBeggining.set(Calendar.MINUTE,0);            
        calTodayBeggining.set(Calendar.SECOND,0);            
        calTodayBeggining.set(Calendar.MILLISECOND,0);
        Timestamp begginingDateTime = new Timestamp(calTodayBeggining.getTimeInMillis());
        dates[0] = begginingDateTime;
        Calendar calTodayEnding = (Calendar) calTodayBeggining.clone();
        calTodayEnding.set(Calendar.HOUR,23);
        calTodayEnding.set(Calendar.MINUTE,59);            
        calTodayEnding.set(Calendar.SECOND,59);            
        calTodayEnding.set(Calendar.MILLISECOND,999);
        Timestamp endingDateTime = new Timestamp(calTodayEnding.getTimeInMillis());
        dates[1] = endingDateTime;
        return dates;   
    }
   
}
