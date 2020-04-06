package edu.grinnell.appdev.grinnelldirectory.activities;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LocalJSON {

    private AppCompatActivity activity;

    public LocalJSON(AppCompatActivity tempActivity){
        activity = tempActivity;
    }

    public String loadJSONFromAsset(){
        String json = null;
        try {
            InputStream is = activity.getAssets().open("dummyData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void something(){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("persons");
            ArrayList<ArrayList<String>> formList = new ArrayList<ArrayList<String>>();
            ArrayList<String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                //Log.d("Details-->", jo_inside.getString("formule"));
                String firstName_value = jo_inside.getString("firstName");
                String lastName_value = jo_inside.getString("lastName");
                String userName_value = jo_inside.getString("userName");
                String classYear_value = jo_inside.getString("classYear");
                String reunionYear_value = jo_inside.getString("reunionYear");
                String box_value = jo_inside.getString("box");
                String email_value = jo_inside.getString("email");
                String major_value = jo_inside.getString("major");
                String minor_value = jo_inside.getString("minor");
                String address_value = jo_inside.getString("address");
                String address1_value = jo_inside.getString("address1");
                String address2_value = jo_inside.getString("address2");
                String address3_value = jo_inside.getString("address3");
                String address4_value = jo_inside.getString("address4");
                String phone_value = jo_inside.getString("phone");
                String homePhone_value = jo_inside.getString("homePhone");
                String city_value = jo_inside.getString("city");
                String state_value = jo_inside.getString("state");
                String zip_value = jo_inside.getString("zip");
                String country_value = jo_inside.getString("country");
                String bldg_value = jo_inside.getString("bldg");
                String room_value = jo_inside.getString("room");
                String spouse_value = jo_inside.getString("spouse");
                String alienStatus_value = jo_inside.getString("alienStatus");
                String hiatus_value = jo_inside.getString("hiatus");
                String imgPath_value = jo_inside.getString("imgPath");
                String office_phone_value = jo_inside.getString("office_phone");
                String office_email_value = jo_inside.getString("office_email");
                String office_addr_value = jo_inside.getString("office_addr");
                String office_box_value = jo_inside.getString("office_box");
                String type_value = jo_inside.getString("type");
                String page_order_value = jo_inside.getString("page_order");
                String crs_ID_value = jo_inside.getString("crs_ID");
                String position_name_value = jo_inside.getString("position_name");
                String office_hours_1_value = jo_inside.getString("office_hours_1");
                String office_hours_2_value = jo_inside.getString("office_hours_2");
                String office_hours_3_value = jo_inside.getString("office_hours_3");
                String office_hours_4_value = jo_inside.getString("office_hours_4");
                String personType_value = jo_inside.getString("personType");
                String deptMajorClass_value = jo_inside.getString("deptMajorClass");
                String title_value = jo_inside.getString("title");
                String title2_value = jo_inside.getString("title2");
                String title3_value = jo_inside.getString("title3");
                String title4_value = jo_inside.getString("title4");
                String title5_value = jo_inside.getString("title5");
                String title6_value = jo_inside.getString("title6");
                String title7_value = jo_inside.getString("title7");
                String title8_value = jo_inside.getString("title8");;

                //Add your values in your `ArrayList` as below:
                m_li = new ArrayList<String>();
                m_li.add(firstName_value);
                m_li.add(lastName_value);
                m_li.add(userName_value);
                m_li.add(classYear_value);
                m_li.add(reunionYear_value);
                m_li.add(box_value);
                m_li.add(email_value);
                m_li.add(major_value);
                m_li.add(minor_value);
                m_li.add(address_value);
                m_li.add(address1_value);
                m_li.add(address2_value);
                m_li.add(address3_value);
                m_li.add(address4_value);
                m_li.add(phone_value);
                m_li.add(homePhone_value);
                m_li.add(city_value);
                m_li.add(state_value);
                m_li.add(zip_value);
                m_li.add(country_value);
                m_li.add(bldg_value);
                m_li.add(room_value);
                m_li.add(spouse_value);
                m_li.add(alienStatus_value);
                m_li.add(hiatus_value);
                m_li.add(imgPath_value);
                m_li.add(office_addr_value);
                m_li.add(office_box_value);
                m_li.add(office_email_value);
                m_li.add(office_phone_value);
                m_li.add(type_value);
                m_li.add(page_order_value);
                m_li.add(crs_ID_value);
                m_li.add(position_name_value);
                m_li.add(office_hours_1_value);
                m_li.add(office_hours_2_value);
                m_li.add(office_hours_3_value);
                m_li.add(office_hours_4_value);
                m_li.add(personType_value);
                m_li.add(deptMajorClass_value);
                m_li.add(title_value);
                m_li.add(title2_value);
                m_li.add(title3_value);
                m_li.add(title4_value);
                m_li.add(title5_value);
                m_li.add(title6_value);
                m_li.add(title7_value);
                m_li.add(title8_value);

                formList.add(m_li);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
