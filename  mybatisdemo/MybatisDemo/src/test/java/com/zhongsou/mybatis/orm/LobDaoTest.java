package com.zhongsou.mybatis.orm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zhongsou.mybatis.BaseTest;
import com.zhongsou.mybatis.LobJdbcDao;
import com.zhongsou.mybatis.dao.LobDao;
import com.zhongsou.mybatis.dao.bean.Lob;
import com.zhongsou.mybatis.dao.bean.POFactory;

public class LobDaoTest extends BaseTest {
	private LobDao lobDao;
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	@Before
	public void init() throws Exception {
		super.init();
		lobDao = (LobDao)ctx.getBean("lobDao");
		dataSourceTransactionManager = (DataSourceTransactionManager)ctx.getBean("transactionManagerMysql01");
	}
	
	@Test
	public void findById() {
		Long id = 3L;
		try
		{
			Lob lob = lobDao.findById(id);
			String s = "";
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insert() {
		try {
			Lob lob = new Lob();
						
			File blobFile = new File(LobJdbcDao.class.getResource("1.gif").getFile());
			InputStream blobStream = new FileInputStream(blobFile);
			byte[] lob_blob = new byte[(int)blobFile.length()];
			blobStream.read(lob_blob, 0, (int)blobFile.length());
			lob.setLob_blob(lob_blob);
			
			File textFile = new File(LobJdbcDao.class.getResource("1.txt").getFile());
			InputStream textStream = new FileInputStream(textFile);
			InputStreamReader textReader = new InputStreamReader(textStream);
			BufferedReader bufferedReader = new BufferedReader(textReader);
			String lob_text = "";
			String line = "";
			while((line = bufferedReader.readLine()) != null) {
				lob_text += line + "\n";
			}
			lob.setLob_text(lob_text);
			
			lobDao.insert(lob);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void update() {
		try {
			Lob lob = new Lob();
			lob.setLob_id(3L);
						
			File blobFile = new File(LobJdbcDao.class.getResource("1.gif").getFile());
			InputStream blobStream = new FileInputStream(blobFile);
			byte[] lob_blob = new byte[(int)blobFile.length()];
			blobStream.read(lob_blob, 0, (int)blobFile.length());
			lob.setLob_blob(lob_blob);
			
			File textFile = new File(LobJdbcDao.class.getResource("1.txt").getFile());
			InputStream textStream = new FileInputStream(textFile);
			InputStreamReader textReader = new InputStreamReader(textStream);
			BufferedReader bufferedReader = new BufferedReader(textReader);
			String lob_text = "";
			String line = "";
			while((line = bufferedReader.readLine()) != null) {
				lob_text += line + "\n";
			}
			lob.setLob_text(lob_text);
			
			lobDao.update(lob);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void partialupdate() {
		try {
			Lob lob = POFactory.getPoInstance(Lob.class);
			lob.setLob_id(3L);
						
			File blobFile = new File(LobJdbcDao.class.getResource("1.gif").getFile());
			InputStream blobStream = new FileInputStream(blobFile);
			byte[] lob_blob = new byte[(int)blobFile.length()];
			blobStream.read(lob_blob, 0, (int)blobFile.length());
			lob.setLob_blob(lob_blob);
			
//			File textFile = new File(LobJdbcDao.class.getResource("1.txt").getFile());
//			InputStream textStream = new FileInputStream(textFile);
//			InputStreamReader textReader = new InputStreamReader(textStream);
//			BufferedReader bufferedReader = new BufferedReader(textReader);
//			String lob_text = "";
//			String line = "";
//			while((line = bufferedReader.readLine()) != null) {
//				lob_text += line + "\n";
//			}
//			lob.setLob_text(lob_text);
			
			lobDao.partialupdate(lob);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void delete() {
		try {
			lobDao.delete(2L);
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
