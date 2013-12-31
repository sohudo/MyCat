package org.hx.rainbow.server.sys.power.service;

import java.util.Date;
import org.hx.rainbow.common.context.RainbowContext;
import org.hx.rainbow.common.util.ObjectId;
import org.hx.rainbow.common.web.session.RainbowSession;
import org.hx.rainbow.common.core.service.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class MybatisService extends BaseService {
	private static final String NAMESPACE = "SYSMYBATIS";
	private static final String QUERYNAMESPACE = "queryNamespace";
	private static final String QUERYNAMESPACECOUNT = "queryNamespaceCount";

	public RainbowContext query(RainbowContext context) {
		super.query(context, NAMESPACE);
		return context;
	}
	
	public RainbowContext queryByPage(RainbowContext context) {
		super.queryByPage(context, NAMESPACE);
		return context;
	}
	public RainbowContext queryStatement(RainbowContext context) {
		String nsCode = (String) context.getAttr("nsCode");
		if(nsCode == null || nsCode.trim().isEmpty()){
			context.setMsg("NAMESPACE不能为空");
			return context;
		}
		super.queryByPage(context, NAMESPACE);
		return context;
	}
	public RainbowContext queryStatementByPage(RainbowContext context) {
		super.queryByPage(context, NAMESPACE);
		return context;
	}
	public RainbowContext queryNamespace(RainbowContext context) {
		super.queryByPage(context, NAMESPACE,QUERYNAMESPACE,QUERYNAMESPACECOUNT);
		return context;
	}
	
	public RainbowContext insert(RainbowContext context) {
		context.addAttr("guid", new ObjectId().toString());
		context.addAttr("createTime", new Date());
		context.addAttr("createUser", RainbowSession.getUserName());
		super.insert(context, NAMESPACE);
		context.getAttr().clear();
		return context;
	}
	public RainbowContext update(RainbowContext context) {
		super.update(context, NAMESPACE);
		context.getAttr().clear();
		return context;
	}

	
	public RainbowContext delete(RainbowContext context) {
		super.delete(context, NAMESPACE);
		context.getAttr().clear();
		return context;
	}
}
