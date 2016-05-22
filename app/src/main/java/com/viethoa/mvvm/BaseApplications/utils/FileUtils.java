package com.viethoa.mvvm.BaseApplications.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by VietHoa on 23/10/15.
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static File getInternalStorageFileDir(Context applicationContext) {
        File filesDir;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            filesDir = applicationContext.getExternalFilesDir(null);
        } else {
            // Load another directory, probably local memory
            filesDir = applicationContext.getFilesDir();
        }
        return filesDir;
    }

    public static String getExternalMovieStorageFileDir() {
        Log.i(TAG, "Get external movies storage file path");
        String state = Environment.getExternalStorageState();
        File mediaStorageDir = null;

        // We can read and write the media
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mediaStorageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Movies");
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.i(TAG, "failed to create directory");
                    return null;
                }
            }
        }

        // This is not
        if (mediaStorageDir == null) {
            Log.e(TAG, "We can read and write the media file");
            return null;
        }

        return mediaStorageDir.getPath();
    }

    //********************************************************************************************//
    //  Copy file to folder
    //********************************************************************************************//

    public static String copyFileToFolder(String filePath, String folderPath) {
        Log.i(TAG, "Coping file: " + filePath + " to: " + folderPath);
        String validFilePathStr = filePath;
        try {
            FileInputStream is = new FileInputStream(filePath);
            BufferedOutputStream o = null;
            String validFileName = getValidFileNameFromPath(filePath);
            validFilePathStr = folderPath + validFileName;
            File destFile = new File(validFilePathStr);
            try {
                byte[] buff = new byte[10000];
                int read = -1;
                o = new BufferedOutputStream(new FileOutputStream(destFile), 10000);
                while ((read = is.read(buff)) > -1) {
                    o.write(buff, 0, read);
                }
            } finally {
                is.close();
                if (o != null) o.close();
            }
            Log.i(TAG, "Coping file: " + filePath + " to: " + folderPath);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return validFilePathStr;
    }

    public static String getValidFileNameFromPath(String path) {
        int startIndex = path.lastIndexOf("/") + 1;
        int endIndex = path.lastIndexOf(".");

        String name = path.substring(startIndex, endIndex);
        String ext = path.substring(endIndex + 1);
        Log.i(TAG, "name: " + name + " ext: " + ext);
        String validName = (name.replaceAll("\\Q.\\E", "_")).replaceAll(" ", "_");
        return validName + "." + ext;
    }

    public static void RefreshingTheMediaStore(Context context, String filePath) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            File refreshFile = new File(filePath);
            Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            scanIntent.setData(Uri.fromFile(refreshFile));
            context.sendBroadcast(scanIntent);
        } else {
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse(filePath)));
        }
    }

    //********************************************************************************************//
    //  Delete directory
    //********************************************************************************************//

    public static void deleteDirectory(File path) {
        if (!path.exists())
            return;

        File[] files = path.listFiles();
        if (files == null) {
            return;
        }

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                deleteDirectory(files[i]);
            } else {
                files[i].delete();
            }
        }

        path.delete();
    }

    //********************************************************************************************//
    //  Tracking file
    //********************************************************************************************//

    public static Integer getVideoDuration(Context context, String videoPath) {
        if (videoPath == null || videoPath.isEmpty())
            return 0;
        File videoFile = new File(videoPath);
        if (!videoFile.exists())
            return 0;
        Uri videoUri = Uri.fromFile(videoFile);
        if (videoUri == null)
            return 0;

        try {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, videoUri);
            int duration = mediaPlayer.getDuration();
            Log.d(TAG, "detect video duration: " + duration);
            mediaPlayer.release();
            return duration;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return 0;
        }
    }

    public static float getFileSizeInMB(String filePath) {
        File file = new File(filePath);
        if (!file.exists())
            return 0;

        // Convert the bytes to MB (1 KB = 1024 Bytes, 1 MB = 1024 Byte)
        float length = file.length();
        return ((length / 1024) / 1024);
    }

    //********************************************************************************************//
    //  Get real path from Uri
    //********************************************************************************************//

    public static String getRealVideoPathFromUri(Context context, Uri contentUri) {
        String realPath = null;

        if (Build.VERSION.SDK_INT < 11)
            realPath = getRealVideoPathFromURI_BelowAPI11(context, contentUri);
        else if (Build.VERSION.SDK_INT < 19)
            realPath = getRealVideoPathFromURI_API11to18(context, contentUri);
        else
            realPath = getRealVideoPathFromURI_API19(context, contentUri);

        Log.i(TAG, "get real path from uri: " + realPath);
        return realPath;
    }

    @SuppressLint("NewApi")
    public static String getRealVideoPathFromURI_API19(Context context, Uri contentUri) {
        if (contentUri == null)
            return null;

        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(contentUri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];
        String[] column = {MediaStore.Video.Media.DATA};

        // where id is equal to
        String sel = MediaStore.Video.Media._ID + "=?";
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{id}, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndex(column[0]);
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        return filePath;
    }


    @SuppressLint("NewApi")
    public static String getRealVideoPathFromURI_API11to18(Context context, Uri contentUri) {
        if (contentUri == null)
            return null;

        String filePath = null;
        String[] proj = {MediaStore.Video.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(column_index);
            }
            cursor.close();
        }

        return filePath;
    }

    public static String getRealVideoPathFromURI_BelowAPI11(Context context, Uri contentUri) {
        if (contentUri == null)
            return null;

        String filePath = null;
        String[] proj = {MediaStore.Video.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(column_index);
            }
            cursor.close();
        }

        return filePath;
    }

    //********************************************************************************************//
    //  Get real path from Uri
    //********************************************************************************************//

    public static String getRealImagePathFromUri(Context context, Uri contentUri) {
        String realPath = null;
        try {
            realPath = getRealImagePathFromURI_BelowAPI11(context, contentUri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (realPath != null && !realPath.isEmpty())
            return realPath;

        try {
            realPath = getRealImagePathFromURI_API11to18(context, contentUri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (realPath != null && !realPath.isEmpty())
            return realPath;

        try {
            realPath = getRealImagePathFromURI_API19(context, contentUri);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return realPath;

//        if (Build.VERSION.SDK_INT < 11)
//            realPath = getRealImagePathFromURI_BelowAPI11(context, contentUri);
//        else if (Build.VERSION.SDK_INT < 19)
//            realPath = getRealImagePathFromURI_API11to18(context, contentUri);
//        else
//            realPath = getRealImagePathFromURI_API19(context, contentUri);
//
//        Log.i(TAG, "get real path from uri: " + realPath);
//        return realPath;
    }

    @SuppressLint("NewApi")
    public static String getRealImagePathFromURI_API19(Context context, Uri contentUri) throws Exception {
        if (contentUri == null)
            return null;

        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(contentUri);
        String id = wholeID.split(":")[1];
        String[] column = {MediaStore.Images.Media.DATA};

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{id}, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndex(column[0]);
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        return filePath;
    }


    @SuppressLint("NewApi")
    public static String getRealImagePathFromURI_API11to18(Context context, Uri contentUri) throws Exception {
        if (contentUri == null)
            return null;

        String filePath = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(column_index);
            }
            cursor.close();
        }

        return filePath;
    }

    public static String getRealImagePathFromURI_BelowAPI11(Context context, Uri contentUri) throws Exception {
        if (contentUri == null)
            return null;

        String filePath = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(column_index);
            }
            cursor.close();
        }

        return filePath;
    }
}
