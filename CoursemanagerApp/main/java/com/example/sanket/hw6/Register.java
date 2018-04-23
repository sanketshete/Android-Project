package com.example.sanket.hw6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;



public class Register extends Fragment {
    private static final int CAMERA_INTENT_REQUEST = 5 ;
    private Bitmap image=null;
    ImageView im;
    final Registerinfo registerinfo = new Registerinfo();
    public Register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View view = inflater.inflate(R.layout.fragment_register, container, false);


        final EditText uname = (EditText)view.findViewById(R.id.uname);
        final EditText lname = (EditText)view.findViewById(R.id.lname);
        final EditText fname = (EditText)view.findViewById(R.id.fname);
        final EditText pass = (EditText)view.findViewById(R.id.pass);
           im = (ImageView)view.findViewById(R.id.addphoto);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_INTENT_REQUEST);
            }
        });
            view.findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ("".equalsIgnoreCase(uname.getText().toString().trim()) || "".equalsIgnoreCase(pass.getText().toString().trim()) ||
                            "".equalsIgnoreCase(fname.getText().toString().trim()) || "".equalsIgnoreCase(lname.getText().toString().trim())
                            ||image==null) {
                        Toast.makeText(getActivity(), "All fields are mandatory", Toast.LENGTH_LONG).show();
                    } else {
                        if(pass.getText().toString().length()< 8){
                            Toast.makeText(getActivity(), "Password should be of minimum 8 chracter", Toast.LENGTH_LONG).show();
                        }else {
                            registerinfo.setUsername(uname.getText().toString());
                            registerinfo.setPassowrd(pass.getText().toString());
                            registerinfo.setFirst_name(fname.getText().toString());
                            registerinfo.setLast_name(lname.getText().toString());
                            if (MainActivity.databaseDataManager.saveUser(registerinfo) != -1) {
                                Toast.makeText(getActivity(), "Register Successsfully", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), "Not Register Successsfully", Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                }

                ;

            });
     return  view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_INTENT_REQUEST){
            if(null!=data && null!=data.getExtras() && data.getExtras().containsKey("data")){
                image =(Bitmap) data.getExtras().get("data");
                registerinfo.setImage(image);
                im.setImageBitmap(image);
            }
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
public interface onInteractionWithRegister{
        public void afterRegister() ;
    }

}
