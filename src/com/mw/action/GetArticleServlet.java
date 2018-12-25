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
@WebServlet(urlPatterns = {"/getArticle","/add","/del"})
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");	
		String uri = request.getRequestURI();
		if (uri.endsWith("/getArticle")) {
			ArrayList<Article> alist = adao.findAllArticle();
			String json = JSON.toJSONString(alist);
//			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			try {
				PrintWriter out = response.getWriter();
			    out.print(json);

			} catch (IOException e) {
			    e.printStackTrace();
			}
		}else if(uri.endsWith("/add")){
			
		}
		String title=request.getParameter("title");
		String tags=request.getParameter("tags");
		String content=request.getParameter("content");
		System.out.println("title:"+title);
		System.out.println("tags:"+tags);
		System.out.println("content:"+content);
//		List<Article> list = new ArrayList<Article>() ;
//		list.add(new Article(1,title));
//		list.add(new Article(2,title));
//		String json = JSON.toJSONString(list);
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json; charset=utf-8");
//		PrintWriter out = null;
//		try {
//		    out = response.getWriter();
//		    out.print(json);
//
//		} catch (IOException e) {
//		    e.printStackTrace();
//		} finally {
//		    if (out != null) {
//		        out.close();
//		    }
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");//*‘ –Ì»Œ∫Œ”Ú
		String title=new String(request.getParameter("title").getBytes("ISO-8859-1"),"UTF-8");
		String tags[]=request.getParameterValues("tags");
		String content=request.getParameter("content");
		System.out.println("title:"+title);
//		System.out.println("tags:"+new String(tags[1].getBytes("ISO-8859-1"),"UTF-8"));
		System.out.println("content:"+content);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
	}

}
