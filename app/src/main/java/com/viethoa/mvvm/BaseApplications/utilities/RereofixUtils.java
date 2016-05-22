package com.viethoa.mvvm.BaseApplications.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit.mime.TypedFile;

/**
 * Created by VietHoa on 21/05/16.
 */
public class RereofixUtils {

    public static TypedFile convertBitmapToTypedFile(Context context, Bitmap bitmap) {
        if (bitmap == null)
            return null;

        File imageFileFolder = new File(FileUtils.getInternalStorageFileDir(context), "photo");
        if (!imageFileFolder.exists()) {
            imageFileFolder.mkdirs();
        }

        FileOutputStream out = null;
        File imageFileName = new File(imageFileFolder, "frontImage.jpg");

        try {
            out = new FileOutputStream(imageFileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
        } catch (IOException ex) {
            Log.d("IOException", ex.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                Log.d("IOException", e.getMessage());
            }
        }

        return new TypedFile("image/jpeg", imageFileName);
    }
}
