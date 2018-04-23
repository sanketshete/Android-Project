package com.example.sanket.hw6;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddInstructure.AddInstructureFragmentInteraction} interface
 * to handle interaction events.
 */
public class AddInstructure extends Fragment {
    private static final int CAMERA_INTENT_REQUEST = 6 ;

    ImageView im;
    Bitmap image=null;
    private AddInstructureFragmentInteraction mListener;
    final InstructorInfo instructorInfo = new InstructorInfo();

    public AddInstructure() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_instructure, container, false);

        final EditText fname = (EditText)view.findViewById(R.id.ifname);
        final EditText lname = (EditText)view.findViewById(R.id.ilname);
        final EditText email = (EditText)view.findViewById(R.id.email);
        final EditText website = (EditText)view.findViewById(R.id.personalWeb);
        im =(ImageView) view.findViewById(R.id.imageView2);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_INTENT_REQUEST);
            }
        });

        final Button resetBtn =(Button) view.findViewById(R.id.reset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(getActivity());
                builder1.setMessage("Are you sure you want to reset ?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                fname.setText("");
                                lname.setText("");
                                email.setText("");
                                website.setText("");
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                android.app.AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equalsIgnoreCase(fname.getText().toString().trim()) || "".equalsIgnoreCase(email.getText().toString().trim()) ||
                        "".equalsIgnoreCase(website.getText().toString().trim()) || "".equalsIgnoreCase(lname.getText().toString().trim())
                        || image ==null) {
                    Toast.makeText(getActivity(), "All fields are mandatory", Toast.LENGTH_LONG).show();
                } else {
                    if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                        Toast.makeText(getActivity(), "Put a valid email address", Toast.LENGTH_LONG).show();
                    }else {
                        instructorInfo.setInstructor_fname(fname.getText().toString());
                        instructorInfo.setInstructor_lname(lname.getText().toString());
                        instructorInfo.setInstructor_email(email.getText().toString());
                        instructorInfo.setInstructor_website(website.getText().toString());
                        if (MainActivity.databaseDataManager.saveInstructor(instructorInfo) != -1) {
                            Toast.makeText(getActivity(), "Instructor Added Successsfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Failed to add instructor", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            ;

        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_INTENT_REQUEST){
            if(null!=data && null!=data.getExtras() && data.getExtras().containsKey("data")){
                image =(Bitmap) data.getExtras().get("data");
                instructorInfo.setImage(image);
                im.setImageBitmap(image);
            }
        }

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddInstructureFragmentInteraction) {
            mListener = (AddInstructureFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement createCourseFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface AddInstructureFragmentInteraction {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
