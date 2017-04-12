/*****************************************************
 * RequestTask
 * This method accepts a string of a valid URL request to DB.
 * It gathers the HTML request, and parses it.
 * It returns an ArrayList of Profiles of the entries.
 ***************************************************/

package edu.grinnell.appdev.grinnelldirectory;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.grinnell.appdev.grinnelldirectory.interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DBScraper extends AsyncTask<String, Void, List<Person>> {

    final public static int NO_ERROR = 0;
    final public static int NO_ENTRIES = 1;
    final public static int TOO_MANY_ENTRIES = 2;
    final public static int NO_RESPONSE_STRING = 3;

    public int errorCode = NO_ERROR;

    private Context context;
    private APICallerInterface apiInterface;

    private String responseString; // makeRequest() stores its response here
    private List<Person> persons; // The final product, a list of downloaded
    private String currentUri; // The current page content is being downloaded from
    private final String UNAVAILABLE = "This data is unavailable off-campus.";

    public DBScraper(Context context, APICallerInterface apiInterface) {
        super();
        this.context = context;
        this.apiInterface = apiInterface;
    }

    @Override
    protected void onPreExecute() {
    }

    protected List<Person> doInBackground(String... uri) {
        persons = new ArrayList<Person>();
        currentUri = uri[0];
        iterativelyScrapePages();
        return persons;
    }

    @Override
    protected void onPostExecute(List<Person> result) {
        switch (errorCode) {
            case TOO_MANY_ENTRIES:
                apiInterface.onServerFailure("Too many results. Please refine search.");
                break;
            case NO_ENTRIES:
                apiInterface.onServerFailure("No results found.");
                break;
            case NO_RESPONSE_STRING:
                apiInterface.onNetworkingError("Please make sure you are connected to the internet.");
                break;
            default:
                apiInterface.onSearchSuccess(result);
                break;
        }
        super.onPostExecute(result);
    }

    // Adds the queried entries to persons
    private void iterativelyScrapePages() {
        do {
            makeRequest(); // download the next page of content
        } while (parseResponse()); // Parse the content. If parseResponse()
        // returns true, a next page exists.
    }

    // This method is a basic HTTP request. It saves the HTML response to
    // responseString.
    private int makeRequest() {
        OkHttpClient client = new OkHttpClient();
        Response response;
        Request request = new Request.Builder()
                .url(currentUri)
                .build();
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                responseString = response.body().string();
                return 0;
            } else {
                errorCode = NO_RESPONSE_STRING;
            }
        } catch (IOException e) {
            errorCode = NO_RESPONSE_STRING;
        }
        return -1;
    }

    // This method parses out entry information an HTML response, and adds
    // Profile objects to persons.
    // responseString must be a valid grinnell College db page
    // This method does not know how to handle the "too many entries" response
    // and the off-campus response.
    private boolean parseResponse() {
        if (responseString == null) {
            errorCode = NO_RESPONSE_STRING;
            return false;
        }
        // Set up the tokenizer, seperating by token '\n'. You should find out
        // what a tokenizer is.
        StringTokenizer strTok = new StringTokenizer(responseString, "\n");
        String curTok, picurl, firstName, lastName, username, dept, phonenum, campusaddress, boxno, stufacstatus, sgapos;
        // boolean indicating if there exists a next page.
        boolean anotherPage = false;
        boolean onCampus = false;

        if (!responseString.contains("off campus viewers")) {
            onCampus = true;
            strTok.nextToken();
            curTok = strTok.nextToken();
        }

        curTok = strTok.nextToken();
        while (!curTok.contains("<p>")) {
            curTok = strTok.nextToken();
        }

        if (curTok.contains("pages")) {
            errorCode = TOO_MANY_ENTRIES;
            return false;
        } else if (curTok.contains("<strong>no</strong>")) {
            errorCode = NO_ENTRIES;
            return false;
        }

        // skip useless information
        for (int i = 0; i < 9; i++) {
            strTok.nextToken();
        }
        curTok = strTok.nextToken();


        // If a next page button exsts, then there is a next page.
        // Grab URL of next pageand set return value of method to true.
        if (curTok.contains("Next Page")) {
            anotherPage = true;
            currentUri = "https://itwebapps.grinnell.edu"
                    + curTok.substring(53, curTok.length() - 38);
            for (int i = 0; i < 22; i++)
                strTok.nextToken();
            curTok = strTok.nextToken();
        } else {
            anotherPage = false;
            for (int i = 0; i < 20; i++) {
                strTok.nextToken();
            }
            curTok = strTok.nextToken();
        }

        // loop, keeps adding entries to persons until there are none.
        do {
            if (onCampus) {
                // parse entries
                // parse image URL. If no image, save " ".
                if (curTok.contains("image1"))
                    picurl = curTok.substring(
                            curTok.indexOf("img src=\"") + 9,
                            curTok.indexOf("\" alt=\""));
                else
                    picurl = "";
                curTok = strTok.nextToken();
            } else {
                picurl = "";
                curTok = strTok.nextToken();
            }
            String fullName;
            // parse full name
            if (onCampus) {
                fullName = curTok.substring(
                        curTok.substring(40).indexOf('>') + 41, curTok
                                .substring(40).indexOf('<') + 40);
            } else {
                String rawName = dataParser(curTok);
                fullName = rawName;
            }
            if (fullName == null) {
                errorCode = NO_ENTRIES;
                return false;
            }
            firstName = fullName.substring(0, fullName.indexOf(','));
            lastName = fullName.substring(fullName.indexOf(',') + 2);
            curTok = strTok.nextToken();

            if (onCampus) {
                // parse student major or faculty department
                dept = curTok.substring(35, curTok.indexOf("</td>"));
                String smallerdeptString = curTok.substring(curTok
                        .indexOf("tny") + 6);
                // some faculty/staff have multiple titles
                if (dept.contains("tny")) {
                    dept = facStaffTitle(dept);
                }
                curTok = strTok.nextToken();

                // parse phone number, username, campus address, box #,
                // student/faculty status
                if (curTok.charAt(37) != '<') {
                    phonenum = curTok.substring(37, 41);
                    if (phonenum.contains("-")) {
                        phonenum = "";
                    }
                } else {
                    phonenum = "";
                }
            } else {
                dept = UNAVAILABLE;
                phonenum = UNAVAILABLE;
                curTok = strTok.nextToken();
            }
            curTok = strTok.nextToken();

            if (!curTok.contains("&nbsp")) {
                username = curTok.substring(53, curTok.indexOf('@'));
            } else {
                username = "";
            }
            strTok.nextToken();
            curTok = strTok.nextToken();

            if (onCampus) {
                campusaddress = curTok
                        .substring(0, curTok.indexOf("</TD>"));
                campusaddress = campusaddress.trim();
                curTok = strTok.nextToken();
                boxno = curTok.substring(36, curTok.indexOf("</TD>"));
                if (boxno.equals("&nbsp;"))
                    boxno = "Not Available";
                curTok = strTok.nextToken();
                stufacstatus = curTok.substring(37,
                        curTok.indexOf(" </TD>"));
                strTok.nextToken();
            } else {
                campusaddress = UNAVAILABLE;
                boxno = UNAVAILABLE;
                stufacstatus = UNAVAILABLE;
                curTok = strTok.nextToken();
                curTok = strTok.nextToken();
                curTok = strTok.nextToken();
            }
            curTok = strTok.nextToken();

            // parse SGA status
            sgapos = "";
            if (curTok.equals("<tr>\r")) {
                // senator
                for (int i = 0; i < 3; i++)
                    curTok = strTok.nextToken();
                sgapos = curTok.substring(18, curTok.indexOf("</span>"));
                while (!curTok.contains("window.open") && !curTok.contains("New Search") && !curTok.contains("style=\"text-align:center;\"")) {
                    curTok = strTok.nextToken();
                }
            }

            // Adds a new Profile to persons containing all the newly
            // parsed information
            Person person = new Person();
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setImgPath(picurl);
            person.setUserName(username);
            person.setDeptMajorClass(dept);
            person.setMajor(dept);
            person.setPhone(phonenum);
            person.setAddress(campusaddress);
            person.setBox(boxno);
            person.setPositionName(sgapos);
            person.setTitle(stufacstatus);
            persons.add(person);

        } while (curTok.contains("&nbsp")); // determine if there is another
        // entry to be parsed

        return anotherPage;// returns boolean indicating if there exists a
        // next page.

			/*
             * if(anotherPage){ for(int i=0; i<6; i++) strTok.nextToken();
			 * curTok = strTok.nextToken();
			 * 
			 * String beginningOfURL = curTok.substring(66); return
			 * "https://itwebapps.grinnell.edu" + beginningOfURL.substring(0,
			 * beginningOfURL.indexOf('"'));
			 * 
			 * }
			 */
    }


    /**
     * @param str
     * @return A simple Regex parser for stripping out numbers
     */

    private String numberParser(String str) {
        String match = "[0-9]+";
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            return m.group(0);
        }
        return null;
    }

    private static String stripBrackets(String str) {
        return str.substring(1, str.length() - 1);
    }

    private static String imageParser(String str) {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            return m.group(0);
        }
        return null;
    }

    /**
     * @param str
     * @return HTML parser when content includes name and email (Regex)
     */

    private static String[] nameEmailRoleParser(String str) {
        int addIndex = 0;
        String[] returnArr = new String[3];
        String match = ">([A-z].*?)<";
        Pattern pattern = Pattern.compile(match);
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            returnArr[addIndex++] = stripBrackets(m.group());
        }
        return returnArr;
    }

    /**
     * @param str
     * @return Generic HTML parser using Regex
     */
    private String dataParser(String str) {
        String match = ">([A-z].*?)<";
        Pattern pattern = Pattern.compile(match);
        Matcher m = pattern.matcher(str);
        if (m.find()) {
            return m.group(0).substring(1, m.group(0).length() - 1);
        } else {
            return null;
        }
    }

    /**
     * @param title
     * @return Get relevantdata regarding Staff and faculty
     */
    private String facStaffTitle(String title) {
        boolean inBracket = false;
        String tmp = "";
        boolean lastcharintempissemicolon = false;

        for (int i = 0; i < title.length(); i++) {
            if (!inBracket && title.charAt(i) != '<') {
                tmp += title.charAt(i);
                lastcharintempissemicolon = true;
            }
            if (title.charAt(i) == '>') {
                inBracket = false;
                if (!lastcharintempissemicolon)
                    tmp += ";";
                lastcharintempissemicolon = false;
            }
            if (title.charAt(i) == '<') {
                inBracket = true;
            }

        }
        return tmp;
    }

}
