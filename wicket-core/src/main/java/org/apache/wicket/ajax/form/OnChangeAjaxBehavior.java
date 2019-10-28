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
package org.apache.wicket.ajax.form;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.lang.Args;
import org.danekja.java.util.function.serializable.SerializableConsumer;

/**
 * A behavior that updates the hosting {@link FormComponent} via Ajax when value of the component is
 * changed.
 * <p>
 * This behavior uses best available method to track changes on different types of form components.
 * For text input form components it uses event 'input', for other form component types the 'change'
 * event is used.
 * </p>
 * 
 * @author Janne Hietam&auml;ki (janne)
 * 
 * @since 1.3
 * @see org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior#onUpdate(org.apache.wicket.ajax.AjaxRequestTarget)
 * @see org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior#onError(org.apache.wicket.ajax.AjaxRequestTarget, RuntimeException)
 */
public abstract class OnChangeAjaxBehavior extends AjaxFormComponentUpdatingBehavior
{
	private static final long serialVersionUID = 1L;

	/**
	 * 'input' and 'change' used as a fallback for all other form component types.
	 */
	public static final String EVENT_NAME = "input change";

	/**
	 * the change event
	 */
	public static final String EVENT_CHANGE = "change";

	/**
	 * Constructor.
	 */
	public OnChangeAjaxBehavior()
	{
		super(EVENT_NAME);
	}

	@Override
	protected void updateAjaxAttributes(AjaxRequestAttributes attributes) 
	{
		super.updateAjaxAttributes(attributes);

		Component component = getComponent();

		// textfiels and textareas will trigger this behavior with either 'input' or 'change' events
		// all the other components will use just 'change'
		if (!(component instanceof TextField || component instanceof TextArea))
		{
			attributes.setEventNames(EVENT_CHANGE);
		}
	}

	/**
	 * Creates an {@link OnChangeAjaxBehavior} based on lambda expressions
	 * 
	 * @param onChange
	 *            the {@code SerializableConsumer} which accepts the {@link AjaxRequestTarget}
	 * @return the {@link OnChangeAjaxBehavior}
	 */
	public static OnChangeAjaxBehavior onChange(SerializableConsumer<AjaxRequestTarget> onChange)
	{
		Args.notNull(onChange, "onChange");

		return new OnChangeAjaxBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				onChange.accept(target);
			}
		};
	}
}
