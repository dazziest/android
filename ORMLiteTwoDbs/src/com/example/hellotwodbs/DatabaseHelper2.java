package com.example.hellotwodbs;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper2 extends OrmLiteSqliteOpenHelper {

	// name of the database file for your application -- change to something appropriate for your app
	private static final String DATABASE_NAME = "helloTwoDb2.db";
	// any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 3;

	// the DAO object we use to access the ComplexData table
	private Dao<ComplexData, Integer> complexDao = null;

	// we do this so there is only one helper
	private static DatabaseHelper2 helper = null;
	private static final AtomicInteger usageCounter = new AtomicInteger(0);

	private DatabaseHelper2(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Get the helper, possibly constructing it if necessary. For each call to this method, there should be 1 and only 1
	 * call to {@link #close()}.
	 */
	public static synchronized DatabaseHelper2 getHelper(Context context) {
		if (helper == null) {
			helper = new DatabaseHelper2(context);
		}
		usageCounter.incrementAndGet();
		return helper;
	}

	/**
	 * This is called when the database is first created. Usually you should call createTable statements here to create
	 * the tables that will store your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper2.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, ComplexData.class);

			// here we try inserting data in the on-create as a test
			Dao<ComplexData, Integer> dao = getComplexDataDao();
			long millis = System.currentTimeMillis();
			// create some entries in the onCreate
			ComplexData complex = new ComplexData(millis);
			dao.create(complex);
			complex = new ComplexData(millis + 1);
			dao.create(complex);
			Log.i(DatabaseHelper2.class.getName(), "created new ComplexData entries in onCreate: " + millis);
		} catch (SQLException e) {
			Log.e(DatabaseHelper2.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
	 * the various data to match the new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper2.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, ComplexData.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper2.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the Database Access Object (DAO) for our ComplexData class. It will create it or just give the cached
	 * value.
	 */
	public Dao<ComplexData, Integer> getComplexDataDao() throws SQLException {
		if (complexDao == null) {
			complexDao = getDao(ComplexData.class);
		}
		return complexDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs. For each call to {@link #getHelper(Context)}, there
	 * should be 1 and only 1 call to this method. If there were 3 calls to {@link #getHelper(Context)} then on the 3rd
	 * call to this method, the helper and the underlying database connections will be closed.
	 */
	@Override
	public void close() {
		if (usageCounter.decrementAndGet() == 0) {
			super.close();
			complexDao = null;
			helper = null;
		}
	}
}
