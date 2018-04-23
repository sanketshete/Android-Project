package com.example.sanket.hw6;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseManagerFragmentInteraction} interface
 * to handle interaction events.
 */
public class courseManager extends Fragment {

    private CourseManagerFragmentInteraction mListener;
    List<CourseInfo> courseList ;
    CourseManagerAdapter adapter ;
    public String userName ;
    public courseManager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_manager, container, false);

        userName = mListener.onFragmentInteractionWithCourseManager() ;
        courseList = MainActivity.databaseDataManager.getAllCourses() ;
        List<InstructorInfo> iList = MainActivity.databaseDataManager.getAll() ;
        if(courseList == null) {
            courseList = new ArrayList<CourseInfo>() ;
        }
        ImageView addCourseButton = (ImageView)view.findViewById(R.id.addCourseCm) ;
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Implement add course button
                mListener.addCourseOnClick();
            }
        });
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.courseListRv) ;
        if(courseList == null || courseList.size() == 0) {
            view.findViewById(R.id.noCourseTvCm).setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            return view;
        }

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity()) ;
        recyclerView.setLayoutManager(llm);
        adapter = new CourseManagerAdapter(getActivity(),courseList) ;
        adapter.setInstructorList(iList);
        recyclerView.setAdapter(adapter);

        return view ;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CourseManagerFragmentInteraction) {
            mListener = (CourseManagerFragmentInteraction) context;
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
    public interface CourseManagerFragmentInteraction {
        // TODO: Update argument type and name
        String onFragmentInteractionWithCourseManager();
        void addCourseOnClick() ;
    }
}
