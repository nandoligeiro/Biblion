package br.biblion.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import br.biblion.R;
import br.biblion.model.Texto;

public class DbHelper extends SQLiteOpenHelper {
	
	private static final String TAG = DbHelper.class.getSimpleName();
	//private static String DB_PATH = "/data/data/br.com.biblion/databases/";
	private static String DB_PATH ="/Biblion/databases/";
	public static final String DB_NAME = "biblion.db";
	public static final int DB_VERSION = 1;
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	
	
	


	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.myContext = context;
		
		
	}

		
	public void createDataBase() throws IOException {

		/*File f = new File(Environment.getExternalStorageDirectory() + DB_PATH );
		f.mkdir();
		*/// dbDir.mkdirs();

		boolean dbExist = checkDataBase();
	
		
		if (dbExist) {

		} else {
			this.getReadableDatabase();
			

			try {

				//copyDataBase();

				descompactarBanco();
			} catch (Exception e) {
				throw new Error("Erro copying database");
			}
		}
	}

	void copyDataBase() throws IOException {

		
		File f = new File(Environment.getExternalStorageDirectory() + DB_PATH );
		f.mkdirs();
		
		/*
		 * File dbDir = myContext.getDir("Data",0); File dbFile = new
		 * File(DB_PATH,"biblion.db");
		 */
		// Abrir local do arquivo de entrada
		//InputStream myInput = myContext.getAssets().open(DB_NAME);
		InputStream myInput = myContext.getResources().openRawResource(
				R.raw.biblion);
		// BufferedInputStream bis = new BufferedInputStream(myInput);
		

		// Caminho do diretorio para criar o db vazio
		String outFileName = f + f.separator + DB_NAME;
		//FileOutputStream outFileName = DB_PATH + DB_NAME;

		// Abrir o banco vazio
		OutputStream myOutput = new FileOutputStream(outFileName);

		// Transferir bytes do input para o output
		byte[] buffer = new byte[1000];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	
	public boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			// checkDB = SQLiteDatabase.openDatabase(myPath, null,
			// SQLiteDatabase.OPEN_READONLY);
			checkDB = SQLiteDatabase.openDatabase(Environment.getExternalStorageDirectory() + myPath, null,
					SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		// myDataBase = SQLiteDatabase.openDatabase(myPath, null,
		// SQLiteDatabase.OPEN_READONLY);
		myDataBase = SQLiteDatabase.openDatabase(Environment.getExternalStorageDirectory()+ myPath, null,
			SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		// NO_LOCALIZED_COLLATORS
	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	
	  void descompactarBanco() {
	  
	  try { InputStream fis = myContext.getResources().openRawResource(R.raw.biblion);
	  ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis)); 
	  ZipEntry entry;
	  
	  // Read each entry from the ZipInputStream until no more entry found 
	  //indicated by a null return value of the getNextEntry() method. 
	  while ((entry = zis.getNextEntry()) != null) { int size; byte[] buffer = new
	  byte[2048];
	  
	  //android way to create file: //
	 // FileOutputStream fos = myContext.openFileOutput(entry.getName(), Context.MODE_PRIVATE);
	  
	  //my way to create it in my dir: //1st - create dirs if doesn't exists
	  File dbDir = new File(Environment.getExternalStorageDirectory() + DB_PATH); 
	  dbDir.mkdirs(); //2nd open it
	  String Output = dbDir + dbDir.separator + DB_NAME;
	  
	  FileOutputStream fos = new FileOutputStream(Output);
	  
	  BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);
	  
	  while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
	  bos.write(buffer, 0, size); } bos.flush(); bos.close(); }
	  
	  zis.close(); fis.close(); } catch (IOException e) { e.printStackTrace();
	  } }
	 

	public ArrayList buscarLivros() {

		Cursor c = myDataBase.rawQuery("select curto,ref from livros", null);

		ArrayList<String> list = new ArrayList<String>();
		while (c.moveToNext()) {

			list.add(c.getString(0));

		}
		return list;

	}

	public ArrayList buscarCap(Object pos) {

		Cursor c = this.myDataBase
				.rawQuery("select distinct capitulo from texto where livro = "
						+ pos + "", null);

		ArrayList<String> list = new ArrayList<String>();
		while (c.moveToNext()) {

			list.add(c.getString(0));

		}

		return list;
	}

	public ArrayList buscarVer(Object pos, Object object2) {

		Cursor c = this.myDataBase.rawQuery(
				"select distinct versiculo from texto where livro = " + pos
						+ " and capitulo = " + object2 + " ", null);

		ArrayList<String> list = new ArrayList<String>();
		while (c.moveToNext()) {

			list.add(c.getString(0));

		}

		return list;

	}

	public ArrayList buscarConsultaAll(Object object, Object object2,
			Object object3) {

		Cursor c = this.myDataBase
				.rawQuery(
						"select t.versiculo, t.texto from texto as t join livros as l on (t.livro = l.ref) where livro = "
								+ object
								+ " and capitulo = "
								+ object2
								+ " and versiculo = " + object3 + "", null);

		ArrayList<String> list = new ArrayList<String>();
		while (c.moveToNext()) {

			Texto texto = new Texto();
			texto.setVersiculo(c.getInt(c.getColumnIndex("versiculo")));
			texto.setTexto(c.getString(c.getColumnIndex("texto")));

			list.add(c.getInt(c.getColumnIndex("versiculo")) + " - "
					+ c.getString(c.getColumnIndex("texto")));

		}

		return list;

	}

	public ArrayList buscarLivroCap(Object livroId, Object i) {

		Cursor c = this.myDataBase
				.rawQuery(
						"select t.versiculo, t.texto from texto as t join livros as l on (t.livro = l.ref) where livro = "
								+ livroId + " and capitulo = " + i + "", null);

		ArrayList<String> list = new ArrayList<String>();
		while (c.moveToNext()) {

			Texto texto = new Texto();
			// texto.setTexto(c.getString(c.getColumnIndex("curto")));
			texto.setVersiculo(c.getInt(c.getColumnIndex("versiculo")));
			texto.setTexto(c.getString(c.getColumnIndex("texto")));

			list.add(c.getInt(c.getColumnIndex("versiculo")) + " - "
					+ c.getString(c.getColumnIndex("texto")));

		}

		return list;

	}

	public ArrayList buscarPorLivro(Object object) {

		Cursor c = this.myDataBase
				.rawQuery(
						"select t.versiculo, t.texto from texto as t join livros as l on (t.livro = l.ref) where livro = "
								+ object + "", null);

		ArrayList<String> list = new ArrayList<String>();
		while (c.moveToNext()) {

			Texto texto = new Texto();
			texto.setVersiculo(c.getInt(c.getColumnIndex("versiculo")));
			texto.setTexto(c.getString(c.getColumnIndex("texto")));

			list.add(c.getInt(c.getColumnIndex("versiculo")) + " - "
					+ c.getString(c.getColumnIndex("texto")));

		}

		return list;

	}

	// Contar qnt de Cap do livro
	public int CountVer(Object livro) {

		Cursor c = this.myDataBase.rawQuery(
				"select MAX(capitulo) from texto WHERE livro = " + livro + "",
				null);
		int num = 0;
		while (c.moveToNext()) {
			
		 num = c.getInt(0);
		
			
		}
		return num;
	}
	
	

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
