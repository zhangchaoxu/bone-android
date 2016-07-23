package com.idogfooding.bone.image;

import java.util.ArrayList;
import java.util.List;

/**
 * PhotoPickerUtils
 *
 * @author Charles
 */
public class PhotoPickerUtils {

    public static List<String> filterPhotos(List<String> selectedPhotos) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : selectedPhotos) {
            if (!s.equalsIgnoreCase(PhotoAdapter.ITEM_MORE)) {
                list.add(s);
            }
        }
        return list;
    }
}
