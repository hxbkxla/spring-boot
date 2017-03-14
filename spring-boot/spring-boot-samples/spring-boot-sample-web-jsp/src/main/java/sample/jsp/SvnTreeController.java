package sample.jsp;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hxb.SVNTEST.IECSJsonUtil;
import org.hxb.SVNTEST.SVNUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmatesoft.svn.core.SVNException;

@Controller
public class SvnTreeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public SvnTreeController() {
    }

    @RequestMapping("/SvnTreeServlet")
    @ResponseBody
	protected String  getSvnTree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   Object svns = null;
    	   String result="";
	       request.setCharacterEncoding("UTF-8");
	       response.setCharacterEncoding("UTF-8");
	       String path = request.getParameter("pid");
//	       String url = "http://128.232.6.234:81/svn/repository/ESERVICE";
//	       String usrName = "sinosoft_hxb";
//	       String password = "Ab123456";
	       if (path == null) {
	           path = "";
	       }
	       try {
//	           SVNUtil svnUtil = new SVNUtil(url, usrName, password);
	           if (SVNAuth.Auth.isLogin()) {
	              svns = SVNUtil.listEntries(SVNAuth.Auth.getRepository(),path);
	              result = IECSJsonUtil.coventJson(svns,new String[]{});
	              
	           } else {
	              System.out.println("验证失败");
	           }
	       } catch (Exception ex) {
	           ex.printStackTrace();
	       }
	       return result;
	}

    @RequestMapping("/SvnHistory")
    @ResponseBody
	public String getHistory(HttpServletRequest request, HttpServletResponse response,String path) throws Exception{

		Object svns = null;
		if (path == null) {
			path = "";
		}
		String json = "";
		if (SVNAuth.Auth.isLogin()) {
			try {
				svns = SVNUtil.getHistory(SVNAuth.Auth.getRepository(),path);
				request.setAttribute("SVNS", svns);
			} catch (SVNException e) {
				e.printStackTrace();
			}

		}
		return IECSJsonUtil.coventJson(svns,new String[]{});
	}
    
    
    @RequestMapping("/jumpToCheck")
	protected String  jumpToCheck(HttpServletRequest request, HttpServletResponse response,String path) {
    	request.setAttribute("path", path);
    	return "check";
    }
    
    
    @RequestMapping("/download")
    @ResponseBody
	public void getHistory(HttpServletRequest request, HttpServletResponse response,String path,String revision) throws Exception{
    	OutputStream os=response.getOutputStream();
		if (StringUtils.isNotBlank(path)) {
			if (SVNAuth.Auth.isLogin()) {
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition", "attachment;filename=" + path.substring(path.lastIndexOf("/")+1));
				SVNUtil.download(os, path, Long.parseLong(revision), SVNAuth.Auth.getRepository());
			}
		}else{
			System.out.println("path为空！");
		}
		
	}
}
