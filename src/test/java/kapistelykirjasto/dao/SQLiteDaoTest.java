package kapistelykirjasto.dao;

import java.io.*;
import java.sql.*;

import kapistelykirjasto.domain.Entry;

import org.junit.*;

import static org.junit.Assert.*;

public class SQLiteDaoTest {
	
	private SQLiteDao dao;
	private final File testDatabaseFile = new File("test_database.db");
	
	@Before
	public void setUp() throws SQLException, IOException {
		assertTrue(testDatabaseFile.createNewFile());
		this.dao = new SQLiteDao(testDatabaseFile.getAbsolutePath());
	}
	
	@After
	public void tearDown( ) {
		this.dao.close();
		assertTrue(testDatabaseFile.delete());
	}
	
	@Test
	public void constructorCreatesEntryTable() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + testDatabaseFile.getAbsolutePath());
		ResultSet tables = connection.getMetaData().getTables(null, null, null, null);
		
		boolean entryTableExists = false;
		while (tables.next()) {
			if (tables.getString("TABLE_NAME").equals("entry")) {
				entryTableExists = true;
			}
		}
		
		assertTrue(entryTableExists);
	}
	
	@Test
	public void createEntryCreatesRowInTableEntry() throws SQLException {
		this.dao.createEntry(new Entry("Test"));
		
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + testDatabaseFile.getAbsolutePath());
		Statement statement = connection.createStatement();
		ResultSet entries = statement.executeQuery("SELECT * FROM entry");
		
		assertTrue(entries.next());
		assertTrue(entries.getString("title").equals("Test"));
		
		entries.close();
		statement.close();
		connection.close();
	}
	
	@Test
	public void createEntryReturnsFalseForDuplicateEntry() throws SQLException {
		this.dao.createEntry(new Entry("Test"));
		assertFalse(this.dao.createEntry(new Entry("Test")));
	}

	@Test
	public void getEntriesReturnsRightSizeList() throws SQLException {
		this.dao.createEntry(new Entry("Test1"));
		this.dao.createEntry(new Entry("Test2"));
		this.dao.createEntry(new Entry("Test3"));

		assertEquals(3,this.dao.getEntries().size());
	}

}
