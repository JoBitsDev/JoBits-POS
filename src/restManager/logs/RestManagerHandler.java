/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class RestManagerHandler extends Handler {

    private File logFolder;
    private File logFile;
    private FileWriter logFileWriter;
    private BufferedWriter logBuffer;

    public RestManagerHandler(Class modelClassLog) {
        setFormatter(new RestManagerFormatter());
        try {
            logFolder = new File(R.logFilePath + "/" + modelClassLog.getSimpleName());
            if (!logFolder.exists()) {
                logFolder.mkdirs();
            }

            logFile = new File(R.logFilePath + "/" + modelClassLog.getSimpleName()
                    + "/" + modelClassLog.getSimpleName() + "-" + R.DATE_FORMAT_FOR_LOGS.format(new Date()) + ".txt");
            logFileWriter = new FileWriter(logFile,true);
            logBuffer = new BufferedWriter(logFileWriter);
            

        } catch (IOException ex) {
            Logger.getLogger(RestManagerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SafeVarargs
    public static void Log(Logger l, Action act, Level lv, Object... actedOn) {
        String sms = act + "";
        for (Object o : actedOn) {
            sms += R.SEPARADOR + o;
        }
        l.log(lv, sms);
    }

    @Override
    public void publish(LogRecord record) {
        try {
            logBuffer.append(getFormatter().format(record));
            flush();
        } catch (IOException ex) {
            Logger.getLogger(RestManagerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void flush() {
        try {
            logBuffer.flush();
        } catch (IOException ex) {
            Logger.getLogger(RestManagerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() throws SecurityException {
        try {
            logBuffer.close();
        } catch (IOException ex) {
            Logger.getLogger(RestManagerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public enum Action {
        BORRAR,
        EDITAR,
        AGREGAR,
        ENVIAR_COCINA,
        IMPRIMIR_TICKET_PARCIAL,
        IMPRIMIRTICKET,
        CERRAR,
        SET_CASA,
        SET_NOTA;

    }

    public class RestManagerFormatter extends Formatter {

        private final String format = "%4$s: %5$s [%1$tY/%1$tm/%1$td/%1$tT]%n";
        private final Date dat = new Date();

        private RestManagerFormatter() {
        }

        @Override
        public String format(LogRecord record) {
            //dat.setTime(record.getMillis());
            R.DATE_FORMAT_FOR_LOGS.format(dat);
            String source;
            if (record.getSourceClassName() != null) {
                source = record.getSourceClassName();
                if (record.getSourceMethodName() != null) {
                    source += " " + record.getSourceMethodName();
                }
            } else {
                source = record.getLoggerName();
            }
            String message = formatMessage(record);
            String throwable = "";
            if (record.getThrown() != null) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                pw.println();
                record.getThrown().printStackTrace(pw);
                pw.close();
                throwable = sw.toString();
            }
            return String.format(format,
                    dat,
                    source,
                    record.getLoggerName(),
                    record.getLevel().getLocalizedName(),
                    message,
                    throwable);
        }
    }

}
