package com.example.win.easy.recognization;

import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.win.easy.recognization.interfaces.RecognitionUnit;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PositionedImage implements RecognitionUnit {

    private static Bitmap bitmap = null;
    private static float[] Float_array;
    private static long gestureId = 1;//默认为1

    public float[] getFloat_array() { return Float_array; }
    public long getGestureId() { return gestureId; }

    public PositionedImage(Bitmap b, long g) { bitmap = b; gestureId = g;}
    public PositionedImage(){}//

    private static float[] Bitmap_to_FloatArray(){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iarray = new int[28*28];
        bitmap.getPixels(iarray, 0, width, 0, 0, width, height);

        float[] farray = new float[28*28];
        /***
         *      A  R  G  B
         *  0x xx xx xx xx
         *  第一位符号位(binary)
         *  由于该bitmap中的RGB值均为0x000000（黑）或0xFFFFFF（白）
         *  有变化的只有A值，变化区间为
         *  0x80000000 -> 0x7FFFFFFF
         *  故对源代码做以下改动以达到图片的正确处理
         */
        for(int i =0;i<28*28;i++){
            if((float)iarray[i]/0x80000000 > 0.001f){
                farray[i] = (float)iarray[i] / 0x80000000;
            }
            else if((float)iarray[i]/0x7fffffff > 0.001f){
                farray[i] = (float)iarray[i] / 0x7fffffff;
            }
            else{
                farray[i] = 0.0f;
            }
        }
        return farray;
    }

    public static PositionedImage create(Gesture gesture, long g){
        bitmap = gesture.toBitmap(28, 28, 3, Color.WHITE);
        Float_array = Bitmap_to_FloatArray();
        return new PositionedImage(bitmap, g);
    }
}
