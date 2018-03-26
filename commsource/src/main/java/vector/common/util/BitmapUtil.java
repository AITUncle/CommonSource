package vector.common.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by vectorzeng on 2018/2/28.
 */

public class BitmapUtil {
    private static final String TG = "vz-BitmapUtil";

    private static Bitmap createEmptyBitmap(){
        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    }

    /***
     * 拉伸图片到指定的宽高
     * @param sourceBmp  ：源图片资源
     * @param newWidth ：缩放后宽度
     * @param newHeight：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap sourceBmp, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = sourceBmp.getWidth();
        float height = sourceBmp.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(sourceBmp, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    /**
     * @param drawable
     * @return never return null
     */

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if(drawable == null){
            throw new RuntimeException("BitmapUtil.drawableToBitmap drawable is null");
        }

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0) {
            try {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError e) {
                System.gc();
                e.printStackTrace();
            }
        }
        if(bitmap == null){
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap drawableToBitmap (Drawable drawable, int width, int height) {
        if(drawable == null || width <= 0 || height <= 0){
            throw new RuntimeException("BitmapUtil.drawableToBitmap width, height" + width + ", " + height + "; " + drawable);
        }
        Bitmap bitmap = drawableToBitmap(drawable);
        return zoomImage(bitmap, width, height);
    }

    public static Bitmap decodeResource(Resources res, int id) {
        try {
            return BitmapFactory.decodeResource(res, id);
        }catch (OutOfMemoryError error){
            return null;
        }
    }

    /**
     * 为QuickShortcut定制的方法。生成app-info的图标。
     * 将back和front合并，并生成一个bitmap。
     *
     *
     * 起点为右下脚，也就是front绘制在右下角
     * @param back 背景
     * @param front 前景
     * @param ratioWidth = width(back)/width(front);
     * @param ratioHeight = height(back)/width(front)
     * @return
     */
    public static Bitmap mergeDrawableToBmpUnSafe(Drawable back, Drawable front, float ratioWidth, float ratioHeight){
        if(back == null || front == null){
            return null;
        }

        int wBack = back.getIntrinsicWidth();
        int hBack = back.getIntrinsicHeight();
        int wFront = (int) (wBack * ratioWidth);
        int hFront = (int) (hBack * ratioHeight);
        int width = wBack + wFront/4;
        int height = hBack + hFront/4;
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        back.setBounds(0, 0, wBack, hBack);
        back.draw(canvas);

        int l = width - wFront;
        int t = height - hFront;
        if(l < 0){
            l = 0;
        }
        if(t < 0){
            t = 0;
        }
        front.setBounds(l, t, width, height);
        front.draw(canvas);
        return result;
    }


    public static Bitmap decodeFile(Uri uri){
        if(uri == null || !"file".equals(uri.getScheme())){
            MyAssert.fail();
            return null;
        }
        String path = uri.getPath();
        if(TextUtils.isEmpty(path)){
            MyAssert.fail();
            return null;
        }
        File file = new File(path);
        if(!file.exists()){
            MyAssert.fail();
            return null;
        }
        try {
            Bitmap ret = BitmapFactory.decodeFile(file.getAbsolutePath());
            return ret;
        }catch (OutOfMemoryError oom){
            oom.printStackTrace();
        }
        return null;
    }
}
