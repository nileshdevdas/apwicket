/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.markup.html.border;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;


/**
 * Test the component: PageView
 * 
 * @author Juergen Donnerstag
 */
public class TogglePanel extends Border
{
	private static final long serialVersionUID = 1L;

	private boolean expanded = true;

	/**
	 * Construct.
	 * 
	 * @param id
	 * @param titleModel
	 */
	public TogglePanel(String id, IModel<String> titleModel)
	{
		super(id, titleModel);

		Link<Void> link = new Link<Void>("title")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				expanded = !expanded;
				getBodyContainer().setVisible(expanded);
			}
		};
		link.add(new Label("titleLabel", titleModel));

		addToBorder(link);
	}
}