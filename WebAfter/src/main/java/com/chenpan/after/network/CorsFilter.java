package com.chenpan.after.network;

import com.chenpan.after.utils.ListUtil;
import com.chenpan.after.utils.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class CorsFilter implements Filter {

	/** Log4j日志处理 */
   // private static final Logger log = Logger.getLogger(UserController.class);
	
	private String allowOrigin;
	private String allowMethods;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;

	public void init(FilterConfig filterConfig) throws ServletException {
		allowOrigin = filterConfig.getInitParameter("allowOrigin");
		allowMethods = filterConfig.getInitParameter("allowMethods");
		allowCredentials = filterConfig.getInitParameter("allowCredentials");
		allowHeaders = filterConfig.getInitParameter("allowHeaders");
		exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
	}

	/**
	 * 通过CORS技术实现AJAX跨域访问,只要将CORS响应头写入response对象中即可
 	 * @param req
	 * @param res
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
						 FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String currentOrigin = request.getHeader("Origin");

        // 禁止浏览器缓存
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");     // HTTP 1.1.
			response.setHeader("Pragma", "no-cache");  // HTTP 1.0.
			response.setHeader("Expires", "0");         // Proxies.

		} catch (Exception e) {}

		if (StringUtil.isNotEmpty(allowOrigin)) {

			List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));

			if (ListUtil.isNotEmpty(allowOriginList)) {

				if (allowOriginList.contains(currentOrigin)) {
					response.setHeader("Access-Control-Allow-Origin","*");
				}
			}
		}
		if (StringUtil.isNotEmpty(allowMethods)) {
		response.setHeader("Access-Control-Allow-Methods", allowMethods);
	}
		if (StringUtil.isNotEmpty(allowCredentials)) {
		response.setHeader("Access-Control-Allow-Credentials",allowCredentials);
	}
		if (StringUtil.isNotEmpty(allowHeaders)) {
		response.setHeader("Access-Control-Allow-Headers", allowHeaders);
	}
		if (StringUtil.isNotEmpty(exposeHeaders)) {
		response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
	}
		chain.doFilter(req, res);
	}

	public void destroy() {
	}
}