package org.techtown.scheduledetail2;

import android.provider.BaseColumns;

public class ScheduleForm {
    private ScheduleForm(){}

    public static class ScheduleEntry implements BaseColumns {
        public static final String TABLE_NAME = "schedule";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENTS = "contents";
        public static final String COLUMN_START_LOCATION = "start_location";
        public static final String COLUMN_END_LOCATION = "end_location";
        public static final String COLUMN_START_DATE= "st_date";
        public static final String COLUMN_START_TIME= "st_time";
        public static final String COLUMN_END_DATE = "end_date";
        public static final String COLUMN_END_TIME= "end_time";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_TITLE + " TEXT," +
                        COLUMN_CONTENTS + " TEXT," +
                        COLUMN_START_LOCATION + " TEXT," +
                        COLUMN_END_LOCATION + " TEXT," +
                        COLUMN_START_DATE + " TEXT," +
                        COLUMN_START_TIME + " TEXT," +
                        COLUMN_END_DATE + " TEXT," +
                        COLUMN_END_TIME+ " TEXT)";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        // SELECT * FROM TABLE_NAME WHERE COLUMN_START_DATE<=DATE<=COLUMN_END_DATE
/*
        public static final String SQL_SELECT_TODAY_SCHEDULE=
                "SELECT " +COLUMN_TITLE+" "+
                        "FROM "+TABLE_NAME+
                        " WHERE "
*/

    }
}
