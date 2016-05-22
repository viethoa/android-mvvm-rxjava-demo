package com.viethoa.mvvm.BaseApplications.loggers;

import android.os.Environment;

import com.viethoa.mvvm.BaseApplications.utilities.DeviceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by VietHoa on 21/05/16.
 */
public class WriteLogFile {
    private static SimpleDateFormat formatter;
    {
        formatter = new SimpleDateFormat("E MMM d yyyy hh:mm a", java.util.Locale.getDefault());
    }

    public static void write(String tag, String text) {
        if (text == null || text.isEmpty())
            return;

        boolean canWrite = DeviceUtils.canWriteExternalStorage();
        if (!canWrite)
            return;

        String storagePath = Environment.getExternalStorageDirectory().toString();
        File logFile = new File(storagePath, "/application_logs.txt");

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                logFile = null;
                e.printStackTrace();
            }
        }
        if (logFile == null)    //Fail to create log file.
            return;

        try {
            //User BufferedWriter for good performance, 'true' to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));

            buf.append(formatter.format(new Date()));
            if (tag != null && !tag.isEmpty())
                buf.append(tag).append(": ");
            if (!text.isEmpty())
                buf.append(text);

            buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
