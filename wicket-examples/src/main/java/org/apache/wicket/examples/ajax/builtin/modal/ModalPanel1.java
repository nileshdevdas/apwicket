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
package org.apache.wicket.examples.ajax.builtin.modal;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * @author Matej Knopp
 */
public class ModalPanel1 extends Panel
{
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public ModalPanel1(String id)
	{
		super(id);
		
		AutoCompleteSettings settings = new AutoCompleteSettings();
		settings.setShowListOnEmptyInput(true);
		
		add(new AutoCompleteTextField<String>("edit", new Model<>(), settings)
		{
			@Override
			protected Iterator<String> getChoices(String input)
			{
				return Arrays.asList("A", "B", "C").iterator();
			}
		});
	}
}
