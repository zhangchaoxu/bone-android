package com.idogfooding.baidu;

/**
 * StaticImage
 * http://lbsyun.baidu.com/index.php?title=static
 *
 * @author Charles
 */
public class StaticImage {

    public static String getMarker(double lat, double lng, int width, int height) {
        String latLng = lat + "," + lng;
        return "http://api.map.baidu.com/staticimage/v2?ak=ogKclRuUNXWt2OcIGPeeS1dh3DGZOt1G&center=" + latLng +"&width=640&height=170&zoom=13&markerStyles=l,,0xff0000&markers=" + latLng;
    }

}
