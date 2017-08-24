package com.tobiasandre.bakingapp.model;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Tobias Andre on 23/08/2017.
 */

@Database(name = BakingDb.NAME, version = BakingDb.VERSION)
public class BakingDb {
    public static final String NAME = "BakingDb";

    public static final int VERSION = 1;
}
