package com.mw.dao;

import java.sql.*;
import java.util.ArrayList;
import com.mw.model.Article;

public class ArticleDAO extends BaseDAO {
	public boolean addArticle(Article article) {// 添加文章
		String sql = "insert into Article" + "(title,type,tags,content,create_time,update_time)values(?,?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getType());
			pstmt.setString(3, article.getTags());
			pstmt.setString(4, article.getContent());
			pstmt.setString(5, article.getCreate_time());
			pstmt.setString(6, article.getUpdate_time());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	public boolean updateArticle(Article article) {// 修改文章
		String sql = "update Article set title ='" + article.getTitle() 
		+ "',type='" + article.getType()
		+ "',tags='" + article.getTags()
		+ "',content='" + article.getContent()
		+ "',update_time='" + article.getUpdate_time()
		+ "'where id='" + article.getId() + "'";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	public boolean deleteArticle(String aid) {// 删除文章
		String sql = "delete from Article where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, aid);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	public Article findByTitle(String title) { // 按标题查询文章
		String sql = "select * from Article where title=?";
		Article article = new Article();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, title);
			try (ResultSet rst = pstmt.executeQuery()) {
				if (rst.next()) {
					article.setId(rst.getInt("id"));
					article.setTitle(rst.getString("title"));
					article.setType(rst.getString("type"));
					article.setTags(rst.getString("tags"));
					article.setContent(rst.getString("content"));
					article.setCreate_time(rst.getString("create_time"));
					article.setUpdate_time(rst.getString("update_time"));
				} else
					return null;
			}
		} catch (SQLException se) {
			return null;
		}
		return article;
	}
	
	public Article findById(String id) { // 按ID查询文章
		String sql = "select * from Article where id=?";
		Article article = new Article();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try (ResultSet rst = pstmt.executeQuery()) {
				if (rst.next()) {
					article.setId(rst.getInt("id"));
					article.setTitle(rst.getString("title"));
					article.setType(rst.getString("type"));
					article.setTags(rst.getString("tags"));
					article.setContent(rst.getString("content"));
					article.setCreate_time(rst.getString("create_time"));
					article.setUpdate_time(rst.getString("update_time"));
				} else
					return null;
			}
		} catch (SQLException se) {
			return null;
		}
		return article;
	}

	public ArrayList<Article> findAllArticle() {// 查询所有文章
		ArrayList<Article> articleList = new ArrayList<Article>();
		String sql = "select * from Article";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rst = pstmt.executeQuery()) {
			while (rst.next()) {
				Article article = new Article();
				article.setId(rst.getInt("id"));
				article.setTitle(rst.getString("title"));
				article.setType(rst.getString("type"));
				article.setTags(rst.getString("tags"));
				article.setContent(rst.getString("content"));
				article.setCreate_time(rst.getString("create_time"));
				article.setUpdate_time(rst.getString("update_time"));
				articleList.add(article);
			}
			return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
