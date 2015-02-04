package by.epam.admoffice.controller.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

public abstract class AbstractFilter implements Filter {

	FilterConfig filterConfig;

	@Override
	public void destroy() {
		filterConfig = null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
