package com.idogfooding.bone.image;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idogfooding.bone.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

/**
 * PhotoAdapter
 *
 * @author Charles
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    public static final String ITEM_MORE = "ITEM_MORE";
    private List<String> mPhotoPaths = new ArrayList<>();

    private Activity mActivity;
    private boolean mShowDeleteButton = true;
    private int mRequestCode = 0;
    private int mPhotoCount = 4;
    private int mGridColumnCount = 4;

    public PhotoAdapter(Activity activity, List<String> photoPaths, boolean showDeleteButton, int requestCode) {
        this.mActivity = activity;
        this.mPhotoPaths = photoPaths;
        this.mShowDeleteButton = showDeleteButton;
        this.mRequestCode = requestCode;
    }

    public PhotoAdapter(Activity activity, List<String> photoPaths, boolean showDeleteButton) {
        this.mActivity = activity;
        this.mPhotoPaths = photoPaths;
        this.mShowDeleteButton = showDeleteButton;
    }

    public PhotoAdapter(Activity activity, List<String> photoPaths) {
        this.mActivity = activity;
        this.mPhotoPaths = photoPaths;
    }

    public int getPhotoCount() {
        return mPhotoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.mPhotoCount = photoCount;
    }

    public int getGridColumnCount() {
        return mGridColumnCount;
    }

    public void setGridColumnCount(int gridColumnCount) {
        this.mGridColumnCount = gridColumnCount;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mActivity).inflate(me.iwf.photopicker.R.layout.__picker_item_photo, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
        String path = mPhotoPaths.get(position);

        if (path.equalsIgnoreCase(ITEM_MORE)) {
            Glide.with(mActivity).load(R.mipmap.ic_more_photo).into(holder.ivPhoto);
            holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhotoPicker.builder()
                            .setPhotoCount(mPhotoCount)
                            .setGridColumnCount(mGridColumnCount)
                            .setSelected(removeMoreItem())
                            .start(mActivity, mRequestCode == 0 ? PhotoPicker.REQUEST_CODE : mRequestCode);
                }
            });
        } else {
            String photoPath = mPhotoPaths.get(position);
            if (photoPath.startsWith("http")) {
                Glide.with(mActivity).load(photoPath).centerCrop().thumbnail(0.1f)
                        .placeholder(me.iwf.photopicker.R.drawable.__picker_ic_photo_black_48dp)
                        .error(me.iwf.photopicker.R.drawable.__picker_ic_broken_image_black_48dp)
                        .into(holder.ivPhoto);
            } else {
                Uri uri = Uri.fromFile(new File(mPhotoPaths.get(position)));
                Glide.with(mActivity).load(uri).centerCrop().thumbnail(0.1f)
                        .placeholder(me.iwf.photopicker.R.drawable.__picker_ic_photo_black_48dp)
                        .error(me.iwf.photopicker.R.drawable.__picker_ic_broken_image_black_48dp)
                        .into(holder.ivPhoto);
            }

            holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhotoPreview.builder()
                            .setPhotos(removeMoreItem())
                            .setCurrentItem(position)
                            .setShowDeleteButton(mShowDeleteButton)
                            .start(mActivity, mRequestCode == 0 ? PhotoPreview.REQUEST_CODE : mRequestCode);
                }
            });
        }
    }

    private ArrayList<String> removeMoreItem() {
        ArrayList<String> list = new ArrayList<>();
        for (String s : mPhotoPaths) {
            if (!s.equalsIgnoreCase(ITEM_MORE)) {
                list.add(s);
            }
        }
        return list;
    }


    @Override
    public int getItemCount() {
        return mPhotoPaths.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private View vSelected;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
            vSelected = itemView.findViewById(R.id.v_selected);
            vSelected.setVisibility(View.GONE);
        }
    }
}
