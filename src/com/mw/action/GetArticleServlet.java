package com.mw.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.mw.dao.ArticleDAO;
import com.mw.model.Article;
import com.mw.model.Article;

/**
 * Servlet implementation class getArticleServlet
 */
@WebServlet(urlPatterns = { "/getArticle","/showArticle","/getOneById", "/addArticle", "/deleteArticle", "/updateArticle" })
public class GetArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleDAO adao = new ArticleDAO();
	Article article = new Article();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetArticleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String uri = request.getRequestURI();
		if (uri.endsWith("/getArticle")) {
			ArrayList<Article> alist = adao.findAllArticle();
			String json = JSON.toJSONString(alist);
			try {
				PrintWriter out = response.getWriter();
				out.print(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (uri.endsWith("/showArticle")) {
			try {
				System.out.println(request.getParameter("title"));
				// String title = new String(request.getParameter("title").getBytes("ISO-8859-1"),"UTF-8");
				String title = request.getParameter("title");
				article = adao.findByTitle(title);
			} catch (Exception e) {
				response.setStatus(202, "");
			}
			String json = JSON.toJSONString(article);
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print(json);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else if (uri.endsWith("/getOneById")) {
			try {
		
				String id = request.getParameter("id");
				article = adao.findById(id);
			} catch (Exception e) {
				response.setStatus(202, "");
			}
			String json = JSON.toJSONString(article);
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print(json);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else if (uri.endsWith("/deleteArticle")) {
			String id = "", msg = "";
			try {
				// System.out.println(request.getParameter("id"));
				// String title = new
				// String(request.getParameter("title").getBytes("ISO-8859-1"),
				// "UTF-8");
				id = request.getParameter("id");

			} catch (Exception e) {
				response.setStatus(202, "");
			}
			Boolean result = adao.deleteArticle(id);
			if (result) {
				System.out.println("truetrue");
				msg = "删除成功！";
			} else {
				System.out.println("falsefalse");
				response.setStatus(202, "");
				msg = "删除失败！";
			}
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print("{\"msg\":\"" + msg + "\"}");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");// *允许任何域
		response.setContentType("application/json; charset=utf-8");
		String uri = request.getRequestURI();
		String msg = "";// 返回的消息
		if (uri.endsWith("/addArticle")) {
			Article article = new Article();
			try {
				String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
				String type = new String(request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8");
				String tags = new String(request.getParameter("tags").getBytes("ISO-8859-1"), "UTF-8");
				String content = new String(request.getParameter("content").getBytes("ISO-8859-1"), "UTF-8");
				String create_time = request.getParameter("create_time");
				article.setTitle(title);
				article.setType(type);
				article.setTags(tags);
				article.setContent(content);
				article.setCreate_time(create_time);
				article.setUpdate_time("");
			} catch (Exception e) {
				msg = "传参有误！";
				response.setStatus(202, "");
			}
			ArticleDAO adao = new ArticleDAO();
			Boolean result = adao.addArticle(article);

			if (result) {
				msg = "保存成功！";
			} else {
				response.setStatus(202, "");
				msg = msg + "保存失败！";
			}
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print("{\"msg\":\"" + msg + "\"}");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}else if(uri.endsWith("/updateArticle")){
			Article article = new Article();
			try {
				String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
				String type = new String(request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8");
				String tags = new String(request.getParameter("tags").getBytes("ISO-8859-1"), "UTF-8");
				String content = new String(request.getParameter("content").getBytes("ISO-8859-1"), "UTF-8");
//				String create_time = request.getParameter("create_time");
				String update_time = request.getParameter("update_time");
				int id = Integer.parseInt(request.getParameter("id"));
				article.setId(id);
				article.setTitle(title);
				article.setType(type);
				article.setTags(tags);
				article.setContent(content);
//				article.setCreate_time(create_time);
				article.setUpdate_time(update_time);
			} catch (Exception e) {
				msg = "传参有误！";
				response.setStatus(202, "");
			}
			ArticleDAO adao = new ArticleDAO();
			Boolean result = adao.updateArticle(article);

			if (result) {
				msg = "更新成功！";
			} else {
				response.setStatus(202, "");
				msg = msg + "更新失败！";
			}
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print("{\"msg\":\"" + msg + "\"}");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}
	}
}
