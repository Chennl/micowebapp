package com.micode.webapp.controller;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.micode.webapp.entity.User;
import com.micode.webapp.service.QuestionService;
import com.micode.webapp.service.UserService;



@Controller
public class QuestionController {
	@Autowired
	QuestionService questionService;
	@Autowired
	UserService userService;
	@Autowired 
	private DataSource dataSource;
	 
	
	@RequestMapping(value= {"/","/index","/index.jsp"},method=RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
	@RequestMapping(value= {"/login"},method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value= {"/login"},method=RequestMethod.POST)
	public String post_login(@RequestParam(defaultValue="",name="username") String userName,
			@RequestParam(required = false,defaultValue="") String password,
			HttpServletRequest request,
			Model model) {
		
		User user= userService.getUserByUserName(userName);
		if(user==null) {
			
			model.addAttribute("error", "用户名或密码错误");
		    //model.addAttribute("re",re)
			return "login";
		}
		if(user.getPassword().equals(password)) {
			HttpSession session=request.getSession();
			session.setAttribute("userName", userName);
			return "index";
		}
		else
			return "login";
	}
	
	@RequestMapping(value= {"/query"},method=RequestMethod.GET)
	public String query(){
		return "query";
	}
	@RequestMapping(value= {"/query"},method=RequestMethod.POST)
	public String executeSqlQuery(Model model, @ModelAttribute("mysqlstring")String sql,@ModelAttribute("pwd")String  pwd) {
		if("admin123456".equals(pwd)) {
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		 String[] sqls=sql.split(";");
		 for(int i=0;i<sqls.length;i++)
			 if(sqls[i].trim().length()>0)
				 jdbcTemplate.update(sqls[i].trim());
		}
		model.addAttribute("msg", "已经成功执行sql!");
		return "query";
	}
	@RequestMapping(value= {"/upload_data"},method=RequestMethod.POST)
	public String uploadData(HttpServletRequest request,HttpServletResponse response) {
		return "data";
	}
	
    public static String[][] toArray(String str) {
        String[] split = str.split("\r\n");
        String[][] array = new String[split.length][];
        for (int i = 0; i < split.length; i++) {
            array[i] = split[i].split("\t");
        }
        return array;
    }
	@RequestMapping(value= {"/upload_question"},method=RequestMethod.POST)
	public String postData(Model model,  @ModelAttribute("questionData") String  questionData,
	         @ModelAttribute("optionData") String  optionData) {
		
	 
//			
//			String[][] questionArray =toArray(questionData);
//			String[][] optionArray =toArray(optionData);
//			
//			
//			String[] rows= questionData.split("\r\n");
//			int questionnaireId=0;
//			List<Question> questions= new ArrayList<Question>();
//			for(int i=0;i<questionArray.length;i++) {
//				int id=Integer.parseInt(questionArray[i][0]);
//				String questionText = questionArray[i][1].trim();
//				questionnaireId=Integer.parseInt(questionArray[i][2]);
//				int order=Integer.parseInt(questionArray[i][3]);
//				String code=questionArray[i][4];
//
//				Question question= new Question();
//				question.setId(id);
//				question.setQuestionText(questionText);
//				question.setQuestionnaireId(questionnaireId);
//				question.setQuestionOrder(order);
//			 	question.setQuestionCode(code);
//			 	Set<Option> options= new HashSet<Option>();
//			 	
//				for(int j=0;j<optionArray.length;j++) {
//						int qid=Integer.parseInt(optionArray[j][0]);
//						if(id==qid) {
//							Option option = new Option();
//							option.setId(Integer.parseInt(optionArray[j][1]));
//							option.setOptionOrder(Integer.parseInt(optionArray[j][2]));
//							option.setOptionCode(optionArray[j][3]);
//							option.setOptionText(optionArray[j][4]);
//							option.setNextQuestionId(Integer.parseInt(optionArray[j][5]));
//							options.add(option);
//						}
//				}
////				if(options.size()>0)
////					question.setOptionSet(options);
//			 	questions.add(question);
// 
//			}
// 			questionService.deleteByQuestionnaireId(questionnaireId);
//			//questionService.addQuestion(questions.get(1));
//			questionService.addQuestions(questions);
		return "data";
	}
//	@RequestMapping(value="/data_upload",method=RequestMethod.POST)
//	void uploadData(HttpServletRequest request,HttpServletResponse response) throw Exception{
//		request.setCharacterEncoding("UTF-8");
//		MultipartHttpServlet multipartRequest = (MultipartHttpServletRequest) request;
//		MultipartFile multipartFile = null;
//		Map<K, V> map = multipartRequest.getFileMap();
//		 for(Iterator i = map.keySet().iterator(); i.hasNext();) {
//			 Object obj = i.next();
//			 multipartFile =(MultipartFile)map.get(obj);
//		 }
//		 String fileName= multipartFile.getOriginalFilename();
//		 InputStream inputStream;
//		try {
//			 inputStream = mulfipartFile.getInputStream();
//			 File tmpFile = File.createTempFile(filename, filename.substring(filename.lastIndexOf(".")));
//			 FileUtils.copyInputStreamToFile(inputStream, tmpFile);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
}
