package com.bookt.bookt;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class A5_MenuTabLayoutAdapter extends FragmentPagerAdapter {

    private int tabCount = 0;

    public A5_MenuTabLayoutAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // should access the arraylist passed from here and return its corresponding fragment >>
        // MenuFragment menuFragment = new MenuFragment(tabList.get(position).get(0));
        // the above line considers the passed arraylist a LIST of LISTs.
        // Meaning, every index of it is an arraylist (menu category)
        // hence, we access the 0th index of that particular list to get the category itself

        MenuFragment menuFragment = new MenuFragment();
        return menuFragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    public static class MenuFragment extends Fragment{

        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        private MenuFragment.OnFragmentInteractionListener mListener;

        public MenuFragment() {

        }

        public static MenuFragment newInstance(String param1, String param2) {
            MenuFragment fragment = new MenuFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.a5_menu_fragment, container, false);

            ListView listView = view.findViewById(R.id.menuListView);

            ArrayList<A5_MenuItemsSetter> menuList = new ArrayList<>();

            menuList.add(new A5_MenuItemsSetter("Cheese Burgers", "Double patty with cheese", "", "6$"));
            menuList.add(new A5_MenuItemsSetter("Cheese Burgers", "Double patty with cheese", "", "6$"));
            menuList.add(new A5_MenuItemsSetter("Cheese Burgers", "Double patty with cheese", "", "6$"));
            menuList.add(new A5_MenuItemsSetter("Cheese Burgers", "Double patty with cheese", "", "6$"));
            menuList.add(new A5_MenuItemsSetter("Cheese Burgers", "Double patty with cheese", "", "6$"));
            menuList.add(new A5_MenuItemsSetter("Cheese Burgers", "Double patty with cheese", "", "6$"));

            A5_MenuListAdapter a5MenuListAdapter = new A5_MenuListAdapter(getContext(), menuList);

            listView.setAdapter(a5MenuListAdapter);

            return view;
        }

        public void onButtonPressed(Uri uri) {
            if (mListener != null) {
                mListener.onFragmentInteraction(uri);
            }
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof MenuFragment.OnFragmentInteractionListener) {
                mListener = (MenuFragment.OnFragmentInteractionListener) context;
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

        public interface OnFragmentInteractionListener {
            void onFragmentInteraction(Uri uri);
        }

    }
}
