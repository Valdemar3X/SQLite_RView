package com.example.sqlite_rview;

import android.provider.BaseColumns;

public class MyConstants {
  private MyConstants(){}

   public static final class DbConstants implements  BaseColumns {
        public static final String TABLE_NAME = "groceryList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
