package com.example.demoapp.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.example.demoapp.CustomGrid;
import com.example.demoapp.ExpandableHeightGridView;
import com.example.demoapp.R;

import java.util.Date;
import java.util.List;

public class earningWays extends Fragment {

    public earningWays() {
        // Required empty public constructor
    }

    private ExpandableHeightGridView gridSite;
    private ExpandableHeightGridView gridApps;
    private FrameLayout social_media_container;
    private CardView rateCardView;
    private CardView inviteCardView;

    String[] socialMedia = {
            "Twitter",
            "Instagram",
            "Facebook",
            "Youtube",
            "Linkedln",
            "Linkedln",

    } ;
    int[] imageId = {
            R.drawable.twitter,
            R.drawable.instagram,
            R.drawable.facebook,
            R.drawable.youtube,
            R.drawable.linkedln,
            R.drawable.linkedln

    };

    String[] appsName = {
            "Twitter",
            "Instagram",
            "Facebook",
            "Youtube",
            "Linkedln"

    } ;
    int[] appId = {
            R.drawable.twitter,
            R.drawable.instagram,
            R.drawable.facebook,
            R.drawable.youtube,
            R.drawable.linkedln

    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_earning_ways, container, false);

        gridSite=view.findViewById(R.id.gridSite);
        gridApps=view.findViewById(R.id.gridApps);

        rateCardView=view.findViewById(R.id.rate_cardView);
        inviteCardView=view.findViewById(R.id.invite_cardView);

        social_media_container=view.findViewById(R.id.social_media_container);

        CustomGrid adapter2 = new CustomGrid(getContext(), appsName, appId);
        gridApps.setAdapter(adapter2);
        gridApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(Intent.ACTION_VIEW);

                switch (appsName[+position]) {
                    case "Twitter":
                        intent.setData(Uri.parse("market://details?id=com.facebook.katana"));
                        break;
                    case "Instagram":
                        intent.setData(Uri.parse("market://details?id=com.facebook.katana"));
                        break;
                    case "FaceBook":
                        intent.setData(Uri.parse("market://details?id=com.facebook.katana"));
                        break;
                    case "YouTube":
                        intent.setData(Uri.parse("market://details?id=com.facebook.katana"));
                        break;
                    case "Linkedln":
                        intent.setData(Uri.parse("market://details?id=com.facebook.katana"));
                        break;
                    default:
                        break;
                }

                startActivity(intent);
            }
        });
        gridApps.setExpanded(true);


        rateCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.facebook.katana"));
                startActivity(intent);
            }
        });

        inviteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String poem_string = getString(R.string.share_content);

                Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.share);
                String  path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), b, "Title", null);


                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/*");
                    Uri file = Uri.parse(path);
                    share.putExtra(Intent.EXTRA_STREAM,file);
                    share.putExtra(Intent.EXTRA_TEXT, poem_string);
                    startActivity(Intent.createChooser(share, "Share Via"));
                }
                else{
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            200);
                }
            }
        });

        CustomGrid adapter = new CustomGrid(getContext(), socialMedia, imageId);
        gridSite.setAdapter(adapter);
        gridSite.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            WebviewFragment webviewFragment = new WebviewFragment();
            Bundle bundle = new Bundle();

            switch (socialMedia[+position]) {
                case "Twitter":
                    bundle.putString("url", "https://www.instagram.com/iniestawebtech/");
                break;
                case "Instagram":
                    bundle.putString("url", "https://www.instagram.com/iniestawebtech/");
                    break;
                case "FaceBook":
                    bundle.putString("url", "https://www.facebook.com/iniestawebtech/");
                    break;
                case "YouTube":
                    bundle.putString("url", "https://www.facebook.com/iniestawebtech/");
                    break;
                case "Linkedln":
                    bundle.putString("url", "https://www.linkedin.com/in/iniesta-webtech-solution-private-limited-111b82184/");
                    break;
                default:
                    break;
            }
            webviewFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.social_media_container, webviewFragment).addToBackStack(null).commit();
            }
        });
        gridSite.setExpanded(true);


        return view;
    }
}
