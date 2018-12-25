package com.mw.dao;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

public class BaseDAO {
	DataSource dataSource;

	// �ڹ��췽���з�������Դ����
	public BaseDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/sampleDS");
		} catch (NamingException ne) {
			System.out.println("Exception:" + ne);
		}
	}
	// ����һ�����Ӷ���
	public Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
}
