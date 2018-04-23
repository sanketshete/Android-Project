package com.example.sanket.hw6;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link instructorManager.onInstructorManagerFragmentInteraction} interface
 * to handle interaction events.
 */
public class instructorManager extends Fragment {
    InstructorManagerAdapter adapter ;
    private onInstructorManagerFragmentInteraction mListener;
    List<InstructorInfo> instructor_infoList ;

    public List<InstructorInfo> getInstructor_infoList() {
        return instructor_infoList;
    }

    public void setInstructor_infoList(List<InstructorInfo> instructor_infoList) {
        this.instructor_infoList = instructor_infoList;
    }

    public instructorManager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_instructor_manager, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.InstructorListRv) ;
        instructor_infoList = MainActivity.databaseDataManager.getAll() ;
        if(instructor_infoList == null || instructor_infoList.size() == 0) {
            view.findViewById(R.id.noInstructorTvCm).setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            return view;
        }

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity()) ;
        recyclerView.setLayoutManager(llm);
        adapter = new InstructorManagerAdapter(getActivity(),instructor_infoList) ;
        recyclerView.setAdapter(adapter);

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onInstructorManagerFragmentInteraction) {
            mListener = (onInstructorManagerFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onInstructorManagerFragmentInteraction");
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
    public interface onInstructorManagerFragmentInteraction {
        // TODO: Update argument type and name
        void onInstructorManagerInteraction();
    }
}
