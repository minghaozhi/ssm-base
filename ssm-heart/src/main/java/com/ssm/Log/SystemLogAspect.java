package com.ssm.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ssm.pojo.Log;
import com.ssm.pojo.SysLog;
import com.ssm.pojo.SysUser;
import com.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 切面类
 * 

 *
 */
@Aspect
@Component
public class SystemLogAspect {

	// TODO 注入保存日志记录的service
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// 本地异常日志记录对象
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemLogAspect.class);

	@Autowired
	private SysLogService sysLogService;
	 //Service层切点  
    @Pointcut("@annotation(com.ssm.Log.SystemServiceLog)")
     public  void serviceAspect() {
    }

	//Controller层切点
    @Pointcut("@annotation(com.ssm.Log.SystemControllerLog)")
     public  void controllerAspect() {  
    }  

	/**
	 * 返回通知用于拦截Controller层记录用户成功的操作
	 */
	@AfterReturning("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		
		//获取request对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		HttpSession session = request.getSession();
		
		//读取session中的用户信息
		SysUser user = (SysUser) session.getAttribute("user");
		
		//获取请求的ip地址
		String ip = request.getRemoteAddr();
		
//		System.out.println("------前置通知开始了------");
//		System.out.println("请求的方法："+(joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()"));
//
//		if(user!=null){
//			System.out.println("请求人："+user.getName());
//		}
//
//		System.out.println("请求ip："+ip);
		
		try {
			//=========数据库日志===========
			SysLog log = new SysLog();
			log.setExecuteOperation(getControllerMethodDescription(joinPoint));
			log.setMacCode(ComputerInfo.getMacAddress());
			log.setComputerName(ComputerInfo.getComputerName());
			log.setEntityType(joinPoint.getTarget().getClass().getName());
			log.setRequestIp(ip);
			log.setCreateBy(user.getId());
			log.setUpdateBy(user.getId());
			Log entityLog=getControllerMethodLogContent(joinPoint);
			if(entityLog!=null && !entityLog.getDescription().equals("")){
				log.setEntityInfo(entityLog.getEntityInfo());
				log.setDescription(entityLog.getDescription());
				log.setEntityType(entityLog.getEntiyType());
				log.setEntityId(entityLog.getEntityId());
			}
			log.setIsActivited(0);
			log.setOperationTime(new Date());
			if (user!=null){
				log.setOperator(user.getRealName());
			}

			if(log.getDescription()!=null && !log.getDescription().equals("")){
				//保存数据库
				sysLogService.add(log);
			}

//			System.out.println("-----前置通知结束------");
		} catch (Exception e) {
			e.getMessage();
			//记录本地异常日志
//			LOGGER.error("=====前置通知异常======");
			LOGGER.error("异常信息：{}",e.getMessage());
		}
	}

	public void doAfterThrowing(JoinPoint joinPoint,Throwable e){
		
		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		 HttpSession session = request.getSession();
		 
		 //获取当前登录的用户
		 SysUser user = (SysUser) session.getAttribute("user");
		 
		 //获取请求的IP地址
		 String ip = request.getRemoteAddr();
		 
		 //获取用户请求方法的参数并序列化为json格式字符串
		 String param = "";
		 if(joinPoint.getArgs()!=null && joinPoint.getArgs().length>0){
			 for(int i=0; i<joinPoint.getArgs().length;i++){
					try {
						param += MAPPER.writeValueAsString(joinPoint.getArgs()[i])+",";
					} catch (JsonProcessingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			 }
		 }
		 
		 try {
			//-------输出到控制台--------
			 System.out.println("--------异常通知开始-----------");
			 System.out.println("异常代码："+ e.getClass().getName());
			 System.out.println("异常信息："+ e.getMessage());
			 System.out.println("异常方法："+ (joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()"));
			 System.out.println("方法描述："+ getServiceMethodDescription(joinPoint));
			 
//			 System.out.println("请求人："+ user.getName());
			 System.out.println("请求IP："+ ip);
			 System.out.println("请求参数："+ param);
			 
			 //----------数据库日志-----------
			 SysLog log = new SysLog();
			 log.setDescription(getServiceMethodDescription(joinPoint));
			 if(user!=null){
				 log.setCreateBy(user.getId());
			 }
			 log.setRequestIp(ip);
			 
			 // TODO 保存到数据库
			 
			 System.out.println("------异常通知结束--------");
		} catch (Exception e1) {
			//记录本地日志
			LOGGER.error("----异常通知异常------");
			LOGGER.error("异常信息：{}"+ e.getMessage());
		}
		 
		 //记录本地异常日志
		 LOGGER.error("异常方法:{}异常代码:{}异常信息:{}参数:{}",joinPoint.getTarget().getClass().getName()
				 +joinPoint.getSignature().getName(),e.getClass().getName(),e.getMessage(),param);
	}
	
	/**
	 * 获取注解中对方法的描述信息，用于service层的注解
	 * @return
	 * @throws Exception 
	 */
	public static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception{
		
		//获取类全限定名
		String targetName = joinPoint.getTarget().getClass().getName();
		
		//获取方法名称
		String methodName = joinPoint.getSignature().getName();
		
		//获取参数
		Object[] arguments = joinPoint.getArgs();
		
		Class targetClass = Class.forName(targetName);
		
		//获取访问类的所有方法
		Method[] methods = targetClass.getMethods(); 
		
		String description = "";
		
		for (Method method : methods) {
			if(method.getName().equals(methodName)){
				Class[] clazzs = method.getParameterTypes();
				if(clazzs.length == arguments.length){
					description = method.getAnnotation(SystemServiceLog.class).description();
					break;
				}
			}
			
		}
		
		return description;
	}
	
	/**
	 * 获取注解中对方法的描述信息，用于Controller层注解
	 * @return
	 * @throws Exception 
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception{
		
		//获取类名
		String targetName = joinPoint.getTarget().getClass().getName();
		
		//获取方法名
		String methodName = joinPoint.getSignature().getName();
		
		//获取参数
		Object[] arguments = joinPoint.getArgs();
		
		//反射获取类的字节码文件
		Class targetClass = Class.forName(targetName);
		
		//获取拦截类中的所有方法
		Method[] methods = targetClass.getMethods();
		
		String description = "";
		
		for (Method method : methods) {
			if(method.getName().equals(methodName)){
				//获取参数类型
				Class<?>[] clazzs = method.getParameterTypes();
				
				if(clazzs.length == arguments.length){
					description = method.getAnnotation(SystemControllerLog.class).description();
					break;
				}
			}
		}
		
		return description;
	}


	/**
	 * 获取注解中对方法日志返回信息
	 * @return
	 * @throws Exception
	 */				 
	public static Log getControllerMethodLogContent(JoinPoint joinPoint) throws Exception{

		//获取类名
		String targetName = joinPoint.getTarget().getClass().getName();

		//获取方法名
		String methodName = joinPoint.getSignature().getName();

		//获取参数
		Object[] arguments = joinPoint.getArgs();

		//反射获取类的字节码文件
		Class targetClass = Class.forName(targetName);

		//获取拦截类中的所有方法
		Method[] methods = targetClass.getMethods();

		Log log = null;

		for (Method method : methods)
			if (method.getName().equals(methodName)) {
				//获取参数类型
				Class<?>[] clazzs = method.getParameterTypes();

				if (clazzs.length == arguments.length) {

					log= (Log) arguments[clazzs.length - 1];
					break;
				}
			}

		return log;
	}
	
}
