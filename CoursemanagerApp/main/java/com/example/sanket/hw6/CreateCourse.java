package com.example.sanket.hw6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link createCourseFragmentInteraction} interface
 * to handle interaction events.
 */
public class CreateCourse extends Fragment {

    private createCourseFragmentInteraction mListener;
    public List<InstructorInfo> instructorList ;
    public InstructorAdapter adapter ;
    public courseInformation newCourseInfo ;
    String globalTemp = null ;
    public CreateCourse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_course, container, false);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity()) ;
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.ChooseInstructorRv) ;
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        instructorList = MainActivity.databaseDataManager.getAll() ;
        if(instructorList == null) {
            instructorList = new ArrayList<InstructorInfo>();
        }
        adapter = new InstructorAdapter(getActivity(),instructorList) ;
        recyclerView.setAdapter(adapter);
        newCourseInfo = new courseInformation() ;
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"} ;
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,days) ;
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner daySpinner = (Spinner)view.findViewById(R.id.daySp) ;
        setOnClickListenerFordaySpinner(daySpinner);
        daySpinner.setAdapter(dayAdapter);
        String[] amPm = {"Am","Pm"} ;
        ArrayAdapter<String> amPmAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,amPm) ;
        amPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner amPmSpinner = (Spinner)view.findViewById(R.id.amPmSp) ;
        setOnClickListenerForAmPmSpinner(amPmSpinner);
        amPmSpinner.setAdapter(amPmAdapter);
        String[] semesters = {"Spring","Summer","Fall"} ;
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,semesters) ;
        amPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner semesterSpinner = (Spinner)view.findViewById(R.id.semesterSp) ;
        setOnClickListenerForSemesterSpinner(semesterSpinner);
        semesterSpinner.setAdapter(semesterAdapter);
        if(instructorList == null || instructorList.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            view.findViewById(R.id.noInstructorTvCC).setVisibility(View.VISIBLE);
        }
        Button createBtn = (Button)view.findViewById(R.id.createBtn) ;
        setOnClickListenerForCreate(createBtn,view);
        Button resetBtn = (Button)view.findViewById(R.id.resetBtn) ;
        setOnClickListenerForReset(resetBtn,view);
        RadioGroup rg = (RadioGroup)view.findViewById(R.id.creditsRG) ;
        setOnCheckedChangeListenerForCredits(rg);
        return view ;
    }

    public void setOnCheckedChangeListenerForCredits(RadioGroup rg) {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.creditOne) {
                    newCourseInfo.setCredit("1");
                }else if(checkedId == R.id.credittwo) {
                    newCourseInfo.setCredit("2");
                }else if(checkedId == R.id.creditThree){
                    newCourseInfo.setCredit("3");
                }
            }
        });

    }

    public void setOnClickListenerForCreate(Button createBtn,View oView) {
        final View view = oView ;
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etv = (EditText) view.findViewById(R.id.hoursCC) ;
                if("".equalsIgnoreCase(etv.getText().toString()) || etv.getText() == null) {
                    Toast.makeText(getActivity(), "One or more mandatory fields missing!", Toast.LENGTH_SHORT).show();
                    return ;
                }
                StringBuilder sb = new StringBuilder(etv.getText().toString()) ;
                etv = (EditText)view.findViewById(R.id.minutesCC) ;
                if("".equalsIgnoreCase(etv.getText().toString()) || etv.getText() == null) {
                    Toast.makeText(getActivity(), "One or more mandatory fields missing!", Toast.LENGTH_SHORT).show();
                    return ;
                }
                sb.append(":"+etv.getText().toString()) ;
                newCourseInfo.setTime(sb.toString());
                etv=(EditText)view.findViewById(R.id.courseTitleEt) ;
                newCourseInfo.setTitle(etv.getText().toString());
                CourseInfo obj = convertObject(newCourseInfo) ;
                Log.d("json",obj.toString()) ;
                if(!checkForMandatoryFields(obj)) {
                    Toast.makeText(getActivity(), "One or more mandatory fields missing!", Toast.LENGTH_SHORT).show();
                return;
                }
                long l = MainActivity.databaseDataManager.saveCourse(obj) ;
                Log.d("json",String.valueOf(l)) ;
                if(l==-1) {
                    Toast.makeText(getActivity(), "Failed to create course", Toast.LENGTH_SHORT).show();
                    return ;
                }else{
                    Toast.makeText(getActivity(), "Course created successfully!", Toast.LENGTH_SHORT).show();
                        return ;
                }
            }
        });
    }

    private boolean checkForMandatoryFields(CourseInfo course) {
        boolean allEntered = true ;
        if("".equalsIgnoreCase(course.getTitle().trim()) || course.getTitle() == null) {
            return !allEntered ;
        }
        if("".equalsIgnoreCase(course.getTime().trim()) || course.getTime() == null) {
            return !allEntered ;
        }
        if("".equalsIgnoreCase(course.getCredithr()) || course.getCredithr() == null) {
            return !allEntered ;
        }
        if(course.getInstructor_id() == -1) {
            return !allEntered ;
        }
        return allEntered ;
    }

    public CourseInfo convertObject(courseInformation ci) {
        CourseInfo courseInfo = new CourseInfo() ;
        courseInfo.setTitle(ci.getTitle());
        courseInfo.setDay(ci.getDay());
        courseInfo.setAmpm(ci.getAmPm()) ;
        courseInfo.setCredithr(ci.getCredit());
        courseInfo.setTime(ci.getTime());
        courseInfo.setSemister(ci.getSemester());
        for(int i=0 ; i<adapter.instructorList.size() ; i++) {
            if(adapter.instructorList.get(i).isChecked()) {
                courseInfo.setInstructor_id(adapter.instructorList.get(i).getInstr_ID());
            }
        }
        return courseInfo ;
    }


    public void setOnClickListenerForReset(Button resetBtn,View originalView) {

        final View view = originalView ;

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("Are you sure you want to reset ?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                newCourseInfo = new courseInformation() ;
                                EditText tv = (EditText)view.findViewById(R.id.courseTitleEt) ;
                                tv.setText("");
                                EditText etv = (EditText) view.findViewById(R.id.hoursCC) ;
                                etv.setText("");
                                etv = (EditText)view.findViewById(R.id.minutesCC) ;
                                etv.setText("");
                                Spinner s = (Spinner)view.findViewById(R.id.daySp) ;
                                s.setSelection(0);
                                s = (Spinner)view.findViewById(R.id.amPmSp) ;
                                s.setSelection(0);
                                s = (Spinner)view.findViewById(R.id.semesterSp) ;
                                s.setSelection(0);
                                RadioGroup rg = (RadioGroup)view.findViewById(R.id.creditsRG) ;
                                rg.clearCheck();
                                for(int i=0 ; i<adapter.instructorList.size() ; i++){
                                    adapter.instructorList.get(i).setChecked(false);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

    }

    public void setOnClickListenerFordaySpinner(Spinner daySpinner) {

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("query",parent.getItemAtPosition(position).toString());
                newCourseInfo.setDay(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void setOnClickListenerForAmPmSpinner(Spinner amPmSpinner) {
        amPmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newCourseInfo.setAmPm(parent.getItemAtPosition(position).toString()) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void setOnClickListenerForSemesterSpinner(Spinner semesterSpinner) {
        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newCourseInfo.setSemester(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public ArrayList<String> createDaySpinnerArray() {
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"} ;
        ArrayList<String> daysList = new ArrayList<String>(Arrays.asList(days)) ;
        return daysList;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof createCourseFragmentInteraction) {
            mListener = (createCourseFragmentInteraction) context;
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
    public interface createCourseFragmentInteraction {
        // TODO: Update argument type and name
        void interactWithCreateCourse(String username);
    }
}
