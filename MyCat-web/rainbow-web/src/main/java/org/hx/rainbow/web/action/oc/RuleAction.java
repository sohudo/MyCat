package org.hx.rainbow.web.action.oc;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hx.rainbow.common.context.RainbowContext;
import org.hx.rainbow.common.core.service.SoaManager;
import org.hx.rainbow.common.web.session.RainbowSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
@RequestMapping("/exportAction")
public class RuleAction {

	private final static String services[] = {"tableruleService","functionService"};
	private final static String TEMPLATE = "/template/rule.ftl";
	private final static String QUERY = "query";
	private final static String XML = "attachment; filename=rule.xml";
	private final static String DOWNLOAD = "application/x-msdownload";
	private final static String FILENAME = "Content-disposition";
	private final static String UTF = "UTF-8";

	@RequestMapping("/rule")
	public void generateRule(HttpServletResponse response, HttpServletRequest request){

		RainbowSession.web2Service(request);

		Writer out = null;
		OutputStream toClient = null;

		try {

			RainbowContext context  = null;
			Map<String,Object> data = new HashMap<String,Object>();

			for(int i = 0; i < services.length; i++){
				
				context  = new RainbowContext(services[i],QUERY);
				SoaManager.getInstance().invoke(context);
				data.put(services[i], context.getRows());
			}
			
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> fns = (List<Map<String, Object>>) data.get("functionService");
			
			for(Map<String,Object> fn : fns){
				
				context  = new RainbowContext("functionParamService",QUERY);
				String functionName = (String) fn.get("name");
				context.addAttr("functionName",functionName);
				SoaManager.getInstance().invoke(context);
				fn.put("property", context.getRows());
			}

			response.setContentType(DOWNLOAD);
			response.setHeader(FILENAME, XML);

			Class<?> clz = this.getClass();

			String packageName = clz.getPackage().getName();
			String packagePath = packageName.replace('.', '/');

			ClassLoader classLoader = clz.getClassLoader();
			InputStream inputstate = classLoader.getResourceAsStream(packagePath + TEMPLATE);
			Template tempState = new Template("", new InputStreamReader(inputstate,UTF), new Configuration());
			toClient = new BufferedOutputStream(response.getOutputStream());
			out = new OutputStreamWriter(toClient,UTF);
			tempState.process(data, out);
			toClient.flush();
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();

		}finally{

			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(toClient != null){
				try {
					toClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
