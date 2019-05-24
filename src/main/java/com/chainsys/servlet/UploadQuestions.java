package com.chainsys.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.chainsys.customExceptions.TestifyExceptions;
import com.chainsys.model.Topics;
import com.chainsys.services.QuestionService;
import com.chainsys.services.TopicService;
import com.google.gson.Gson;

/**
 * Servlet implementation class UploadQuestions
 */

public class UploadQuestions extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int maxFileSize = 1024 * 1024 * 5;
	private long maxRequestSize = 1024 * 1024;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadQuestions() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TopicService topicService = new TopicService();
		try {
			ArrayList<Topics> topicsList = topicService.displayTopics();
			Gson gson = new Gson();
			String topics = gson.toJson(topicsList);
			response.getWriter().write(topics);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd=null;
		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxFileSize);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
			servletFileUpload.setFileSizeMax(maxFileSize);
			servletFileUpload.setSizeMax(maxRequestSize);
			String uploadPath = "C:\\Users\\robi2116\\Downloads\\";
			
			try {
				QuestionService questionService = new QuestionService();
				List<FileItem> formItems = servletFileUpload.parseRequest(request);
				if (formItems != null && formItems.size() > 0) {
					int topicId = 0;
					String filePath = "";
					String fileName = "";
					for (FileItem fileItem : formItems) {
						if (fileItem.isFormField()) {
							String fieldValue = fileItem.getString();
							topicId = Integer.parseInt(fieldValue);
						} else {
							fileName = new File(fileItem.getName()).getName();
							filePath = uploadPath + File.separator + fileName;
						}
					}
					Boolean status = questionService.importFromExcel(filePath, topicId, fileName);
					if (status) {
						request.setAttribute("message", "success");
					} else {
						request.setAttribute("message", "failed");
					}
				} else {
					request.setAttribute("message", "Invalid data");
				}
				rd=request.getRequestDispatcher("bulkquestions.jsp");
				rd.forward(request, response);
			} catch (TestifyExceptions | FileUploadException e) {
				request.setAttribute("message", "failed");
				rd = request.getRequestDispatcher("bulkquestions.jsp");
				rd.forward(request, response);
			}
		}
	}

}
