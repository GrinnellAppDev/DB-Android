[1mdiff --git a/app/src/main/java/edu/grinnell/appdev/grinnelldirectory/fragments/AdvancedSearchFragment.java b/app/src/main/java/edu/grinnell/appdev/grinnelldirectory/fragments/AdvancedSearchFragment.java[m
[1mindex 9dd3074..489145c 100644[m
[1m--- a/app/src/main/java/edu/grinnell/appdev/grinnelldirectory/fragments/AdvancedSearchFragment.java[m
[1m+++ b/app/src/main/java/edu/grinnell/appdev/grinnelldirectory/fragments/AdvancedSearchFragment.java[m
[36m@@ -3,12 +3,9 @@[m [mpackage edu.grinnell.appdev.grinnelldirectory.fragments;[m
 import android.os.Bundle;[m
 import android.support.annotation.Nullable;[m
 import android.support.v4.app.Fragment;[m
[31m-import android.support.v4.app.FragmentActivity;[m
 import android.view.LayoutInflater;[m
 import android.view.View;[m
 import android.view.ViewGroup;[m
[31m-import android.widget.Button;[m
[31m-import android.widget.EditText;[m
 [m
 import java.io.Serializable;[m
 [m
[36m@@ -22,7 +19,7 @@[m [mpublic class AdvancedSearchFragment extends Fragment implements Serializable {[m
     @Nullable[m
     @Override[m
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {[m
[31m-        view = inflater.inflate(R.layout.advanced_search_fragment, null);[m
[32m+[m[32m        view = inflater.inflate(R.layout.fragment_advanced_search, null);[m
         return view;[m
     }[m
 [m
[1mdiff --git a/app/src/main/java/edu/grinnell/appdev/grinnelldirectory/fragments/SimpleSearchFragment.java b/app/src/main/java/edu/grinnell/appdev/grinnelldirectory/fragments/SimpleSearchFragment.java[m
[1mindex 256f293..83a7d5b 100644[m
[1m--- a/app/src/main/java/edu/grinnell/appdev/grinnelldirectory/fragments/SimpleSearchFragment.java[m
[1m+++ b/app/src/main/java/edu/grinnell/appdev/grinnelldirectory/fragments/SimpleSearchFragment.java[m
[36m@@ -3,7 +3,6 @@[m [mpackage edu.grinnell.appdev.grinnelldirectory.fragments;[m
 import android.os.Bundle;[m
 import android.support.annotation.Nullable;[m
 import android.support.v4.app.Fragment;[m
[31m-import android.support.v4.app.FragmentActivity;[m
 import android.view.LayoutInflater;[m
 import android.view.View;[m
 import android.view.ViewGroup;[m
[36m@@ -12,9 +11,6 @@[m [mimport android.widget.EditText;[m
 [m
 import java.io.Serializable;[m
 [m
[31m-import butterknife.BindView;[m
[31m-import butterknife.ButterKnife;[m
[31m-import butterknife.OnClick;[m
 import edu.grinnell.appdev.grinnelldirectory.R;[m
 [m
 [m
[36m@@ -29,7 +25,7 @@[m [mpublic class SimpleSearchFragment extends Fragment implements Serializable {[m
     @Nullable[m
     @Override[m
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {[m
[31m-        view = inflater.inflate(R.layout.simple_search_fragment, null);[m
[32m+[m[32m        view = inflater.inflate(R.layout.fragment_simple_search, null);[m
         return view;[m
     }[m
 [m
[1mdiff --git a/app/src/main/res/layout/advanced_search_fragment.xml b/app/src/main/res/layout/fragment_advanced_search.xml[m
[1msimilarity index 100%[m
[1mrename from app/src/main/res/layout/advanced_search_fragment.xml[m
[1mrename to app/src/main/res/layout/fragment_advanced_search.xml[m
[1mdiff --git a/app/src/main/res/layout/simple_search_fragment.xml b/app/src/main/res/layout/fragment_simple_search.xml[m
[1msimilarity index 100%[m
[1mrename from app/src/main/res/layout/simple_search_fragment.xml[m
[1mrename to app/src/main/res/layout/fragment_simple_search.xml[m
