package com.dannextech.apps.eventmanager;

import android.provider.BaseColumns;

/**
 * Created by amoh on 12/7/2017.
 */

public class EventDbContract {
    public EventDbContract(){};
    public static class EventDbDetails implements BaseColumns{
        public static final String TABLE_NAME = "events";
        public static final String COL_TITLE = "title";
        public static final String COL_DESCRIPTION = "description";
        public static final String COL_VENUE = "venue";
        public static final String COL_DATE = "date";
        public static final String COL_TIME = "time";
        public static final String COL_REMIND = "remind";
    }

    public static class TaskDbDetails implements BaseColumns{
        public static final String TABLE_NAME = "tasks";
        public static final String COL_TITLE = "title";
        public static final String COL_DESCRIPTION = "description";
        public static final String COL_DATE = "date";
        public static final String COL_TIME = "time";
        public static final String COL_REMIND = "remind";
    }
}
