package com.sds.study.projectlinecontrol;

import android.graphics.Bitmap;
import android.graphics.Region;

/**
 * Created by CANET on 2016-11-27.
 */

public class HitTest{

    public boolean isHit(int x, int y, Region region ){

        //Log.d(TAG, "x : "+x+", y : "+y+", region : "+region+", 판단 : "+region.contains(x, y));

        return region.contains(x, y);

    }

    public Region getBitmapRegion(Bitmap bitmap, int bitmapX, int bitmapY ){

        int[] pixels = null;
        Region rgn = null;
        boolean bMask = false;

        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        try{
            pixels = new int[width * height];
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

            rgn = new Region(0, 0, 0, 0);
            int start = 0, end = 0;

            for(int j = height - 1; j >= 0; j--){
                for(int i = 0; i < width; i++){
                    if(pixels[i + width * j] != 0x00000000){
                        if(!bMask){
                            start = i;
                            end = i + 1;
                            bMask = true;
                        }else{
                            end = i;
                        }
                    }else{
                        if(bMask){
                            rgn.op(new Region(start+bitmapX, j+bitmapY, end+bitmapX, j + 1+bitmapY), Region.Op.XOR);
                        }
                        bMask = false;
                    }
                }
                if(bMask){
                    rgn.op(new Region(start+bitmapX, j+bitmapY, end+bitmapX, j + 1+bitmapY), Region.Op.XOR);
                }
                bMask = false;
            }
        }catch(IllegalArgumentException e){

            e.printStackTrace();

        }catch(ArrayIndexOutOfBoundsException e){

            e.printStackTrace();

        }

        pixels = null;

        return rgn;

    }


}
