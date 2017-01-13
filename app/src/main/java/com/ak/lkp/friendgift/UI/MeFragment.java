package com.ak.lkp.friendgift.UI;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ak.lkp.friendgift.Adapter.PhotoAdapter;
import com.ak.lkp.friendgift.Adapter.RecyclerItemClickListener;
import com.ak.lkp.friendgift.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.Call;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String UPLOAD_URL="http://192.168.82.85:8888/dopostpic/UploadServlet";
    private String mParam1;
    private String mParam2;
    private View view;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private List<File> files;

    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(String param1) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, container, false);
        initView();


        return view;
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        photoAdapter = new PhotoAdapter(getActivity(), selectedPhotos);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);
       view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(6)
                        .setGridColumnCount(4)
                        .start(getContext(),MeFragment.this);
            }
        });

        view.findViewById(R.id.button_no_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(7)
                        .setShowCamera(false)
                        .setPreviewEnabled(false)
                        .start(getContext(),MeFragment.this);
            }
        });

        view.findViewById(R.id.button_one_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .start(getContext(),MeFragment.this);
            }
        });

        view.findViewById(R.id.button_photo_gif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setShowCamera(true)
                        .setShowGif(true)
                        .start(getContext(),MeFragment.this);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD) {
                            PhotoPicker.builder()
                                    .setPhotoCount(PhotoAdapter.MAX)
                                    .setShowCamera(true)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(getContext(),MeFragment.this);
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(getContext(),MeFragment.this);
                        }
                    }
                }));

        view.findViewById(R.id.button_photo_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (files!= null){
                OkGo.post(UPLOAD_URL)
                        .addFileParams("photos",files)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                Toast.makeText(getActivity(), "上传失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                     Toast.makeText(getActivity(), "请选择图片", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode ==RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            File file ;
            files = new ArrayList<>();
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);

                for (int i = 0; i < photos.size(); i++) {
                    file = new File(photos.get(i));
                    files.add(file);
                }



            }
            selectedPhotos.clear();

            if (photos != null) {

                selectedPhotos.addAll(photos);
            }
            photoAdapter.notifyDataSetChanged();
        }
    }

}
