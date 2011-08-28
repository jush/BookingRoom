/**
   Copyright: 2011 Android Aalto

   This file is part of BookingRoom.

   BookingRoom is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 3 of the License, or
   (at your option) any later version.

   BookingRoom is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with BookingRoom. If not, see <http://www.gnu.org/licenses/>.
 */

package org.androidaalto.bookingroom;

import org.androidaalto.bookingroom.model.User;
import org.androidaalto.bookingroom.model.db.DataBaseHelper;
import org.androidaalto.bookingroom.model.db.MeetingDb;
import org.androidaalto.bookingroom.model.db.UserDb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.sql.Timestamp;

public class MainActivity extends Activity {
    DataBaseHelper myDbHelper = null;
    UserDb userDB;
    MeetingDb meetingDB;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        if ( myDbHelper != null ) {
            myDbHelper.close();
        }
        userDB = null;
        meetingDB = null;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        // Copy the database when the application is launched for the first time
        myDbHelper = new DataBaseHelper(this);
        userDB = new UserDb(myDbHelper);
        meetingDB = new MeetingDb(myDbHelper);

        Log.e("DBW", ""+userDB.returnUserCount());
        Log.e("DBW", ""+meetingDB.returnMeetingCount());
        User myUser = userDB.get("test@test.com");
        Log.e("DBW", ""+ myUser.getName());
        Log.e("DBW", ""+meetingDB.getMeetings(new Timestamp(1314519235 * 1000), new Timestamp(1314522839 * 1000)));
    }
}
