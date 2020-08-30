/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.tgxml.tjtf.processors.taghandlers.impl;
import java.io.IOException;
import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.processors.taghandlers.ParserHandlerSupport;
import com.sun.tgxml.tjtf.processors.taghandlers.EmitterHandlerSupport;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;

import com.sun.tgxml.tjtf.api.code.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * CodeSource_TH - The tag-handler for a CodeSource tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    CodeSource_TH 
 * ============================================================================================ 
 */ 
public class CodeSource_TH extends TextStreamTagHandler  {


   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 




   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 


    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

   /** 
    *   CodeSource_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public CodeSource_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_codesource;
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag(String text) throws SAXException {
	super.endTag(text);
	Stack testItemStack = m_ParserHandler.getStack();
	Object testitem = testItemStack.peek();
	
	if (testitem == null)
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));
	
	if (! (testitem instanceof InlineSupportClass))
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext", getTagName(), 
							     TagsImpl.ctStr_tag_inlinesupportclass));
	
	InlineSupportClass isc = (InlineSupportClass) testitem;
	// validated the source
	//   If the tag is parsed in, it can not have null text.
	if (text == null)
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstring", getTagName()));
	
	StringTokenizer tknzr = new StringTokenizer(text, " \t\n\r");
	if (! tknzr.hasMoreTokens())
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstring", getTagName()));
	
	isc.setSource(text);
    }
     
 
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         
         

  /**
    *   emit the Ref text.
    *  <p>
    * @see #endTag
    */
    public void emitTextFlow(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof String))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"String", tdObject.getClass().getName()));

	String source = (String) tdObject;
	// validated the source
	//   If the tag is parsed in, it can not have null text.
	if (source == null)
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.text.null", getTagName()));
	
	StringTokenizer tknzr = new StringTokenizer(source, " \t\n\r");
	if (! tknzr.hasMoreTokens())
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.text.null", getTagName()));

	m_EmitterHandler.emitText(source);
    }

}
