/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.demo.segments.context.contributor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.segments.context.Context;
import com.liferay.segments.context.contributor.RequestContextContributor;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * @author Jan
 */
@Component(
	immediate = true,
	property = {
		"request.context.contributor.key=" + SeasonRequestContextContributor.KEY,
		"request.context.contributor.type=String"
	},
	service = RequestContextContributor.class
)
public class SeasonRequestContextContributor
	implements RequestContextContributor {

	public static final String KEY = "season";

	private static final String northern_seasons[] = {
			"winter", "winter",
			"spring", "spring", "spring",
			"summer", "summer", "summer",
			"autumn", "autumn", "autumn",
			"winter"
	};

	private static final String southern_seasons[] = {
			"summer", "summer",
			"autumn", "autumn", "autumn",
			"winter", "winter", "winter",
			"spring", "spring", "spring",
			"summer"
	};

	@Override
	public void contribute(
			Context context, HttpServletRequest httpServletRequest) {

		HttpSession session = httpServletRequest.getSession();

		if (context.get(KEY) == null) {

			try {
				//TODO check visitors location nothern vs southern hemisphere
				Calendar now = Calendar.getInstance();

				_log.debug(String.format("Using season %s", northern_seasons[now.get(Calendar.MONTH)]));
				session.setAttribute(KEY, northern_seasons[now.get(Calendar.MONTH)]);
				context.put(KEY, northern_seasons[now.get(Calendar.MONTH)]);
			} catch (Exception e) {
				_log.debug(e.getMessage());
				_log.debug("Global exception: " + e.toString());
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SeasonRequestContextContributor.class);
}