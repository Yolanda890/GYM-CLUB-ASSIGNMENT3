package com.example.asus.handbook.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asus.handbook.R;
import com.example.asus.handbook.adapter.SearchAdapter;
import com.example.asus.handbook.dataobject.Coach;
import com.example.asus.handbook.dataobject.Course;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //users
    private View view;
    private RecyclerView view_course;
    private SearchAdapter sAdapter;
    private String type;

    /* 加一个成员变量 */
    private String currentusername;

    private List<String> values1;
    private List<String> values2;
    private SwipeRefreshLayout refreshLayout;

    private OnFragmentInteractionListener mListener;

    public CourseFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            type = bundle.getString("type");

            /* 传入参数 */
            currentusername = bundle.getString("username");
        }
        else{
            type = "";
        }

        view=inflater.inflate(R.layout.fragment_course,null);
        view_course = view.findViewById(R.id.view_course);
        view_course.setLayoutManager(new LinearLayoutManager(getContext()));
        view_course.setItemAnimator(new DefaultItemAnimator());

        values1 = new ArrayList<>();
        values2 = new ArrayList<>();

        refreshLayout = view.findViewById(R.id.refresh_search);
        refreshLayout.setColorSchemeResources(R.color.blue,R.color.blue,R.color.green);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                refreshLayout.setRefreshing(false);
            }
        });

        refresh();

        return view;
    }

    private void refresh(){
        System.out.println("refresh");
        values1.clear();
        values2.clear();
        if(type.equalsIgnoreCase("教练")){
            BmobQuery<Coach> query=new BmobQuery<Coach>();
            query.findObjects(new FindListener<Coach>() {
                @Override
                public void done(List<Coach> list, BmobException e) {
                    if(e == null){
                        for(int i = 0;i < list.size();i++){
                            values1.add(list.get(i).getCName());
                            values2.add(list.get(i).getImage());
                        }

                        /* 加参数 */
                        sAdapter = new SearchAdapter("教练",currentusername,values1,values2, R.layout.layout_coursecard, getContext());
                        view_course.setAdapter(sAdapter);
                    }
                    else{
                        System.out.println("error");
                        Toast ts = Toast.makeText(getContext(),getResources().getString(getResources().getIdentifier("stringShowCourseFail", "string", getContext().getPackageName())), Toast.LENGTH_LONG);
                        ts.show() ;
                    }

                }
            });
        }
        else{
            BmobQuery<Course> query=new BmobQuery<Course>();
            query.addWhereEqualTo("coursetype",type);   //条件查询
            query.findObjects(new FindListener<Course>() {
                @Override
                public void done(List<Course> list, BmobException e) {
                    if(e == null){
                        for(int i = 0;i < list.size();i++){
                            values1.add(list.get(i).getName());
                            values2.add(list.get(i).getImage());
                        }

                        /* 加参数 */
                        sAdapter = new SearchAdapter("课程",currentusername,values1,values2, R.layout.layout_coursecard, getContext());
                        view_course.setAdapter(sAdapter);
                    }
                    else{
                        System.out.println("error");
                        Toast ts = Toast.makeText(getContext(),getResources().getString(getResources().getIdentifier("stringShowCourseFail", "string", getContext().getPackageName())), Toast.LENGTH_LONG);
                        ts.show() ;
                    }

                }
            });
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
