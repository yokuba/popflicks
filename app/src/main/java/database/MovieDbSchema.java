package database;

/**
 * Created by amac on 9/26/16.
 */

public class MovieDbSchema {
    public static final class MovieTable {
        public static final String NAME = "movies";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String VOT_AVG = "vot_avg";
            public static final String RELEASE = "release";
            public static final String DESCRIPTION = "description";
            public static final String POSTER = "poster";
        }
    }
}
